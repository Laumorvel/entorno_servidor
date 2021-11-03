package dwes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Servlet ejemplo de cabeceras y codigos de estado de respuesta
// Realiza 4 tareas, en funcion de lo que se le pida:
// - Calcular numeros primos y mostrar el ultimo numero encontrado
// - Enviar una cabecera de redireccion
// - Enviar un sendError()
// - Enviar un setStatus

@WebServlet("/servletrespuesta")
public class ServletCabecerasRespuesta extends HttpServlet implements Runnable {
	private static final long serialVersionUID = -1841719948896523872L;

	long primo = 1; // Ultimo numero primo descubierto
	Thread t = new Thread(this); // Hilo para calcular numeros primos

	// Metodo de inicializacion

	public void init() {
		t.start();
	}

	// Metodo para GET

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

		// Si coinciden nombre y contraseña y no hay una sesión iniciada
		if (map.containsKey(nombreUser) && map.get(nombreUser).equals(passwordUser)
				&& session.getAttribute("nombreUser") == null) {

			session.setAttribute("nombreUser", nombreUser);
			response.sendRedirect("/proyecto_servlet/HTML/catalogo.html");
		} else {
			response.sendRedirect("/proyecto_servlet/HTML/init_session.html");
			session.invalidate();
		}
	}
	// Main del hilo

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
