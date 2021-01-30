package bd.lab;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Invoice {
	@Id
	@GeneratedValue
	private int id;
	private String number;
	private String clientName;
	private String clientAddress;
	private java.sql.Date issueDate;
	private java.sql.Date realizationDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public java.sql.Date getRealizationDate() {
		return realizationDate;
	}
	public void setRealizationDate(java.sql.Date realizationDate) {
		this.realizationDate = realizationDate;
	}
	public java.sql.Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(java.sql.Date issueDate) {
		this.issueDate = issueDate;
	}
}
