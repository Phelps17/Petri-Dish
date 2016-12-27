import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class NonMicrobe extends Circle {
	final static int SHOCK_CLOCK_DEFAULT = 20;
	final static int RESET_CLOCK_DEFAULT = 200;

	public double dx = 1, dy = 1;
	public double speed;
	private int shockClock = SHOCK_CLOCK_DEFAULT;
	private int resetClock = RESET_CLOCK_DEFAULT;
	private NonMicrobe lastCollision;

	NonMicrobe(double x, double y, int radius) {
		super(x, y, radius);
		setStroke(Color.RED);
		setStrokeWidth(2.5);
	}

	public double getTop() {
		return this.getCenterY() - this.getRadius();
	}

	public double getBottom() {
		return this.getCenterY() + this.getRadius();
	}

	public double getRight() {
		return this.getCenterX() + this.getRadius();
	}

	public double getLeft() {
		return this.getCenterX() - this.getRadius();
	}

	public boolean isColliding(ObservableList<Node> microbes) {
		for (Node node : microbes) {
			if (!(node instanceof Microbe)) {
				NonMicrobe neighbor = (NonMicrobe)node;
				if (!neighbor.equals(this)) {

					Double delta = Math.sqrt(Math.abs(Math.pow((this.getCenterX() - neighbor.getCenterX()), 2) + 
							Math.pow((this.getCenterY() - neighbor.getCenterY()), 2)));

					if (delta <= (this.getRadius() + neighbor.getRadius())) {
						this.setStroke(Color.RED);
						this.shockClock = SHOCK_CLOCK_DEFAULT;

						if (neighbor.equals(this.lastCollision) && !(this instanceof Bouncer)) {
							this.resetClock--;
							if (this.resetClock <= 0) {
								this.setCenterX((25 + Math.random()*900));
								this.setCenterY((25 + Math.random()*500));
								this.lastCollision = null;
							}
						}
						else {
							this.lastCollision = neighbor;
							this.resetClock = RESET_CLOCK_DEFAULT;
						}

						return true;
					}
				}
			}
		}

		if (this.shockClock <= 0) {
			this.setStroke(Color.BLUE);
		}
		else {
			this.shockClock--;
		}

		return false;
	}
}