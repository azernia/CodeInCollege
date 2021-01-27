package cn.com.paint;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PaintJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img;
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		try {
			img = ImageIO.read(new File("C:\\Users\\87271\\Desktop\\Java课程设计\\仓库管理系统\\WareHouse\\src\\cn\\com\\images\\长条.png"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
