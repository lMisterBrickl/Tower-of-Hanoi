import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class pnlGamePlay extends JPanel {
	
	Stack tower1 = new Stack();
	Stack tower2 = new Stack();
	Stack tower3 = new Stack();
	
	boolean isSelectedTower1 = true;
	boolean isSelectedTower2 = false;
	boolean isSelectedTower3 = false;
	
	Block carriedBlock = null;
	
	public pnlGamePlay() {
		
		addKeyListener(new ControlAdapter());
		setFocusable(true);
		
		//se creaza blocurile
		tower1.push(new Block(90));
		tower1.push(new Block(70));
		tower1.push(new Block(50));
		tower1.push(new Block(30));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.gray);
		//turnul 1
		g.fillRect(70, 40, 20, 160);
		//turnul 2
		g.fillRect(230, 40, 20, 160);
		//tuenul 3
		g.fillRect(390, 40, 20, 160);
		
		tower1.drawBlocks(g, 30);
		tower2.drawBlocks(g, 190);
		tower3.drawBlocks(g, 350);
		
		if(carriedBlock != null) {
			int xPos = 0;
			if(isSelectedTower1)
				xPos = 30+(100-carriedBlock.getLength())/2;
			else if(isSelectedTower2)
				xPos = 190+(100-carriedBlock.getLength())/2;
			else if(isSelectedTower3)
				xPos = 350+(100-carriedBlock.getLength())/2;
			
			g.fillRect(xPos, 20, carriedBlock.getLength(), 19);
		}
		
		g.setColor(Color.black);
		if(isSelectedTower1)
			g.drawRect(1, 20, 158, 190);
		else if(isSelectedTower2)
			g.drawRect(161, 20, 158, 190);
		else if(isSelectedTower3)
			g.drawRect(321, 20, 158, 190);
		
			
		
	}
	
	private void winner() {
		if(tower2.count() == 4 || tower3.count() == 4) {
			JOptionPane.showMessageDialog(pnlGamePlay.this, "Ai castigat");
			System.exit(0);
		}
	}
	
	public class ControlAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_RIGHT) {
				if(isSelectedTower1) {
					isSelectedTower2 = true;
					isSelectedTower1 = false;
				}
				else if(isSelectedTower2) {
					isSelectedTower3 = true;
					isSelectedTower2 = false;
				}
			}
			if(key == KeyEvent.VK_LEFT) {
				if(isSelectedTower2) {
					isSelectedTower1 = true;
					isSelectedTower2 = false;
				}
				else if(isSelectedTower3) {
					isSelectedTower2 = true;
					isSelectedTower3 = false;
				}
			}
			if(key == KeyEvent.VK_UP) {
				if(carriedBlock == null) {
					if(isSelectedTower1)
						carriedBlock = tower1.pop();
					else if(isSelectedTower2)
						carriedBlock = tower2.pop();
					else if(isSelectedTower3)
						carriedBlock = tower3.pop();
				}
			}
			if(key == KeyEvent.VK_DOWN) {
				if(carriedBlock != null) {
					if(isSelectedTower1) {
						if(tower1.peek() == null || tower1.peek().getLength() > carriedBlock.getLength()) {
							tower1.push(carriedBlock);
							carriedBlock = null;
						}
						
					}
						
					else if(isSelectedTower2) {
						if(tower2.peek() == null || tower2.peek().getLength() > carriedBlock.getLength()) {
							tower2.push(carriedBlock);
							carriedBlock = null;
						}
					}
	
					else if(isSelectedTower3) {
						if(tower3.peek() == null || tower3.peek().getLength() > carriedBlock.getLength()) {
							tower3.push(carriedBlock);
							carriedBlock = null;
						}
					}
					
				}
				
				
			}
			
				repaint();
				winner();
				
		}
		
		
	}


}
