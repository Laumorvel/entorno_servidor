package logic;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/factura")
public class Factura extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		if (session.getAttribute("nombreUser") == null) {
			session.setAttribute("error", "errorIdentificacion");
			response.sendRedirect("/proyecto_servlets/HTML/init_session.jsp");
		}
	}

	/**
	 * En función de si se trata de un envío o una recogida, se imprimirá una cosa u
	 * otra.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	private void imprimeFactura(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {

		String total = (String) session.getAttribute("total");
		Double total1 = Double.parseDouble(total);
		String envio = (String) request.getAttribute("envio");

		response.setContentType("text/html");
		PrintWriter out;
		out = response.getWriter();
		out.println("<!DOCTYPE html><html>" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Resumen</title>\n" + "    <link rel=\"stylesheet\" href=\"CSS/factura.css\">\n"
				+ "</head>\n" + "\n" + "<body>\n" + "    <h1 class=\"title\">Información del pedido</h1>");

		if (envio.equals("domicilio")) {
			out.println("<div class=\"envio\">\n" + "\n"
					+ "        <p>Su pedido se entregará en un periodo de 24 a 48 horas</p>\n" + "    </div>");
		} else {
			out.println("<div class=\"recogida\">\n"
					+ "        <p>Puede recoger su pedido en cualquier momento en nuestra tienda de Sevilla</p>\n"
					+ "        <iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3169.961037871186!2d-5.996651884693376!3d37.39075367983123!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd126c101cddb079%3A0xe114975ce20b4190!2sC.%20Sierpes%2C%2041004%20Sevilla!5e0!3m2!1ses!2ses!4v1636149164252!5m2!1ses!2ses\"\n"
					+ "            width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\"></iframe>\n"
					+ "    </div>");
		}

		out.println("\n" + "    <div class=\"factura\">\n"
				+ "        <table class=\"tabla\" border=\"1px\" cellspacing=\"4px\" align=\"center\" cellpadding=\"10px\" width=\"18em\" height=\"10px\">\n"
				+ "            <tr>\n" + "                <td>PRECIO DEL PEDIDO</td>\n" + "                <td> " +  total1 + " </td>\n"
				+ "            </tr>\n" + "            <tr>\n" + "                <td>IVA</td>\n"
				+ "                <td></td>\n" + "            </tr>");

		if (envio.equals("domicilio")) {
			total1 += 2.5;//aumentamos el precio del envío al precio de los productos
			out.println("   <tr>\n" + "                <td>GASTOS DE ENVÍO</td>\n" + "                <td>2,5€</td>\n"
					+ "            </tr>");
		}
		//me baso en un iva del 21%
		out.println("<tr class=\"totalPrecio\">\n" + "                <td>TOTAL</td>\n" + "                <td> " + total1*1.21 + "</td>\n"
				+ "            </tr>\n" + "        </table>\n" + "\n" + "    </div>\n" + "</body>\n" + "\n"
				+ "</html>");
	}
}