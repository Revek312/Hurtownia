package bd.lab;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class OrderLine {
	@EmbeddedId
	private OrderLinePK id;
	private int price;
	private int tax;
	private int quantity;

	public void setId(OrderLinePK id) {
		this.id = id;
	}
	public OrderLinePK getId() {
		return id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
