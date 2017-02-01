import javafx.scene.shape.Line;

public class VisionLine extends Line {

	private Microbe microbe;
	private double endX, endY;
		
	public VisionLine(Microbe microbe, double endX, double endY) {
		super(microbe.getMyX(), microbe.getMyY(), endX, endY);
		
		this.microbe = microbe;
		this.endX = endX;
		this.endY = endY;
	}
	
	public int checkVision() {
		//TODO update depending on what the line sees
		
		return 0;
	}
	
	public double getVisionX() {
		return this.endX;
	}
	
	public double getVisionY() {
		return this.endY;
	}
	
}
