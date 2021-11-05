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
import javax.servlet.http.HttpSession;

@WebServlet("/catalogo")
public class Catalogo extends HttpServlet{

	/**
	 * Introducimos todos los datos que nos serán necesarios para elaborar un
	 * resumen del pedido y calculamos el precio total de cada producto según el
	 * número de artículos seleccionado
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// Comprobamos que haya un usuario logueado

		if (session.getAttribute("nombreUser") == null) {
			response.sendRedirect("/proyecto_servlets/HTML/init_session.html");
			session.invalidate();
		}

		List<String> fotos = new ArrayList<>();
		String foto1 = "../img/pngwing.com.png";
		String foto2 = "../img/pngwing.com.png(1)";
		String foto3 = "../img/pngwing.com.png(2)";
		String foto4 = "../img/pngwing.com.png(3)";
		String foto5 = "../img/pngwing.com.png(4)";
		String foto6 = "../img/pngwing.com.png(5)";

		fotos.add(foto1);
		fotos.add(foto2);
		fotos.add(foto3);
		fotos.add(foto4);
		fotos.add(foto5);
		fotos.add(foto6);

		generacionForm(response, fotos, request);

	}

	private void generacionForm(HttpServletResponse response, List<String> fotos, HttpServletRequest request) {
		CatalogoBean catalogo = new CatalogoBean();
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'");
			out.println("<title>Catálogo</title>");
			out.println("<link rel='stylesheet' href='../CSS/catalogo.css\' />");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class= 'contenedor'>");
			out.println("<h1 align='center' class='main_title'>Catálogo de productos</h1>");
			out.println("<hr width='650'>");
			out.println("<form action='proyecto_servlet/catalogo' method='post'>");
			out.println("<table class='tabla'>");

			for (ProductoBean product : catalogo.getProductos()) {
				String nombreProducto = product.getNombre();
				double precioProducto = product.getPrecio();
				int i = 0;

				out.println("<div class='producto'>");
				out.println("<tr>");
				out.println("<div class='nombre'>");
				out.println("<td class='title'>" + nombreProducto + "</td>");
				out.println("</div>");
				out.println("<div class='foto'>");
				out.println("<td rowspan=3 class='foto'><img + src=" + fotos.get(i) + " width='200' height='140'>");
				out.println("</td>");
				out.println("</div>\"");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>");
				out.println("<div class='elem-group1'>");
				out.println("Cantidad <input type='number' name='total1' placeholder='0' min='0'>");
				out.println("</div>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>");
				out.println("<div class='precio'>");
				out.println("<p name='precio'>" + precioProducto + " </p>");
				out.println("</div>");
				out.println("</td>>");
				out.println("</tr>");
				out.println("</div>");
				i++;

			}
			out.println("</table>");
			out.println("<div class='submit'>");
			out.println("<input type='submit' value='Seleccionar'>");
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
