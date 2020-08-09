package canvas.animation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

//负责画圆盘的类
public class Disc extends AnimationItem{
	private Double width,height;

	private Color color;
	public Disc(Double left, Double top, Double width, Double height, Color color) {
		super(left,top);
		this.width = width;
		this.height = height;
		this.color = color;
	}
	@Override
	public void drawItem(Graphics2D gg) {
		//画圆盘
		RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, width, height, height, height);
		gg.setColor(color);
		gg.fill(rect);
	}
	public Double getWidth() {
		return width;
	}
	public Double getHeight() {
		return height;
	}
}

