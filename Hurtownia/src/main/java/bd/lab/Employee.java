package bd.lab;
import javax.persistence.*;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String surname;
	private int position;
	
	public Employee() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
