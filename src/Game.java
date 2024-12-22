import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Game extends JPanel {
	Graphics2D g;
	Snake snake;
	Apple apple;
	Color scoreColor;
	Font font;

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Data.getWidth(), Data.getHeight());
	}

	Game() {
		snake = new Snake();
		apple = new Apple();
	}

	void Init() {
		g = (Graphics2D) getGraphics();
		scoreColor = new Color(30, 30, 30);
		font = g.getFont().deriveFont(40.0f);
		g.setFont(font);
	}

	void Loop() {
		snake.Loop();
		apple.Loop();

		if ( Vec2.Compare(snake.getHead(), apple.pos) ) {
			snake.Grow();
			apple.Reset();
		}
	}

	void Draw() {
		g.setColor( Color.WHITE );
		g.clearRect(0, 0, Data.getWidth(), Data.getHeight());

		g.setColor( scoreColor );
		g.drawString( String.valueOf( snake.body.size() ), Data.getWidth()/2, Data.getHeight()/2 );

		snake.Draw(g);
		apple.Draw(g);
	}

	void Input(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 38: // Up
				snake.setDirectionY(-1);
				break;

			case 40: // Down
				snake.setDirectionY(1);
				break;

			case 39: // Right
				snake.setDirectionX(1);
				break;

			case 37: // Left
				snake.setDirectionX(-1);
				break;
		}
	}
}
