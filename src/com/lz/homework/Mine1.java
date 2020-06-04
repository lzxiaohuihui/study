package com.lz.homework;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;



public class Mine1 extends JFrame implements MouseListener{
	Random ra = new Random();
	int row,column,mine_number;      
	JButton btn[];
	JLabel JLabel[];
	int lei[];
	JTextField t = new JTextField("");
	Dialog d = new Dialog(this,"扫雷!!",true);
	JLabel win = new JLabel("win!!");
	JLabel lost = new JLabel("lose!!");
	JLabel time = new JLabel("0");
	JLabel come = new JLabel("加油");
	JButton rebtn = new JButton("F5");
	JButton rankbtn = new JButton("rank");
	
	int num[][];  //记。。。
	int z;//雷的个数
	int m;//标记雷按钮个数
	int n;//正确标记雷的个数
	
	public Mine1() {
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
		num = new int[row*column-z][2];
		setBounds(300,150,column*40+60,row*40+150);
		setBackground(Color.lightGray);
		t.setBounds(20, 40, 50, 50);
		t.setText(""+z);
		t.setBackground(Color.lightGray);
		t.setFont((new Font("谐体",Font.BOLD|Font.ITALIC,32)));
		t.setEditable(false);
		t.setBorder(BorderFactory.createEmptyBorder());
		add(t);
		rebtn.setBounds(column*20, 40, 50, 50);
		rebtn.setBackground(Color.lightGray);
		add(rebtn);
		come.setBounds(column*40-60, 40, 80, 50);
		come.setFont((new Font("思源",Font.PLAIN,32)));
		add(come);
		
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
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		
		//*******************添加按钮*****************
		for(int i=0;i<row;i++) {
			for (int j=0;j<column;j++) {
				btn[i*column+j] = new JButton();
				btn[i*column+j].setBounds(20+j*40,100+i*40,40,40);
				btn[i*column+j].setFont(new Font("谐体",Font.BOLD|Font.ITALIC,12));
				add(btn[i*column+j]);
				btn[i*column+j].addMouseListener(this);
			}
		}		

		//**********************添加数字标签******************
		for(int i=0;i<row;i++) {
			for (int j=0;j<column;j++) {
				JLabel[i*column+j] = new JLabel();
				JLabel[i*column+j].setBackground(Color.lightGray);
				JLabel[i*column+j].setForeground(Color.BLUE);
				JLabel[i*column+j].setBounds(20+j*40,100+i*40,40,40);
				add(JLabel[i*column+j]);
				
			}
		}
		
	
		setnumber();    //设置数字
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);	
		setVisible(true);
	}
	public void setnumber() {
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
				if(z==0) {
					JLabel[column*i+j].setText("");
				}
				z=0;
			}
		}

	}
	
	public void remove_around(int i,int j) {
		
		for(int x=i-1;x<=i+1;x++) {
			for(int y=j-1;y<=j+1;y++) {
				if((x>=0&&y>=0)&&(x<row&&y<column)) {
					if(btn[column*x+y].getText().equals("")) {
						remove(btn[column*x+y]);
						repaint();
						if(lei[column*x+y]==1) {
							d.add(lost);
							d.setVisible(true);
						}
					}
					if(x==i&&y==j) continue;
					if(JLabel[x*column+y].equals("")) {
						remove_around(x,y);
					}
				}
			}
		}
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		JButton sbtn = (JButton)arg0.getSource();
		if(sbtn.getText().equals("")) {
			remove(sbtn);
			repaint();
		}
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
				if(btn[column*i+j]==sbtn) {
					if (JLabel[column*i+j].getText().equals("")) {
						remove_around(i,j);
					}
				}
			}
		}
		
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	
	public static void main(String[] args) {		
		new Mine1();	
	}
}
	





