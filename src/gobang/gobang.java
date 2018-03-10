package gobang;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javax.swing.*;

public class gobang extends JFrame implements MouseListener {
	Vector v = new Vector();// 记录每步走棋信息
	Vector white = new Vector();// 记录白棋走棋信息
	Vector black = new Vector();// 记录黑棋走棋信息
	boolean b;// 记录棋子的颜色
	int whitecount, blackcount;// 记录悔棋信息
	int size = 44;// 棋子的大小
	int x = 50;// x边界间距
	int y = 50;// y边界间距
	int w = 25;// 间距大小
	int width = 375;// 宽度
	int height = 375;// 高度
	int[][] array = new int[16][16];// 用来存棋子的数组

	public gobang() {
		JFrame frame = new JFrame();
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		this.setTitle("五子棋");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		Color bgcolor=new Color(135,206,250);
		this.setBackground(bgcolor);
		this.setSize(500, 500);
		this.setLayout(new BorderLayout());
		this.addMouseListener(this);
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawRect(x, y, width, height);
		for (int i = 0; i < 16; i++) {
			g.drawLine(x, y + i * w, x + width, y + i * w);
			g.drawLine(x + i * w, y, x + i * w, y + height);
		}
		for(int i=0;i<v.size();i++) {
			String str=(String)v.get(i);
			String tmp[]=str.split(":");
			int a=Integer.parseInt(tmp[0]);
			int b=Integer.parseInt(tmp[1]);
			a=a*w+x;
			b=b*w+y;
			if(i%2==0)
				g.setColor(Color.WHITE);
			else
				g.setColor(Color.BLACK);
			g.fillArc(a-w/2, b-w/2, w, w, 0, 360);
		}
	}

	public boolean victory(int x, int y, Vector contain) {
		int number_1=0;
		int number_2=0;
		int number_3=0;
		int number_4=0;	
		boolean flag_1=false;
		boolean flag_2=false;
		boolean flag_3=false;
		boolean flag_4=false;
		for(int i=1;i<6;i++) {
			if(contain.contains((x+i)+":"+y)&&(x+i)>=0&&(x+i)<16){
				number_1++;
			}
			else
				break;
		}
		for(int i=1;i<6;i++) {
			if(contain.contains((x-i)+":"+y)&&(x+i)>=0&&(x+i)<16){
				number_1++;
			}
			else
				break;
		}
		for(int i=1;i<6;i++) {
			if(contain.contains(x+":"+(y+i))&&(y+i)>=0&&(y+i)<16){
				number_2++;
			}
			else
				break;
		}
		for(int i=1;i<6;i++) {
			if(contain.contains(x+":"+(y-i))&&(y-i)>=0&&(y-i)<16){
				number_2++;
			}
			else
				break;
		}
		for(int i=1;i<6;i++) {
			if(contain.contains((x-i)+":"+(y-i))&&(x-i)>=0&&(x-i)<16&&(y-i)>=0&&(y-i)<16){
				number_3++;
			}
			else
				break;
		}
		for(int i=1;i<6;i++) {
			if(contain.contains((x+i)+":"+(y+i))&&(x+i)>=0&&(x+i)<16&&(y+i)>=0&&(y+i)<16){
				number_3++;
			}
			else
				break;
		}
		for(int i=1;i<6;i++) {
			if(contain.contains((x-i)+":"+(y+i))&&(x-i)>=0&&(x-i)<16&&(y+i)>=0&&(y+i)<16){
				number_4++;
			}
			else
				break;
		}
		for(int i=1;i<6;i++) {
			if(contain.contains((x+i)+":"+(y-i))&&(x+i)>=0&&(x+i)<16&&(y-i)>=0&&(y-i)<16){
				number_4++;
			}
			else
				break;
		}
		System.out.println(number_1+","+number_2+","+number_3+","+number_4);
		if(number_1>=4||number_2>=4||number_3>=4||number_4>=4) {
			return true;
		}
		else
			return false;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == e.BUTTON1) {
			int pointx = e.getX();
			int pointy = e.getY();
			pointx = (pointx - pointx % w) + (pointx % w > w / 2 ? w : 0);
			pointx = (pointx - x) / w;
			pointy = (pointy - pointy % w) + (pointy % w > w / 2 ? w : 0);
			pointy = (pointy - y) / w;
			if (pointx >= 0 && pointy >= 0 && pointx < 16 && pointy < 16) {
				if (v.contains(pointx + ":" + pointy)) {
					JOptionPane.showMessageDialog(this, "这个位置已经有棋了");
				} else {
					v.add(pointx + ":" + pointy);
					this.repaint();
					if (v.size() % 2 == 0) {
						black.add(pointx + ":" + pointy);
						if(this.victory(pointx, pointy, black)==true)
							JOptionPane.showMessageDialog(this, "黑方获胜");
					} else {
						white.add(pointx + ":" + pointy);
						if(this.victory(pointx, pointy, white)==true)
							JOptionPane.showMessageDialog(this, "白方获胜");
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "越界");
			}
		}
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		gobang a = new gobang();
	}
}
