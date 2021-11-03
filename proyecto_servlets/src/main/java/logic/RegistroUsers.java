package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

	// Metodo para GET

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

		// Si coinciden nombre y contraseña y no hay una sesión iniciada se habrá
		// producido un inicio de sesión correcto
		if (map.containsKey(nombreUser) && map.get(nombreUser).equals(passwordUser)
				&& session.getAttribute("nombreUser") == null) {

			session.setAttribute("nombreUser", nombreUser);// lo usaremos más adelante para comprobar que el usuario se
															// ha identificado y no ha entrado por url directamente
			response.sendRedirect("/proyecto_servlets/HTML/catalogo.html");

		} else {// en caso contrario se invalida la sesión y se redirige a la misma página de
				// nuevo mostrando mensaje de error
			session.setAttribute("error", "errorIdentificacion");
			compruebaError(session, response);
			response.sendRedirect("/proyecto_servlets/HTML/init_session.html");
			session.invalidate();
		}
	}

	/**
	 * Método para que se imprima el mensaje de error en la página siempre que se
	 * establezca un atributo error de identificación en la sesión. En caso
	 * contrario, se elimina el atributo
	 * 
	 * @param session
	 * @param response
	 */
	private void compruebaError(HttpSession session, HttpServletResponse response) {
		if (session.getAttribute("error").equals("errorIdentificacion")) {
			PrintWriter out = null;
			try {
				response.setContentType("text/html");
				out = response.getWriter();
		        out.println("<div>" +
		                       "<h3>El usuario o contraseña es incorrecto.</h3></div>");
				
		        //out.close();   Si no lo cierro, no funciona pero si lo cierro no me redirige 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			session.setAttribute("error", "");
		}
	}

}
