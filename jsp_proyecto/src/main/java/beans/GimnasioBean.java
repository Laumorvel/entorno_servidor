package beans;

import java.util.HashSet;


public class GimnasioBean {
	
	private HashSet<ClienteBean>clientes;
	
	public GimnasioBean() {
		this.clientes = new HashSet<ClienteBean>();
	}

	public HashSet<ClienteBean> getClientes() {
		return clientes;
	}

	public void setClientes(HashSet<ClienteBean> clientes) {
		this.clientes = clientes;
	}
	
	public void addClient(ClienteBean client) {
		clientes.add(client);
	}

}
