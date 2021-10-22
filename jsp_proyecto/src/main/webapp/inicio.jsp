<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gimnasio Moreno</title>
<link rel="stylesheet" href="CSS/Inicio.css" />
</head>
<body>
	<h1>Gimnasio Moreno</h1>

	<h3>Comprometidos con tu salud</h3>
	<br />
	<%
	beans.GimnasioBean gym = new beans.GimnasioBean();
	%>
	<div class="contenedor">
		<p>
			<a href="FormNewClient.jsp">Hazte socio</a> <a href="Horarios.jsp">Consulta
				horarios</a>
		</p>
		<br />
	</div>
</body>
</html>