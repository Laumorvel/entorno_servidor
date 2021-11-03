package logic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/catalogo")
public class Catalogo {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// Comprobamos que haya un usuario logueado
		if (session.getAttribute("nombreUser") == null) {
			response.sendRedirect("/registroUsers");
			session.invalidate();
		}

		// PRODUCTO 1
		int total1 = Integer.parseInt(request.getParameter("total1"));
		double precio1 = total1 * 1.20;
		session.setAttribute("standard1", 1.20);
		session.setAttribute("total1", total1);// crear método --> si el total es 0, no mostrar
		session.setAttribute("precio1", precio1);

		// PRODUCTO 2
		int total2 = Integer.parseInt(request.getParameter("total2"));
		double precio2 = total1 * 1.80;
		session.setAttribute("standard2", 1.80);
		session.setAttribute("total2", total2);
		session.setAttribute("precio2", precio2);

		// PRODUCTO3
		int total3 = Integer.parseInt(request.getParameter("total3"));
		double precio3 = total1 * 1.50;
		session.setAttribute("standard3", 1.50);
		session.setAttribute("total3", total3);
		session.setAttribute("precio3", precio3);

		// PRODUCTO 4
		int total4 = Integer.parseInt(request.getParameter("total4"));
		double precio4 = total1 * 1.20;
		session.setAttribute("standard4", 1.20);
		session.setAttribute("total2", total4);
		session.setAttribute("precio2", precio4);

		// PRODUCTO5
		int total5 = Integer.parseInt(request.getParameter("total5"));
		double precio5 = total5 * 2;
		session.setAttribute("standard5", 2);
		session.setAttribute("total5", total5);
		session.setAttribute("precio5", precio5);

		// PRODUCTO6
		int total6 = Integer.parseInt(request.getParameter("total6"));
		double precio6 = total1 * 2.20;
		session.setAttribute("standard6", 2.20);
		session.setAttribute("total6", total6);
		session.setAttribute("precio6", precio6);
	}

}
