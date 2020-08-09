package canvas.animation;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import canvas.CanvasItem;
import canvas.CanvasPanel;

public class AnimationCanvasPanel extends CanvasPanel{
	private ArrayList<AnimationItem> animationItems = new ArrayList<>();
	private boolean isPaused = false;
	private Timer timerRefresh = null,timerFrame = null;
	private int refreshRate;							//每秒画面刷新次数
	private Double speedScale=1.0;								//速度缩放

	@Override
	public void addItem(CanvasItem i) {
		super.addItem(i);
		if(i instanceof AnimationItem) {
			animationItems.add((AnimationItem)i);
		}
	}
	@Override
	public void removeItem(CanvasItem i) {
		// TODO Auto-generated method stub
		super.removeItem(i);
		animationItems.remove(i);
	}
	public AnimationCanvasPanel() {
		setRefreshRate(30);
	}
	private Double refreshCount;
	public void setRefreshRate(int rate) { 
		if(rate > 1000)
			throw new RuntimeException("refresh rate can not be greater than 1000!");
		this.refreshRate = rate;
		schduleRefresh();
	}
	public void schduleRefresh() {
		if(timerFrame != null)
			timerFrame.cancel();
		if(timerRefresh != null) 
			timerRefresh.cancel();
		refreshCount = 0.0;
		//安排更新动画数据
		timerFrame = new Timer();
		timerFrame.schedule(new TimerTask() {
			@Override
			public void run() {
				if(isPaused)return;
				synchronized (this) {
					refreshCount += 100.0/refreshRate*speedScale;
					for(var a:animationItems) {
						for(int i=0;i<refreshCount.intValue();i++)
							a.nextFrame();
						}
					}
					refreshCount -= refreshCount.intValue();
				}
			}
		, 0,1000/refreshRate);
		//安排更新画面
		timerRefresh  = new Timer();
		timerRefresh.schedule(new TimerTask() {
			@Override
			public void run() {
				repaint();
			}
		}, 0, 1000/refreshRate);
	}
	public void setSpeedScale(Double speedScale) {
		if(speedScale < 0)
			throw new RuntimeException("speed scale must be positive number!");
		this.speedScale = speedScale;
	}
	public void pause() {
		isPaused = true;
	}
	public void play() {
		isPaused = false;
	}
}
