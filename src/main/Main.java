package main;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static void main(String[]args) {
			
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Game");
		
		GamePanel gm = new GamePanel();
		window.add(gm);
		
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gm.setupGame();
		gm.startGameThread();
		
	}
}