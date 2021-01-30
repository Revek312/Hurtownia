package bd.lab;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class OrderLine {
	@EmbeddedId
	private OrderLinePK id;
	private int cost;
	private int tax;
	private int quantity;
	
	public void setInvoiceLinePK(OrderLinePK id) {
		this.id = id;
	}
	public OrderLinePK getInvoiceLinePK() {
		return id;
	}
	public void setId(OrderLinePK id) {
		this.id = id;
	}
	public OrderLinePK getId() {
		return id;
	}
	public int getOrderID() {
		return id.getOrderId();
	}
	public void setOrderID(int orderId) {
		id.setOrderId(orderId);
	}
	public int getProductID() {
		return id.getProductId();
	}
	public void setProductID(int productId) {
		id.setProductId(productId);
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
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
