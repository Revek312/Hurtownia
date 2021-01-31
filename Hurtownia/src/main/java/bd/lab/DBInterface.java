package bd.lab;

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
	@SuppressWarnings({"unchecked", "rawtypes" })
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
	private static boolean addNewRecord(Object object) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(object);
			tx.commit();
		} catch(HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
			success = false;
		}
		session.close();
		return success;
	}
	private static boolean updateRecord(Object object) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(object);
			tx.commit();
		} catch(HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
			success = false;
		}
		session.close();
		return success;
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
	public static List<OrderLine> getAllOrderLines(){
		return (List<OrderLine>)getAllRecords("OrderLine");
	}
	
	public static List<OrderLine> getAllOrderLinesById(int OrderId){
		return (List<OrderLine>)getRecordsWithConditon("OrderLine", "id="+OrderId);
	}
	public static List<ProductAvailability> getAllProductsAvailabilityById(int id){
		return (List<ProductAvailability>)getRecordsWithConditon("ProductAvailability", "id="+id);
	}
	
	public static boolean addNewClient(String name, String address, String nip, String phone, String email) {
		Client newClient = new Client();
		newClient.setAddress(address);
		newClient.setName(name);
		newClient.setEmail(email);
		newClient.setNIP(nip);
		newClient.setPhone(phone);
		return addNewRecord(newClient);
	}
	public static boolean addNewProduct(String name, int price, int tax) {
		Product newProduct = new Product();
		newProduct.setName(name);
		newProduct.setPrice(price);
		newProduct.setTax(tax);
		return addNewRecord(newProduct);
	}
	public static boolean addNewPayment(java.sql.Date date, int value, int tax, int paymentMethod) {
		Payment newPayment = new Payment();
		newPayment.setDate(date);
		newPayment.setTax(tax);
		newPayment.setValue(value);
		newPayment.setPaymentMethod(paymentMethod);
		return addNewRecord(newPayment);
	}
	public static boolean addNewOrder(int clientId) {
		Order newOrder = new Order();
		newOrder.setClientId(clientId);
		return addNewRecord(newOrder);
	}
	public static boolean addNewOrderLine(int orderId, int productId, int price, int tax, int quantity) {
		OrderLine newOL = new OrderLine();
		OrderLinePK pk = new OrderLinePK();
		pk.setOrderId(orderId);
		pk.setProductId(productId);
		
		newOL.setId(pk);
		newOL.setPrice(price);
		newOL.setTax(tax);
		newOL.setQuantity(quantity);
		return addNewRecord(newOL);
	}
	
	public static boolean updateProduct(int productId, String name, int price, int tax) {
		Product product = new Product();
		product.setId(productId);
		product.setName(name);
		product.setPrice(price);
		product.setTax(tax);
		
		return updateRecord(product);
	}
	public static boolean updateProductAvailability(int productId, int quantity) {
		ProductAvailability pa = new ProductAvailability();
		pa.setId(productId);
		pa.setQuantity(quantity);
		return updateRecord(pa);
	}

}
