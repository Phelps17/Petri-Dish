import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class Microbe {
	private double x, y, visionAngle;

	private int generation, name, clan;
	private double rPigment, gPigment, bPigment, visionFieldDistance;
	private Microbe parent1, parent2;

	public VisionLine sight1, sight2, sight3, sight4, sight5, sight6, sight7;
	private Brain brain;
	
	//private int stomachSize, stomachSpace, hungerRate;
	//private int strength, speed, vision, fertility;

	public Microbe() {
		this.x = Config.RADIUS_DEFAULT + Math.random() * (960 - Config.RADIUS_DEFAULT);
		this.y = Config.RADIUS_DEFAULT + Math.random() * (560 - Config.RADIUS_DEFAULT);
		this.visionFieldDistance = 1 + Math.random() * Config.MAX_VISION_FIELD;
		this.visionAngle = Math.random() * 360;

		this.brain = new Brain(this);
		
		this.generation = 0;
		this.clan = (int)(Math.random() * 1000000);
		this.setName(this.clan + (int)(Math.random() * 1000000));

		this.parent1 = null;
		this.parent2 = null;

		this.rPigment = Math.random();
		this.bPigment = Math.random(); 
		this.gPigment = Math.random();

		System.out.println("ADDED:");
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
		//TODO add a surroundings parameters and
		//process perceptrons here
		drawMicrobe(updatedBodies);
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

	public Microbe reproduceWith(Microbe partner) {
		this.increaseGeneration();
		partner.increaseGeneration();

		return new Microbe(this, partner);
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

		this.sight1 = new VisionLine(this, 
				this.getMyX()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-25+this.visionAngle))), 
				this.getMyY()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-25+this.visionAngle))));
		petriDish.add(this.sight1);
		this.sight2 = new VisionLine(this, 
				this.getMyX()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-45+this.visionAngle))), 
				this.getMyY()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-45+this.visionAngle))));
		petriDish.add(this.sight2);
		this.sight3 = new VisionLine(this, 
				this.getMyX()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-60+this.visionAngle))), 
				this.getMyY()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-60+this.visionAngle))));
		petriDish.add(this.sight3);
		this.sight4 = new VisionLine(this, 
				this.getMyX()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-90+this.visionAngle))), 
				this.getMyY()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-90+this.visionAngle))));
		petriDish.add(this.sight4);
		this.sight5 = new VisionLine(this, 
				this.getMyX()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-120+this.visionAngle))), 
				this.getMyY()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-120+this.visionAngle))));
		petriDish.add(this.sight5);
		this.sight6 = new VisionLine(this, 
				this.getMyX()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-135+this.visionAngle))), 
				this.getMyY()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-135+this.visionAngle))));
		petriDish.add(this.sight6);
		this.sight7 = new VisionLine(this, 
				this.getMyX()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-155+this.visionAngle))), 
				this.getMyY()+(Config.RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-155+this.visionAngle))));
		petriDish.add(this.sight7);

	}
}
