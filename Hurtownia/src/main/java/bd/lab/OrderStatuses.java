package bd.lab;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderStatuses {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	public OrderStatuses() {}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
