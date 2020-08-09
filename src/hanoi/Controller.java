package hanoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import canvas.Rect;
import canvas.animation.AnimationCanvasPanel;
import canvas.animation.AnimationItem;
import canvas.animation.Disc;

public class Controller {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HanoiWindow frame = new HanoiWindow();
					frame.setVisible(true);
					Controller c = new Controller(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private HanoiWindow window;
	private AnimationCanvasPanel aPanel;
	public Controller(HanoiWindow window) {
		this.window = window;
		this.aPanel = window.getMainPanel();
		setupPillars(30.0, 400.0);
		initWindow();
		window.getRbtnPlus1().setSelected(true);
		window.getComboFPS().setSelectedIndex(2);
		window.getComboNum().setSelectedIndex(0);
		aPanel.play();
	}

	private Double pillarWidth,pillarHeight;
	private ArrayList<ArrayList<Disc>> pillars = new ArrayList<>();
	//显示柱子
	public void setupPillars(Double pillarWidth,Double pillarHeight) {
		this.pillarWidth = pillarWidth;
		this.pillarHeight = pillarHeight;
		//放置三根柱子
		for(int i=0;i<3;i++) {
			Double left = getPillarLeft(i);
			Double top = getPillarTop();
			aPanel.addItem(new Rect(left,top,pillarWidth,pillarHeight,nextColor()));
			pillars.add(new ArrayList<>());
		}
	}
	private static final Color[] colors = {Color.blue,Color.green,Color.yellow,Color.red,Color.cyan};
	private static int colorIndex = 0;
	private Color nextColor() {
		if(colorIndex >= colors.length) {
			colorIndex = 0;
		}
		return colors[colorIndex++];
	}
	private Double getPillarTop() {
		Dimension size = aPanel.getSize();
		Double height = size.getHeight();
		return height - this.pillarHeight;
	}
	private Double getPillarLeft(int number) {
		Dimension size = aPanel.getSize();
		Double width = size.getWidth();
		Double baseX = width/3*number;
		return (width/3-pillarWidth)/2 + baseX;
	}
	private Disc getPillarTopDisc(int number) {
		var a = pillars.get(number);
		if(a.isEmpty()) {
			return null;
		}else {
			return a.get(a.size()-1);
		}
	}
	private int getContainerPillarNum(Disc d) {
		for(int i=0;i<pillars.size();i++) {
			if(pillars.get(i).contains(d))
				return i;
		}
		return -1;
	}
	//产生圆盘
	public void setupDiscs(int n) {
		//清除已有的圆盘
		for(var a:pillars) {
			while(a.size()>0) {
				Disc d = a.get(0);
				a.remove(0);
				aPanel.removeItem(d);
			}
		}
		Double step = 25.0;
		Double width = 60.0+(n-1)*step,height = 30.0;
		var a = pillars.get(0);
		for(int i=0;i<n;i++) {
			Double centerX = getPillarLeft(0) + pillarWidth/2;
			Disc d = getPillarTopDisc(0);
			Double bottom = d==null?aPanel.getSize().getHeight():d.getTop();
			Double left = centerX - width/2;
			Double top = bottom - height;
			Disc disc = new Disc(left,top,width,height,nextColor());
			a.add(disc);
			aPanel.addItem(disc);
			width-=step;
		}
	}
	private ArrayList<HanoiSolver> solution;
	private Double discSpeed = 8.0;
	private boolean jobDone = false;
	public void showSolutionAnimation() {
		if(solution.isEmpty()) {
			//所有圆盘已经搬运完毕
			System.out.println("hanoi job done!");
			jobDone = true;
			return;
		}
		jobDone = false;
		HanoiSolver s = solution.get(0);
		System.out.println(s);
		solution.remove(s);
		int from = (s.getFrom()-1 + 3)%3;
		int to = (s.getTo()-1 + 3)%3;
		Disc d = getPillarTopDisc(from);
		pillars.get(from).remove(d);
		pillars.get(to).add(d);
		Double left = d.getLeft();
		Double top = getPillarTop() - 60;

		d.moveTo(left, top, discSpeed, (item)->{moveUpFinish(item);});
	}
	private void moveUpFinish(AnimationItem d) {
		Disc disc = (Disc)d;
		int num = getContainerPillarNum(disc);
		Double left = getPillarLeft(num) + pillarWidth/2 -disc.getWidth()/2;
		Double top = disc.getTop();
		disc.moveTo(left, top, discSpeed, (item)->{moveHoriFinish(item);});
		
	}
	private void moveHoriFinish(AnimationItem d) {
		Disc disc = (Disc)d;
		int num = getContainerPillarNum(disc);
		Double left = disc.getLeft();
		var p = pillars.get(num);
		Double bottom = p.size()>1?p.get(p.size()-2).getTop():aPanel.getSize().getHeight();
		Double top = bottom - disc.getHeight();
		disc.moveTo(left, top, discSpeed, (item)->{showSolutionAnimation();});//拿起下一个圆盘
	}
	private void initWindow() {
		// TODO Auto-generated method stub
		window.getBtnPlay().addActionListener((e)->{
			aPanel.play();
			if(jobDone) {
				//动画已放完,再放一遍
				int from = -1;
				while(from++>-2) 
					if(pillars.get(from).size()>0)
						break;
				int to = (from+1)%3;
				int temp = (from+2)%3;
				Integer n = (Integer)window.getComboNum().getSelectedItem();
				solution = HanoiSolver.solve(n, from+1, to+1, temp+1);
				showSolutionAnimation();
			}
		});
		window.getBtnPause().addActionListener((e)->{aPanel.pause();});
		window.getComboNum().setModel(new DefaultComboBoxModel(new Integer[] {4,5,6,7}));
		window.getComboNum().addActionListener((e)->{
			System.out.println("action perform!");
			aPanel.pause();
			Integer n = (Integer)window.getComboNum().getSelectedItem();
			setupDiscs(n);
			solution = HanoiSolver.solve(n, 1, 2, 3);
			showSolutionAnimation();
		});
		window.getComboFPS().setModel(new DefaultComboBoxModel(new Integer[] {240,120,60,45,30,15,10,5}));
		window.getComboFPS().addActionListener((e)->{
			System.out.println("action perform!");
			Integer n = (Integer)window.getComboFPS().getSelectedItem();
			aPanel.setRefreshRate(n);
		});
		//点击缩放按钮,选择缩放速度
		ActionListener speedListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.getRbtnPlus1().setSelected(false);
				window.getRbtnPlus2().setSelected(false);
				window.getRbtnPlus4().setSelected(false);
				window.getRbtnPlus8().setSelected(false);
				JRadioButton b = (JRadioButton)e.getSource();
				b.setSelected(true);
				int n = Integer.parseInt(b.getText().replace("x", ""));
				aPanel.setSpeedScale(new Double(n));
			}
		};
		window.getRbtnPlus1().addActionListener(speedListener);
		window.getRbtnPlus2().addActionListener(speedListener);
		window.getRbtnPlus4().addActionListener(speedListener);
		window.getRbtnPlus8().addActionListener(speedListener);
		window.getRbtnPlus1().setSelected(true);
	}
}
