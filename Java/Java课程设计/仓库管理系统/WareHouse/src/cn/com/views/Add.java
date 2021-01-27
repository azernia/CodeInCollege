package cn.com.views;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.com.VO.GoodsClassVO;
import cn.com.VO.GoodsVO;
import cn.com.dao.GoodsDAOImpl;
import cn.com.paint.PaintJPanel;

import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Add extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=147,-13
	 */
	private final JTextField textField = new JTextField();
	private JTextField txtGoodsID;
	private JTextField txtGoodsName;
	private JTextField txtGoodsAmount;
	private JTextField txtClassName;
	private JTextField txtStockPrice;
	private JTextField txtStockTime;
	private JTextField txtShipmentTime;
	private JTextField txtAreaID;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label;
	private JTextArea textArea;
	private String date;
	private GoodsDAOImpl gdao;
	/**
	 * Create the frame.
	 */
	public Add() {
		textField.setColumns(10);
		textField.setBackground(SystemColor.menu);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Add.class.getResource("/cn/com/images/warehouse_608px_1208990_easyicon.net.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("text"));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtGoodsID = new JTextField("编号");
		txtGoodsID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGoodsID.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textArea.append("编号:"+txtGoodsID.getText()+"\r\n");
			}
		});
		txtGoodsID.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtGoodsID.setForeground(Color.GRAY);
		txtGoodsID.setColumns(10);
		txtGoodsID.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtGoodsID.setBackground(SystemColor.menu);
		txtGoodsID.setBounds(61, 98, 70, 30);
		contentPane.add(txtGoodsID);
		
		label = new JLabel("",JLabel.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("微软雅黑", Font.BOLD, 15));
		//label.setIcon(new ImageIcon(Add.class.getResource("/cn/com/images/gap.png")));
		label.setOpaque(true); 
		label.setBackground(new Color(238,238,238));
		label.setBounds(0, 0, 684, 30);
		contentPane.add(label);
		
		txtGoodsName = new JTextField("名称");
		txtGoodsName.setForeground(Color.GRAY);
		txtGoodsName.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtGoodsName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGoodsName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textArea.append("名称:"+txtGoodsName.getText()+"\r\n");
			}
		});
		txtGoodsName.setColumns(10);
		txtGoodsName.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtGoodsName.setBackground(SystemColor.menu);
		txtGoodsName.setBounds(131, 98, 70, 30);
		contentPane.add(txtGoodsName);
		
		txtGoodsAmount = new JTextField("进货数量");
		txtGoodsAmount.setForeground(Color.GRAY);
		txtGoodsAmount.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtGoodsAmount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGoodsAmount.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textArea.append("进货数量:"+txtGoodsAmount.getText()+"\r\n");
			}
		});
		txtGoodsAmount.setColumns(10);
		txtGoodsAmount.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtGoodsAmount.setBackground(SystemColor.menu);
		txtGoodsAmount.setBounds(201, 98, 70, 30);
		contentPane.add(txtGoodsAmount);
		
		txtClassName = new JTextField("类别");
		txtClassName.setForeground(Color.GRAY);
		txtClassName.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtClassName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtClassName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textArea.append("类别:"+txtClassName.getText()+"\r\n");
			}
		});
		txtClassName.setColumns(10);
		txtClassName.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtClassName.setBackground(SystemColor.menu);
		txtClassName.setBounds(271, 98, 70, 30);
		contentPane.add(txtClassName);
		
		txtStockPrice = new JTextField("进货价");
		txtStockPrice.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtStockPrice.setForeground(Color.GRAY);
		txtStockPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtStockPrice.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textArea.append("进货价"+txtStockPrice.getText()+"\r\n");
			}
		});
		txtStockPrice.setColumns(10);
		txtStockPrice.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtStockPrice.setBackground(SystemColor.menu);
		txtStockPrice.setBounds(341, 98, 70, 30);
		contentPane.add(txtStockPrice);
		
		txtStockTime = new JTextField("进货时间");
		txtStockTime.setEditable(false);
		txtStockTime.setForeground(Color.GRAY);
		txtStockTime.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtStockTime.setColumns(10);
		txtStockTime.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtStockTime.setBackground(SystemColor.menu);
		txtStockTime.setBounds(411, 98, 70, 30);
		contentPane.add(txtStockTime);
		
		txtShipmentTime = new JTextField("出货时间");
		txtShipmentTime.setForeground(Color.GRAY);
		txtShipmentTime.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtShipmentTime.setEditable(false);
		txtShipmentTime.setColumns(10);
		txtShipmentTime.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtShipmentTime.setBackground(SystemColor.menu);
		txtShipmentTime.setBounds(481, 98, 70, 30);
		contentPane.add(txtShipmentTime);
		
		txtAreaID = new JTextField("区号");
		txtAreaID.setForeground(Color.GRAY);
		txtAreaID.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtAreaID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAreaID.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
				txtStockTime.setText(date);
				textArea.append("进货时间:"+date+"\r\n出货时间:\r\n"+"区号:"+txtAreaID.getText());
			}
		});
		txtAreaID.setColumns(10);
		txtAreaID.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtAreaID.setBackground(SystemColor.menu);
		txtAreaID.setBounds(551, 98, 70, 30);
		contentPane.add(txtAreaID);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoodsVO gvo = new GoodsVO();
				GoodsClassVO gcvo = new GoodsClassVO();
				gvo.setGoodsID(txtGoodsID.getText());
				gvo.setGoodsName(txtGoodsName.getText());
				gvo.setClassName(txtClassName.getText());
				gvo.setAmount(Integer.parseInt(txtGoodsAmount.getText()));
				gvo.setStockPrice(Float.parseFloat(txtStockPrice.getText()));
				gvo.setStockTime(txtStockTime.getText());
				gvo.setShipmentTime(txtShipmentTime.getText());
				gvo.setAreaID(txtAreaID.getText());
				gcvo.setClassName(txtClassName.getText());
//				gcdao = new GoodsClassDAOImpl();
//				gcdao.inserGoodsClass(gcvo);
				gdao = new GoodsDAOImpl();
				boolean b = gdao.goodsInsert(gvo);
				if(b) {
					JOptionPane.showMessageDialog(null, "进货成功");
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Add.class.getResource("/cn/com/images/\u6DFB\u52A0\u6309\u94AE.png")));
		btnNewButton.setBounds(201, 357, 120, 30);
		contentPane.add(btnNewButton);
		
		PaintJPanel panel = new PaintJPanel();
		panel.setBounds(228, 46, 200, 40);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(12, 4, 32, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Add.class.getResource("/cn/com/images/\u8FDB\u8D27blue.png")));
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					Shipment frame = new Shipment();
					frame.setVisible(true);
					Add.this.dispose();
				}
			}
		});
		label_1.setIcon(new ImageIcon(Add.class.getResource("/cn/com/images/\u51FA\u8D27gray.png")));
		label_1.setBounds(82, 4, 32, 32);
		panel.add(label_1);
		
		label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					Search frame = new Search();
					frame.setVisible(true);
					Add.this.dispose();
				}
			}
		});
		label_2.setIcon(new ImageIcon(Add.class.getResource("/cn/com/images/\u641C\u7D22gray.png")));
		label_2.setBounds(152, 4, 32, 32);
		panel.add(label_2);
		
		textArea = new JTextArea();
		textArea.setText(" ");
		textArea.setEditable(false);
		textArea.setBounds(60, 130, 561, 214);
		textArea.setForeground(Color.GRAY);
		textArea.setFont(new Font("微软雅黑", Font.BOLD, 15));
		contentPane.add(textArea);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtGoodsID.setText("编号");
				txtGoodsName.setText("名称");
				txtGoodsAmount.setText("进货数量");
				txtClassName.setText("类别");
				txtStockPrice.setText("进货价");
				txtStockTime.setText("进货时间");
				txtShipmentTime.setText("出货时间");
				txtAreaID.setText("区号");
				textArea.setText("");
			}
		});
		button.setIcon(new ImageIcon(Add.class.getResource("/cn/com/images/\u6E05\u9664.png")));
		button.setBounds(367, 357, 120, 30);
		contentPane.add(button);
		new Thread(new DateThread()).start();
	}
	class DateThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				String date = new SimpleDateFormat("yyyy年mm月dd日 HH:mm:ss").format(new Date());
				label.setText(date);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
