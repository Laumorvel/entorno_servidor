package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.repository.LineaPedidoRepository;

@Service
public class LineaPedidoService {
	
	@Autowired
	private LineaPedidoRepository repositorio;
	
	public LineaPedido borraLineaPedido(long idPedido) {
		if(repositorio.existsById(idPedido)) {
			LineaPedido lp = repositorio.findById(idPedido).get();
			repositorio.deleteById(idPedido);
			return lp;
		}else {
			return null;
		}
	}

	public LineaPedido creaLineaPedido(LineaPedido lineaPedido) {
		return repositorio.save(lineaPedido);
	}
	
	public List<LineaPedido> findAll(){
		return repositorio.findAll();
	}
	
	public LineaPedido findById(long lineaPedidoId) {
		return repositorio.findById(lineaPedidoId).orElse(null);
	}
	
	public LineaPedido edit (LineaPedido lp, long id) {
		if(repositorio.existsById(id)) {
			lp.setIdLinea(id);
			return repositorio.save(lp);
		}else {
			return null;
		}
	}
	
	public LineaPedido add(LineaPedido p) {
		return repositorio.save(p);
	}
	
	public LineaPedido delete (long id) {
		if (repositorio.existsById(id)) {
			LineaPedido p = repositorio.findById(id).get();
			repositorio.deleteById(id);
			return p;
		} else {
			return null;
		}
	}
	
}
