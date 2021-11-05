<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="logic.RegistroUsers"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
<link rel="stylesheet" href="../CSS/init_session.css" />
</head>
<body>

	<h1 align="center" class="titulo">Log in</h1>
	</br>
	<div class="contenedor">
		<form action="/proyecto_servlets/registroUsers" method="post">
			Usuario: <input type="text" name="nombre"></br> Contraseña: <input
				type="password" name="password"></br> <input type="submit"
				value="Enviar">
		</form>
	</div>
	<!-- MOSTRAMOS ERROR EN EL CASO DE QUE LA COMPROBACIÓN INDIQUE QUE EL ATRIBURO error DE session ES EL ERROR IDENTIFICACIÓN -->
	<div id="error">
		<%
		String msg = "";
		if (session.getAttribute("error") != null) {
			msg = "Introduzca un usuario y contraseña válidos.";
			session.removeAttribute("error");
		}

		session.removeAttribute("nombreUser");
		%>
		<p>
			<%=msg%>
		</p>
	</div>
</body>
</html>