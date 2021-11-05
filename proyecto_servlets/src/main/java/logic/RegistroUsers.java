package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/registroUsers")
public class RegistroUsers extends HttpServlet {

	/*
	 * Estos son los usuarios que podrán entrar dentro de la aplicación pues son lo
	 * únicos segistrados
	 */

	// Metodo para POST

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// Metodo para POST

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// creamos la sesión si y se crea si no exite. --> despues tendremos que
		// comprobar que no sea nueva

		HttpSession session = request.getSession(true);

		String nombreUser = (String) request.getParameter("nombre");
		String passwordUser = (String) request.getParameter("password");

		// USUARIOS PREDEFINIDOS ACEPTADOS
		Map<String, String> map = new HashMap<String, String>();
		map.put("lauramolez", "lauramolez");
		map.put("alex43", "alex43");
		map.put("lola3", "lola3");
		map.put("", "");

		// Si coinciden nombre y contraseña y no hay una sesión iniciada se habrá
		// producido un inicio de sesión correcto
		if (map.containsKey(nombreUser) && map.get(nombreUser).equals(passwordUser)
				&& session.getAttribute("nombreUser") == null) {// isNew

			session.setAttribute("nombreUser", nombreUser);// lo usaremos más adelante para comprobar que el usuario se
															// ha identificado y no ha entrado por url directamente
			generacionForm(response, request, session);

		} else {// en caso contrario se invalida la sesión y se redirige a la misma página de
				// nuevo mostrando mensaje de error
			session.setAttribute("error", "errorIdentificacion");
			response.sendRedirect("/proyecto_servlets/HTML/init_session.jsp");

		}

	}

	private void generacionForm(HttpServletResponse response, HttpServletRequest request, HttpSession session) {

		CatalogoBean catalogo = new CatalogoBean();
		List<String> fotos = catalogo.getFotos();
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<!DOCTYPE html><html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>Catálogo</title>");
			out.println("<link rel='stylesheet' href='CSS/catalogo.css' />");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='contenedor'>");
			out.println("<h1 align='center' class='main_title'>Catálogo de productos</h1>");
			out.println("<hr width='650'>");
			out.println("<form action='/proyecto_servlets/resumen' method='post'>");// redirige con el action a la
																					// página
																					// de resumen de pedido
			out.println("<table class='tabla'>");
			int i = 0;
			for (ProductoBean product : catalogo.getProductos()) {
				String nombreProducto = product.getNombre();
				double precioProducto = product.getPrecio();

				out.println("<div class='producto'>");
				out.println("<tr>");
				out.println("<div class='nombre'>");
				out.println("<td class='title'>" + nombreProducto);
				out.println("</td>");
				out.println("</div>");
				out.println("<div class='foto'>");
				out.println("<td rowspan=3 class='foto'><img src=" + fotos.get(i) + " width='200' height='140'>");
				out.println("</td>");
				out.println("</div>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>");
				out.println("<div class='elem-group1'>");
				out.println("Cantidad <input type='number' name='total' placeholder='0' min='0'>");// cantidad que
																									// recuperaremos en
																									// el resumen
				out.println("</div>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>");
				out.println("<div class='precio'>");
				out.println("<p name='precio'>" + precioProducto + " </p>");
				out.println("</div>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</div>");
				i++;

			}
			out.println("</table>");
			out.println("<div class='submit'>");
			out.println("<input type='submit' value='Siguiente'>");
			out.println("</div>");
			out.println("</form>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
