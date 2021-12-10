package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

@Controller
public class PedidosController {

	@Autowired
	private PedidoService servicePedido;

	@Autowired
	private UsuarioService serviceUsuario;

	@Autowired
	private ProductoService serviceProducto;

	/**
	 * Método principal para comprobar que el usuario se haya logueado y no esté
	 * accediendo directamente a una parte interna de la aplicación. En el login
	 * habremos cambiado el valor de la variable que usamos para ello a true. Se
	 * utilizará este método cada vez que entremos en una vista nueva para comprobar
	 * la sesión.
	 */
	public String compruebaSesion() {
		if (!serviceUsuario.isLogueado()) {
			return "login";
		}
		return "menuPersonal";
	}

	@RequestMapping(value = "/menuPersonal/submit", method = RequestMethod.POST, params = "pedidos")
	public String consultaPedidos(Model model, @ModelAttribute("usuario") Usuario usuario) {
		compruebaSesion();
		model.addAttribute("listaPedidos", servicePedido.encuentraPedidosDeUsuario());
		return "consultaPedidos";
	}

	/**
	 * Se listan los productos de nuestro catálogo, enlazados con un html que se
	 * autogenera, de forma que si añadimos nuevos productos al catálogo, la vista
	 * del mismo se modificará automáticamente.
	 * 
	 * @param model
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/menuPersonal/submit", method = RequestMethod.POST, params = "newPedido")
	public String nuevoPedidoMenu(Model model, @ModelAttribute("usuario") Usuario usuario) {
		compruebaSesion();
		Usuario usuario1 = serviceUsuario.findById(serviceUsuario.getUserId());
		model.addAttribute("usuario", usuario1);
		model.addAttribute("listaProductos", serviceProducto.findAll());
		return "newPedido";
	}

	public String compruebaSesionConsultaPedido() {
		if (!serviceUsuario.isLogueado()) {
			return "login";
		}
		return "menuPersonal";
	}

	/**
	 * Creo un endPoint donde se pase tanto la lista genérica de productos para
	 * poder listarlos en el catálogo como la lista de productos del mismo usuario
	 * para que estos se modifiquen. Recogeré el listado de productos y le
	 * introduciré la cantidad contenida dentro del array de cantidades que
	 * conseguimos dentro del formulario. Inicializo i en 0 puesto y recorro los
	 * productos puesto que la lista de productos y la de cantidades tiene la misma
	 * longitud.
	 * 
	 * @param model
	 * @param usuario
	 * @param listaProductos
	 */
	@PostMapping("/newPedido/submit")
	public void enviaListaDePedidos(Model model, @RequestParam("cantidad") Integer[] cantidades) {// para el envío, el
																									// valor es el mismo
																									// en el radio y se
																									// recoge con un
																									// String

		Pedido pedidoNuevo = new Pedido();
		servicePedido.creaPedido(pedidoNuevo); // este método ya busca dentro al usuario logueado y le añade el pedido a
												// su lista de pedidos automáticamente
		int i = 0;
		for (Producto producto : pedidoNuevo.getProductos()) {
			producto.setCantidad(cantidades[i]);
			i++;
		}

	}

}
