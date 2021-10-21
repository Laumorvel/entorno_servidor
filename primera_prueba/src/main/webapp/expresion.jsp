<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo de uso de expresiones</title>
</head>
<body>
<h1><%= new String ("Esto es un String") %></h1>
<h1><%= 128 + 234 %></h1>
<h1><%=  "prueba de string" %></h1>
<h1><%= Integer.valueOf(128) + Integer.valueOf(234) %></h1>
</body>
</html>