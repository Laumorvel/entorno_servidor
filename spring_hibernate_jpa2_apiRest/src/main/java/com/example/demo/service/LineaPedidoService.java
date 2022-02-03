package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Producto;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.ProductoRepository;

@Service
public class LineaPedidoService {

	@Autowired
	private LineaPedidoRepository repositorio;

	@Autowired
	private ProductoRepository productRepo;

	/**
	 * Borra una linea de pedido buscada por su id.
	 * @param idPedido
	 * @return linea borrada
	 */
	public LineaPedido borraLineaPedido(long idPedido) {
		if (repositorio.existsById(idPedido)) {
			LineaPedido lp = repositorio.findById(idPedido).get();
			repositorio.deleteById(idPedido);
			return lp;
		} else {
			return null;
		}
	}

	/**
	 * Crea una nueva linea de pedido
	 * @param lineaPedido
	 * @return linea creada
	 */
	public LineaPedido creaLineaPedido(LineaPedido lineaPedido) {
		return repositorio.save(lineaPedido);
	}

	/**
	 * Encuentra todas las linea de pedido de la bbdd
	 * @return
	 */
	public List<LineaPedido> findAll() {
		return repositorio.findAll();
	}

	/**
	 * Encuentra una linea de pedido en concreto.
	 * @param lineaPedidoId
	 * @return linea buscada
	 */
	public LineaPedido findById(long lineaPedidoId) {
		return repositorio.findById(lineaPedidoId).orElse(null);
	}

	/**
	 * Edita la linea de pedido --> su cantidad.
	 * @param lp
	 * @param id
	 * @return linea editado
	 */
	public LineaPedido edit(LineaPedido lp, long id) {
		if (repositorio.existsById(id)) {
			LineaPedido lineaCambiada = repositorio.findById(id).orElse(null);
			lineaCambiada.setCantidad(lp.getCantidad());
			lineaCambiada.setPrecioCantidad();
			return repositorio.save(lineaCambiada);
		} else {
			return null;
		}
	}

	/**
	 * Rescato el producto al que va dirigido puesto que, de otra manera, nos daría
	 * un error 404. De esta forma, establezco el resto de campos de la tabla
	 * (lógica que se encuentra en el mismo modelo puesto solo es establecer campos
	 * del producto que contiene la linea en los propios campos de la linea). De
	 * esta forma, en la petición solo necesito introducir la cantidad y el id del
	 * producto. 
	 * 
	 * De primeras todas las cantidades de los productos van a ser 0
	 * porque al recuperarlas de la bbdd no se ha establecido aún, sin embargo,
	 * consetearlo del objeto creado en el requestBody de la petición, que incluye
	 * una cantidad (incluida en el objeto simplemente para no crear otra clase
	 * nueva).
	 * 
	 * @param p
	 * @return
	 */
	public LineaPedido add(Producto pro) {
		Producto producto = productRepo.findById(pro.getId()).orElse(null);
		LineaPedido p = new LineaPedido();
		p.setProducto(producto);
		p.setCantidad(pro.getCantidad());
		p.setNombreProducto();
		p.setPrecioProducto();
		p.setPrecioCantidad();
		return repositorio.save(p);
	}

	/**
	 * Borra una linea de pedido buscada por id. 
	 * @param id
	 * @return linea de pedido borrada.
	 */
	public LineaPedido delete(long id) {
		if (repositorio.existsById(id)) {
			LineaPedido p = repositorio.findById(id).get();
			repositorio.deleteById(id);
			return p;
		} else {
			return null;
		}
	}

}
