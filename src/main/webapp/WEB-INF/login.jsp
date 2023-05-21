<%@ page import="beans.UsuarioDTO" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session='false'%>
<!DOCTYPE html>
<html>
<head lang="es">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
	
<style>
form{
	width: 25em;
	text-align: center;
	margin: 0 auto;
}
h2{
	text-align: center;
}
p{text-align: center;}
p#errorMsj{color: red;}
p#signupMsj{color: green;}
</style>	
</head>
<body>

<%
	String errorMsj = request.getAttribute("errorMsj") == null ? "" : request.getAttribute("errorMsj").toString();
	String signupMsj = request.getParameter("src") == null ? "" : "¡Cuenta creada exitosamente!";
%>

<p id="signupMsj"><%=signupMsj%></p>
<h2 class="display-4">- Expenses Tracker -</h2>
<br><br>
<h2>Inicia sesión</h2>
<form action="login" method="post">
<div class="form-floating mb-3">
<input type="email" name="email" id="input_email" class="form-control" placeholder="name@example.com" required>
<label for="input_email">Correo electrónico</label>
</div>
 
<div class="form-floating mb-3">
<input type="password" name="password" id="input_password" class="form-control" placeholder="password" required>
<label for="input_password">Contraseña</label>
</div>

<button type="submit" class="btn btn-primary">Vamos</button>

</form>



<p id="errorMsj"><%=errorMsj%></p>
<p style="text-align: center"><a href="signup">Aún no tengo cuenta</a></p>
</body>
</html>