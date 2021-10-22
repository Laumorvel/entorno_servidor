<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="beans.ClienteBean"%>
<%@page import="beans.GimnasioBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%><!-- Añado la página de error en caso de excepción si el usuario está repetido (depende del dni)-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guardado de datos de usuarios</title>
<link rel="stylesheet" href="CSS/Main.css" />
</head>
<body>

	<!-- ESTABLECIMIENTO DE LOS DATOS DEL USUARIO E INICIALIZACIÓN - FORMA 1 -->
	<jsp:useBean id="usuario" class="beans.ClienteBean" scope="page">
		<jsp:setProperty name="usuario" property="nombre" param="nombre" />
		<jsp:setProperty name="usuario" property="apellidos" param="apellidos" />
		<jsp:setProperty name="usuario" property="mayorEdad" param="mayorEdad" />
		<jsp:setProperty name="usuario" property="dni" param="dni" />
		<jsp:setProperty name="usuario" property="situacion" param="situacion" />
	</jsp:useBean>

	<!-- INICIALIZACIÓN DE LA CLASE GIMNASIOBEAN - FORMA 2
	//En realidad nunca se llegaría a introducir al usuario porque la colección es un hasSet pero así utilizo las excepciones -->
	<jsp:useBean id="gym" class="beans.GimnasioBean" scope="session"></jsp:useBean>
	<%
	if (gym.getClientes().contains(usuario)) {
		throw new RuntimeException("No puede registrarse dos veces");
	} else {
		gym.addClient(usuario);
	}
	System.out.println("---------------------------------------------------------------------");

	for (ClienteBean client : gym.getClientes()) {
		System.out.println(client);

	}
	%>

	<div class="saludo">
		<h1>
			Hola,
			<jsp:getProperty property="nombre" name="usuario" /></h1>
		<br /> Bienvenido a tu página de inicio <br />

		
		<h2>Calcule el precio a pagar por sus clases</h2>
		<div class="contenedor"><!-- esconder con css y mostrar al final cuando se pulse el botón con js -->
			<%
			double precio = usuario.calculaPrecio();
			%>
			<p>El precio a pagar es de <%=precio%>  euros</p>
		</div>
	</div>


</body>
</html>