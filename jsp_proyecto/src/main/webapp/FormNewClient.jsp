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
	<h1>Formulario de nuevo ingreso</h1>

	<%
	//Fijamos el tiempo de refresco 
	//response.setIntHeader("Refresh", 10);

	//Se obtiene la hora actual 
	Calendar calendar = new GregorianCalendar();
	String am_pm;
	int hour = calendar.get(Calendar.HOUR);
	int minute = calendar.get(Calendar.MINUTE);
	int second = calendar.get(Calendar.SECOND);

	String CT = hour + ":" + minute + ":" + second + " ";
	out.println("Current Time is: " + CT + "\n");
	%>

	<form action="Main.jsp" method="get">

		Nombre: <input type="text" name="nombre"> <br /> Apellidos: <input
			type="text" name="apellidos" /> <br /> ¿Eres mayor de edad? <br />
		<input type="radio" name="mayorEdad" value="si" />si <br /> <input
			type="radio" name="mayorEdad" value="no" />no <br /> DNI: <input
			type="text" name="dni" /> <br />
		<%
		//comprobar que los dnis introducidos no estén ya repetidos
		%>

		Situación: <select name="situacion" /> <br />
		<option>PENSIONISTA</option>
		<option>EN_PARO</option>
		<option>FAMILIA_NUMEROSA</option>
		<option>FAMILIA_ESPECIAL</option>
		<option>NINGUNA</option>

		<%
			//se añade a la clase GimnasioBean
		%>

		<br /> <br /> <input type="submit" value="Enviar" />
		<!-- debo poner aquí onsubmit enlazado a un metodo para que añada al participante en la lista de usuarios del gym y que comprueba si ya es usuario -->
	</form>

</body>
</html>