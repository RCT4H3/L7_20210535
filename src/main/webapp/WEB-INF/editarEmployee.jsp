<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.l7_20210535.Beans.JobsB" %>
<%@ page import="org.example.l7_20210535.Beans.EmployeesB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<JobsB> listaJobs = (ArrayList<JobsB>) request.getAttribute("lista2"); %>
<% ArrayList<EmployeesB> listaEmployees = (ArrayList<EmployeesB>) request.getAttribute("lista3"); %>
<% EmployeesB employee = (EmployeesB) request.getAttribute("employee"); %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar empleado</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Editar información del empleado</h1>
    <form method="post" action="<%= request.getContextPath() %>/EmployeeServlet?action=edit&id=<%= employee.getEmployee_id() %>">
        <% String[] partes = employee.getFullNameEmployee().split(" "); %>
        <% String primerNombre = partes[0]; %>
        <% String apellido = partes[1]; %>
        <div class="form-group">
            <label>Primer nombre:</label>
            <input type="text" class="form-control" name="firstName" value="<%= primerNombre %>" required>
        </div>
        <div class="form-group">
            <label>Apellido:</label>
            <input type="text" class="form-control" name="lastName" value="<%= apellido %>" required>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" class="form-control" name="email" value="<%= employee.getEmail() %>" required>
        </div>
        <div class="form-group">
            <label>Número de teléfono:</label>
            <input type="text" class="form-control" name="phoneNumber" value="<%= employee.getPhone_number() %>" required>
        </div>
        <div class="form-group">
            <label for="trabajos">Selecciona un trabajo:</label>
            <select id="trabajos" name="jobId" class="form-control" onchange="actualizarRangoSalario()" required>
                <option value="" selected disabled><%= employee.getJobName() %></option>
                <% for(JobsB jobsB : listaJobs) { %>
                <option value="<%= jobsB.getJob_id() %>" data-salario-min="<%= jobsB.getMin_salary() %>" data-salario-max="<%= jobsB.getMax_salary() %>">
                    <%= jobsB.getJob_title() %>
                </option>
                <% } %>
            </select>
        </div>
        <div class="form-group">
            <label for="managers">Selecciona el manager:</label>
            <select id="managers" name="idManager" class="form-control" required>
                <option value="" selected disabled><%= employee.getManagerName() %></option>
                <% for (EmployeesB employeesB : listaEmployees) { %>
                <option value="<%= employeesB.getEmployee_id() %>"><%= employeesB.getFullNameEmployee() %></option>
                <% } %>
            </select>
        </div>
        <div class="form-group">
            <label>Ingrese el salario</label>
            <input type="number" step="0.01" class="form-control" name="salary" id="salario" value="<%= employee.getSalary() %>" required>
            <small id="ayudaSalario" class="form-text text-muted">Salario entre <span id="salarioMin"></span> y <span id="salarioMax"></span></small>
        </div>
        <div class="form-group">
            <label for="hire_date">Seleccione la fecha y hora de la contratación</label>
            <input type="datetime-local" class="form-control" id="hire_date" name="hire_date" value="<%= employee.getHire_date().toString().replace(" ", "T") %>" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="<%= request.getContextPath() %>/EmployeeServlet?action=lista" class="btn btn-secondary ml-2">Regresar</a>
        </div>
    </form>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function actualizarRangoSalario() {
        const select = document.getElementById('trabajos');
        const opcionSeleccionada = select.options[select.selectedIndex];

        const salarioMin = opcionSeleccionada.getAttribute('data-salario-min');
        const salarioMax = opcionSeleccionada.getAttribute('data-salario-max');

        document.getElementById('salarioMin').innerText = salarioMin;
        document.getElementById('salarioMax').innerText = salarioMax;

        document.getElementById('salario').setAttribute('min', salarioMin);
        document.getElementById('salario').setAttribute('max', salarioMax);
    }

    // Inicializar el rango de salario basado en el trabajo seleccionado al cargar la página
    document.addEventListener('DOMContentLoaded', function() {
        const trabajoSeleccionado = document.getElementById('trabajos').value;
        if (trabajoSeleccionado) {
            actualizarRangoSalario();
        }
    });
</script>
</body>
</html>
