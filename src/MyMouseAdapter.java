import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		
			c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			myFrame = (JFrame) c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			x = e.getX();
			y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}

	
	
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			
			e.translatePoint(-x1, -y1);
			
			int x = e.getX();
			int y = e.getY();
			
			myPanel.x = x;
			myPanel.y = y;

			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);

			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else  if ((gridX == -1) || (gridY == -1)) {
				//Is releasing outside
				//Do nothing
			} else if ((myPanel.mouseDownGridX == gridX) && (myPanel.mouseDownGridY == gridY)) {
				//Released the mouse button on the same cell where it was pressed
				if (myPanel.Mines[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == 0){
					myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
				}else{
					int mines = MineCounter(myPanel.mouseDownGridX, myPanel.mouseDownGridY, myPanel);
					
					System.out.println(mines);
					Color newColor = Color.LIGHT_GRAY;
					
					myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
					myPanel.repaint();
				}
			}

			myPanel.repaint();
			break;
		case 3:
			c = e.getComponent();

			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}

			myFrame = (JFrame)c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			myInsets = myFrame.getInsets();

			x1 = myInsets.left;
			y1 = myInsets.top;

			e.translatePoint(-x1, -y1);

			x = e.getX();
			y = e.getY();

			myPanel.x = x;
			myPanel.y = y;

			gridX = myPanel.getGridX(x, y);
			gridY = myPanel.getGridY(x, y);

			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else if ((gridX == -1) || (gridY == -1)) {
				//Is releasing outside
				//Do nothing
			} else if ((myPanel.mouseDownGridX == gridX) && (myPanel.mouseDownGridY == gridY)){
				myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED;
			}	
			myPanel.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}

	public int MineCounter(int mouseDownX, int mouseDownY, MyPanel myPanel){
		int mineCounter = 0;
		for (int i = mouseDownY-1; i <= mouseDownY+1; i++){
			for (int j = mouseDownX-1; j <= mouseDownX+1;j++){
				try {
					if (myPanel.Mines[i][j] == 0){
						mineCounter++;
					}
				} catch (Exception e){}
			}
		}
		return mineCounter; 
	}
}



















