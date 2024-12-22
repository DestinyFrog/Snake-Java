import java.util.List;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Snake {
	Vec2 direction;
	List<Vec2> body;
	Color color;
	Color resetingColor;
	boolean isReseting = false;

	Snake() {
		body = new ArrayList<>();
		color = new Color(0, 255, 0); // green
		resetingColor = new Color(0, 0, 255); // blue
		direction = new Vec2(1, 0);
		Vec2 head = new Vec2(0,0);
		body.add(head);
	}

	Vec2 getHead() {
		return body.get(0);
	};

	void setHead(Vec2 data) {
		body.set(0, data);
	}

	Vec2 getTail() {
		return body.get( body.size()-1 );
	};

	void Loop() {
		for (int i = body.size()-1; i > 0; i--) {
			body.set(i, new Vec2(
				body.get(i-1).x,
				body.get(i-1).y
			));
		}

		Vec2 head = getHead();
		Vec2 newHead = TeleportOnBounds( new Vec2( head.x + direction.x, head.y + direction.y ) );
		setHead(newHead);

		if ( CheckBodyCollision() ) {
			isReseting = true;
		}

		if ( isReseting ) {
			NotGrow();
		}
	}

	Vec2 TeleportOnBounds(Vec2 h) {
		Vec2 k = h;
	
		if (h.x < 0) {
			k.x = Data.COLS-1;
		}

		if (h.y < 0) {
			k.y = Data.ROWS-1;
		}

		if (h.x >= Data.COLS) {
			k.x = 0;
		}

		if (h.y >= Data.ROWS) {
			k.y = 0;
		}

		return k;
	}

	boolean CheckBodyCollision() {
		Vec2 head = getHead();

		for (int i = 1; i < body.size(); i++) {
			Vec2 part = body.get(i);
			if ( Vec2.Compare(head, part) ) {
				return true;
			}
		}

		return false;
	}

	void Draw(Graphics2D g) {
		g.setColor( isReseting ? resetingColor : color );
		for (Vec2 ebody : body) {
			g.fillRect(ebody.x*Data.UNIT, ebody.y*Data.UNIT, Data.UNIT, Data.UNIT);
		}
	}

	void setDirectionX(int x) {
		this.direction.x = x;
		this.direction.y = 0;
	}

	void setDirectionY(int y) {
		this.direction.x = 0;
		this.direction.y = y;
	}

	void Grow() {
		Vec2 tail = getTail();
		Vec2 newTail = new Vec2(tail.x, tail.y);
		body.add(newTail);
	}

	void NotGrow() {
		if (body.size() <= 1) {
			isReseting = false;
			return;
		}

		body.remove( body.size()-1 );
	}
}
