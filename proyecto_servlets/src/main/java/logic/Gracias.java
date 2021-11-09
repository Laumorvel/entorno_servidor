package logic;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/gracias")
public class Gracias extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	/**
	 * Comprobamos que proceda de la clase anterior (factura). Al imprimir lo
	 * necesario, volvemos a marcar el atributo como null para que al volver a la
	 * clase anterior, en caso de querer continuar, nos lleva al inicio de nuevo, ya
	 * que puede ser peligroso poder volver atrás justo después de haber realizado
	 * la compra. Por otro lado, también invalidamos la sesión.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("factura") == null) {
			response.sendRedirect("/proyecto_servlets/HTML/init_session.jsp");
		}

		response.setContentType("text/html");
		PrintWriter out;
		out = response.getWriter();
		out.println("<!DOCTYPE html>" + "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n"
				+ "<title>Gracias</title>\n" + "<link rel='stylesheet' href='CSS/gracias.css' />" + "</head>" + "<body>"
				+ "<h1 align='center'>GRACIAS POR SU COMPRA</h1>" + "<p align='center'>"
				+ "<a align='center' href='/proyecto_servlets/HTML/init_session.jsp'>Inicio</a>" + "</p>" + "</body>\n"
				+ "</html>");

		// Hago que, una vez realizada la compra no se pueda volver atrás, pues sería
		// peligroso
		session.setAttribute("factura", null);
		session.invalidate();
	}
}
