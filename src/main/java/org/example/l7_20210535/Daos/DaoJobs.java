package org.example.l7_20210535.Daos;

import org.example.l7_20210535.Beans.EmployeesB;
import org.example.l7_20210535.Beans.JobsB;

import java.sql.*;
import java.util.ArrayList;

public class DaoJobs {
    public ArrayList<JobsB> listarJobs(){
        ArrayList<JobsB> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/hr";
        String sql = "SELECT " +
                "j.job_id, " +
                "j.job_title, " +
                "j.min_salary, " +
                "j.max_salary, " +
                "d.department_name AS department, " +
                "jh.start_date, " +
                "jh.end_date " +
                "FROM hr.jobs j " +
                "LEFT JOIN hr.job_history jh ON j.job_id = jh.job_id " +
                "LEFT JOIN hr.departments d ON jh.department_id = d.department_id";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {

                JobsB jobs = new JobsB();
                jobs.setJob_id(rs.getString(1));
                jobs.setJob_title(rs.getString(2));
                jobs.setMin_salary(rs.getInt(3));
                jobs.setMax_salary(rs.getInt(4));
                lista.add(jobs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public JobsB buscarJob(String idJob){
        JobsB jobsB = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql = "SELECT job_id, job_title, min_salary, max_salary FROM hr.jobs WHERE job_id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,idJob);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    jobsB = new JobsB();
                    jobsB.setJob_id(rs.getString(1));
                    jobsB.setJob_title(rs.getString(2));
                    jobsB.setMin_salary(rs.getInt(3));
                    jobsB.setMax_salary(rs.getInt(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jobsB;
    }
    public void crearJob(JobsB jobsB){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql = "INSERT INTO hr.jobs (job_id, job_title, min_salary, max_salary) VALUES (?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1, jobsB.getJob_id());
            pstmt.setString(2, jobsB.getJob_title());
            pstmt.setInt(3, jobsB.getMin_salary());
            pstmt.setInt(4, jobsB.getMax_salary());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
