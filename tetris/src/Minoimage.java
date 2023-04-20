import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Minoimage extends JPanel{
	Image img;
	Minoimage(int k){
		switch(k){
			case 1:
				img = Toolkit.getDefaultToolkit().getImage("img/Imino.png");
				break;
			case 2:
				img = Toolkit.getDefaultToolkit().getImage("img/Omino.png");
				break;
			case 3:
				img = Toolkit.getDefaultToolkit().getImage("img/Tmino.png");
				break;
			case 4:
				img = Toolkit.getDefaultToolkit().getImage("img/Jmino.png");
				break;
			case 5:
				img = Toolkit.getDefaultToolkit().getImage("img/Lmino.png");
				break;
			case 6:
				img = Toolkit.getDefaultToolkit().getImage("img/Smino.png");
				break;
			case 7:
				img = Toolkit.getDefaultToolkit().getImage("img/Zmino.png");
				break;
			default:
				img = Toolkit.getDefaultToolkit().getImage("img/blackmino.png");
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//画像の表示
		g.drawImage(img, 0, 0, this);
	}
}