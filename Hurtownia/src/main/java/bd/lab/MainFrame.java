package bd.lab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class MainFrame implements ActionListener {
    JButton editQuantityProducts = new JButton("Edytuj ilosc");
    JButton deleteButtonProducts = new JButton("Usun");
    JButton createButtonProducts = new JButton("Stworz");
    String[] columnNamesProducts = {"Id", "Nazwa", "Cena", "Ilosc"};
    JTable tableProducts;

    JButton addButtonClients = new JButton("Dodaj klienta");
    JButton editButtonClients = new JButton("Edytuj dane");
    JButton deleteButtonClients = new JButton("Usun");
    String[] columnNamesClients = {"Id", "Nazwa", "NIP", "Adres", "Email", "Telefon"};
    JTable tableClients;

    JButton payButtonOrders = new JButton("Oplac");
    JButton createButtonOrders = new JButton("Stworz");
    JButton detailsButtonOrders = new JButton("Szczegoly");
    String[] columnNamesOrders = {"Id","Klient", "Status", "Data", "Kwota" };
    JTable tableOrders;

    String[] columnNamesInvoice = {"Id","Number", "Klient", "Adres", "Data wystawieni", "Data Realizacji"};
    JTable tableInvoice;
    
    JButton makeBackup = new JButton("Stwórz backup");
    JButton loadBackup = new JButton("Wczytaj backup");
    
    JPanel admin;
    JPanel products;
    JPanel orders;
    JPanel invoices;
    JPanel clients;

    public MainFrame() {
        JFrame frame = new JFrame("Hurtownia");

        createUI(frame);

        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUI(final JFrame frame){
        JTabbedPane tabbedPane = new JTabbedPane();
        this.products();
        this.orders();
        this.invoices();
        this.admin();
        this.clients();
        tabbedPane.addTab("Produkty", this.products);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Klienci", this.clients);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);

        tabbedPane.addTab("Zamowienia", this.orders);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);

        tabbedPane.addTab("Faktury", this.invoices);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_4);

        tabbedPane.addTab("Admin", this.admin);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_5);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    private void admin() {
        JPanel panel = new JPanel();
        if (false) {
            JLabel label = new JLabel("Brak uprawnien");
            panel.add(label);
            this.admin = panel;
            return;
        }
        makeBackup.addActionListener(this);
        loadBackup.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(makeBackup);
        buttonPanel.add(loadBackup);

        panel.add(buttonPanel);
        this.admin = panel;
    }
    private void updateProductsValues() {
    	DefaultTableModel tableModel;
    	Object[][] data;
        List<Product> products = DBInterface.getAllProducts();
        if(products != null) {
	        Iterator<Product> it = products.iterator();
	        data = new Object[products.size()][columnNamesProducts.length];
	        int i = 0;
	        while(it.hasNext()) {
	        	Product p = it.next();
	        	ProductAvailability a = DBInterface.getProductAvailabilityById(p.getId());
	        	data[i][0] = p.getId();
	        	data[i][1] = p.getName();
	        	data[i][2] = p.getPrice();
	        	data[i][3] = a.getQuantity();
	        	i++;
	        }
	        tableModel = new DefaultTableModel(data, columnNamesProducts);
	        if(tableProducts == null) {
	        	tableProducts = new JTable(tableModel);
	        }
	        else {
		        tableProducts.setModel(tableModel);
		        tableProducts.revalidate();
	        }
        }
        else {
        	data = new Object[1][1];
        	data[0][0] = "Brak produktow";
        	String[] c = {"Brak produktow"};
        	tableProducts = new JTable(data, c);
        }
        //String[] columnNamesProducts = {"Nazwa", "Cena", "Ilosc"};
        tableProducts.setDefaultEditor(Object.class, null);
        tableProducts.revalidate();
        tableProducts.repaint();
        
    }
    private void products() {
        JPanel panel = new JPanel();
        updateProductsValues();
        JScrollPane scrollPane = new JScrollPane(tableProducts);
        JPanel buttonPanel = new JPanel();

        editQuantityProducts.addActionListener(this);
        deleteButtonProducts.addActionListener(this);
        createButtonProducts.addActionListener(this);

        buttonPanel.add(editQuantityProducts);
        buttonPanel.add(deleteButtonProducts);
        buttonPanel.add(createButtonProducts);

        tableProducts.setFillsViewportHeight(true);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        this.products = panel;
    }
    private void updateClientsValues() {
    	Object[][] data;
    	DefaultTableModel dataModel;
        List<Client> clients = DBInterface.getAllClients();
        if(clients != null) {
	        Iterator<Client> it = clients.iterator();
	        data = new Object[clients.size()][columnNamesClients.length];
	        int i = 0;
	        while(it.hasNext()) {
	        	Client c = it.next();
	        	data[i][0] = c.getId();
	        	data[i][1] = c.getName();
	        	data[i][2] = c.getNIP();
	        	data[i][3] = c.getAddress();
	        	data[i][4] = c.getEmail();
	        	data[i][5] = c.getPhone();
	        	i++;
	        }
	        dataModel = new DefaultTableModel(data, columnNamesClients);
	        if(tableClients == null)
	        	tableClients = new JTable(dataModel);
	        else {
	        	tableClients.setModel(dataModel);
	        	tableClients.revalidate();
	        }
	        System.out.println("Fetched " + clients.size() + " clients");
        }
        else {
        	data = new Object[1][1];
        	data[0][0] = "Brak klientow";
        	String[] c = {"Brak klientow"};
        	tableClients = new JTable(data, c);
        }
        //String[] columnNamesClients = {"Id", "Nazwa", "NIP", "Adres", "Email", "Telefon"};
        tableClients.setDefaultEditor(Object.class, null);
        tableProducts.revalidate();
    }
    private void clients() {
        JPanel panel = new JPanel();        
        updateClientsValues();
        JScrollPane scrollPane = new JScrollPane(tableClients);
        JPanel buttonPanel = new JPanel();

        addButtonClients.addActionListener(this);
        editButtonClients.addActionListener(this);
        deleteButtonClients.addActionListener(this);

        buttonPanel.add(addButtonClients);
        buttonPanel.add(editButtonClients);
        buttonPanel.add(deleteButtonClients);

        tableClients.setFillsViewportHeight(true);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        this.clients = panel;
    }
    private void updateOrdersValues() {
    	Object[][] data;
    	DefaultTableModel dataModel;
        List<Order> orders = DBInterface.getAllOrders();
        if(orders != null) {
	        Iterator<Order> it = orders.iterator();
	        data = new Object[orders.size()][columnNamesOrders.length];
	        List<OrderStatuses> statuses = DBInterface.getAllOrderStatuses();
	        int i = 0;
	        while(it.hasNext()) {
	        	Order o = it.next();
	        	OrderPrice op = DBInterface.getOrderPriceById(o.getId());
	        	data[i][0] = o.getId();
	        	data[i][1] = o.getClientId();
	        	data[i][2] = statuses.get(o.getStatus()-1).getName();
	        	data[i][3] = o.getDate();
	        	if(op != null)
	        		data[i][4] = op.getPrice();
	        	else data[i][4] = 0;
	        	i++;
	        }
	        dataModel = new DefaultTableModel(data, columnNamesOrders);
	        if(tableOrders == null)
	        	tableOrders = new JTable(dataModel);
	        else {
	        	tableOrders.setModel(dataModel);
	        	tableOrders.revalidate();
	        }
	        System.out.println("Fetched " + orders.size() + " orders");
        }
        else {
        	data = new Object[1][1];
        	data[0][0] = "Brak zamowien";
        	String[] c = {"Brak zamowien"};
        	tableOrders = new JTable(data, c);
        	
        }
        // columnNamesOrders = {"Klient", "Id zamowienia"};
        tableOrders.setDefaultEditor(Object.class, null);
        tableProducts.revalidate();
    }
    private void orders() {

        JPanel panel = new JPanel();
        updateOrdersValues();
        JScrollPane scrollPane = new JScrollPane(tableOrders);
        JPanel buttonPanel = new JPanel();

        payButtonOrders.addActionListener(this);
        createButtonOrders.addActionListener(this);
        detailsButtonOrders.addActionListener(this);

        buttonPanel.add(createButtonOrders);
        buttonPanel.add(payButtonOrders);
        buttonPanel.add(detailsButtonOrders);

        tableOrders.setFillsViewportHeight(true);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        this.orders = panel;
    }
    private void updateInvoicesValues() {
    	List<Invoice> invoices = DBInterface.getAllInvoices();
        Object[][] data;
        DefaultTableModel dataModel;
        if(invoices != null) {
	        Iterator<Invoice> it = invoices.iterator();
	        data = new Object[invoices.size()][columnNamesInvoice.length];
	        int i = 0;
	        while(it.hasNext()) {
	        	Invoice in = it.next();
	        	data[i][0] = in.getId();
	        	data[i][1] = in.getNumber();
	        	data[i][2] = in.getClientName();
	        	data[i][3] = in.getClientAddress();
	        	data[i][4] = in.getIssueDate();
	        	data[i][5] = in.getRealizationDate();
	        }
	        dataModel = new DefaultTableModel(data, columnNamesClients);
	        if(tableInvoice == null)
	        	tableInvoice = new JTable(dataModel);
	        else {
	        	tableInvoice.setModel(dataModel);
	        	tableInvoice.revalidate();
	        }
	        System.out.println("Fetched " + invoices.size() + " invoices");
        }
        else {
        	data = new Object[1][1];
        	data[0][0] = "Brak faktur";
        	String[] c = {"Brak faktur"};
        	tableInvoice = new JTable(data, c);
        }
        //  String[] columnNamesInvoice = {"Id","Number", "Klient", "Adres", "Data wystawieni", "Data Realizacji"};
        tableInvoice.setDefaultEditor(Object.class, null);
        tableProducts.revalidate();
    }
    private void invoices() {
        JPanel panel = new JPanel();
        updateInvoicesValues();
        JScrollPane scrollPane = new JScrollPane(tableInvoice);
        JPanel buttonPanel = new JPanel();

        tableInvoice.setFillsViewportHeight(true);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        this.invoices = panel;
    }

    private void productEditQuantity(int id) {
        JFrame frame =new JFrame();
        String quantity = JOptionPane.showInputDialog(frame,"Podaj nowa ilosc", "Edycja ilosc produktu: " + id, JOptionPane.INFORMATION_MESSAGE);
        if(quantity == null)
        	return;
        try {
        	int q = Integer.parseInt(quantity);
        	DBInterface.updateProductAvailability(id, q);
        }
        catch(Exception e) {}
        			
    }
    private void productDelete(int id) {
        //JFrame frame=new JFrame();
        System.out.print("Delete product id: " + id);
        if(DBInterface.deleteProduct(id)) {
        	//JOptionPane.showMessageDialog(frame,"Usunieto produkt: " + id, "Usuniecie produktu: " + id, JOptionPane.INFORMATION_MESSAGE);
        	System.out.println("Product " + id + " deleted");
        }
        else {
        	//JOptionPane.showMessageDialog(frame,"Nie udalo sie usunac produktu", "Nie udalo sie usunac produktu", JOptionPane.INFORMATION_MESSAGE);
        	System.out.println("Product " + id + " delete FAILED");
        }
        
    }
    private void productCreate() {
        CreateProduct createProduct = new CreateProduct();
    }
    private void clientAdd() {
        CreateClient createClient= new CreateClient();
    }
    private void clientEdit(int id) {
        JFrame frame =new JFrame();
        String newData = JOptionPane.showInputDialog(frame,"Podaj jego dane", "Edycja danych klineta: " + id, JOptionPane.INFORMATION_MESSAGE);
    }
    private void clientDelete(int id) {
        JFrame frame = new JFrame();
        
        if(DBInterface.deleteClient(id)) {
        	JOptionPane.showMessageDialog(frame,"Klient " + id + " zostal usuniety.", "Usuniecie klienta: " + id, JOptionPane.INFORMATION_MESSAGE);
        	updateClientsValues();
        }
        else {
        	JOptionPane.showMessageDialog(frame,"Klient " + id + "nie zostal usuniety.", "Usuniecie klienta zakonczone niepowodzeniem: " + id, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void orderCreate() {
        CreateOrder createOrder = new CreateOrder();
    }
    private void orderPay(int id) {
        JFrame frame =new JFrame();
        String newData = JOptionPane.showInputDialog(frame,"Ile hajsu: ", "Oplacenie: " + id, JOptionPane.INFORMATION_MESSAGE);
    }
    private void orderDetails(int id) {
    	ViewOrderLines v = new ViewOrderLines(id);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        int id = 0;

        if(source == editQuantityProducts) {
            System.out.println("editQuantityProduct");
            if (tableProducts.getSelectedRow() != -1) {
                try {
                	id = Integer.parseInt(tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(), 0).toString());
                	this.productEditQuantity(id);
                }
                catch(Exception ex) {}
            }
        }
        else if(source == deleteButtonProducts) {
            System.out.println("deleteButtonProducts");
            if (tableProducts.getSelectedRow() != -1) {
                try {
                	id = Integer.parseInt(tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(), 0).toString());
                	this.productDelete(id);
                }
                catch(Exception ex) {}
            }
        }
        else if(source == createButtonProducts) {
            System.out.println("createButtonProducts");
            this.productCreate();
        }
        else if(source == addButtonClients) {
            System.out.println("addButtonClients");
            this.clientAdd();
        }
        else if(source == editButtonClients) {
            System.out.println("editButtonClients");
            if (tableClients.getSelectedRow() != -1)
                id = Integer.parseInt(tableClients.getModel().getValueAt(tableClients.getSelectedRow(), 0).toString());
            this.clientEdit(id);
        }
        else if(source == deleteButtonClients) {
            System.out.println("deleteButtonClients");
            if (tableClients.getSelectedRow() != -1) {
                try {
            	id = Integer.parseInt(tableClients.getModel().getValueAt(tableClients.getSelectedRow(), 0).toString());
                this.clientDelete(id);
                }
                catch(Exception ex) {
                	System.out.println("Exception at deleteButtonClients");
                }
            }
                
        }
        else if(source == payButtonOrders) {
            System.out.println("payButtonOrders");
            if (tableOrders.getSelectedRow() != -1)
                id = Integer.parseInt(tableOrders.getModel().getValueAt(tableOrders.getSelectedRow(), 0).toString());
            this.orderPay(id);
        }
        else if(source == createButtonOrders) {
            System.out.println("createButtonOrders");
            this.orderCreate();
        }
        else if(source == detailsButtonOrders) {
        	System.out.println("detailsButtonOrders");
            if (tableOrders.getSelectedRow() != -1) {
                try {
	            	id = Integer.parseInt(tableOrders.getModel().getValueAt(tableOrders.getSelectedRow(), 0).toString());
	            	System.out.println("id = " + id);
	                this.orderDetails(id);
                }
                catch(Exception ex) {
                	System.out.println("Exception at detailsButtonOrders");
                }
            }
        }
        else if(source == makeBackup) {
            System.out.println("make backup");
            DBInterface.createBackup();
        }
        else if(source == loadBackup) {
            System.out.println("load backup");
            DBInterface.restoreFromBackup();
        }
    }
    
    class ViewOrderLines implements ActionListener {
    	String[] columnNames = {"Produkt", "cena jednostkowa", "Ilosc"};
        JTable table;
        
        public ViewOrderLines(int orderId) {
        	List <OrderLine> lines = DBInterface.getAllOrderLinesById(orderId);
        	if(lines == null || lines.size() == 0) {
        		System.out.println("No data");
        		Object[][] data = {{"Brak danych"}};
        		String[] c = {"Brak danych"};
        		table = new JTable(data, c);
        	}
        	else {
        		System.out.println("With data " + lines.size());
        		Iterator<OrderLine> it = lines.iterator();
        		String[] c = {"id", "Cena", "Ilosc"};
        		Object[][] data = new Object[lines.size()][c.length];
        		int i = 0;
        		while(it.hasNext()) {
        			OrderLine o = it.next();
        			data[i][0] = o.getId().getProductId();
        			data[i][1] = o.getPrice();
        			data[i][2] = o.getQuantity();
        			i++;
        		}
        		
        		table = new JTable(new DefaultTableModel(data, c));
                
        	}

            JFrame frame = new JFrame("Szczegoly");
            JPanel quantityPanel = new JPanel();
            quantityPanel.setLayout(new FlowLayout());
            JScrollPane productsPanel = new JScrollPane(table);

            frame.add(quantityPanel);
            frame.add(productsPanel);

            frame.setLayout(new GridLayout(1,2));

            frame.setSize(500,300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        public void actionPerformed(ActionEvent e) {}
    }

    class CreateProduct implements ActionListener  {
        JButton confirm = new JButton("Stworz");
        JButton cancel = new JButton("Anuluj");
        JTextField nameField = new JTextField(16);
        JLabel nameLabel = new JLabel("Nazwa");
        JTextField priceField = new JTextField(16);
        JLabel priceLabel = new JLabel("Cena");
        JTextField taxField = new JTextField(16);
        JLabel taxLabel = new JLabel("Podatek");
        public CreateProduct() {
            confirm.addActionListener(this);
            cancel.addActionListener(this);
            JFrame frame = new JFrame("StwÃ³rz produkt");
            JPanel panel = new JPanel();
            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(priceLabel);
            panel.add(priceField);
            panel.add(taxLabel);
            panel.add(taxField);
            panel.add(confirm);
            panel.add(cancel);
            panel.setLayout(new GridLayout(4,2,20,20));
            frame.add(panel);
            frame.setSize(300,200);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == confirm) {
                try {
                	String name = nameField.getText();
                	int price = Integer.parseInt(priceField.getText());
                	int tax = Integer.parseInt(taxField.getText());
                	if(DBInterface.addNewProduct(name, price, tax)) {
                		System.out.println("Product added: " + name);
                		updateProductsValues();
                	}
                }catch(Exception ex) {
                	
                }
            }
            else if(source == cancel) {
                System.out.println("cancel");
                return;
            }
        }
    }

    class CreateOrder implements ActionListener  {
        JButton confirm = new JButton("Stworz");
        JButton cancel = new JButton("Anuluj");
        JButton addProduct = new JButton("Dodaj produkt");
        JButton removeProduct = new JButton("Usun produkt");

        JTextField clientField = new JTextField(16);
        JLabel clientLabel = new JLabel("Klient");
        String[] columnNames = {"Produkt", "cena jednostkowa", "Ilosc"};
        JTable table;
        
        OrderLine bufforLine;
        
        List<OrderLine> lines;

        public CreateOrder() {
        	lines = new ArrayList<OrderLine>();
        	bufforLine = new OrderLine();
            confirm.addActionListener(this);
            cancel.addActionListener(this);
            addProduct.addActionListener(this);
            removeProduct.addActionListener(this);
            
            Object[][] data  = {{0}};
            String[] col = {"Brak"};
            table = new JTable(new DefaultTableModel(data,col));
            updateTable();
            JFrame frame = new JFrame("Stworz produkt");
            JPanel clientPanel = new JPanel();
            clientPanel.setLayout(new FlowLayout());
            JScrollPane productsPanel = new JScrollPane(table);

            clientPanel.add(clientLabel);
            clientPanel.add(clientField);
            clientPanel.add(addProduct);
            clientPanel.add(removeProduct);

            clientPanel.add(confirm);
            clientPanel.add(cancel);

            frame.add(clientPanel);
            frame.add(productsPanel);

            frame.setLayout(new GridLayout(1,2));

            frame.setSize(500,300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        public void ping() {
        	Product p = DBInterface.getProductById(bufforLine.getId().getProductId());
        	//bufforLine.setPrice(p.getPrice());
        	//bufforLine.setTax(p.getTax());
        	OrderLine newOrderLine = new OrderLine();
        	OrderLinePK newPk = bufforLine.getId();
        	newOrderLine.setId(newPk);
        	newOrderLine.setQuantity(bufforLine.getQuantity());
        	newOrderLine.setPrice(p.getPrice());
        	newOrderLine.setTax(p.getTax());
        	
        	lines.add(newOrderLine);
        	updateTable();
        }
        private void updateTable() {
        	String[] col = {"id", "nazwa", "cena", "ilosc"};
        	Object [][] data = new Object[lines.size()][col.length];
        	Iterator<OrderLine> it = lines.iterator();
        	int i = 0;
        	while(it.hasNext()) {
        		OrderLine ol = it.next();
        		Product p = DBInterface.getProductById(ol.getId().getProductId());
        		data[i][0] = ol.getId().getProductId();
        		data[i][1] = p.getName();
        		data[i][2] = ol.getPrice();
        		data[i][3] = ol.getQuantity();
        		i++;
        	}
        	
        	DefaultTableModel dataModel = new DefaultTableModel(data, col);
        	table.setModel(dataModel);
        	table.revalidate();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == confirm) {
                try {
                	int clientId = Integer.parseInt(clientField.getText());
                	if(lines.size() == 0)
                		return;
                	
                	System.out.println("ClientID: " + clientId);
                	Order newOrder = new Order();
                	newOrder.setClientId(clientId);
                	DBInterface.addNewRecord(newOrder);
                	
                	Iterator<OrderLine> it = lines.iterator();
                	while(it.hasNext()) {
                		OrderLine ol = it.next();
                		OrderLinePK olpk = ol.getId();
                		olpk.setOrderId(newOrder.getId());
                		ol.setId(olpk);
                		System.out.println("oID: " + ol.getId().getOrderId() + " pID: " + ol.getId().getProductId());
                		DBInterface.addNewRecord(ol);
                	}
                	updateOrdersValues();
                	
                }catch(Exception ex) {}
            }
            else if(source == cancel) {
                System.out.println("cancel");
            }
            else if(source == addProduct) {
            	ProductChooser pc = new ProductChooser(bufforLine, this);
            }
            else if(source == removeProduct) {
                if (table.getSelectedRow() != -1) {
                    lines.remove(table.getSelectedRow());
                    updateTable();
                }
            }
        }
    }
    
    class ProductChooser implements ActionListener {
    	JButton confirm = new JButton("Dodaj");
        JButton cancel = new JButton("Anuluj");
        CreateOrder pingu;
        
        JTextField quantityField = new JTextField(16);
        JLabel quantityLabel = new JLabel("Ilosc");
        String[] columnNames = {"Produkt", "cena jednostkowa", "Ilosc"};
        JTable table;
        OrderLine orderLine;
        int orderId;
        
        public ProductChooser(OrderLine orderLine, CreateOrder pingu) {
        	this.orderLine = orderLine;
        	this.pingu = pingu;
        	confirm.addActionListener(this);
        	cancel.addActionListener(this);
        	
        	TableModel dataModel = tableProducts.getModel();
            table = new JTable(dataModel);
            JFrame frame = new JFrame("Wybierz produkt");
            JPanel quantityPanel = new JPanel();
            quantityPanel.setLayout(new FlowLayout());
            JScrollPane productsPanel = new JScrollPane(table);

            quantityPanel.add(quantityLabel);
            quantityPanel.add(quantityField);

            quantityPanel.add(confirm);
            quantityPanel.add(cancel);

            frame.add(quantityPanel);
            frame.add(productsPanel);

            frame.setLayout(new GridLayout(1,2));

            frame.setSize(500,300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    	
    	@Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == confirm) {
                System.out.println("confirm\n");
                try {
                	int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                	int quantity = Integer.parseInt(quantityField.getText());
                	
                	OrderLinePK pk = new OrderLinePK();
                	pk.setProductId(id);
                	orderLine.setId(pk);
                	orderLine.setQuantity(quantity);
                	pingu.ping();
                	
                }
                catch(Exception ex) {
                	
                }
                
            }
            else if(source == cancel) {
                System.out.println("cancel");
            }
        }
    } 

    class CreateClient implements ActionListener  {
        JButton confirm = new JButton("Stworz");
        JButton cancel = new JButton("Anuluj");
        JTextField nipField = new JTextField(16);
        JLabel nipLabel = new JLabel("NIP");
        JTextField nameField = new JTextField(16);
        JLabel nameLabel = new JLabel("Nazwa");
        JTextField addressField = new JTextField(16);
        JLabel addressLabel = new JLabel("Adres");
        JTextField emailField = new JTextField(16);
        JLabel emailLabel = new JLabel("Email");
        JTextField phoneField = new JTextField(16);
        JLabel phoneLabel = new JLabel("Numer telefonu");

        public CreateClient() {
            confirm.addActionListener(this);
            cancel.addActionListener(this);
            JFrame frame = new JFrame("Stworz produkt");
            JPanel panel = new JPanel();
            panel.add(nipLabel);
            panel.add(nipField);

            panel.add(nameLabel);
            panel.add(nameField);

            panel.add(addressLabel);
            panel.add(addressField);

            panel.add(emailLabel);
            panel.add(emailField);

            panel.add(phoneLabel);
            panel.add(phoneField);

            panel.add(confirm);
            panel.add(cancel);
            panel.setLayout(new GridLayout(6,2,10,10));
            frame.add(panel);
            frame.setSize(300,240);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == confirm) {
            	String name = nameField.getText();
            	String address = addressField.getText();
            	String nip = nipField.getText();
            	String phone = phoneField.getText();
            	String email = emailField.getText();
            	
            	if(name == null || name.compareTo("") == 0)
            		return;
            	if(address == null || address.compareTo("") == 0)
            		return;
            	if(nip == null || nip.compareTo("") == 0)
            		return;
            	
            	if(DBInterface.addNewClient(name, address, nip, phone, email)) {
            		System.out.println("Client " + name + " added.");
            		updateClientsValues();
            	}
            	else
            		System.out.println("Client " + name + " not added.");
            }
            else if(source == cancel) {
                System.out.println("cancel");
            }
        }
    }
}