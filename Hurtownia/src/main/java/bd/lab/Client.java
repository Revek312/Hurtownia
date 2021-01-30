package bd.lab;

import javax.persistence.*;

@Entity
public class Client {

	@Id
	@GeneratedValue
	private int id;
	private String NIP;
	private String name;
	private String address;
	private String email;
	private String phone;
	
	public Client() {}
	
	public void setId(int ID) {
		this.id = ID;
	}
	public int getId() {
		return this.id;
	}
	public void setNIP(String NIP) {
		this.NIP = NIP;
	}
	public String getNIP() {
		return this.NIP;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return this.phone;
	}
	@Override
	public String toString() {
		return "Client{"+
					"ID="+this.id+
					", name="+this.name+
					", NIP="+this.NIP+
					", address="+this.address+
					", email="+this.email+
					", phone="+this.phone+
					"}";
	}
}
