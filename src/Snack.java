import javafx.scene.paint.Color;

class Snack extends NonMicrobe {
	final static int SHOCK_CLOCK_DEFAULT = 20;
	final static double SPEED_DEFAULT = 0.04;
	final static int RADIUS_DEFAULT = 5;
	final static Color COLOR_DEFAULT = new Color(1, 1, 0, 0.5);

	Snack(double x, double y) {
		super(x, y, RADIUS_DEFAULT);
		setFill(COLOR_DEFAULT);
		this.speed = SPEED_DEFAULT;
	}
}