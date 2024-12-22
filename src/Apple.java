import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Apple {
	Vec2 pos;
	Color color;

	Apple() {
		pos = getRandomPosition();
		color = new Color(255, 0, 0); // red
	}

	Vec2 getRandomPosition() {
		Random rnd = new Random();
		return new Vec2(
			rnd.nextInt(Data.COLS),
			rnd.nextInt(Data.ROWS)
		);
	}

	void Reset() {
		pos = getRandomPosition();
	}

	void Loop() {}

	void Draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(pos.x*Data.UNIT, pos.y*Data.UNIT, Data.UNIT, Data.UNIT);
	}
}
