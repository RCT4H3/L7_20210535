<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.l7_20210535.Beans.JobsB" %>
<%
    ArrayList<JobsB> lista = (ArrayList<JobsB>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de trabajos</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid mt-5 ml-0 mr-0">
    <h1 class="mb-4">Lista de Trabajos</h1>
    <button class="btn btn-primary mb-3"><a href="<%=request.getContextPath()%>/JobServlet?act=new" class="text-white text-decoration-none">Crear nuevo trabajo</a></button>
    <table class="table table-striped">

        <thead class="thead-dark">
        <tr>
            <th>ID Trabajo</th>
            <th>Nombre Trabajo</th>
            <th>Salario Mínimo</th>
            <th>Máximo salario</th>
            <th>Ver</th>

        </tr>
        </thead>
        <tbody>
        <% for (JobsB jobsB : lista) { %>
        <tr>
            <td><%= jobsB.getJob_id() %></td>
            <td><%= jobsB.getJob_title()%></td>
            <td><%= jobsB.getMin_salary() %></td>
            <td><%= jobsB.getMax_salary() %></td>
            <td><a href="<%=request.getContextPath()%>/JobServlet?act=ver&id=<%=jobsB.getJob_id() %>" class="btn btn-info">Ver</a></td>

        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
