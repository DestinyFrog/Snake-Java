
public class Vec2 {
	int x;
	int y;

	Vec2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	static boolean Compare(Vec2 a, Vec2 b) {
		return a.x == b.x && a.y == b.y;
	}
}
