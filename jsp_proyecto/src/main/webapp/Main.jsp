<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guardado de datos de usuarios</title>
<link rel="stylesheet" href="CSS/Main.css" />
</head>
<body>

	<jsp:useBean id="usuario" class="beans.ClienteBean" />
	<jsp:setProperty name="usuario" property="nombre" param="nombre" />
	<jsp:setProperty name="usuario" property="apellidos" param="apellidos" />
	<jsp:setProperty name="usuario" property="mayorEdad" param="mayorEdad" />
	<jsp:setProperty name="usuario" property="situacion" param="situacion" />
	Hola,
	<jsp:getProperty property="nombre" name="usuario" />
	<br /> Bienvenido a tu página de inicio
	<br />

	<table id="tabla" class="tabla">
		<tr>
			<td>Seleccione la clase en la que desea participar: </td>
			<td>
			<select>
			<option>Yoga</option>
			<option>Spinning</option>
			<option>Padel</option>
			</select>
			</td>
		</tr>
	</table>


</body>
</html>