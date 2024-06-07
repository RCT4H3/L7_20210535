package org.example.l7_20210535.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.l7_20210535.Beans.EmployeesB;
import org.example.l7_20210535.Beans.JobsB;
import org.example.l7_20210535.Daos.DaoEmployee;
import org.example.l7_20210535.Daos.DaoJobs;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/home", "/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action = request.getParameter("action")== null ? "lista" : request.getParameter("action");
        DaoEmployee daoEmployee = new DaoEmployee();
        DaoJobs daoJobs = new DaoJobs();
        switch (action){
            case "lista":
                ArrayList<EmployeesB> list = daoEmployee.listarEmployees();
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/listaEmployees.jsp");
                rd.forward(request,response);
                break;
            case "new":
                ArrayList<JobsB> list0 = daoJobs.listarJobs();
                ArrayList<EmployeesB> list1 = daoEmployee.listarEmployees();
                request.setAttribute("lista",list0);
                request.setAttribute("lista1",list1);
                request.getRequestDispatcher("WEB-INF/crearEmployee.jsp").forward(request,response);
                break;

            case "edit":
                String id = request.getParameter("id");
                EmployeesB employeesB = daoEmployee.buscarEmployee(Integer.parseInt(id));
                ArrayList<JobsB> list2 = daoJobs.listarJobs();
                ArrayList<EmployeesB> list3 = daoEmployee.listarEmployees();
                request.setAttribute("lista2",list2);
                request.setAttribute("lista3",list3);
                request.setAttribute("employee",employeesB);
                if(employeesB != null){
                    request.setAttribute("employee",employeesB);
                    request.getRequestDispatcher("WEB-INF/editarEmployee.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                }
                break;
            case "del":
                String idd = request.getParameter("id");
                EmployeesB employeesB1 = daoEmployee.buscarEmployee(Integer.parseInt(idd));

                if(employeesB1 != null){
                    try {
                        daoEmployee.borrar(idd);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
            case "ver":
                String id2 = request.getParameter("id");
                EmployeesB employeesB2 = daoEmployee.buscarEmployee(Integer.parseInt(id2));
                request.setAttribute("employee",employeesB2);
                if(employeesB2 != null){
                    request.setAttribute("employee",employeesB2);
                    request.getRequestDispatcher("WEB-INF/verEmployee.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        EmployeesB employeesB = new EmployeesB();
        DaoEmployee daoEmployee = new DaoEmployee();
        DaoJobs daoJobs =  new DaoJobs();
        ArrayList<JobsB> listJobs = daoJobs.listarJobs();
        ArrayList<EmployeesB> listEmployees = daoEmployee.listarEmployees();
        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");
        switch (action){
            case "crear":

                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String phoneNumber = request.getParameter("phoneNumber");
                String jobId = request.getParameter("jobId");
                String salary = request.getParameter("salary");
                String idManager = request.getParameter("idManager");
                String hire_date = request.getParameter("hire_date");
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String hireDateF = null;

                try {
                    Date hireDate = originalFormat.parse(hire_date);
                    hireDateF = desiredFormat.format(hireDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int max_salary = 0;
                int min_salary = 0;
                String jobTitle = null;
                int idDepartment = 0;
                for (EmployeesB employee : listEmployees) {
                    if (employee.getEmployee_id() == Integer.parseInt(idManager)) {
                        idDepartment = employee.getId_department();
                    }
                }
                for(JobsB job : listJobs){
                   if (Objects.equals(job.getJob_id(), jobId)){
                       max_salary = job.getMax_salary();
                       min_salary = job.getMin_salary();
                       jobTitle = job.getJob_title();
                   }
                }

                boolean isAllValid = true;

                if(jobTitle.length() > 35){
                    isAllValid = false;
                }

                if(jobId.length() > 10){
                    isAllValid = false;
                }

                if (Double.parseDouble(salary) > max_salary || Double.parseDouble(salary) < min_salary){
                    isAllValid = false;
                }
                if(isAllValid){
                    daoEmployee.crearEmployee(firstName, lastName,email, phoneNumber,jobId,Double.parseDouble(salary), Integer.parseInt(idManager), hireDateF, idDepartment);
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");

                }else{
                    request.getRequestDispatcher("WEB-INF/crearEmployee.jsp").forward(request,response);
                }
                break;
            case "edit":

                String firstName2 = request.getParameter("firstName");
                String lastName2 = request.getParameter("lastName");
                String email2 = request.getParameter("email");
                String phoneNumber2 = request.getParameter("phoneNumber");
                String jobId2 = request.getParameter("jobId");
                String salary2 = request.getParameter("salary");
                String idManager2 = request.getParameter("idManager");
                String hire_date2 = request.getParameter("hire_date");
                SimpleDateFormat originalFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                SimpleDateFormat desiredFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String hireDateF2 = null;

                try {
                    Date hireDate = originalFormat2.parse(hire_date2);
                    hireDateF2 = desiredFormat2.format(hireDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int max_salary2 = 0;
                int min_salary2 = 0;
                String jobTitle2 = null;
                int idDepartment2 = 0;
                for (EmployeesB employee : listEmployees) {
                    if (employee.getEmployee_id() == Integer.parseInt(idManager2)) {
                        idDepartment2 = employee.getId_department();
                    }
                }
                for(JobsB job : listJobs){
                    if (Objects.equals(job.getJob_id(), jobId2)){
                        max_salary2 = job.getMax_salary();
                        min_salary2 = job.getMin_salary();
                        jobTitle2 = job.getJob_title();
                    }
                }

                boolean isAllValid2 = true;

                if(jobTitle2.length() > 35){
                    isAllValid2 = false;
                }

                if(jobId2.length() > 10){
                    isAllValid2 = false;
                }

                if (Double.parseDouble(salary2) > max_salary2 || Double.parseDouble(salary2) < min_salary2){
                    isAllValid2 = false;
                }
                String idEmployee = request.getParameter("id");
                if(isAllValid2){
                    daoEmployee.EditarEmployee(idEmployee,firstName2, lastName2,email2, phoneNumber2,jobId2,Double.parseDouble(salary2), Integer.parseInt(idManager2), hireDateF2, idDepartment2);
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");

                }
                break;
        }
    }
}
