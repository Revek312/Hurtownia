package bd.lab;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DBInterface {
	private static SessionFactory factory;
	public static void init() {
		try {
			factory = HibernateFactory.getSessionFactory();
		} catch(Throwable ex) {
			System.err.println("Failed to create session factory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List getAllRecords(String table) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Client> list = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery("FROM "+table).list();
			tx.commit();
		} catch(HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
		session.close();
		
		return list;
	}
	@SuppressWarnings({"unchecked" })
	private static List getRecordsWithConditon(String table, String condition) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Client> list = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery("FROM "+table+" WHERE "+condition).list();
			tx.commit();
		} catch(HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
		session.close();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	public static List<Client> getAllClients(){
		return (List<Client>) getAllRecords("Client");
	}
	@SuppressWarnings("unchecked")
	public static List<Employee> getAllEmployees(){
		return (List<Employee>) getAllRecords("Employee");
	}
	@SuppressWarnings("unchecked")
	public static List<Invoice> getAllInvoices() {
		return (List<Invoice>)getAllRecords("Invoice");
	}
	@SuppressWarnings("unchecked")
	public static List<Order> getAllOrders() {
		return (List<Order>)getAllRecords("Order");
	}
	@SuppressWarnings("unchecked")
	public static List<Product> getAllProducts(){
		return (List<Product>)getAllRecords("Product");
	}
	@SuppressWarnings("unchecked")
	public static List<Payment> getAllPayments(){
		return (List<Payment>)getAllRecords("Payment");
	}
	@SuppressWarnings("unchecked")
	public static List<ProductAvailability> getAllProductsAvailability(){
		return (List<ProductAvailability>)getAllRecords("ProductAvailability");
	}
	public static List<OrderLine> getAllOrderLines(int OrderId){
		return (List<OrderLine>)getRecordsWithConditon("OrderLine", "id="+OrderId);
	}
}
