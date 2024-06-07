<%@ page import="org.example.l7_20210535.Beans.JobsB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% JobsB jobsB = (JobsB) request.getAttribute("job"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ver información del empleado</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
            color: #007bff;
        }
        .card-label {
            font-weight: bold;
            color: #555;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Ver información del trabajo</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title mb-4">Detalles del trabajo</h5>

            <div class="row mb-3">
                <div class="col-sm-4 card-label">ID TRABAJO</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= jobsB.getJob_id() %></p></div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Título del trabajo:</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= jobsB.getJob_title() %></p></div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Mínimo Salario</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= jobsB.getMin_salary() %></p></div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Máximo Salario</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= jobsB.getMax_salary() %></p></div>
            </div>

            <a href="<%= request.getContextPath() %>/EmployeeServlet?action=lista" class="btn btn-primary">Regresar</a>
        </div>
    </div>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
