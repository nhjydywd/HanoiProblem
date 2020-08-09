package hanoi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import canvas.CanvasPanel;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import canvas.animation.AnimationCanvasPanel;

public class HanoiWindow extends JFrame {

	private JPanel contentPane;
	private AnimationCanvasPanel mainPanel;
	private JComboBox comboNum;
	private JButton btnPlay;
	private JComboBox comboFPS;
	private JRadioButton rbtnPlus4;
	private JButton btnPause;
	private JRadioButton rbtnPlus1;
	private JRadioButton rbtnPlus2;
	private JRadioButton rbtnPlus8;



	/**
	 * Create the frame.
	 */
	public HanoiWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		mainPanel = new AnimationCanvasPanel();
		contentPane.add(mainPanel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setPreferredSize(new Dimension(800, 30));
		lblNewLabel_3.setMaximumSize(new Dimension(500, 15));
		lblNewLabel_3.setMinimumSize(new Dimension(500, 15));
		mainPanel.add(lblNewLabel_3);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(rigidArea);
		
		Box verticalBox = Box.createVerticalBox();
		contentPane.add(verticalBox);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 5));
		verticalStrut.setMinimumSize(new Dimension(0, 5));
		verticalBox.add(verticalStrut);
		
		JLabel lblNewLabel = new JLabel("\u5706\u76D8\u6570\u91CF");
		verticalBox.add(lblNewLabel);
		
		comboNum = new JComboBox();
		comboNum.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboNum.setMaximumSize(new Dimension(150, 25));
		verticalBox.add(comboNum);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 5));
		verticalStrut_1.setMinimumSize(new Dimension(0, 5));
		verticalBox.add(verticalStrut_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u6BCF\u79D2\u5E27\u7387");
		verticalBox.add(lblNewLabel_1);
		
		comboFPS = new JComboBox();
		comboFPS.setMaximumSize(new Dimension(150, 25));
		comboFPS.setAlignmentX(0.0f);
		verticalBox.add(comboFPS);
		
		Component verticalStrut_1_1 = Box.createVerticalStrut(20);
		verticalStrut_1_1.setPreferredSize(new Dimension(0, 5));
		verticalStrut_1_1.setMinimumSize(new Dimension(0, 5));
		verticalBox.add(verticalStrut_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u500D\u901F\u64AD\u653E");
		verticalBox.add(lblNewLabel_2);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox);
		
		rbtnPlus1 = new JRadioButton("x1");
		horizontalBox.add(rbtnPlus1);
		
		rbtnPlus2 = new JRadioButton("x2");
		horizontalBox.add(rbtnPlus2);
		
		rbtnPlus4 = new JRadioButton("x4");
		horizontalBox.add(rbtnPlus4);
		
		rbtnPlus8 = new JRadioButton("x8");
		horizontalBox.add(rbtnPlus8);
		
		Component verticalStrut_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_1_1_1.setPreferredSize(new Dimension(0, 5));
		verticalStrut_1_1_1.setMinimumSize(new Dimension(0, 5));
		verticalBox.add(verticalStrut_1_1_1);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox_1);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_2);
		
		btnPlay = new JButton("\u64AD\u653E");
		btnPlay.setPreferredSize(new Dimension(60, 28));
		horizontalBox_1.add(btnPlay);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_1);
		
		btnPause = new JButton("\u6682\u505C");
		btnPause.setPreferredSize(new Dimension(60, 28));
		horizontalBox_1.add(btnPause);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue);
		
		Component glue = Box.createGlue();
		glue.setMaximumSize(new Dimension(200, 32767));
		verticalBox.add(glue);
		
	}

	public AnimationCanvasPanel getMainPanel() {
		return mainPanel;
	}
	public JComboBox getComboNum() {
		return comboNum;
	}
	public JButton getBtnPlay() {
		return btnPlay;
	}
	public JComboBox getComboFPS() {
		return comboFPS;
	}
	public JRadioButton getRbtnPlus4() {
		return rbtnPlus4;
	}
	public JButton getBtnPause() {
		return btnPause;
	}
	public JRadioButton getRbtnPlus1() {
		return rbtnPlus1;
	}
	public JRadioButton getRbtnPlus2() {
		return rbtnPlus2;
	}
	public JRadioButton getRbtnPlus8() {
		return rbtnPlus8;
	}
}
