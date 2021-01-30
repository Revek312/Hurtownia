package bd.lab;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class OrderLinePK implements Serializable {
	private static final Long serialVersionUID = 1L;
	protected int orderId;
	protected int productId;
	
	public OrderLinePK() {}
	public OrderLinePK(int orderId, int productId) {
		this.orderId = orderId;
		this.productId = productId;
	}
	
	public int getOrderId() {
		return this.orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return this.productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderLinePK)) return false;
		OrderLinePK that = (OrderLinePK) o;
		return Objects.equals(getOrderId(), that.getOrderId()) && 
				Objects.equals(getProductId(), that.getProductId());
	}
	@Override
	public int hashCode() {
		return Objects.hash(getOrderId(), getProductId());
	}
}
