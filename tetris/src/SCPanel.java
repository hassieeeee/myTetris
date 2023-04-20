import java.awt.*;
import javax.swing.*;

class SCPanel extends JPanel{
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Color col = new Color(0,0,0,140);
		g.setColor(col);
		g.fillRect(0,0,120,120);
	}
}