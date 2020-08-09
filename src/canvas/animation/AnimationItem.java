package canvas.animation;


import canvas.CanvasItem;
import canvas.animation.AnimationItem.MoveEndListener;

//抽象动画类,定义了动画的属性方法
public abstract class AnimationItem extends CanvasItem{
	private double targetLeft,targetTop;
	private double sinA,cosA;
	private int moveCount;					//剩余移动次数, 大于0代表还要继续移动, 等于0代表刚好移动结束, 小于0代表移动结束.
	private double speed;					//动画移动速度
	private MoveEndListener mListener;
	public AnimationItem(Double left, Double top) {
		super(left, top);
		moveCount=-1;
	}
	//计算下一帧数据
	public void nextFrame() {
		if(moveCount>0) {
			//进行移动
			setLeft(getLeft()+cosA*speed);
			setTop(getTop() + sinA*speed);
		}else if(moveCount==0){
			//恰好移动完毕
			setLeft(targetLeft);
			setTop(targetTop);
			mListener.onMoveEnd(this);
		}
		moveCount--;
	}
	//移动到某点(使用动画移动)
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
		//动画结束时的回调函数
		public void onMoveEnd(AnimationItem item);
	} 
	public boolean isMoving() {
		return moveCount>0;
	}
}
