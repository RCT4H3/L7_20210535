package org.example.l7_20210535.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.l7_20210535.Beans.EmployeesB;
import org.example.l7_20210535.Beans.JobsB;
import org.example.l7_20210535.Daos.DaoEmployee;
import org.example.l7_20210535.Daos.DaoJobs;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "JobServlet", value = "/JobServlet")
public class JobServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("act")== null ? "lista" : request.getParameter("act");
        DaoJobs daoJobs = new DaoJobs();
        switch (action){
            case "lista":

                ArrayList<JobsB> list = daoJobs.listarJobs();
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/listJobs.jsp");
                rd.forward(request,response);
                break;
            case "ver":
                String id = request.getParameter("id");
                JobsB jobsB = daoJobs.buscarJob(id);


                request.setAttribute("job",jobsB);
                request.getRequestDispatcher("WEB-INF/verJob.jsp").forward(request,response);
                break;
            case "new":
                request.getRequestDispatcher("WEB-INF/crearJob.jsp").forward(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("act") == null ? "crear" : request.getParameter("act");
        DaoJobs daoJobs = new DaoJobs();
        JobsB jobsB = new JobsB();
        switch (action){
            case "crear":
                String jobId = request.getParameter("jobId");
                String jobName = request.getParameter("jobName");
                String minSalary = request.getParameter("minSalary");
                String maxSalary = request.getParameter("maxSalary");
                jobsB.setJob_id(jobId);
                jobsB.setJob_title(jobName);
                jobsB.setMin_salary(Integer.parseInt(minSalary));
                jobsB.setMax_salary(Integer.parseInt(maxSalary));
                daoJobs.crearJob(jobsB);
                response.sendRedirect(request.getContextPath() + "/JobServlet");
                break;
        }
    }
}
