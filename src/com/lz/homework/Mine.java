package com.lz.homework;

import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import javax.swing.*;


public class Mine{
	Instant start = Instant.now();//开始计时
	Random ra = new Random();
	JFrame f=new JFrame("扫雷");
	int row,column,mine_number;      
	JButton btn[];
	JLabel JLabel[];
	int lei[];
	JTextField t = new JTextField("");
	Dialog d = new Dialog(f,"扫雷!!",true);
	JLabel win = new JLabel("win!!");
	JLabel lost = new JLabel("lose!!");
	JLabel time = new JLabel("0");
	JLabel come = new JLabel("加油");
	JButton rebtn = new JButton("F5");
	JButton rankbtn = new JButton("rank");
	
	int z;//雷的个数
	int m;//标记雷按钮个数
	int n;//正确标记雷的个数
	
	public Mine() {
		Object[] options ={ "简单", "中等", "困难" };  //自定义按钮上的文字
		int l = JOptionPane.showOptionDialog(null, "选择难度吧！", "提示",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
		if(l==0) {
			row = 10;
			column=10;
			mine_number=10;
		}
		else if(l==1) {
			row = 16;
			column=16;
			mine_number=40;
		}
		else if(l==2) {
			row = 16;
			column=30;
			mine_number=100;
		}
		
		
		/*row=16;							//*********************全局设置行列和雷的最多个数 
		column = 30;
		mine_number=99;*/
		
		JLabel = new JLabel[row*column];
		btn = new JButton[row*column];
		lei = new int[row*column];
		//*******************设置雷***********************
		for(int i=0;i<mine_number;i++) {
			lei[ra.nextInt(row*column)]=1;
		}
		for(int i=0;i<row*column;i++) {						//记雷的个数
			if(lei[i]==1) {					
				z++;
			}
		}
		f.setBounds(300,150,column*40+60,row*40+150);
		f.setBackground(Color.lightGray);
		t.setBounds(20, 40, 50, 50);
		t.setText(""+z);
		t.setBackground(Color.lightGray);
		t.setFont((new Font("谐体",Font.BOLD|Font.ITALIC,32)));
		t.setEditable(false);
		t.setBorder(BorderFactory.createEmptyBorder());
		f.add(t);
		rebtn.setBounds(column*20, 40, 50, 50);
		rebtn.setBackground(Color.lightGray);
		f.add(rebtn);
		come.setBounds(column*40-60, 40, 80, 50);
		come.setFont((new Font("思源",Font.PLAIN,32)));
		f.add(come);
		
		d.setLayout(null);
		d.setBounds(700, 400, 300, 300);
		rankbtn.setBounds(100, 200, 80, 30);
		d.add(rankbtn);
		time.setBounds(60, 120, 200, 50);
		time.setFont(new Font("谐体",Font.BOLD|Font.ITALIC,32));
		win.setBounds(40, 50, 200, 50);
		win.setFont(new Font("谐体",Font.BOLD|Font.ITALIC,32));
		lost.setBounds(40, 50,200, 50);
		lost.setFont(new Font("谐体",Font.BOLD|Font.ITALIC,32));
		d.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//d.setVisible(true);
		rankbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
			}
		});
		
		rebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Mine();
			}
		});
		
		
		//*******************设置按钮*****************
		for(int i=0;i<row;i++) {
			for (int j=0;j<column;j++) {
				btn[i*column+j] = new JButton();
				btn[i*column+j].setBounds(20+j*40,100+i*40,40,40);
				btn[i*column+j].setFont(new Font("谐体",Font.BOLD|Font.ITALIC,12));
				f.add(btn[i*column+j]);
				btn[i*column+j].addMouseListener(new MouseListener() {
					public void mouseReleased(MouseEvent e) {}
					public void mousePressed(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					public void mouseClicked(MouseEvent e) {
						JButton sbtn = (JButton) e.getSource();
						if(e.getButton()==MouseEvent.BUTTON1) {
							if(sbtn.getText().equals("")) {
								f.remove(sbtn);
								f.repaint();
							}
//   **************************除去全部空白************************ 
							for(int i=0;i<row;i++) {
								for(int j=0;j<column;j++) {
									if(btn[column*i+j]==sbtn) {
										if (JLabel[column*i+j].getText().equals("")) {
											for (int k=0;k<row;k++) {
												for(int l=0;l<column;l++) {
													if(JLabel[column*k+l].getText().equals("")) {
														for(int x=k-1;x<=k+1;x++) {
															for(int y=l-1;y<=l+1;y++) {
																if((x>=0&&y>=0)&&(x<row&&y<column)) {																	
																	if(btn[column*x+y].getText().equals("")) {
																		f.remove(btn[column*x+y]);
																		f.repaint();
																	}																	
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							//   *************除去连续空白
							
						
							if(sbtn.getText().equals("")) {
								for(int i=0;i<row;i++) {
									for(int j=0;j<column;j++) {
										if(btn[column*i+j]==sbtn) {
											if(JLabel[column*i+j].getText().equals(" boom")) {
												Instant end = Instant.now();
												long timeElapsed = Duration.between(start, end).toMillis(); // 单位为毫秒
												time.setText("历时"+timeElapsed/1000+"秒");
												d.add(time);
												d.add(lost);
												d.setVisible(true);
											}
										}
									}
								}
							}
							f.repaint();
						}
						if(e.getButton()==MouseEvent.BUTTON3) {
							z=0;
							for(int i=0;i<row*column;i++) {						//记雷的个数
								if(lei[i]==1) {					
									z++;
								}
							}
							
							if(sbtn.getText().equals("!")) {
								sbtn.setText("");
								f.repaint();
								m--;
								for(int i=0;i<row*column;i++) {
									if(btn[i]==sbtn) {
										if(lei[i]==1) {
											n--;
										}
									}
								}
							}
							else if(sbtn.getText().equals("")) {
								if(m<=z-1){
									sbtn.setText("!");
									f.repaint();
									m++;
									for(int i=0;i<row*column;i++) {
										if(btn[i]==sbtn) {
											if(lei[i]==1) {
												n++;
											}
										}
									}
								}
							}
							t.setText(""+(z-m));
							
							
							if(n==z) {
								Instant end = Instant.now();
								long timeElapsed = Duration.between(start, end).toMillis(); // 单位为毫秒
								time.setText("历时"+timeElapsed/1000+"秒"); 
								d.add(time);
								d.add(win);
								d.setVisible(true);
							}
						}
						
					}
				});
			}
		}
		//**********************设置数字标签******************
		for(int i=0;i<row;i++) {
			for (int j=0;j<column;j++) {
				JLabel[i*column+j] = new JLabel();
				JLabel[i*column+j].setBackground(Color.lightGray);
				JLabel[i*column+j].setForeground(Color.BLUE);
				JLabel[i*column+j].setBounds(20+j*40,100+i*40,40,40);
				f.add(JLabel[i*column+j]);
			}
		}
		//**********************设置数字******************
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
				
				JLabel[column*i+j].setFont(new Font("谐体",Font.BOLD|Font.ITALIC,12));
				z=0;
				if(lei[column*i+j]==1) {
					JLabel[column*i+j].setText(" boom"); 
					JLabel[column*i+j].setForeground(Color.black);
					continue;
				}
				for(int x=i-1;x<=i+1;x++) {
					for(int y=j-1;y<=j+1;y++) {
						if((x>=0&&y>=0)&&(x<row&&y<column)) {
							if(lei[column*x+y]==1) {
								z++;
							}
						}
					}
				}
				JLabel[column*i+j].setText("    "+z);
				if(z==0) JLabel[column*i+j].setText("");
				z=0;
				
				JLabel[column*i+j].addMouseListener(new PU(f,btn,row,column,i,j,lei,d,lost));
		
			}
		}
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);	
		f.setVisible(true);
	}
	
	
	public static void main(String[] args) {		
		new Mine();	
	}
}
class PU extends MouseAdapter{				//双击标签
	int row,column,i,j;
	int lei[];
	Dialog d;
	JLabel lost;
	JFrame f;
	JButton btn[];
	PU(JFrame f,JButton btn[],int row,int column,int i,int j,int lei[],Dialog d,JLabel lost){
		this.f=f;this.d=d;this.lost=lost;
		this.btn = btn; 
		this.row = row; this.column = column;this.i = i;this.j = j;this.lei=lei;
	}
	public void mouseClicked(MouseEvent e) {
		
		
		if(e.getButton()==MouseEvent.BUTTON1&& e.getClickCount() == 2) {
			for(int x=i-1;x<=i+1;x++) {
				for(int y=j-1;y<=j+1;y++) {
					if((x>=0&&y>=0)&&(x<row&&y<column)) {
						if(btn[column*x+y].getText().equals("")) {
							f.remove(btn[column*x+y]);
							f.repaint();
							if(lei[column*x+y]==1) {
								d.add(lost);
								d.setVisible(true);
							}
						}
					}
				}
			}
		}
		
	}
}




