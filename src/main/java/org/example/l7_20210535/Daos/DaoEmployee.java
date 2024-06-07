package org.example.l7_20210535.Daos;

import org.example.l7_20210535.Beans.EmployeesB;

import java.sql.*;
import java.util.ArrayList;

public class DaoEmployee {
    public ArrayList<EmployeesB> listarEmployees(){
        ArrayList<EmployeesB> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/hr";
        String sql = "SELECT " +
                "e.employee_id, " +
                "CONCAT(e.first_name, ' ', e.last_name) AS full_name, " +
                "e.email, " +
                "e.phone_number, " +
                "e.hire_date, " +
                "e.salary, " +
                "j.job_title AS jobName, " +
                "CONCAT(m.first_name, ' ', m.last_name) AS managerName, " +
                "d.department_name AS department, " +
                "e.department_id AS idDepartment, " +
                "e.job_id " +
                "FROM hr.employees e " +
                "LEFT JOIN hr.jobs j ON e.job_id = j.job_id " +
                "LEFT JOIN hr.employees m ON e.manager_id = m.employee_id " +
                "LEFT JOIN hr.departments d ON e.department_id = d.department_id";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {

                EmployeesB employeesB = new EmployeesB();
                employeesB.setEmployee_id(rs.getInt(1));
                employeesB.setFullNameEmployee(rs.getString(2));
                employeesB.setEmail(rs.getString(3));
                employeesB.setPhone_number(rs.getString(4));
                employeesB.setHire_date(rs.getString(5));
                employeesB.setSalary(rs.getDouble(6));
                employeesB.setJobName(rs.getString(7));
                employeesB.setManagerName(rs.getString(8));
                employeesB.setDepartment(rs.getString(9));
                employeesB.setId_department(rs.getInt(10));
                employeesB.setJob_id(rs.getString(11));
                lista.add(employeesB);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public EmployeesB buscarEmployee(int id_employee){
        EmployeesB employeesB = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql = "SELECT " +
                "e.employee_id, " +
                "CONCAT(e.first_name, ' ', e.last_name) AS full_name, " +
                "e.email, " +
                "e.phone_number, " +
                "e.hire_date, " +
                "e.salary, " +
                "j.job_title AS jobName, " +
                "CONCAT(m.first_name, ' ', m.last_name) AS managerName, " +
                "d.department_name AS department " +
                "FROM hr.employees e " +
                "LEFT JOIN hr.jobs j ON e.job_id = j.job_id " +
                "LEFT JOIN hr.employees m ON e.manager_id = m.employee_id " +
                "LEFT JOIN hr.departments d ON e.department_id = d.department_id " +
                "WHERE e.employee_id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,id_employee);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    employeesB = new EmployeesB();
                    employeesB.setEmployee_id(rs.getInt(1));
                    employeesB.setFullNameEmployee(rs.getString(2));
                    employeesB.setEmail(rs.getString(3));
                    employeesB.setPhone_number(rs.getString(4));
                    employeesB.setHire_date(rs.getString(5));
                    employeesB.setSalary(rs.getDouble(6));
                    employeesB.setJobName(rs.getString(7));
                    employeesB.setManagerName(rs.getString(8));
                    employeesB.setDepartment(rs.getString(9));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeesB;
    }
    public void crearEmployee(String first_name, String last_name, String email, String number_phone, String jobId, double salary, int idManager, String hire_date, int idDepartment){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql = "INSERT INTO hr.employees (" +
                "first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id, enabled" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setString(4, number_phone);
            pstmt.setString(5, hire_date);
            pstmt.setString(6, jobId);
            pstmt.setDouble(7, salary);
            pstmt.setInt(8, idManager);
            pstmt.setInt(9, idDepartment);


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void EditarEmployee(String idEmployee, String first_name, String last_name, String email, String number_phone, String jobId, double salary, int idManager, String hire_date, int idDepartment){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql = "UPDATE hr.employees SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "email = ?, " +
                "phone_number = ?, " +
                "hire_date = ?, " +
                "job_id = ?, " +
                "salary = ?, " +
                "manager_id = ?, " +
                "department_id = ?, " +
                "enabled = 1 " +
                "WHERE employee_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(10,Integer.parseInt(idEmployee));

            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setString(4, number_phone);
            pstmt.setString(5, hire_date);
            pstmt.setString(6, jobId);
            pstmt.setDouble(7, salary);
            pstmt.setInt(8, idManager);
            pstmt.setInt(9, idDepartment);


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void borrar(String employeeId) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "delete from hr.employees where employee_id  = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,employeeId);
            pstmt.executeUpdate();

        }
    }

}
