import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class App extends JFrame {
	Game game;
	boolean isRunning = true;

	App() {
		game = new Game();
	}

	void Init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		add(game);
		pack();
		setTitle(Data.TITLE);
		setVisible(true);

		game.Init();

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				game.Input(e);
			}
		});

		try {
			while(isRunning) {
				game.Loop();
				game.Draw();
				Thread.sleep(Data.DELAY);
			}
		} catch (Exception e) {}
	}

	public static void main(String[] args) {
		App app = new App();
		app.Init();
	}	
}
