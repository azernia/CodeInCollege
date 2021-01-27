package cn.com.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import cn.com.VO.GoodsVO;
import cn.com.dao.GoodsDAOImpl;
import cn.com.paint.PaintJPanel;
import cn.com.util.DatePicker;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Search extends JFrame {

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
	private JTextField txtAmount;
	private JTextField txtGoodsClass;
	private JTextField txtStockPrice;
	private JTextField txtAreaID;
	private JTextField stockTime;
	private JTextField shipmentTime;
	private JLabel lblNewLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label;
	private GoodsDAOImpl gdao;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public Search() {
		gdao = new GoodsDAOImpl();
		textField.setColumns(10);
		textField.setBackground(SystemColor.menu);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Search.class.getResource("/cn/com/images/warehouse_608px_1208990_easyicon.net.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("text"));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtGoodsID = new JTextField("编号:");
		txtGoodsID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					List<GoodsVO> list = gdao.selectByGoodsID(txtGoodsID.getText());
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "该编号不存在");
					}else {
						refreshTable(list);
					}
				}
			}
		});
		txtGoodsID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGoodsID.setText("");
				txtGoodsName.setText("名称:");
				txtAmount.setText("库存数量:");
				txtGoodsClass.setText("类别:");
				txtStockPrice.setText("进货价:");
				txtAreaID.setText("区号:");
			}
		});
		txtGoodsID.setForeground(Color.GRAY);
		txtGoodsID.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtGoodsID.setColumns(10);
		txtGoodsID.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtGoodsID.setBackground(SystemColor.menu);
		txtGoodsID.setBounds(14, 98, 70, 30);
		contentPane.add(txtGoodsID);
		
		label = new JLabel("",JLabel.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("微软雅黑", Font.BOLD, 15));
		//label.setIcon(new ImageIcon("C:\\Users\\aerer\\Workspaces\\MyEclipse 2016 CI\\demo\\src\\icon\\gap.png"));
		label.setOpaque(true); 
		label.setBackground(new Color(238,238,238));
		label.setBounds(0, 0, 684, 30);
		contentPane.add(label);
		
		txtGoodsName = new JTextField("名称:");
		txtGoodsName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					List<GoodsVO> list = gdao.selectByGoodsName(txtGoodsName.getText());
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "该货物不存在");
					}else {
						refreshTable(list);
					}
				}
			}
		});
		txtGoodsName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGoodsName.setText("");
				txtGoodsID.setText("编号:");
				txtAmount.setText("库存数量:");
				txtGoodsClass.setText("类别:");
				txtStockPrice.setText("进货价:");
				txtAreaID.setText("区号:");
			}
		});
		txtGoodsName.setForeground(Color.GRAY);
		txtGoodsName.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtGoodsName.setColumns(10);
		txtGoodsName.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtGoodsName.setBackground(SystemColor.menu);
		txtGoodsName.setBounds(84, 98, 70, 30);
		contentPane.add(txtGoodsName);
		
		txtAmount = new JTextField("库存数量:");
		txtAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					List<GoodsVO> list = gdao.selectByAmount(Integer.parseInt(txtAmount.getText()));
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "没有处在该库存数量的货物");
					}else {
						refreshTable(list);
					}
				}
			}
		});
		txtAmount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAmount.setText("");
				txtGoodsID.setText("编号:");
				txtGoodsName.setText("名称:");
				txtGoodsClass.setText("类别:");
				txtStockPrice.setText("进货价:");
				txtAreaID.setText("区号:");
			}
		});
		txtAmount.setForeground(Color.GRAY);
		txtAmount.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtAmount.setColumns(10);
		txtAmount.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtAmount.setBackground(SystemColor.menu);
		txtAmount.setBounds(154, 98, 70, 30);
		contentPane.add(txtAmount);
		
		txtGoodsClass = new JTextField("商品类别:");
		txtGoodsClass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					List<GoodsVO> list = gdao.selectByGoodsClass(txtGoodsClass.getText());
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "该货物类别不存在");
					}else {
						refreshTable(list);
					}
				}
			}
		});
		txtGoodsClass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGoodsClass.setText("");
				txtGoodsID.setText("编号:");
				txtGoodsName.setText("名称:");
				txtAmount.setText("库存数量:");
				txtStockPrice.setText("进货价:");
				txtAreaID.setText("区号:");
			}
		});
		txtGoodsClass.setForeground(Color.GRAY);
		txtGoodsClass.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtGoodsClass.setColumns(10);
		txtGoodsClass.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtGoodsClass.setBackground(SystemColor.menu);
		txtGoodsClass.setBounds(224, 98, 70, 30);
		contentPane.add(txtGoodsClass);
		
		txtStockPrice = new JTextField("进货价:");
		txtStockPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					List<GoodsVO> list = gdao.selectByStockPrice(Float.parseFloat(txtStockPrice.getText()));
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "该进货价的商品不存在");
					}else {
						refreshTable(list);
					}
				}
			}
		});
		txtStockPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtStockPrice.setText("");
				txtGoodsID.setText("编号:");
				txtGoodsName.setText("名称:");
				txtAmount.setText("库存数量:");
				txtGoodsClass.setText("类别:");
				txtAreaID.setText("区号:");
			}
		});
		txtStockPrice.setForeground(Color.GRAY);
		txtStockPrice.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtStockPrice.setColumns(10);
		txtStockPrice.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtStockPrice.setBackground(SystemColor.menu);
		txtStockPrice.setBounds(294, 98, 70, 30);
		contentPane.add(txtStockPrice);
		
		
		
		
		txtAreaID = new JTextField("区号:");
		txtAreaID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					List<GoodsVO> list = gdao.selectByAreaID(txtAreaID.getText());
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "该区号的货物不存在");
					}else {
						refreshTable(list);
					}
				}
			}
		});
		txtAreaID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAreaID.setText("");
				txtGoodsID.setText("编号:");
				txtGoodsName.setText("名称:");
				txtAmount.setText("库存数量:");
				txtGoodsClass.setText("类别:");
				txtStockPrice.setText("进货价:");
			}
		});
		txtAreaID.setForeground(Color.GRAY);
		txtAreaID.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtAreaID.setColumns(10);
		txtAreaID.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtAreaID.setBackground(SystemColor.menu);
		txtAreaID.setBounds(604, 98, 70, 30);
		contentPane.add(txtAreaID);
		
		stockTime = new DatePicker();
		stockTime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					List<GoodsVO> list = gdao.selectByStockTime(stockTime.getText());
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "该货物不存在");
					}else {
						refreshTable(list);
					}
				}
			}
		});
		stockTime.setFont(new Font("微软雅黑", Font.BOLD, 14));
		stockTime.setForeground(Color.GRAY);
		stockTime.setColumns(10);
		stockTime.setBackground(SystemColor.menu);
		stockTime.setBorder(new EmptyBorder(0, 0, 0, 0));
		stockTime.setBounds(364, 98, 120, 30);
		contentPane.add(stockTime);
		
		shipmentTime = new DatePicker();
		shipmentTime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					List<GoodsVO> list = gdao.selectByShipmentTime(shipmentTime.getText());
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "该货物不存在");
					}else {
						refreshTable(list);
					}
				}
			}
		});
		shipmentTime.setForeground(Color.GRAY);
		shipmentTime.setFont(new Font("微软雅黑", Font.BOLD, 14));
		shipmentTime.setColumns(10);
		shipmentTime.setBackground(SystemColor.menu);
		shipmentTime.setBorder(new EmptyBorder(0, 0, 0, 0));
		shipmentTime.setBounds(484, 99, 120, 30);
		contentPane.add(shipmentTime);
		
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
					Search.this.dispose();
				}
			}
		});
		lblNewLabel.setBounds(12, 4, 32, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Search.class.getResource("/cn/com/images/\u8FDB\u8D27gray.png")));
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					Shipment frame = new Shipment();
					frame.setVisible(true);
					Search.this.dispose();
				}
			}
		});
		label_1.setIcon(new ImageIcon(Search.class.getResource("/cn/com/images/\u51FA\u8D27gray.png")));
		label_1.setBounds(82, 4, 32, 32);
		panel.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Search.class.getResource("/cn/com/images/\u641C\u7D22blue.png")));
		label_2.setBounds(152, 4, 32, 32);
		panel.add(label_2);
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(new Color(238,238,238));
		scrollPane.setBorder(null);
		scrollPane.setBounds(14, 138, 660, 300);
		contentPane.add(scrollPane);
		
		List<GoodsVO> list = new GoodsDAOImpl().selectAll();
		
		table = new JTable();
		table.setEnabled(false);
		table.setBorder(null);
		table.setForeground(Color.GRAY);
		table.setShowGrid(false);
		table.setFont(new Font("微软雅黑", Font.BOLD, 15));
		table.setBackground(new Color(238,238,238));
		refreshTable(list);
		FitTableColumns(table);
		
		new Thread(new DateThread()).start();
	}
	public void refreshTable(List<GoodsVO> list) {
		TableModel dataModel = new AbstractTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] titles = {"编号","名称","类别","进货价","进货时间","出货时间","区号","库存数量"};
			public int getColumnCount(){
				 return titles.length; 
			}
			public String getColumnName(int column) {
				return titles[column];
			}
			public int getRowCount() { 
				return list.size();
			}
			public Object getValueAt(int row, int col) {
				GoodsVO gvo = list.get(row);
				if(col==0) {
					return gvo.getGoodsID();
				}else if(col==1) {
					return gvo.getGoodsName();
				}else if(col==2) {
					return gvo.getClassName();
				}else if(col==3) {
					return gvo.getStockPrice();
				}else if(col==4) {
					return gvo.getStockTime();
				}else if(col==5) {
					return gvo.getShipmentTime();
				}else if(col==6) {
					return gvo.getAreaID();
				}
				return gvo.getAmount();
			}
		};
		table.setModel(dataModel);
		FitTableColumns(table);
		scrollPane.setViewportView(table);
	}
	
	public void FitTableColumns(JTable myTable){
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while(columns.hasMoreElements()){
			TableColumn column = (TableColumn)columns.nextElement();
		    int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
		    int width = (int)myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
		    for(int row = 0; row<rowCount; row++){
		    	int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
		        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
		        width = Math.max(width, preferedWidth);
		    }
		    header.setResizingColumn(column); // 此行很重要
		    column.setWidth(width+myTable.getIntercellSpacing().width);
		}
	}
	
	/**
	 * 时间刷新线程
	 * @author 87271
	 *
	 */
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
