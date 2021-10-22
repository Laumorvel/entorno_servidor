<%@page import="beans.ClienteBean"%>
<%@page import="beans.GimnasioBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario nuevo ingreso</title>
<link rel="stylesheet" href="CSS/FormNewClient.css" />
</head>
<body>
	<h1 class="titulo">Formulario de nuevo ingreso</h1>

	<%
	//SE OBTIENE LA FECHA ACTUAL
	Calendar calendar = new GregorianCalendar();
	String am_pm;
	int dia = calendar.get(Calendar.DAY_OF_MONTH);
	int mes = calendar.get(Calendar.MONTH);
	int anyo = calendar.get(Calendar.YEAR);

	String CT = dia + "/" + mes + "/" + anyo + " ";
	out.println("Fecha de inscripción: " + CT + "\n");
	%>



	<!-- FORMULARIO DE INSCRIPCIÓN -->
	<div>
		<form action="Main.jsp" method="get">

			<p class="nombre">
				Nombre: <input type="text" name="nombre">
			</p>
			<p>
				Apellidos:<input type="text" name="apellidos" />
			</p>
			<p>¿Eres mayor de edad?</p>
			<input type="radio" name="mayorEdad" value="si" />si <br /> <input
				type="radio" name="mayorEdad" value="no" />no <br />
			<p>DNI:</p>
			<input type="text" name="dni" />
			<p>Situación:</p>
			<select name="situacion" />
				<option>PENSIONISTA</option>
				<option>EN_PARO</option>
				<option>FAMILIA_NUMEROSA</option>
				<option>FAMILIA_ESPECIAL</option>
				<option>NINGUNA</option>
			<br /> <br /> <br /> <input type="submit" value="Enviar" />
		</form>
	</div>


</body>
</html>