import javafx.scene.shape.Line;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class VisionLine extends Line {

	private Microbe microbe;
	private double offsetAngle, visionAngle;

	public VisionLine(Microbe microbe, double offsetAngle) {
		super(microbe.getMyX(), microbe.getMyY(), microbe.getMyX(), microbe.getMyY());

		this.microbe = microbe;
		this.offsetAngle = offsetAngle;
		this.visionAngle = this.microbe.getVisionAngle();

		update();
	}

	public int checkVision() {
		if (this.getEndX() <= 0 || this.getEndY() <= 0
				||this.getEndX() >= Config.SCENE_WIDTH
				|| this.getEndY() >= Config.SCENE_HEIGHT) {

			this.setStroke(Config.SENSATION_NEGATIVE);
			return Config.WALL_PENALTY;
		}

		for (Microbe neighbor : PetriDish.getMicrobes()) {
			if (noticesNeighbor(neighbor)) {
				this.setStroke(Config.SENSATION_POSITIVE);
				return Config.MICROBE_PENALTY;
			}
		}

		this.setStroke(Config.SENSATION_NEUTRAL);
		return Config.NO_PENALTY;
	}

	private boolean noticesNeighbor(Microbe neighbor) {
		double distance = Math.pow(Math.pow((neighbor.getMyX() - this.getEndX()), 2) 
				+ Math.pow((neighbor.getMyY() - this.getEndY()), 2), 0.5);
		
		if (distance <= Config.RADIUS_DEFAULT) {
			return true;
		}
		return false;
	}

	public double getVisionX() {
		return this.getEndX();
	}

	private void setVisionX() {
		this.setEndX(this.microbe.getMyX()+(Config.RADIUS_DEFAULT*this.microbe.getVisionFieldDistance()
				*Math.cos(Math.toRadians(this.offsetAngle+this.visionAngle))));
	}

	private void setVisionY() {
		this.setEndY(this.microbe.getMyY()+(Config.RADIUS_DEFAULT*this.microbe.getVisionFieldDistance()
				*Math.sin(Math.toRadians(this.offsetAngle+this.visionAngle))));
	}

	public double getVisionY() {
		return this.getEndY();
	}

	// BUGS TRYING TO USE MORE THAN ONE POINT
	private ArrayList<Point2D> pointsOnLine() {
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		double xDelta = (this.getEndX()- this.getStartX());
		double yDelta = (this.getEndY() - this.getScaleY());

		for (int i = 1; i <= Config.VISION_POINTS; i++) {
			points.add(new Point2D(this.getStartX()+((i*xDelta)/Config.VISION_POINTS), 
					this.getStartY()+((i*yDelta)/Config.VISION_POINTS)));
		}

		return points;
	}

	public void update() {
		this.visionAngle = this.microbe.getVisionAngle();
		this.setStartX(this.microbe.getMyX());
		this.setStartY(this.microbe.getMyY());
		setVisionX();
		setVisionY();
	}

}
