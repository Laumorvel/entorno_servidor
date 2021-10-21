<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage = "error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo de control de excepciones</title>
</head>
<body>
<%
   // Se lanza una excepción para invocar a la página de error
   int x = 1;
   
   if (x == 1) {
      throw new RuntimeException("Se ha producido un error!!!");
   }
%>
</body>
</html>