<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo de uso del objeto application</title>
</head>
<body>
<%
   Integer hitsCount = (Integer)application.getAttribute("hitCounter");
   if( hitsCount ==null || hitsCount == 0 ) {
      /* First visit */
      out.println("Bienvenido a mi web!");
      hitsCount = 1;
   } else {
      /* return visit */
      out.println("Hola de nuevo! me encanta ver que estás de vuelta en mi web");
      hitsCount += 1;
   }
   application.setAttribute("hitCounter", hitsCount);
%>
<p>Número total de visitas: <%= hitsCount%></p>
</body>
</html>