<%@ page import="org.example.l7_20210535.Beans.EmployeesB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% EmployeesB employee = (EmployeesB) request.getAttribute("employee"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ver empleado</title>
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
    <h1 class="mb-4">Ver información del empleado</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title mb-4">Detalles del empleado</h5>
            <% String[] parts = employee.getFullNameEmployee().split(" "); %>
            <% String first_name = parts[0]; %>
            <% String last_name = parts.length > 1 ? parts[1] : ""; %>

            <div class="row mb-3">
                <div class="col-sm-4 card-label">Primer nombre:</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= first_name %></p></div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Apellido:</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= last_name %></p></div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Email:</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= employee.getEmail() %></p></div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Número de teléfono:</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= employee.getPhone_number() %></p></div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Puesto de trabajo:</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= employee.getJobName() %></p></div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Manager:</div>
                <div class="col-sm-8">
                    <p class="form-control-plaintext">
                        <%= employee.getManagerName() != null ? employee.getManagerName() : "No tiene manager" %>
                    </p>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Salario:</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= employee.getSalary() %></p></div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 card-label">Fecha y hora de la contratación:</div>
                <div class="col-sm-8"><p class="form-control-plaintext"><%= employee.getHire_date().toString().replace(" ", "T") %></p></div>
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
