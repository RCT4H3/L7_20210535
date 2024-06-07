<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.l7_20210535.Beans.EmployeesB" %>
<%
    ArrayList<EmployeesB> lista = (ArrayList<EmployeesB>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de empleados</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .table td, .table th {
            padding: .25rem;
            font-size: 0.875rem;
        }
        .btn {
            padding: .25rem .5rem;
            font-size: 0.75rem;
        }
        .container-fluid {
            padding-left: 10px;
            padding-right: 10px;
        }
    </style>
</head>
<body>
<div class="container-fluid mt-5 ml-0 mr-0">
    <h1 class="mb-4">Lista de Empleados</h1>
    <button class="btn btn-primary mb-3"><a href="<%=request.getContextPath()%>/EmployeeServlet?action=new" class="text-white text-decoration-none">Crear nuevo empleado</a></button>
    <button class="btn btn-success mb-3"><a href="<%=request.getContextPath()%>/JobServlet?action=lista" class="text-white text-decoration-none">Ir a la lista de trabajos</a></button>
    <table class="table table-striped">

        <thead class="thead-dark">
        <tr>
            <th>ID Empleado</th>
            <th>Nombre y apellido</th>
            <th>Email</th>
            <th>Número de teléfono</th>
            <th>Fecha de contratación</th>
            <th>Salario</th>
            <th>Puesto de trabajo</th>
            <th>Nombre del manager</th>
            <th>Departamento</th>
            <th>Ver más</th>
            <th>Editar</th>
            <th>Borrar</th>
        </tr>
        </thead>
        <tbody>
        <% for (EmployeesB employeesB : lista) { %>
        <tr>
            <td><%= employeesB.getEmployee_id() %></td>
            <td><%= employeesB.getFullNameEmployee() %></td>
            <td><%= employeesB.getEmail() %></td>
            <td><%= employeesB.getPhone_number() %></td>
            <td><%= employeesB.getHire_date() %></td>
            <td><%= employeesB.getSalary() %></td>
            <td><a href="<%=request.getContextPath()%>/JobServlet?act=ver&id=<%=employeesB.getJob_id() %>"><%= employeesB.getJobName() %></a></td>
            <td>
                <% if (employeesB.getManagerName() != null) { %>
                <%= employeesB.getManagerName() %>
                <% } else { %>
                No tiene Manager
                <% } %>
            </td>
            <td><%= employeesB.getDepartment() %></td>
            <td><a href="<%=request.getContextPath()%>/EmployeeServlet?action=ver&id=<%= employeesB.getEmployee_id() %>" class="btn btn-info">Ver más</a></td>
            <td><a href="<%=request.getContextPath()%>/EmployeeServlet?action=edit&id=<%= employeesB.getEmployee_id() %>" class="btn btn-warning">Editar</a></td>
            <td><a onclick="return confirm('¿Esta seguro de borrar?')" href="<%=request.getContextPath()%>/EmployeeServlet?action=del&id=<%= employeesB.getEmployee_id() %>" class="btn btn-danger">Borrar</a></td>
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
