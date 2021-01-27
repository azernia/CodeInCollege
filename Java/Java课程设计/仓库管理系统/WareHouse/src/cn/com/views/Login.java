package cn.com.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.com.VO.ManagerVO;
import cn.com.dao.ManagerDAOImpl;
import cn.com.util.VerificationCode;

import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField valicode;
	private JLabel lblNewLabel_2;
	private VerificationCode vc;
	private JPasswordField txtPwd;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/cn/com/images/warehouse_608px_1208990_easyicon.net.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel.setText("");
			}
		});
		txtUserName.setForeground(Color.GRAY);
		txtUserName.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtUserName.setBackground(UIManager.getColor("menu"));
		txtUserName.setBorder(new EmptyBorder(0,0,0,0));
		txtUserName.setBounds(330, 80, 160, 30);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		valicode = new JTextField();
		valicode.setBackground(Color.WHITE);
		valicode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					boolean flag = isLogin();
					if(flag) {
						Add frame = new Add();
						frame.setVisible(true);
						Login.this.dispose();
					}
				}
			}
		});
		valicode.setColumns(10);
		valicode.setBounds(426, 200, 64, 30);
		contentPane.add(valicode);
		
		vc = new VerificationCode();
		vc.setBounds(330, 200, 94, 30);
		contentPane.add(vc);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = isLogin();
				if(b) {
					Add frame = new Add();
					frame.setVisible(true);
					Login.this.dispose();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/cn/com/images/login.png")));
		btnNewButton.setBackground(UIManager.getColor("text"));
		btnNewButton.setBounds(330, 260, 160, 30);
		contentPane.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/cn/com/images/\u4FE1\u606F.png")));
		lblNewLabel_2.setBounds(40, 60, 260, 260);
		contentPane.add(lblNewLabel_2);
		
		txtPwd = new JPasswordField();
		txtPwd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_1.setText("");
			}
		});
		txtPwd.setForeground(Color.GRAY);
		txtPwd.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtPwd.setBackground(UIManager.getColor("menu"));
		txtPwd.setBorder(new EmptyBorder(0,0,0,0));
		txtPwd.setBounds(330, 140, 160, 30);
		contentPane.add(txtPwd);
		
		lblNewLabel = new JLabel("用户名:");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel.setBounds(330, 60, 72, 18);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("密码:");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_1.setBounds(330, 119, 72, 18);
		contentPane.add(lblNewLabel_1);
	}
	
	public boolean isLogin() {
		String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		String validCode = valicode.getText();
		String pwd = new String(txtPwd.getPassword());
		ManagerVO mvo = new ManagerVO();
		ManagerDAOImpl mdao = new ManagerDAOImpl();
		boolean b = false;
		if(vc.getCode().equals(validCode)) {
//			//正则表达式4-16位可以有字母数字
//			if(pwd.matches("^[a-zA-Z0-9]{4,16}$")) {
				mvo.setManagerName(txtUserName.getText());
				mvo.setManagerPassWord(pwd);
				boolean check = mdao.checkLogin(mvo);
				if(check) {
					b = true;
					mdao.updateLoginDate(date,txtUserName.getText());
					return b;
				}else {
					JOptionPane.showMessageDialog(null, "密码或用户名错误！");
				}
//			}else {
//				JOptionPane.showMessageDialog(null, "密码必须包含字母开头，长度在6-18之间");
//			}
		}else {
			JOptionPane.showMessageDialog(null, "验证码错误");
		}
		return b;
	}
}
