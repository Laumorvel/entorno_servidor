package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CatalogoBean {

	private List<ProductoBean> productos;

	public CatalogoBean() {
		this.productos = new ArrayList<ProductoBean>();
		generaList();
	}

	public List<ProductoBean> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoBean> productos) {
		this.productos = productos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogoBean other = (CatalogoBean) obj;
		return Objects.equals(productos, other.productos);
	}

	@Override
	public String toString() {
		String texto = null;
		for (ProductoBean productoBean : productos) {
			texto += productoBean.toString() + "\n";
		}
		return texto;
	}

	private void generaList() {
		ProductoBean p1 = new ProductoBean("Bombones rellenos de crema de fresa", 1.20);
		ProductoBean p2 = new ProductoBean("Barrita de crema con caramelo", 1.80);
		ProductoBean p3 = new ProductoBean("Toffee con caramelo salado", 1.50);
		ProductoBean p4 = new ProductoBean("Bombones rellenos de crema de avellanas", 1.20);
		ProductoBean p5 = new ProductoBean("Muffin de cacao", 2.00);
		ProductoBean p6 = new ProductoBean("Brownie con pepitas de chocolate", 2.20);

		this.productos.add(p1);
		this.productos.add(p2);
		this.productos.add(p3);
		this.productos.add(p4);
		this.productos.add(p5);
		this.productos.add(p6);
	}

}
