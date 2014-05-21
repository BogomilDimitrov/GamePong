import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class AIPaddle {
	int x;
	int y;
	int width = 15;
	int heigth = 50;
	int speed = 4;
	int closestBall;

	boolean isTwoPlayer = false;

	Rectangle boundingBox1;
	Rectangle boundingBox2;

	boolean goingUp = false;
	boolean goingDown = false;

	public AIPaddle(int x, int y) {
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

		if (!isTwoPlayer) {
			
			if (game.isHardcore) {
				if ((game.ball.y < y && y >= 0 && game.ball.x > game.ball2.x)||
					(game.ball2.y < y && y >= 0 && game.ball2.x > game.ball.x)) {
					y -= speed;
				} else if ((game.ball.y > y && y <= game.getHeight() - heigth && game.ball.x > game.ball2.x)||
						   (game.ball2.y > y && y <= game.getHeight() - heigth && game.ball2.x > game.ball.x)) {
					y += speed;
				}
			}else if (game.isHacker) {
				if ((game.ball.y < y && y >= 0 && (game.ball.x > game.ball2.x)&&(game.ball.x > game.ball3.x))||
					(game.ball2.y < y && y >= 0 && (game.ball2.x > game.ball.x)&&(game.ball2.x > game.ball3.x))||
					(game.ball3.y < y && y >= 0 && (game.ball3.x > game.ball2.x)&&(game.ball3.x > game.ball.x))) {
					
						y -= speed;
					} else if ((game.ball.y > y && y <= game.getHeight()-heigth && (game.ball.x > game.ball2.x)&&(game.ball.x > game.ball3.x))||
							(game.ball2.y > y && y <= game.getHeight()-heigth && (game.ball2.x > game.ball.x)&&(game.ball2.x > game.ball3.x))||
							(game.ball3.y > y && y <= game.getHeight()-heigth && (game.ball3.x > game.ball2.x)&&(game.ball3.x > game.ball.x))) {
						y += speed;
					}
			}else {
				if (game.ball.y < y && y >= 0) {
					y -= speed;
				} else if (game.ball.y > y && y <= game.getHeight() - heigth) {
					y += speed;
				}
			}

			
		}else {
			if (goingUp && y > 0){
				y -= speed;
			} else if (goingDown && y < game.getHeight()   - heigth) {
				y += speed;
			}
		}

	}

	Image image = Toolkit.getDefaultToolkit().createImage("spaceship2.png");

	public void render(Graphics g) {
		g.drawImage(image, x, y, width, heigth, null);
	}
}
