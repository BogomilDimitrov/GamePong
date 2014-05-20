import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;


public class MainMenu extends JFrame{
	private static final long serialVersionUID = 1L;
	
	int screenWidth = 250;
	int screenHeigth = 230;
	
	int buttonWidth = 100;
	int buttonHeigth = 40;
	
	JButton Play, Quit;
	JCheckBox twoPlayer;
	JCheckBox hardDifficulty;
	JCheckBox hackerDifficulty;

	public MainMenu(){
		
		addButtons();
		addActions();
		
		getContentPane().setLayout(null);
		
		Play.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth, buttonHeigth); //Position the PLAY button
		Quit.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth, buttonHeigth); //Position the QUIT button
		twoPlayer.setBounds(50, 95, buttonWidth*2, buttonHeigth); //Position the twoPlayer check box
		hardDifficulty.setBounds(50, 125, buttonWidth*2, buttonHeigth); //Position the hardDifficulty check box
		hackerDifficulty.setBounds(50, 155, buttonWidth*2, buttonHeigth); //Position the hardDifficulty check box
		
		//Adding buttons
		getContentPane().add(Play); //Add the button to the JFrame
		getContentPane().add(Quit); //Add the button to the JFrame
		getContentPane().add(twoPlayer); //Add the button to the JFrame
		getContentPane().add(hardDifficulty); //Add the button to the JFrame
		getContentPane().add(hackerDifficulty); //Add the button to the JFrame
		
		//JFrame stuff
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeigth);
		setTitle("Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private void addButtons(){
		Play = new JButton("Play");
		Quit = new JButton("Quit");
		twoPlayer = new JCheckBox("Two Plyers?");
		hardDifficulty = new JCheckBox("Hardcore mode?");
		hackerDifficulty = new JCheckBox("Are you a softuni hacker?");
	}
	
	private void addActions(){
		Play.addActionListener(new ActionListener() { //Take Play button, add new ActionListener
			public void actionPerformed(ActionEvent arg0) { //Turn the action performed into a variable for usage
				dispose();
				
				Game game = new Game();
				
				if (twoPlayer.isSelected()) {
					game.ai.isTwoPlayer = true;
				}else {
					game.ai.isTwoPlayer = false;
				}
				if (hardDifficulty.isSelected()) {
					game.isHardcore = true;
				}else {
					game.isHardcore = false;
				}
				if (hackerDifficulty.isSelected()) {
					game.isHacker = true;
				}else {
					game.isHacker = false;
				}
				
				game.start();
			}
		}); //Play button
		
		Quit.addActionListener(new ActionListener() { //Take Quit button, add new ActionListener
			public void actionPerformed(ActionEvent arg0) { //Turn the action performed into a variable for usage
				System.exit(0); //Shutdown the program
			}
		}); //Quit button
	}
}
