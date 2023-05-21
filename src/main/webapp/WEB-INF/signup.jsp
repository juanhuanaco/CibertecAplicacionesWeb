<%@ page import="beans.UsuarioDTO" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session='false'%>
<!DOCTYPE html>
<html>
<head lang="es">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Registrarse</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
</head>
<style>
form{
	width: 25em;
	text-align: center;
	margin: 0 auto;
}
h2{
	text-align: center;
}
p{text-align:center;}
p#errorMsj{color: red;}
</style>
<body>

<%
	String errorMsj = request.getAttribute("errorMsj") == null ? "" : request.getAttribute("errorMsj").toString();
%>

<h2 class="display-4">- Expenses Tracker -</h2>
<br><br>
<h2>Registrarse</h2>
<p>¡Estás a pocos pasos de mejorar tu control financiero!</p>
<form action="signup" method="post">
<div class="form-floating mb-3">
<input type="text" name="nombre" id="input_nombre" class="form-control" placeholder="Fulanito Perez" required>
<label for="input_nombre">Nombre de usuario</label>
</div>

<div class="form-floating mb-3">
<input type="email" name="email" id="input_email" class="form-control" placeholder="name@example.com" required>
<label for="input_email">Correo electrónico</label>
</div>
 
<div class="form-floating mb-3">
<input type="number" name="presupuesto" id="input_presupuesto" class="form-control" placeholder="5000" required>
<label for="input_presupuesto">Presupuesto del mes</label>
</div>
 
<div class="form-floating mb-3">
<input type="password" name="password" id="input_password" class="form-control" placeholder="password" required>
<label for="input_password">Contraseña</label>
</div>

<button type="submit" class="btn btn-primary">¡Crear cuenta!</button>
</form>

<p id="errorMsj"><%=errorMsj%></p>

</body>
</html>