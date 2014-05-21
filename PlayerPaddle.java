import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class PlayerPaddle {
	int x;
	int y;
	int width = 15;
	int heigth = 50;
	int speed = 4;

	Rectangle boundingBox1;
	Rectangle boundingBox2;

	boolean goingUp = false;
	boolean goingDown = false;

	public PlayerPaddle(int x, int y) {
		this.x = x;
		this.y = y;

		boundingBox1 = new Rectangle(x, y, width, 25);
		boundingBox1.setBounds(x, y, width, 25);
		
		boundingBox2 = new Rectangle(x, y+25, width, 25);
		boundingBox2.setBounds(x, y+25, width, 25);
	}

	public void tick(Game game) {
		boundingBox1.setBounds(x, y, width, 25);
		boundingBox2.setBounds(x, y+25, width, 25);
		if (goingUp && y > 0) {
			y -= speed;
		}
		if (goingDown && y < game.getHeight() - heigth) {
			y += speed;
		}

	}

	Image image = Toolkit.getDefaultToolkit().createImage("spaceship1.png");

	public void render(Graphics g) {
		g.drawImage(image, x, y, width, heigth, null);
	}
}
