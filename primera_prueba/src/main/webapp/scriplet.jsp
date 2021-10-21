<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo de uso de scriplet</title>
</head>
<body>
<%
java.util.Calendar ahora = java.util.Calendar.getInstance();
int hora = ahora.get(java.util.Calendar.HOUR_OF_DAY);
%>
<b>Hola mundo, <i>
<% if (hora>20 || hora<6) { %>
	buenas noches
<% }
   else if (hora >=6 && hora <=12) {%>
   buenos días
<%		}
		else {%>
   buenas tardes
<%		} %>
</i></b>
</body>
</html>