package canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rect extends CanvasItem{
	private Double width,height;
	private Color color;
	public Rect(Double left, Double top, Double width, Double height,Color color) {
		super(left, top);
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	protected void drawItem(Graphics2D gg) {
		// TODO Auto-generated method stub
		Rectangle2D r = new Rectangle2D.Double(0, 0, width, height);
		gg.setColor(color);
		gg.fill(r);
	}
	
}
