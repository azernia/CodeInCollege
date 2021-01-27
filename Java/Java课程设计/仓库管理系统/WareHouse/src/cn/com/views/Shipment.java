package cn.com.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shipment extends JFrame {

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
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField txtGoodsName;
	private JTextField txtAmount;
	private JLabel label;
	private JTextField txtShipmentNum;
	private List<GoodsVO> list;
	private GoodsDAOImpl gdao;
	private GoodsVO gvo;
	private JLabel label_3;

	/**
	 * Create the frame.
	 */
	public Shipment() {
		textField.setColumns(10);
		textField.setBackground(SystemColor.menu);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Shipment.class.getResource("/cn/com/images/warehouse_608px_1208990_easyicon.net.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("text"));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		gdao = new GoodsDAOImpl();
		
		txtGoodsID = new JTextField("编号:");
		txtGoodsID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					gvo = new GoodsVO();
					gvo.setGoodsID(txtGoodsID.getText());
					list = gdao.selectShipmentByGoodsID(gvo);
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "该货物不存在");
					}else {
						txtGoodsName.setText(list.get(0).getGoodsName());
						txtAmount.setText(String.valueOf(list.get(0).getAmount()));
					}
					
				}
			}
		});
		txtGoodsID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGoodsID.setText("");
			}
		});
		txtGoodsID.setForeground(Color.GRAY);
		txtGoodsID.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtGoodsID.setColumns(10);
		txtGoodsID.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtGoodsID.setBackground(SystemColor.menu);
		txtGoodsID.setBounds(63, 100, 140, 30);
		contentPane.add(txtGoodsID);
		
		label = new JLabel("",JLabel.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("微软雅黑", Font.BOLD, 15));
		//label.setIcon(new ImageIcon("C:\\Users\\aerer\\Workspaces\\MyEclipse 2016 CI\\demo\\src\\icon\\gap.png"));
		label.setOpaque(true); 
		label.setBackground(new Color(238,238,238));
		label.setBounds(0, 0, 684, 30);
		contentPane.add(label);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int shipmentNum = Integer.parseInt(txtShipmentNum.getText());
				int check = Integer.parseInt(txtAmount.getText());
				if(check == 0 || check < shipmentNum) {
					JOptionPane.showMessageDialog(null, "库存不足无法出货");
				}else {
					int remain = Integer.parseInt(txtAmount.getText()) - Integer.parseInt(txtShipmentNum.getText());
					String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
					gvo.setAmount(remain);
					gdao.updateShipment(date, gvo);
					JOptionPane.showMessageDialog(null, "出货成功");
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Shipment.class.getResource("/cn/com/images/\u51FA\u8D27.png")));
		btnNewButton.setBounds(271, 173, 120, 30);
		contentPane.add(btnNewButton);
		
		PaintJPanel panel = new PaintJPanel();
		panel.setBounds(228, 46, 200, 40);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					Add frame = new Add();
					frame.setVisible(true);
					Shipment.this.dispose();
				}
			}
		});
		lblNewLabel.setBounds(12, 4, 32, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Shipment.class.getResource("/cn/com/images/\u8FDB\u8D27gray.png")));
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Shipment.class.getResource("/cn/com/images/\u51FA\u8D27blue.png")));
		label_1.setBounds(82, 4, 32, 32);
		panel.add(label_1);
		
		label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					Search frame = new Search();
					frame.setVisible(true);
					Shipment.this.dispose();
				}
			}
		});
		label_2.setIcon(new ImageIcon(Shipment.class.getResource("/cn/com/images/\u641C\u7D22gray.png")));
		label_2.setBounds(152, 4, 32, 32);
		panel.add(label_2);
		
		txtGoodsName = new JTextField("名称:");
		txtGoodsName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					gvo = new GoodsVO();
					gvo.setGoodsName(txtGoodsName.getText());
					list = gdao.selectShipmentByGoodsName(gvo);
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "该货物不存在");
					}else {
						txtGoodsID.setText(list.get(0).getGoodsID());
						txtAmount.setText(String.valueOf(list.get(0).getAmount()));
					}
					
				}
			}
		});
		txtGoodsName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGoodsName.setText("");
			}
		});
		txtGoodsName.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtGoodsName.setForeground(Color.GRAY);
		txtGoodsName.setColumns(10);
		txtGoodsName.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtGoodsName.setBackground(SystemColor.menu);
		txtGoodsName.setBounds(203, 100, 140, 30);
		contentPane.add(txtGoodsName);
		
		txtAmount = new JTextField("库存数量:");
		txtAmount.setEditable(false);
		txtAmount.setForeground(Color.GRAY);
		txtAmount.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtAmount.setColumns(10);
		txtAmount.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtAmount.setBackground(SystemColor.menu);
		txtAmount.setBounds(343, 100, 140, 30);
		contentPane.add(txtAmount);
		
		txtShipmentNum = new JTextField("出货数量:");
		txtShipmentNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtShipmentNum.setText("");
			}
		});
		txtShipmentNum.setForeground(Color.GRAY);
		txtShipmentNum.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtShipmentNum.setColumns(10);
		txtShipmentNum.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtShipmentNum.setBackground(SystemColor.menu);
		txtShipmentNum.setBounds(483, 100, 140, 30);
		contentPane.add(txtShipmentNum);
		
		label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					txtGoodsID.setText("编号:");
					txtGoodsName.setText("名称:");
					txtAmount.setText("库存数量:");
					txtShipmentNum.setText("出货数量:");
					Shipment frame = new Shipment();
					frame.setVisible(true);
					Shipment.this.dispose();
				}
			}
		});
		label_3.setIcon(new ImageIcon(Shipment.class.getResource("/cn/com/images/refresh_16px_1158437_easyicon.net.png")));
		label_3.setBounds(637, 114, 16, 16);
		contentPane.add(label_3);
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
