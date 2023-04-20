import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

class MinoPanel extends JPanel{
	int i,j,x=0;
	Color col;
	Image img;
	boolean state = true;
	MinoPanel(int k){
		switch(k){
			case 1:
				img = Toolkit.getDefaultToolkit().getImage("img/mino1.png");
				break;
			case 2:
				img = Toolkit.getDefaultToolkit().getImage("img/mino2.png");
				break;
			case 3:
				img = Toolkit.getDefaultToolkit().getImage("img/mino3.png");
				break;
			case 4:
				img = Toolkit.getDefaultToolkit().getImage("img/mino4.png");
				break;
			case 5:
				img = Toolkit.getDefaultToolkit().getImage("img/mino5.png");
				break;
			case 6:
				img = Toolkit.getDefaultToolkit().getImage("img/mino6.png");
				break;
			case 7:
				img = Toolkit.getDefaultToolkit().getImage("img/mino7.png");
				break;
			default:
				img = Toolkit.getDefaultToolkit().getImage("img/minoblack.png");
				break;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//画像の表示
		g.drawImage(img, 0, 0, this);
	}

}