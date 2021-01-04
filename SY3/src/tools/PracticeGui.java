
package tools;

import model.Exercise;
import model.Formula;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PracticeGui extends JFrame {

	private int winWidth = 800;//宽度

	private int winHeight = 400;//高度

	private int formulaNum = 25;//算式数量

	private int row = 5;//列数

	private int formulaWidth = 100;//算式长度

	private int answerWidth = 30;//答案长度

	private int formulaAnswerHeight = 20;//算式和答案的高度

	private JTextField[] displayFormula;//显示算式数组

	private JTextField[] displayAnswer;//显示答案数组

	private JPanel jp;//面板容器

	private Exercise exercise;//习题集


	private int rightNum;//正确答案个数

	private int errorNum;//错误答案个数

	private ButtonGroup typeBtnGroup = new ButtonGroup();//类型按钮组，控制单选

	private ButtonGroup numBtnGroup = new ButtonGroup();//数量按钮组，控制单选

	private int currentPage;//当前页

	private int pageNum;//页数

	private JButton previousPage;//上一页

	private JButton nextPage;//下一页


	private JLabel labelCurrent;//当前显示

	private JLabel labelTotal;//显示总共页数

	private boolean submit=false;//

	JLabel labelStatus;

	JLabel labelStat;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PracticeGui frame = new PracticeGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PracticeGui() {

		//设置窗体标题
		setTitle("加减法运算");

		//设置窗体关闭方式
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, winWidth, winHeight);

		//定制新面板替代旧面板
		jp = new JPanel();
		this.setContentPane(jp);

		//取消窗体布局管理器
		jp.setLayout(null);

		//上左下右逆时针方法依次指定四个方向距离边框的空白像素
		jp.setBorder(new EmptyBorder(10, 10, 10, 10));

		//设置颜色
		jp.setBackground(Color.pink);


		//创建“生成习题”按钮，设置大小，位置
		JButton generateBtn = new JButton("重新生成习题");
		generateBtn.setBounds(390, 10, 110, 30);
		jp.add(generateBtn);

		//利用匿名内部类实为“生成习题”按钮增加监听事件
		generateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//arg0不可随意改变
				generateExercise();
				updateComponets();//重新生成后在界面上显示
			}
		});

		//创建“提交习题”按钮，设置大小，位置
		JButton submitBtn = new JButton("提交答案");
		submitBtn.setBounds(650, 10, 110, 30);
		jp.add(submitBtn);

		//利用匿名内部类实为“提交答案”按钮增加监听事件
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//e不可随意改变
				submit = true;
				judgeAnswer();//提交习题，判断正确性
			}
		});

		//创建“清空答案”按钮，设置大小，位置
		JButton clearBtn = new JButton("清空答案");
		clearBtn.setBounds(520, 10, 110, 30);
		jp.add(clearBtn);

		//利用匿名内部类实为“清空答案”按钮增加监听事件
		clearBtn.addActionListener(new ActionListener() { //清空答案
			public void actionPerformed(ActionEvent e) {
				submit = false;
				clearAnswers();
			}
		});

		//加入菜单栏，设置大小，位置
		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.setBounds(10,10,245,30);
		jp.add(jMenuBar);

		//一级菜单,类型
		JMenu jMenuType = new JMenu();
		jMenuType.setText("生成不同类型的习题");
		jMenuBar.add(jMenuType);

		//菜单选项
		JRadioButtonMenuItem plusType = new JRadioButtonMenuItem();
		plusType.setSelected(true);
		plusType.setText("加法");
		typeBtnGroup.add(plusType);
		jMenuType.add(plusType);

		JRadioButtonMenuItem minusType = new JRadioButtonMenuItem();
		minusType.setSelected(true);
		minusType.setText("减法");
		typeBtnGroup.add(minusType);
		jMenuType.add(minusType);

		JRadioButtonMenuItem mixType = new JRadioButtonMenuItem();
		mixType.setSelected(true);
		mixType.setText("混合");
		typeBtnGroup.add(mixType);
		jMenuType.add(mixType);


		//一级菜单，数量
		JMenu jMenuNum = new JMenu();
		jMenuNum.setText("生成不同数量的习题");
		jMenuBar.add(jMenuNum);

		//菜单选项
		JRadioButtonMenuItem num25 = new JRadioButtonMenuItem();
		num25.setSelected(true);
		num25.setText("25");
		numBtnGroup.add(num25);
		jMenuNum.add(num25);

		JRadioButtonMenuItem num50 = new JRadioButtonMenuItem();
		num50.setSelected(true);
		num50.setText("50");
		numBtnGroup.add(num50);
		jMenuNum.add(num50);

		JRadioButtonMenuItem num75 = new JRadioButtonMenuItem();
		num75.setSelected(true);
		num75.setText("75");
		numBtnGroup.add(num75);
		jMenuNum.add(num75);

		JRadioButtonMenuItem num100 = new JRadioButtonMenuItem();
		num100.setSelected(true);
		num100.setText("100");
		numBtnGroup.add(num100);
		jMenuNum.add(num100);


		//菜单栏监听事件
		plusType.addActionListener(new ActionListener() { //选择仅加法
			public void actionPerformed(ActionEvent e) {
				int length = exercise.getLength();
				currentPage=1;
				exercise.generatePlusExercise(length);
				updateComponets();
			}
		});
		minusType.addActionListener(new ActionListener() { //选择仅减法
			public void actionPerformed(ActionEvent e) {
				int length = exercise.getLength();
				currentPage=1;
				exercise.generateMinusExercise(length);
				updateComponets();
			}
		});
		mixType.addActionListener(new ActionListener() { //选择加减混合
			public void actionPerformed(ActionEvent e) {
				int length = exercise.getLength();
				currentPage=1;
				exercise.generateExercise(length);
				updateComponets();
			}
		});

		num25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exercise.generateType(25);
				currentPage=1;
				updateComponets();
			}
		});


		num50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exercise.generateType(50);
				currentPage=1;
				updateComponets();
			}
		});
		num75.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exercise.generateType(75);
				currentPage=1;
				updateComponets();
			}
		});
		num100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exercise.generateType(100);
				currentPage=1;
				updateComponets();
			}
		});

		previousPage = new JButton();
		previousPage.setBounds(6,100,40,100);
		previousPage.setText("<html>上<br>一<br>页</html>");
		previousPage.setFocusPainted(false);
		jp.add(previousPage);

		nextPage = new JButton();
		nextPage.setBounds(744,100,40,100);
		nextPage.setText("<html>下<br>一<br>页</html>");
		nextPage.setFocusPainted(false);
		jp.add(nextPage);


		previousPage.addActionListener(new ActionListener() { //上一页
			public void actionPerformed(ActionEvent e) {
				prePage();
			}
		});
		nextPage.addActionListener(new ActionListener() { //下一页
			public void actionPerformed(ActionEvent e) {
				nextPage();
			}
		});

		//下面为做题信息，描述总题述，正确率等
		labelStatus = new JLabel();
		//设置文本信息
		labelStatus.setText("");
		//定义位置，大小
		labelStatus.setBounds(50, 300, 690, 60);

		labelStatus.setFont(new Font("宋体", Font.PLAIN, 12));
		//将控件加入面板

		jp.add(labelStatus);
		//增加分割线
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 80, 800, 10);
		jp.add(separator);

		//下面为做题信息，描述总题述，正确率等
		labelStat = new JLabel();
		//设置文本信息
		labelStat.setText("");
		//定义位置，大小
		labelStat .setBounds(200, 300, 690, 60);
		labelStat.setFont(new Font("宋体", Font.PLAIN, 12));

		//将控件加入面板
		jp.add(labelStat);


		//显示页数信息
		JLabel label1 = new JLabel("第");
		label1.setBounds(316, 260, 23, 23);
		jp.add(label1);
		labelCurrent = new JLabel("1");
		labelCurrent.setBounds(346, 260, 23, 23);
		jp.add(labelCurrent);
		JLabel label2 = new JLabel("页");
		label2.setBounds(376, 260, 23, 23);
		jp.add(label2);
		JLabel label3 = new JLabel("共");
		label3.setBounds(406, 260, 23, 23);
		jp.add(label3);
		labelTotal = new JLabel("1");
		labelTotal.setBounds(436, 260, 23, 23);
		jp.add(labelTotal);
		JLabel label4 = new JLabel("页");
		label4.setBounds(466, 260, 23, 23);
		jp.add(label4);

		initComponets();

		updateComponets();

	}

	private void generateExercise(){ //重新生成题目
		int length = exercise.getLength();
		exercise.generateType(length);
		updateComponets();
	}
	private void judgeAnswer(){ //提交答案，判题
		this.submit = true;
		getAnswers(this.currentPage);
		updateComponets();
	}
	private void prePage(){ //上翻一页
		getAnswers(this.currentPage);
		if(this.currentPage == this.pageNum) this.leaveEnd();
		if(--currentPage == 1) this.reachBegin();
		this.labelCurrent.setText(String.valueOf(currentPage));
		updateComponets();
	}
	private void nextPage(){ //下翻一页
		getAnswers(this.currentPage);
		if(this.currentPage == 1) this.leaveBegin();
		if(++currentPage == this.pageNum) this.reachEnd();
		this.labelCurrent.setText(String.valueOf(currentPage));
		updateComponets();

	}
	private void getAnswers(int pageIndex){
		for(int i=0; i<formulaNum; i++){
			exercise.setAnswer((pageIndex-1)*formulaNum + i, displayAnswer[i].getText());
		}
	}
	private void clearAnswers(){
		exercise.clearAnswers();
		this.submit = false;
		updateComponets();
	}
	private void initComponets(){
		this.submit = false;
		exercise = new Exercise();
		exercise.generatePlusExercise(formulaNum);
		this.currentPage = 1;
		this.pageNum = 1;
		this.reachBegin();
		this.reachEnd();

		displayFormula = new JTextField[formulaNum];
		displayAnswer = new JTextField[formulaNum];
		for(int i=0; i<formulaNum; i++){
			displayFormula[i] = new JTextField();
			displayFormula[i].setBounds(50 + (i%row)*(formulaWidth+answerWidth+10),
					100 + (i/row)*(formulaAnswerHeight+10),
					formulaWidth,
					formulaAnswerHeight);
			displayFormula[i].setHorizontalAlignment(JTextField.LEFT);
			displayFormula[i].setEditable(false);
			jp.add(displayFormula[i]);

			displayAnswer[i] = new JTextField();
			displayAnswer[i].setBounds(50+formulaWidth+(i%row)*(formulaWidth+answerWidth+10),
					100+(i/row)*(formulaAnswerHeight+10),
					answerWidth,
					formulaAnswerHeight);
			jp.add(displayAnswer[i]);
		}
	}
	private void reachBegin(){
		this.previousPage.setEnabled(false);
	}
	private void reachEnd(){
		this.nextPage.setEnabled(false);
	}
	private void leaveBegin(){
		this.previousPage.setEnabled(true);
	}
	private void leaveEnd(){
		this.nextPage.setEnabled(true);
	}
	private void updateComponets(){
		this.pageNum = exercise.getLength() / formulaNum;
		this.labelCurrent.setText(String.valueOf(this.currentPage));
		this.labelTotal.setText(String.valueOf(this.pageNum));
		if(this.currentPage == 1) this.reachBegin();
		else this.leaveBegin();
		if(this.currentPage == this.pageNum) this.reachEnd();
		else this.leaveEnd();

		Formula op;

		for(int i=0; i<formulaNum; i++){
			op = exercise.getOperation((currentPage-1)*formulaNum + i);
			displayFormula[i].setText(op.asString());
			if(!submit){
				displayAnswer[i].setBackground(Color.WHITE);
			}else{
				if(exercise.getJudgement((currentPage-1)*formulaNum + i))
					displayAnswer[i].setBackground(Color.GREEN);
				else
					displayAnswer[i].setBackground(Color.RED);
			}
			displayAnswer[i].setText(exercise.getAnswer((currentPage-1)*formulaNum + i));
			String curType="";
			switch(exercise.getCurrentType()){
				case MIX:
					curType = "加减混合";
					break;
				case PLUS:
					curType = "仅加法";
					break;
				case MINUS:
					curType = "仅减法";
			}
			labelStatus.setText("类型"
					+ curType + "数量" + exercise.getLength() + "");

			if(!submit){
				labelStat.setText("答题统计：");
			}else{
				int total = exercise.getLength();
				int correct = exercise.Correct();
				int wrong = total - correct;
				int cratio = (int)((float)correct / total * 100);
				int wratio = 100 - cratio;
				String stat = "答题统计： 正确题数" + correct
						+ "  错误题数" + wrong
						+ " 正确率" + cratio
						+ "%  错误率" + wratio + "%";
				labelStat.setText(stat);
			}
		}
	}

}
