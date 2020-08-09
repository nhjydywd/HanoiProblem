package canvas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

//画布用于绘制图形
public class CanvasPanel extends JPanel{
	private ArrayList<CanvasItem> items = new ArrayList<>();
	public void addItem(CanvasItem i) {
		items.add(i);
	}
	public void removeItem(CanvasItem i) {
		items.remove(i);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D)g;
		for(var i:items) {
			Graphics2D temp = (Graphics2D) gg.create();
			i.draw(temp);
		}
	}
}
