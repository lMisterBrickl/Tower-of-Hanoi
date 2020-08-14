import java.awt.Color;
import java.awt.Graphics;

public class Stack{
	private Block headBlock;
	
	public Block getHeadBlock() {
		return headBlock;
	}
	
	public void setHeadBlock(Block headBlock) {
		this.headBlock = headBlock;
	}
	//adauga blocuri
	public void push(Block block) {
		if(headBlock == null)
			headBlock = block;
		else {
			Block currentBlock = headBlock;
			while(currentBlock.getNext() != null) {
				currentBlock = currentBlock.getNext();
			}
			currentBlock.setNext(block);
		}
			
	}
	// sterge blocurile
	public Block pop() {
		Block popBlock = null;
		if(count() == 1) {
			popBlock = headBlock;
			headBlock = null;		
		}
		else if(count()>1) {
			Block currentBlock = headBlock;
			for(int i = 0;i < count()-2; i++) 
				currentBlock = currentBlock.getNext();
			popBlock = currentBlock.getNext();
			currentBlock.setNext(null);
		}
		return popBlock;
	}
	// se iau blocurile
	public Block peek() {
		if(count() > 0) {
			Block currentBlock = headBlock;
			while(currentBlock.getNext() != null) 
				currentBlock = currentBlock.getNext();
			return currentBlock;
		}
		else
			return null;
	}
	//se numara blocurile
	public int count() {
		int ctr = 0;
		Block currentBlock = headBlock;
		while(currentBlock != null) {
			ctr++;
			currentBlock = currentBlock.getNext();
		}
		return ctr;
	}
	
	public void drawBlocks(Graphics g, int x) {
		Block currentBlock = headBlock;
		for (int i = 0; i < count(); i++) {
			int xPos = x + (100-currentBlock.getLength())/2;
			int yPos = 185 - (i*20);
			
			g.setColor(Color.green);
			g.fillRect(xPos, yPos, currentBlock.getLength(), 19);
			currentBlock = currentBlock.getNext();
		}
	}
	
}