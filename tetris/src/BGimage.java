import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

class BGimage extends JPanel{
	Image img;
	BGimage(){
		img = Toolkit.getDefaultToolkit().getImage("img/テトリス背景.png");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//画像の表示
		g.drawImage(img, 0, 0, this);
	}
}