package canvas;

import java.awt.Graphics2D;

//����ͼ����,�����˿��Ի��Ƶ�ͼ�ε����Է���
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
		//�任����Ӧλ��
		gg.translate(left, top);
		drawItem(gg);
	}
	//����ͼ��
	protected abstract void drawItem(Graphics2D gg) ;
}
