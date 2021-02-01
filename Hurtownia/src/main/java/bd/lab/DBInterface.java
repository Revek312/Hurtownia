package bd.lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.CodeSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
	public static List getRecordsWithConditon(String table, String condition) {
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
	private static boolean removeRecord(Class<?> c, int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean suc = true;
		try {
			tx = session.beginTransaction();
			Object object = session.get(c, id);
			session.delete(object);
			tx.commit();
		} catch(HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
			suc = false;
		}
		session.close();
		
		return suc;
	}
	public static boolean addNewRecord(Object object) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(object);
			tx.commit();
		} catch(Exception e) {
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
	public static boolean login(String name, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		System.out.println("login");
		String properName = "\'" + name + "\'";
		List<Employee> employee = ((List<Employee>) getRecordsWithConditon("Employee", "name="+properName));
		System.out.println("E : " + employee.size());
		if (employee.get(0) != null) {
			String statement = "id=" + employee.get(0).getId();
			List<EmployeesPasswords> employeePassword = (List<EmployeesPasswords>) getRecordsWithConditon("EmployeesPasswords", statement);
			if (employeePassword.get(0) != null) {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				digest.reset();
				digest.update(password.getBytes("utf8"));
				String sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
				System.out.println(name + " " + sha1);
				if (employeePassword.get(0).getPass().equals(sha1)) {
					switch (employee.get(0).getPosition()) {
						case 1:
							System.out.println("Logging in as employee");
							factory.close();
							factory = HibernateFactory.getSessionFactoryAsEmployee();
							return true;
						case 2:
							System.out.println("Logging in as manager");
							factory.close();
							factory = HibernateFactory.getSessionFactoryAsManager();
							return true;
						case 3:
							System.out.println("Logging in as CEO");
							factory.close();
							factory = HibernateFactory.getSessionFactoryAsRoot();
							return true;
						case 4:
							System.out.println("Logging in as root");
							factory.close();
							factory = HibernateFactory.getSessionFactoryAsRoot();
							return true;
					}
				}
			}
		}
		return false;
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
		return (List<OrderLine>)getRecordsWithConditon("OrderLine", "id.orderId="+OrderId);
	}
	public static ProductAvailability getProductAvailabilityById(int id){
		return ((List<ProductAvailability>)getRecordsWithConditon("ProductAvailability", "id="+id)).get(0);
	}
	public static List<OrderStatuses> getAllOrderStatuses(){
		return (List<OrderStatuses>)getAllRecords("OrderStatuses");
	}
	public static OrderPrice getOrderPriceById(int id) {
		List<OrderPrice> list = (List<OrderPrice>)getRecordsWithConditon("OrderPrice", "id="+id);
		if (list == null || list.size() == 0)
			return null;
		else return list.get(0);
	}
	public static Product getProductById(int id) {
		return ((List<Product>)getRecordsWithConditon("Product", "id="+id)).get(0);
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
	public static boolean addNewPayment(int orderId, int value, int tax, int paymentMethod) {
		Payment newPayment = new Payment();
		newPayment.setOrderId(orderId);
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
	public static boolean addNewInvoice(int orderId, String clientName, String clientAddress) {
		Invoice i = new Invoice();
		i.setOrderId(orderId);
		i.setClientAddress(clientAddress);
		i.setClientName(clientName);
		
		return addNewRecord(i);
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

	public static boolean deleteProduct(int productId) {
		return removeRecord(Product.class,productId);
	}
	public static boolean deleteClient(int clientId) {
		return removeRecord(Client.class,clientId);
	}
	public static int saveOrder(Order o) {
		int userId = 0;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			userId = (Integer) session.save(o);
			tx.commit();
			return userId;
		} catch(HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
		session.close();
		return userId;
	}
	
	public static void createBackup() {
		
	        String mysqldumpPath = "C:\\\"Program Files\"\\MySQL\\\"MySQL Server 8.0\"\\bin\\";

	        String savePath = "C:\\backups\\backup.sql";

	        String executeCmd = mysqldumpPath + "mysqldump -u root --password=\"root\" hurtownia > " + savePath;

	        System.out.println(executeCmd);
	        ProcessBuilder builder = new ProcessBuilder(
	                "cmd.exe", "/c", executeCmd);
	            builder.redirectErrorStream(true);
	            Process p = null;
				try {
					p = builder.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            String line = null;
	            while (true) {
	                try {
						line = r.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
	                if (line == null) { break; }
	                System.out.println(line);
	            }
	}
	
	public static void restoreFromBackup() {

		JOptionPane.showMessageDialog(new JFrame(), "Wklej to do cmd line: mysql -u root -p < C:\\backups\\backup.sql");
        //String mysqldumpPath = "C:\\\"Program Files\"\\MySQL\\\"MySQL Server 8.0\"\\bin\\";

//        String savePath = "C:\\backups\\backup.sql";
//
//        String executeCmd = "mysql -u root --password=\"root\" hurtownia < " + savePath;
//
//        System.out.println(executeCmd);
//        try {
//			Runtime.getRuntime().exec(  new String [] {"mysql", "-u", "root", "--password", "\"root\"", "hurtownia","<",savePath});
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        ProcessBuilder builder = new ProcessBuilder(
//                "cmd.exe", "/c", executeCmd);
//        builder.redirectErrorStream(true);
//        Process p = null;
//		try {
//			p = builder.start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line = null;
//        while (true) {
//            try {
//				line = r.readLine();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//            if (line == null) { break; }
//            System.out.println(line);
//        }
	}
}