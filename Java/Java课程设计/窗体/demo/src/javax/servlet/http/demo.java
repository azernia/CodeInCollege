package javax.servlet.http;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class demo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demo frame = new demo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public demo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aerer\\Workspaces\\MyEclipse 2016 CI\\demo\\src\\icon\\ico.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(UIManager.getColor("menu"));
		textField.setBounds(330, 80, 160, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(426, 200, 64, 30);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\aerer\\Workspaces\\MyEclipse 2016 CI\\demo\\src\\icon\\login.png"));
		btnNewButton.setBackground(UIManager.getColor("text"));
		btnNewButton.setBounds(330, 260, 160, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\u9A8C\u8BC1\u7801");
		lblNewLabel.setBackground(UIManager.getColor("menu"));
		lblNewLabel.setBounds(330, 200, 90, 30);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\aerer\\Workspaces\\MyEclipse 2016 CI\\demo\\src\\icon\\\u4FE1\u606F.png"));
		lblNewLabel_2.setBounds(40, 60, 260, 260);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\aerer\\Workspaces\\MyEclipse 2016 CI\\demo\\src\\icon\\\u95F4\u9694.png"));
		lblNewLabel_1.setBackground(Color.GREEN);
		lblNewLabel_1.setBounds(0, 0, 524, 30);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(UIManager.getColor("menu"));
		textField_1.setBounds(330, 140, 160, 30);
		contentPane.add(textField_1);
	}
}
