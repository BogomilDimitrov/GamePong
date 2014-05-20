import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AIPaddle {
	int x;
	int y;
	int width = 15;
	int heigth = 40;
	int speed = 3;
	int closestBall;

	boolean isTwoPlayer = false;

	Rectangle boundingBox;

	boolean goingUp = false;
	boolean goingDown = false;

	public AIPaddle(int x, int y) {
		this.x = x;
		this.y = y;

		boundingBox = new Rectangle(x, y, width, heigth);
		boundingBox.setBounds(x, y, width, heigth);
	}

	public void tick(Game game) {
		boundingBox.setBounds(x, y, width, heigth);

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

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, heigth);
	}
}
