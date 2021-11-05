package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CatalogoBean {

	private List<ProductoBean> productos;
	private List<String> fotos;

	public CatalogoBean() {
		this.productos = new ArrayList<ProductoBean>();
		generaList();
		this.fotos = new ArrayList<String>();
		generaFotosCatalogo();
	}

	public List<String> getFotos() {
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
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

	private void generaFotosCatalogo() {
		
		String foto1 = "'img/pngwing.com.png'";
		String foto2 = "'img/pngwing.com(1).png'";
		String foto3 = "'img/pngwing.com(2).png'";
		String foto4 = "'img/pngwing.com(3).png'";
		String foto5 = "'img/pngwing.com(4).png'";
		String foto6 = "'img/pngwing.com(5).png'";

		this.fotos.add(foto1);
		this.fotos.add(foto2);
		this.fotos.add(foto3);
		this.fotos.add(foto4);
		this.fotos.add(foto5);
		this.fotos.add(foto6);
	}
	
}
