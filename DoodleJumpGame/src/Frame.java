import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	private int numPlat = 1000;
	private boolean platMove = false;
	private Platform store = new Platform(180, 700);
	
	ArrayList<Platform> list = new ArrayList<Platform>();

	Background b = new Background();
	DoodleJumpDude d = new DoodleJumpDude();
	Score s = new Score();
	Music j = new Music("jump.wav", false);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		b.paint(g);
		d.paint(g);
		Font totalFont = new Font ("SansSerif", Font.PLAIN, 40);
		g.setFont(totalFont);
		g.setColor(Color.BLACK);
		g.drawString(s.getScore() + "0", s.getX(), s.getY());
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).paint(g, platMove);
		}
		
		if(store.getY() >= 730) {
			
			platMove = false;
			
		}

		
		if (d.getVY() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).collision(d);
				
				if (list.get(i).collision(d) == true) {
					j.play();
				}
				
				if ((list.get(i).collision(d) == true && list.get(i).getY() < 730)) {
					
					store = list.get(i);
					
					platMove = true;
					
					s.updateScore(list.get(i));
					
					
						
				}
					d.setTime(1800);
					
			}
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getY() > 835) {
				list.remove(i);
				i--;
			}	
		}
		
		if (s.getScore() > 1000) {
			s.setX(400);
		}
		
		if (d.getY() > 880) {
			d.death();
			s.setX(10000);
			Color myColor = new Color(255,0,0,50);
			g.setColor(myColor);
			g.fillRect(0,0,550,880);
			
			Font scoreFont = new Font ("SansSerif", Font.PLAIN, 60);
			g.setFont(scoreFont);
			g.setColor(Color.BLACK);
			g.drawString("Game Over", 105, 350);
			Font rFont = new Font ("SansSerif", Font.PLAIN, 35);
			g.setFont(rFont);
			g.drawString("Click to Restart Game", 90, 400);
			
			g.drawString("Score: " + s.getScore() + "0", 165, 443);
			
		}
		
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Doodle Jump");
		f.setSize(new Dimension(550, 880));
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		list.add(store);
		
		int startY = 600;
		
		for (int i = 0; i < numPlat; i++) {
			if (Math.random()<.20) {
				list.add(new MovingPlatform(startY));
			} else {
			list.add(new Platform(startY));
			}
			startY -= 105;
		}
		
	}
	
	public void reset() {
		
		list.removeAll(list);
		
		store = new Platform(180, 700);
		
		list.add(store);
		
		int startY = 600;
		
		for (int i = 0; i < numPlat; i++) {
			if (Math.random()<.30) {
				list.add(new MovingPlatform(startY));
			} else {
			list.add(new Platform(startY));
			}
			startY -= 105;
		}
		
		d.setXY(225, 650);
		
		s.setScore(0);
		s.setX(420);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (d.getDie() == true) {
			reset();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getKeyCode());
		//d.moveX(arg0);
		
		if(arg0.getKeyCode() == 39) {
			
			d.setVX(12);
			
		}
		
		if(arg0.getKeyCode() == 37) {
			
			d.setVX(-12);
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode() == 39) {
			
			d.setVX(0);
			
		}
		
		if(arg0.getKeyCode() == 37) {
			
			d.setVX(0);
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
