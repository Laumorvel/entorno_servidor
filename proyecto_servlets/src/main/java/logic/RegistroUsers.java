package logic;

import java.io.IOException;
import java.io.PrintWriter;
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

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// Metodo para POST
	/**
	 * Controlamos que el usuario sea uno de los registrados en la applicación. Si
	 * es así, imprimimos el catálogo de productos. En caso contrario, reconducimos
	 * al inicio. Marcamos que se invalide la sesión pasados 2 minutos sin actividad
	 * en la página.
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// creamos la sesión si y se crea si no exite. --> despues tendremos que
		// comprobar que no sea nueva

		HttpSession session = request.getSession(true);// si no hay sesión iniciada, que se cree

		session.setMaxInactiveInterval(120);

		String nombreUser = request.getParameter("nombre");
		String passwordUser = request.getParameter("password");

		// USUARIOS PREDEFINIDOS ACEPTADOS
		/*
		 * Estos son los usuarios que podrán entrar dentro de la aplicación pues son lo
		 * únicos segistrados
		 */
		Map<String, String> map = new HashMap<>();
		map.put("lauramolez", "lauramolez");
		map.put("alex43", "alex43");
		map.put("lola3", "lola3");
		map.put("", "");// he añadido este usuario y contraseña para que sea más rápido de probar

		// Si coinciden nombre y contraseña y no hay un nombre para sesión iniciada se habrá
		// producido un inicio de sesión correcto
		if (map.containsKey(nombreUser) && map.get(nombreUser).equals(passwordUser)
				&& session.getAttribute("nombreUser") == null) {// isNew

			session.setAttribute("nombreUser", nombreUser);// lo usaremos más adelante para comprobar que el usuario se
															// ha identificado y no ha entrado por url directamente

			session.setAttribute("registroUser", "true");// defino variable en la sesión para compreobar que en la
															// siguiente pantalla procesa de esta
			generacionForm(response);

		} else {
			session.setAttribute("error", "errorIdentificacion");
			response.sendRedirect("/proyecto_servlets/HTML/init_session.jsp");

		}

	}

	/**
	 * Vamos a generar el formulario que registra los pedidos del usuario.
	 * Posteriormente recogeremos las cantidades que vamos a pedir de cada producto.
	 * 
	 * @param response
	 * @param request
	 * @param session
	 */
	private void generacionForm(HttpServletResponse response) {

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
