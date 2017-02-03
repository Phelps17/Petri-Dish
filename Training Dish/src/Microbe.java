import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class Microbe {
	private double x, y, visionAngle;

	private int generation, name, clan;
	private double rPigment, gPigment, bPigment, visionFieldDistance;
	private Microbe parent1, parent2;

	public ArrayList<VisionLine> visionLines;
	private Brain brain;

	//private int stomachSize, stomachSpace, hungerRate;
	//private int strength, speed, vision, fertility;

	public Microbe() {
		this.x = Config.RADIUS_DEFAULT + Math.random() * (960 - Config.RADIUS_DEFAULT);
		this.y = Config.RADIUS_DEFAULT + Math.random() * (560 - Config.RADIUS_DEFAULT);

		setupVision();

		this.brain = new Brain(this);

		this.generation = 0;
		this.clan = (int)(Math.random() * 1000000);
		this.setName(this.clan + (int)(Math.random() * 1000000));

		this.parent1 = null;
		this.parent2 = null;

		this.rPigment = Math.random();
		this.bPigment = Math.random(); 
		this.gPigment = Math.random();

		System.out.println("ADDED " + PetriDish.getMicrobes().size() + ":");
		System.out.println(this.toString());
		System.out.println();
	}

	public Microbe(Microbe parent1, Microbe parent2) {
		this();

		this.parent1 = parent1;
		this.parent2 = parent2;

		//TODO need to make this genetic**********************
		this.visionFieldDistance = ((parent1.getVisionFieldDistance() + parent2.getVisionFieldDistance()) / 2);
		this.rPigment = ((parent1.getrPigment() + parent2.getrPigment()) / 2);
		this.bPigment = ((parent1.getbPigment() + parent2.getbPigment()) / 2);
		this.gPigment = ((parent1.getgPigment() + parent2.getgPigment()) / 2);
		//****************************************************
	}

	public void update(ArrayList<Shape> updatedBodies) {
		//TODO add a surroundings parameters for vision
		this.brain.fireNeurons();

		updateX();
		updateY();
		updateAngle();

		drawMicrobe(updatedBodies);
	}

	private void updateX() {
		if (this.getMyX()+Config.RADIUS_DEFAULT < Config.SCENE_WIDTH 
				&& this.getMyX()-Config.RADIUS_DEFAULT > 0) {
			this.x += this.brain.getXDelta();
		}
	}

	private void updateY() {
		if (this.getMyY()+Config.RADIUS_DEFAULT < Config.SCENE_HEIGHT 
				&& this.getMyY()-Config.RADIUS_DEFAULT > 0) {
			this.y += this.brain.getYDelta();
		}
	}

	private void updateAngle() {
		this.visionAngle += this.brain.getAngleDelta();
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public double getrPigment() {
		return rPigment;
	}

	public void setrPigment(double rPigment) {
		this.rPigment = rPigment;
	}

	public double getbPigment() {
		return bPigment;
	}

	public void setbPigment(double bPigment) {
		this.bPigment = bPigment;
	}

	public double getgPigment() {
		return gPigment;
	}

	public void setgPigment(double gPigment) {
		this.gPigment = gPigment;
	}

	public int getGeneration() {
		return generation;
	}

	public void increaseGeneration() {
		this.generation++;

		if (this.parent1 != null) {
			this.parent1.increaseGeneration();
		}
		if (this.parent2 != null) {
			this.parent2.increaseGeneration();
		}
	}

	public int getClan() {
		return clan;
	}

	public double getMyX() {
		return this.x;
	}

	public double getMyY() {
		return this.y;
	}

	public double getVisionFieldDistance() {
		return this.visionFieldDistance;
	}

	public ArrayList<VisionLine> getVisionLines() {
		return this.visionLines;
	}

	public double getVisionAngle() {
		return this.visionAngle;
	}

	public Microbe reproduceWith(Microbe partner) {
		this.increaseGeneration();
		partner.increaseGeneration();

		return new Microbe(this, partner);
	}

	private void setupVision() {
		this.visionFieldDistance = 1 + Math.random() * Config.MAX_VISION_FIELD;
		this.visionAngle = Math.random() * 360;
		this.visionLines = new ArrayList<VisionLine>();

		this.visionLines.add(new VisionLine(this, -25));
		this.visionLines.add(new VisionLine(this, -45));
		this.visionLines.add(new VisionLine(this, -65));
		this.visionLines.add(new VisionLine(this, -90));
		this.visionLines.add(new VisionLine(this, -120));
		this.visionLines.add(new VisionLine(this, -135));
		this.visionLines.add(new VisionLine(this, -155));
	}

	private void updateVision() {
		for (VisionLine line : this.visionLines) {
			line.update();
		}
	}

	public String toString() {
		String parentString;
		if (parent1 == null) {
			parentString = "inoculation";
		}
		else {
			parentString = this.parent1.getName() + " " + this.parent2.getName();
		}

		double myX = getMyX();
		double myY = getMyY();

		return "Name: " + this.name +"\nParents: " + parentString + "\nClan: " + this.clan + "\nGeneration: " + 
		this.generation + "\nRGB: " + this.rPigment + " " + 
		this.gPigment + " " + this.bPigment + "\nCurrent Coordinates: (" + myX + ", " + myY + ")";
	}

	public void drawMicrobe(ArrayList<Shape> petriDish) {
		Circle body = new Circle(this.x, this.y, Config.RADIUS_DEFAULT);
		body.setFill(new Color(this.rPigment, this.gPigment, this.bPigment, 0.5));
		body.setStroke(Color.GREEN);
		body.setStrokeWidth(3.5);
		petriDish.add(body);

		Circle visionField = new Circle(this.x, this.y, Config.RADIUS_DEFAULT*this.visionFieldDistance);
		visionField.setFill(new Color(0, 0, 0, 0));
		visionField.setStroke(Color.BLACK);
		visionField.setStrokeWidth(1);
		petriDish.add(visionField);

		updateVision();
		petriDish.addAll(this.visionLines);
	}
}
