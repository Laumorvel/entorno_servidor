<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--Ejemplo de recuperación de parámetros de entrada y mostrar su valor a través de out --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo de objeto out</title>
</head>
<body>

<% 
//usamos el objeto request para recoger datos de la petición
String dato1 = request.getParameter("dato1"); 
String dato2 = request.getParameter("dato2");

//usamos el objeto out para escribir en el buffer de salida
out.println(dato1);
out.println(dato2);
%>

</body>
</html>