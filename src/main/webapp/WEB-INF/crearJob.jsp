<%--
  Created by IntelliJ IDEA.
  User: 51917
  Date: 7/06/2024
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Trabajo</title>
    <meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Crear un nuevo empleado</h1>
    <form method="post" action="<%=request.getContextPath()%>/JobServlet?act=crear">
        <div class="form-group">
            <label>Ingrese el ID del trabajo</label>
            <input type="text" class="form-control" name="jobId" required>
        </div>
        <div class="form-group">
            <label>Ingrese el nombre del trabajo:</label>
            <input type="text" class="form-control" name="jobName" required>
        </div>
        <div class="form-group">
            <label>Ingrese el salario mínimo</label>
            <input type="text" class="form-control" name="minSalary" required>
        </div>
        <div class="form-group">
            <label>Ingrese el salario máximo</label>
            <input type="text" class="form-control" name="maxSalary" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="<%= request.getContextPath() %>/JobServlet?action=lista" class="btn btn-secondary ml-2">Regresar</a>
        </div>

    </form>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
