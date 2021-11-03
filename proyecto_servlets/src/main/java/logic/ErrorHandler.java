package logic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * En esta clase controlaré todos las excepciones propias incluídas en la
 * aplicación. No he querido realizar una clases error tal cual como vimos en la
 * unidad anterior con jsp puesto que prefiero que cada error especifique su
 * causa.
 * 
 * @author laura
 *
 */
public class ErrorHandler extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String error = (String) session.getAttribute("error");
		
		
		
		
	}
	
	private void printError(String error) throws Exception {
		if(error.equals("errorIdentificacion")) {
			throw new Exception("El usuario o contraseña es incorrecto.");
		}
	}
}
