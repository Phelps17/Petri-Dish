import javafx.scene.shape.Line;

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
		//going to just avoid things for now
		if (this.getVisionX() <= 0 || this.getVisionY() <= 0
				|| this.getVisionX() >= Config.SCENE_WIDTH
				|| this.getVisionY() >= Config.SCENE_HEIGHT) {
			this.setStroke(Config.SENSATION_NEGATIVE);
			return Config.WALL_PENALTY;
		}
		
		this.setStroke(Config.SENSATION_NEUTRAL);
		return 1;
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
	
	public void update() {
		this.visionAngle = this.microbe.getVisionAngle();
		this.setStartX(this.microbe.getMyX());
		this.setStartY(this.microbe.getMyY());
		setVisionX();
		setVisionY();
	}
	
}
