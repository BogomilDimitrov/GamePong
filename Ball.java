import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Ball {
	int x, y;
	int size = 20;
	int speed = 5;
	int vx, vy;
	
	Rectangle boundingBox;
	
	public Ball(int x, int y){
		this.x = x;
		this.y = y;
		
		vx = speed;
		vy = speed;
		
		boundingBox = new Rectangle(x, y, size, size);
		boundingBox.setBounds(x, y, size, size);
	}
	
	public void tick(Game game){
		boundingBox.setBounds(x, y, size, size);
		
		if(x <= 0){
			game.p2Score++;
			vx = speed;
		}else if (x + size >= game.getWidth()) {
			game.p1Score++;
			vx = -speed;
		}
		
		if(y <= 0){
			vy = speed;
		}else if (y + size >= game.getHeight()) {
			vy = -speed;
		}
		
		x += vx;
		y += vy;
		
		paddleCollide(game);
	}
	
	private void paddleCollide(Game game){
		if (boundingBox.intersects(game.player.boundingBox1)) {
			vx = speed;
			vy = -speed;
		}else if (boundingBox.intersects(game.player.boundingBox2)) {
			vx = speed;
			vy = speed;
		}else if (boundingBox.intersects(game.ai.boundingBox1)) {
			vx = -speed;
			vy = -speed;
		}else if (boundingBox.intersects(game.ai.boundingBox2)) {
			vx = -speed;
			vy = speed;
		}
	}
	
	Image image = Toolkit.getDefaultToolkit().createImage("ball1.png");
	
	public void render(Graphics g){
		g.drawImage(image, x, y, size, size, null);
	}
}
