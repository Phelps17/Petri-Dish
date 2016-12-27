import javafx.scene.paint.Color;

class Food extends NonMicrobe {
	final static int SHOCK_CLOCK_DEFAULT = 20;
	final static double SPEED_DEFAULT = 0.08;
	final static int RADIUS_DEFAULT = 10;
	final static Color COLOR_DEFAULT = new Color(0, 0.5, 0, 0.5);

	Food(double x, double y) {
		super(x, y, RADIUS_DEFAULT);
		setFill(COLOR_DEFAULT);
		this.speed = SPEED_DEFAULT;
	}
}