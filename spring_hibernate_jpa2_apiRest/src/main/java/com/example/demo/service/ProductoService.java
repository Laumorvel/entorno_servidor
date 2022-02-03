package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository repositorio;

	/**
	 * Método creado para poder añadir productos en nuestro objeto wrapper para
	 * poder hacer el binding de la lista de productos que el usuario está
	 * editando.
	 * 
	 * @param producto
	 */
	public void addProducto(Producto p) {
		repositorio.save(p);
	}

	/**
	 * Devolvemos la lista de productos
	 * @return todos los productos del repositorio
	 */
	public List<Producto> findAll() {
		return repositorio.findAll();
	}

	/**
	 * Se elimina un producto buscado por su id.
	 * @param id
	 * @return producto eliminado.
	 */
	public Producto delete (long id) {
		if (repositorio.existsById(id)) {
			Producto p = repositorio.findById(id).get();
			repositorio.deleteById(id);
			return p;
		} else {
			return null;
		}
	}
	
	/**
	 * Se añade un producto a la bbdd.
	 * @param p
	 * @return
	 */
	public Producto add(Producto p) {
		return repositorio.save(p);
	}

	/**
	 * Se edita un producto de la bbdd
	 * @param p
	 * @param id
	 * @return pedido editado
	 */
	public Producto edit(Producto p, long id) {
		if (repositorio.existsById(id)) {
			p.setId(id);
			return repositorio.save(p);
		} else {
			return null;
		}
	}
	
	public Producto findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
}
