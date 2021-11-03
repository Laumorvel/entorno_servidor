package dwes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Ejemplo de servlet que genera texto plano

public class ClaseServlet extends HttpServlet
{
	// Metodo para procesar una peticion GET
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		out.println ("Hola, este es un servlet sencillo de prueba");
	}
}