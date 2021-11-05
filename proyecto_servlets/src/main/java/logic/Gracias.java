package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gracias")
public class Gracias extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out;
		out = response.getWriter();
		out.println("<!DOCTYPE html>" + "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n"
				+ "<title>Gracias</title>\n" + "<link rel='stylesheet' href='../CSS/gracias.css' />"
				+ "</head>" + "<body>"  + "<h1>GRACIAS POR SU COMPRA</h1>" + "</body>\n"
				+ "</html>");

	}
}
