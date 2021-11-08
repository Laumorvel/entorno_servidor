package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/resumen")
public class ResumenPedido extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Integer> cantidades = new ArrayList<Integer>();

		// creamos la sesión si y se crea si no exite. --> despues tendremos que
		// comprobar que no sea nueva
		HttpSession session = request.getSession(true);
		if (session.getAttribute("nombreUser") == null || session.getAttribute("registroUser") == null) {
			session.setAttribute("error", "errorIdentificacion");
			response.sendRedirect("/proyecto_servlets/HTML/init_session.jsp");
		}

		//Variable para comprobar que el usuario procede de esta clase en la siguiente
		session.setAttribute("resumenPedido", "true");
		calculaPreciosEImprime(request, response, session);

	}

	private void calculaPreciosEImprime(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

		response.setContentType("text/html");
		PrintWriter out;
		out = response.getWriter();
		out.println("<!DOCTYPE html><html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Resumen</title>");
		out.println("<link rel='stylesheet' href='CSS/resumenPedido.css' />");
		out.println("</head>");
		out.println("<body>" + " <h1 class=\"title\">Resumen de pedido</h1>\n" + "    <div class=\"contenedor\">");
		out.println("<form action='/proyecto_servlets/factura' method='post'>");
		String[] cantidadesForm = request.getParameterValues("total");// cantidades de cada producto
		CatalogoBean catalogo = new CatalogoBean();
		List<String> fotos = catalogo.getFotos();
		double precio;
		int j = 0;
		double Preciototal = 0;
		for (int i = 0; i < cantidadesForm.length; i++) {
			if (!cantidadesForm[i].equals("")) {
				j++;
				ProductoBean product = catalogo.getProductos().get(i);
				precio = product.getPrecio();
				String nombre = product.getNombre();
				int cantidad = Integer.parseInt(cantidadesForm[i]);
				double total = precio * cantidad;
				total = (double) Math.round(total * 100d) / 100d;

				out.println("<div class=\"product\">\n" + "             <div class='datos'>\n"
						+ "            <p class=\"nombreProducto\">" + j + ".  " + nombre + "</p>\n"
						+ "            <p>Cantidad: " + cantidad + " </p>\n" + "            <p>Precio/unidad: " + precio
						+ " </p>\n" + "            <p>Total: " + total + " </p>\n" + "        </div>\n"
						+ "        <div class=\"img\">\n" + "            <img src=" + fotos.get(i)
						+ " width=\"200\" height=\"140\">\n" + "        </div>" + "        </div>\n");

				Preciototal += total;
			}
		}
		out.println("<div class='envio'> "
				+ " <label for='envio'>Seleccione el modo de envío: "
				+"   <select name=\"envio\" id=\"\">\n"
				+ "              <option value=\"domicilio\">Envío a domicilio</option>\n"
				+ "              <option value=\"recoger\">Recogida en tienda</option>\n"
				+ " </select>"
				+" </label>"
				+" </div>");
		
		out.println("<p>Precio de compra: " + Preciototal  + " </p>");
		
		if (j == 0) {
			out.println("</br><h2>No tiene productos en el carrito</h2>");
		}
		
		//Guardamos el precio total para recuperarlo en nuestra última clase
		session.setAttribute("total", Preciototal);

		out.println("</div>");
		out.println("<div class='submit'>");
		out.println("<input type='submit' value='Comprar'>");
		out.println("</div>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

}