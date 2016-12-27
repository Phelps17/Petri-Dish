import javafx.scene.paint.Color;

class Bouncer extends NonMicrobe {
	final static int SHOCK_CLOCK_DEFAULT = 20;
	final static double SPEED_DEFAULT = 0.02;
	final static int RADIUS_DEFAULT = 65;
	final static Color COLOR_DEFAULT = new Color(0, 0, 0, 0.5);

	Bouncer(double x, double y) {
		super(x, y, RADIUS_DEFAULT);
		setFill(COLOR_DEFAULT);
		this.speed = SPEED_DEFAULT;
	}
}