package canvas.animation;


import canvas.CanvasItem;
import canvas.animation.AnimationItem.MoveEndListener;

//���󶯻���,�����˶��������Է���
public abstract class AnimationItem extends CanvasItem{
	private double targetLeft,targetTop;
	private double sinA,cosA;
	private int moveCount;					//ʣ���ƶ�����, ����0����Ҫ�����ƶ�, ����0����պ��ƶ�����, С��0�����ƶ�����.
	private double speed;					//�����ƶ��ٶ�
	private MoveEndListener mListener;
	public AnimationItem(Double left, Double top) {
		super(left, top);
		moveCount=-1;
	}
	//������һ֡����
	public void nextFrame() {
		if(moveCount>0) {
			//�����ƶ�
			setLeft(getLeft()+cosA*speed);
			setTop(getTop() + sinA*speed);
		}else if(moveCount==0){
			//ǡ���ƶ����
			setLeft(targetLeft);
			setTop(targetTop);
			mListener.onMoveEnd(this);
		}
		moveCount--;
	}
	//�ƶ���ĳ��(ʹ�ö����ƶ�)
	public void moveTo(Double targetLeft,Double targetTop,Double speed,MoveEndListener listener) {
		if(isMoving())
			throw new RuntimeException("item is already moving!");
		this.targetLeft = targetLeft;
		this.targetTop = targetTop;
		this.mListener = listener;
		this.speed = speed;
		Double x = targetLeft - this.getLeft();
		Double y = targetTop - this.getTop();
		Double distance = Math.sqrt(x*x + y*y);
		this.sinA = y/distance;
		this.cosA = x/distance;
		this.moveCount = (int)(distance/speed);
	}
	
	@FunctionalInterface
	public static interface MoveEndListener{
		//��������ʱ�Ļص�����
		public void onMoveEnd(AnimationItem item);
	} 
	public boolean isMoving() {
		return moveCount>0;
	}
}
