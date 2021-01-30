package bd.lab;

import java.util.Iterator;
import java.util.List;

public class App {
	public static void main(String[] args) {
		DBInterface.init();
		List<Client> clients = DBInterface.getAllClients();
		Iterator<Client> iter = clients.iterator();
		while(iter.hasNext()) {
			Client client = iter.next();
			System.out.println(client.toString());
		}
	}
	
}
