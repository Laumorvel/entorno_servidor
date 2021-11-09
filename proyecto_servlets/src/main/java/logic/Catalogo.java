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
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

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

	}

	
}
