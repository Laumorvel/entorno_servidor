<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*" %>
<%
   // Se obtiene la fecha de creación de la sesión
   Date createTime = new Date(session.getCreationTime());
   
   // Se obtiene el último acceso a la web
   Date lastAccessTime = new Date(session.getLastAccessedTime());

   String title = "Bienvenido de nuevo a mi web";
   Integer visitCount = Integer.valueOf(0);
   String visitCountKey = new String("visitCount");
   String userIDKey = new String("userID");
   String userID = new String("ABCD");

   // Se comprueba si es un nuevo acceso a la web
   if (session.isNew() ){
      title = "Bienvenido a mi web";
      session.setAttribute(userIDKey, userID);
      session.setAttribute(visitCountKey,  visitCount);
   } 
   visitCount = (Integer)session.getAttribute(visitCountKey);
   visitCount = visitCount + 1;
   userID = (String)session.getAttribute(userIDKey);
   session.setAttribute(visitCountKey,  visitCount);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo de uso del objeto session</title>
</head>
<body>
	<h1>Session Tracking</h1>
      
      <table border = "1"> 
         <tr bgcolor = "#949494">
            <th>Session info</th>
            <th>Value</th>
         </tr> 
         <tr>
            <td>id</td>
            <td><% out.print( session.getId()); %></td>
         </tr> 
         <tr>
            <td>Creation Time</td>
            <td><% out.print(createTime); %></td>
         </tr> 
         <tr>
            <td>Time of Last Access</td>
            <td><% out.print(lastAccessTime); %></td>
         </tr> 
         <tr>
            <td>User ID</td>
            <td><% out.print(userID); %></td>
         </tr> 
         <tr>
            <td>Number of visits</td>
            <td><% out.print(visitCount); %></td>
         </tr> 
      </table> 
</body>
</html>