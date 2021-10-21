<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! 
   int pageCount = 0;
   void addCount() {
      pageCount++;
   }
%>
<% addCount(); %>
<html>
   <head>
      <title>The include Directive Example</title>
   </head>
   <body>
         <h2>The include Directive Example</h2>
         <p>This site has been visited <%= pageCount %> times.</p>
      <br/><br/>