import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class PlayerPaddle {
	int x;
	int y;
	int width = 15;
	int heigth = 40;
	int speed = 2;
	
	Rectangle boundingBox;
	
	boolean goingUp = false;
	boolean goingDown = false;
	
	public PlayerPaddle(int x, int y){
		this.x = x;
		this.y = y;
		
		boundingBox = new Rectangle(x, y, width, heigth);
		boundingBox.setBounds(x, y, width, heigth);
	}
	 
	public void tick(Game game){
		boundingBox.setBounds(x, y, width, heigth);
		if (goingUp && y > 0) {
			y -= speed;
		}
		if (goingDown && y < game.getHeight()   - heigth) {
			y += speed;
		}
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, heigth);
	}
}
