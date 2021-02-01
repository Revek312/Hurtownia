package bd.lab;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmployeesPasswords {
	@Id
	@GeneratedValue
	int id;
	String pass;
	
	public EmployeesPasswords() {}
	
	public void setId(int id) { this.id = id; }
	public void setPass(String pass) {this.pass = pass;}
	
	public int getId() { return id; }
	public String getPass() { return pass; }
}
