package bd.lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class MainFrame implements ActionListener {
    JButton editQuantityProducts = new JButton("Edytuj ilość");
    JButton deleteButtonProducts = new JButton("Usuń");
    JButton createButtonProducts = new JButton("Stwórz");
    String[] columnNamesProducts = {"Nazwa", "Id", "Ilość"};
    Object[][] dataProducts = this.getData('p');
    JTable tableProducts = new JTable(dataProducts, columnNamesProducts);

    JButton detailsButtonClients = new JButton("Szczegóły");
    JButton addButtonClients = new JButton("Dodaj klienta");
    JButton editButtonClients = new JButton("Edytuj dane");
    JButton deleteButtonClients = new JButton("Usuń");
    String[] columnNamesClients = {"Nazwa", "Id"};
    Object[][] dataClients = this.getData('c');
    JTable tableClients = new JTable(dataClients, columnNamesClients);

    JButton detailsButtonOrders = new JButton("Szczegóły");
    JButton payButtonOrders = new JButton("Opłać");
    JButton editButtonOrders = new JButton("Edytuj dane");
    JButton createButtonOrders = new JButton("Stwórz");
    String[] columnNamesOrders = {"Klient", "Id zamówienia"};
    Object[][] dataOrders = this.getData('o');
    JTable tableOrders = new JTable(dataOrders, columnNamesOrders);

    JButton detailsButtonInvoice = new JButton("Szczegóły");
    String[] columnNamesInvoice = {"Klient", "Id", "Status", "Kwota"};
    Object[][] dataInvoice = this.getData('i');
    JTable tableInvoice = new JTable(dataInvoice, columnNamesInvoice);

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

        tabbedPane.addTab("Produkty", this.products());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Klienci", this.clients());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);

        tabbedPane.addTab("Zamówienia", this.orders());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);

        tabbedPane.addTab("Faktury", this.invoices());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_4);

        tabbedPane.addTab("Admin", this.admin());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_5);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel admin() {
        JPanel panel = new JPanel();
        if (true) {
            JLabel label = new JLabel("Brak uprawnień robaku");
            panel.add(label);
            return panel;
        }
        JPanel buttonPanel = new JPanel();
        JButton addEmployeeButton = new JButton("Dodaj pracownika");

        buttonPanel.add(addEmployeeButton);
        panel.add(buttonPanel);
        return panel;
    }

    private JPanel products() {
        JPanel panel = new JPanel();
        tableProducts.setDefaultEditor(Object.class, null);
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
        return panel;
    }
    private JPanel clients() {
        JPanel panel = new JPanel();
        tableClients.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(tableClients);
        JPanel buttonPanel = new JPanel();

        detailsButtonClients.addActionListener(this);
        addButtonClients.addActionListener(this);
        editButtonClients.addActionListener(this);
        deleteButtonClients.addActionListener(this);

        buttonPanel.add(detailsButtonClients);
        buttonPanel.add(addButtonClients);
        buttonPanel.add(editButtonClients);
        buttonPanel.add(deleteButtonClients);

        tableClients.setFillsViewportHeight(true);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }
    private JPanel orders() {
        JPanel panel = new JPanel();
        tableOrders.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(tableOrders);
        JPanel buttonPanel = new JPanel();


        detailsButtonOrders.addActionListener(this);
        payButtonOrders.addActionListener(this);
        editButtonOrders.addActionListener(this);
        createButtonOrders.addActionListener(this);

        buttonPanel.add(detailsButtonOrders);
        buttonPanel.add(createButtonOrders);
        buttonPanel.add(editButtonOrders);
        buttonPanel.add(payButtonOrders);

        tableOrders.setFillsViewportHeight(true);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }
    private JPanel invoices() {
        JPanel panel = new JPanel();
        tableInvoice.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(tableInvoice);
        JPanel buttonPanel = new JPanel();
        detailsButtonInvoice.addActionListener(this);
        buttonPanel.add(detailsButtonInvoice);

        tableInvoice.setFillsViewportHeight(true);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private Object[][] getData(char table) {
        switch (table) {
            case 'p':
                Object[][] data1 = {
                    {"Produkt A", 2137, 420},
                    {"Produkt B", 420, 69},
                    {"Produkt C", 69, 1337},
                };
                return data1;
            case 'c':
                Object[][] data2 = {
                        {"Amazon", 1},
                        {"SpaceX", 2},
                        {"GME", 3},
                };
                return data2;
            case 'o':
                Object[][] data3 = {
                        {"Amazon", 2},
                        {"SpaceX", 1},
                        {"GME", 3},
                        {"GME", 7},
                };
                return data3;
            case 'i':
                Object[][] data4 = {
                        {"Amazon", 2, "Opłacona", 123},
                        {"SpaceX", 1, "Nieopłacona", 234},
                        {"GME", 3, "W realizacji", 345},
                        {"GME", 7, "Nie wiem", 456},
                };
                return data4;
            default:
                return null;
        }
    }

    private void productEditQuantity(int id) {
        JFrame frame =new JFrame();
        String newQuantity = JOptionPane.showInputDialog(frame,"Podaj nową ilość", "Edycja ilości produktu: " + id, JOptionPane.INFORMATION_MESSAGE);
    }
    private void productDelete(int id) {
        JFrame frame=new JFrame();
        JOptionPane.showMessageDialog(frame,"Usunięto produkt: " + id, "Usunięcie produktu: " + id, JOptionPane.INFORMATION_MESSAGE);
    }
    private void productCreate() {
        CreateProduct createProduct= new CreateProduct();
    }
    private void clientDetails(int id) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,"Klient: " + id, "Szczegóły klienta: " + id, JOptionPane.INFORMATION_MESSAGE);
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
        JOptionPane.showMessageDialog(frame,"Klient " + id + " został usunięty z bazy", "Usunięcie klienta: " + id, JOptionPane.INFORMATION_MESSAGE);
    }
    private void orderDetails(int id) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,"Zamówienie: " + id,"Szczegóły zamówienia: " + id, JOptionPane.INFORMATION_MESSAGE);
    }
    private void orderCreate() {
        CreateOrder createOrder = new CreateOrder();
    }
    private void orderEdit(int id) {
        JFrame frame =new JFrame();
        String newData = JOptionPane.showInputDialog(frame,"Podaj dane zamówienia", "Edycja zamówienia: " + id, JOptionPane.INFORMATION_MESSAGE);
    }
    private void orderPay(int id) {
        JFrame frame =new JFrame();
        String newData = JOptionPane.showInputDialog(frame,"Ile hajsu: ", "Opłacenie: " + id, JOptionPane.INFORMATION_MESSAGE);
    }
    private void invoiceDetails(int id) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,"Faktura: " + id, "Szczegóły faktury: " + id, JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        int id = 0;

        if(source == editQuantityProducts) {
            System.out.println("addButtonProducts");
            if (tableProducts.getSelectedRow() != -1)
                id = Integer.parseInt(tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(), 1).toString());
            this.productEditQuantity(id);
        }
        else if(source == deleteButtonProducts) {
            System.out.println("deleteButtonProducts");
            if (tableProducts.getSelectedRow() != -1)
                id = Integer.parseInt(tableProducts.getModel().getValueAt(tableProducts.getSelectedRow(), 1).toString());
            this.productDelete(id);
        }
        else if(source == createButtonProducts) {
            System.out.println("createButtonProducts");
            this.productCreate();
        }
        else if(source == detailsButtonClients) {
            System.out.println("detailsButtonClients");
            if (tableClients.getSelectedRow() != -1)
                id = Integer.parseInt(tableClients.getModel().getValueAt(tableClients.getSelectedRow(), 1).toString());
            this.clientDetails(id);
        }
        else if(source == addButtonClients) {
            System.out.println("addButtonClients");
            this.clientAdd();
        }
        else if(source == editButtonClients) {
            System.out.println("editButtonClients");
            if (tableClients.getSelectedRow() != -1)
                id = Integer.parseInt(tableClients.getModel().getValueAt(tableClients.getSelectedRow(), 1).toString());
            this.clientEdit(id);
        }
        else if(source == deleteButtonClients) {
            System.out.println("deleteButtonClients");
            if (tableClients.getSelectedRow() != -1)
                id = Integer.parseInt(tableClients.getModel().getValueAt(tableClients.getSelectedRow(), 1).toString());
            this.clientDelete(id);
        }
        else if(source == detailsButtonOrders) {
            System.out.println("detailsButtonOrders");
            if (tableOrders.getSelectedRow() != -1)
                id = Integer.parseInt(tableOrders.getModel().getValueAt(tableOrders.getSelectedRow(), 1).toString());
            this.orderDetails(id);
        }
        else if(source == payButtonOrders) {
            System.out.println("payButtonOrders");
            if (tableOrders.getSelectedRow() != -1)
                id = Integer.parseInt(tableOrders.getModel().getValueAt(tableOrders.getSelectedRow(), 1).toString());
            this.orderPay(id);
        }
        else if(source == editButtonOrders) {
            System.out.println("editButtonOrders");
            if (tableOrders.getSelectedRow() != -1)
                id = Integer.parseInt(tableOrders.getModel().getValueAt(tableOrders.getSelectedRow(), 1).toString());
            this.orderEdit(id);
        }
        else if(source == createButtonOrders) {
            System.out.println("createButtonOrders");
            this.orderCreate();
        }
        else if(source == detailsButtonInvoice) {
            System.out.println("detailsButtonInvoice");
            if (tableInvoice.getSelectedRow() != -1)
                id = Integer.parseInt(tableInvoice.getModel().getValueAt(tableInvoice.getSelectedRow(), 1).toString());
            this.invoiceDetails(id);
        }
    }

    class CreateProduct implements ActionListener  {
        JButton confirm = new JButton("Stwórz");
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
            JFrame frame = new JFrame("Stwórz produkt");
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
                System.out.println("confirm\n");
            }
            else if(source == cancel) {
                System.out.println("cancel");
            }
        }
    }

    class CreateOrder implements ActionListener  {
        JButton confirm = new JButton("Stwórz");
        JButton cancel = new JButton("Anuluj");
        JButton addProduct = new JButton("Dodaj produkt");
        JButton removeProduct = new JButton("Usuń produkt");

        JTextField clientField = new JTextField(16);
        JLabel clientLabel = new JLabel("Klient");
        String[] columnNames = {"Produkt", "cena jednostkowa", "Ilość"};
        Object[][] data = {{"produkt A", 12, 32},{"preda", 123, 321},{"[dasdsa", 32,1}};
        JTable table = new JTable(data, columnNames);

        public CreateOrder() {
            confirm.addActionListener(this);
            cancel.addActionListener(this);
            addProduct.addActionListener(this);
            removeProduct.addActionListener(this);


            JFrame frame = new JFrame("Stwórz produkt");
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
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == confirm) {
                System.out.println("confirm\n");
            }
            else if(source == cancel) {
                System.out.println("cancel");
            }
            else if(source == addProduct) {
                System.out.println("add");
            }
            else if(source == removeProduct) {
                String product = "";
                if (table.getSelectedRow() != -1)
                    product = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                System.out.println("remove " + product);
            }
        }
    }

    class CreateClient implements ActionListener  {
        JButton confirm = new JButton("Stwórz");
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
            JFrame frame = new JFrame("Stwórz produkt");
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
                System.out.println("confirm\n");
            }
            else if(source == cancel) {
                System.out.println("cancel");
            }
        }
    }
}