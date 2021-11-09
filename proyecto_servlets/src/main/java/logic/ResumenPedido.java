package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/resumen")
public class ResumenPedido extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * Comprobamos que el usuario esté registrado
	 * En caso contrario mostraríamos un error en la página de inicio a la que lo redirigiríamos
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// creamos la sesión si y se crea si no exite. --> despues tendremos que
		// comprobar que no sea nueva
		HttpSession session = request.getSession(false);
		session.setMaxInactiveInterval(120);
		if (session.getAttribute("nombreUser") == null || session.getAttribute("registroUser") == null) {
			session.setAttribute("error", "errorIdentificacion");
			response.sendRedirect("/proyecto_servlets/HTML/init_session.jsp");
		}

		// Variable para comprobar que el usuario procede de esta clase en la siguiente
		session.setAttribute("resumenPedido", "true");
		calculaPreciosEImprime(request, response, session);

	}

	/**
	 * Imprimimos todos aquellos productos que se correspondan con la lista de cantidades recuperadas del formulario de RegistroUsers
	 * y que no sean "", es decir, que sean >0. 
	 * Se imprime el precio de la unidad del producto, la cantidad y el precio de cada lote de producto.
	 * Se redondea a dos decimales cada precio.
	 * En caso de que no se haya marcado ningún producto, se mostrará que el carrito se encuentra vacío.
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	private void calculaPreciosEImprime(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {

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
				total =  Math.round(total * 100d) / 100d;

				out.println("<div class=\"product\">\n" + "             <div class='datos'>\n"
						+ "            <p class=\"nombreProducto\">" + j + ".  " + nombre + "</p>\n"
						+ "            <p>Cantidad: " + cantidad + " </p>\n" + "            <p>Precio/unidad: " + precio
						+ " </p>\n" + "            <p>Total: " + total + " </p>\n" + "        </div>\n"
						+ "        <div class=\"img\">\n" + "            <img src=" + fotos.get(i)
						+ " width=\"200\" height=\"140\">\n" + "        </div>" + "        </div>\n");

				Preciototal += total;
			}
		}
		if (j == 0) {
			out.println("</br><h2>No tiene productos en el carrito</h2>");
		} else {
			out.println("<div class='envio'> " + " <label for='envio'>Seleccione el modo de envío: "
				+ "   <select name=\"envio\" id=\"\">\n"
				+ "              <option value=\"domicilio\">Envío a domicilio</option>\n"
				+ "              <option value=\"recoger\">Recogida en tienda</option>\n" + " </select>" + " </label>"
				+ " </div>");

		double definitivo = Math.round(Preciototal * 100d) / 100d;
		out.println("<p class='precioCompra'>Precio de compra: " + definitivo + " </p>");



			// Guardamos el precio total para recuperarlo en nuestra última clase
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

}