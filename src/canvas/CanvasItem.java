package canvas;

import java.awt.Graphics2D;

//抽象图形类,定义了可以绘制的图形的属性方法
public abstract class CanvasItem {
	private double left,top;
	public CanvasItem(Double left,Double top) {
		this.setPos(left,top);
	}
	public void setPos(Double left,Double top) {
		this.left = left;
		this.top = top;
	}
	public Double getLeft() {
		return left;
	}
	public Double getTop() {
		return top;
	}
	public void setLeft(Double left) {
		this.left = left;
	}
	public void setTop(Double top) {
		this.top = top;
	}
	public void draw(Graphics2D gg) {
		//变换到相应位置
		gg.translate(left, top);
		drawItem(gg);
	}
	//画出图形
	protected abstract void drawItem(Graphics2D gg) ;
}
