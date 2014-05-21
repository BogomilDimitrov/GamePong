import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	boolean isHardcore = false;
	boolean isHacker = false;

	public static PlayerPaddle player;
	public static AIPaddle ai;
	public static Ball ball;
	public static Ball2 ball2;
	public static Ball3 ball3;
	InputHandler IH;

	JFrame frame; // Sets the window of the game
	public final int WIDTH = 600;
	public final int HEIGHT = WIDTH / 16 * 9;
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT); // Condense WIDTH and HEIGHT in one variable
	public final String TITLE = "Pong"; // Sets title

	Image image = Toolkit.getDefaultToolkit().createImage("normal.jpg");
	Image imageW = Toolkit.getDefaultToolkit().createImage("pWin.jpg");
	Image imageHard = Toolkit.getDefaultToolkit().createImage("hardcore.jpg");
	Image imageHack = Toolkit.getDefaultToolkit().createImage("hacker.jpg");
	Image imageAi = Toolkit.getDefaultToolkit().createImage("win.jpg");
	Image imageTwoP = Toolkit.getDefaultToolkit().createImage("twoplayer.jpg");
	

	static boolean gameRunning = false; // Set's game running

	int p1Score, p2Score;

	public void run() {

		while (gameRunning) { // if gameRunning = true
			tick();
			render();
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// END run method

	public synchronized void start() {
		gameRunning = true;
		new Thread(this).start();
	}// END start method

	public static synchronized void stop() {
		gameRunning = false;
		System.exit(0);
	}// END stop method

	public Game() {
		frame = new JFrame();

		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);

		IH = new InputHandler(this);

		player = new PlayerPaddle(10, 60);
		ai = new AIPaddle(getWidth() - 15, 60);
		ball = new Ball(getWidth() / 2, getHeight() / 2);
		ball2 = new Ball2(getWidth() / 2 + 50 , getHeight() / 2 + 50);
		ball3 = new Ball3(getWidth() / 2 + 100, getHeight() / 2 + 100);


	} // END Game method

	public void tick() { // Method for making changes
		player.tick(this);
		ai.tick(this);
		ball.tick(this);
		if (isHardcore) {
			ball2.tick(this);
		}
		if (isHacker) {
			ball2.tick(this);
			ball3.tick(this);
		}

	}// END tick method

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(imageW, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(imageAi, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if (ai.isTwoPlayer) {
			g.drawImage(imageTwoP, 0, 0, getWidth(), getHeight(), null);
		}else {
			if (isHardcore) {
				g.drawImage(imageHard, 0, 0, getWidth(), getHeight(), null);
			}
			if (isHacker) {
				g.drawImage(imageHack, 0, 0, getWidth(), getHeight(), null);
			}
		}
		
		g.setColor(Color.white);
		g.drawString("Player 1: " + p1Score, 0, 10);
		g.drawString("Player 2: " + p2Score, getWidth() - 70, 10);
		

		player.render(g);
		ai.render(g);
		ball.render(g);
		
		if (isHardcore) {
			ball2.render(g);
		}
		if (isHacker) {
			ball2.render(g);
			ball3.render(g);
		}

		
		if (p1Score == 10) {
			g.drawImage(imageW, 0, 0, getWidth(), getHeight(), null);
			if (ai.isTwoPlayer) {
				g.drawImage(imageTwoP, 0, 0, getWidth(), getHeight(), null);
			}
			g.setColor(Color.white);
			g.drawString("Player 1 wins!", 270, getHeight()/2 - 20);
			gameRunning = false;
		}else if (p2Score == 10 && !ai.isTwoPlayer) {
			g.drawImage(imageAi, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.white);
			g.drawString("AI wins!", 270, getHeight()/2 - 20);
			gameRunning = false;
		}else if (p2Score == 10 && ai.isTwoPlayer) {
			g.drawImage(imageTwoP, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.white);
			g.drawString("Player 2 wins!", 270, getHeight()/2 - 20);
			gameRunning = false;
		}

		g.dispose();
		bs.show();
	} // END render method
}
