package bd.lab;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame implements ActionListener{
	JButton loginButton;
    JTextField loginField;
    JPasswordField passwordField;
    JFrame frame;
	boolean logged = false;

	public LoginFrame() {
		loginButton = new JButton("Zaloguj siê");
        loginButton.addActionListener(this);
        frame = new JFrame("Sign in");
        JPanel panel = new JPanel();
        loginField = new JTextField(16);
        JLabel loginLabel = new JLabel("Login");
        passwordField = new JPasswordField(16);
        JLabel passwordLabel = new JLabel("Password");
        
        panel.add(loginLabel);
        panel.add(loginField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(loginButton);
        
        panel.setLayout(new GridLayout(3,2,10,10));
        frame.add(panel);
        frame.setSize(300,120);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == loginButton) {
            checkLogin(loginField.getText(), passwordField.getText());
        }
    }
    public void close() {
    	this.frame.dispose();
    }
    private void checkLogin(String name, String password) {
    	try {
    		logged = DBInterface.login(name, password);
        }
        catch(Exception e) {}
    	if (logged) {
    		System.out.println("logged");
    	}
    	else {
    		JOptionPane.showMessageDialog(new JFrame(), "Niepoprawny login lub has³o");
    	}
    }
}
