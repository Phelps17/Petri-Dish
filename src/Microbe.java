import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Microbe extends BorderPane {
	private static final int RADIUS_DEFAULT = 18;
	private static final int MAX_VISION_FIELD = 3;

	private int age, generation, name, clan;
	private double rPigment, gPigment, bPigment, visionFieldDistance;
	private Microbe parent1, parent2;

	public Circle body, visionField;

	//private int stomachSize, stomachSpace, hungerRate;
	//private int strength, speed, vision, fertility;

	public Microbe() {
		this.body = new Circle(0, 0, RADIUS_DEFAULT);
		getChildren().add(this.body);

		this.visionFieldDistance = 1 + Math.random() * MAX_VISION_FIELD;
		addVisionField();

		this.setLayoutY(Math.random() * 560);
		this.setLayoutX(Math.random() * 960);

		this.setAge(0);
		this.generation = 0;
		this.clan = (int)(Math.random() * 1000000);
		this.setName(this.clan + (int)(Math.random() * 1000000));

		this.parent1 = null;
		this.parent2 = null;

		this.rPigment = Math.random();
		this.bPigment = Math.random(); 
		this.gPigment = Math.random();

		this.body.setFill(new Color(this.rPigment, this.gPigment, this.bPigment, 0.5));
		this.body.setStroke(Color.GREEN);
		this.body.setStrokeWidth(3.5);

		this.visionField.setFill(new Color(0, 0, 0, 0));
		this.visionField.setStroke(Color.BLACK);
		this.visionField.setStrokeWidth(1);

		this.setRotate(Math.random() * 360);

		System.out.println("ADDED:");
		System.out.println(this.toString());
		System.out.println();
		
		
	}

	public Microbe(Microbe parent1, Microbe parent2) {
		this();

		//TODO: adjust vision field accordingly

		this.parent1 = parent1;
		this.parent2 = parent2;

		//need to make this genetic**********************
		this.rPigment = ((parent1.getrPigment() + parent2.getrPigment()) / 2);
		this.bPigment = ((parent1.getbPigment() + parent2.getbPigment()) / 2);
		this.gPigment = ((parent1.getgPigment() + parent2.getgPigment()) / 2);
		//***********************************************
	}

	private void addVisionField() {
		this.visionField = new Circle(0, 0, RADIUS_DEFAULT*this.visionFieldDistance);
		getChildren().add(this.visionField);

		getChildren().add(new Line(0, 0, (RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-25))), 
				(RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-25)))));
		getChildren().add(new Line(0, 0, (RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-45))), 
				(RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-45)))));
		getChildren().add(new Line(0, 0, (RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-60))), 
				(RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-60)))));
		getChildren().add(new Line(0, 0, (RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-90))), 
				(RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-90)))));
		getChildren().add(new Line(0, 0, (RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-115))), 
				(RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-115)))));
		getChildren().add(new Line(0, 0, (RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-135))), 
				(RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-135)))));
		getChildren().add(new Line(0, 0, (RADIUS_DEFAULT*this.visionFieldDistance*Math.cos(Math.toRadians(-150))), 
				(RADIUS_DEFAULT*this.visionFieldDistance*Math.sin(Math.toRadians(-150)))));
	}

	public void update(ObservableList<Node> surroundings) {
		double delta = 0;

		for (Node node : surroundings) {
			if (!node.equals(this)) {
				
				double myX = getMyX();
				double myY = getMyY();
				double itsX = 0;
				double itsY = 0;
				
				delta = Math.sqrt(Math.abs(Math.pow((myX - itsX), 2) + Math.pow((myY - itsY), 2)));
				
				if (node instanceof Microbe) {
					Microbe nearby = (Microbe)node;

					if (delta < (2*this.body.getRadius())) {
						System.out.println(this.name + " collided with " + nearby.getName() + ". Delta = " + delta);
					}
				}
				else {
					itsX = ((Circle) node).getCenterX();
					itsY = ((Circle) node).getCenterY();
					
					delta = Math.sqrt(Math.abs(Math.pow((myX - itsX), 2) + Math.pow((myY - itsY), 2)));
				}
				
				if (node instanceof Bouncer) {
					//System.out.println("This x: " + myX);
					//System.out.println("Nearby x: " + itsX);
					//System.out.println("This y: " + myY);
					//System.out.println("Nearby y: " + itsY);
					//System.out.println("Delta: " + delta);
					
					if (delta < (this.body.getRadius() + Bouncer.RADIUS_DEFAULT)) {
						
						System.out.println(this.name + " collided with a bouncer. delta = " + delta);
					}
				}
				else if (node instanceof Snack) {
					if (delta < (this.body.getRadius() + Snack.RADIUS_DEFAULT)) {
						System.out.println(this.name + " collided with a snack. delta = " + delta);
					}
				}
				else if (node instanceof Food) {
					if (delta < (this.body.getRadius() + Food.RADIUS_DEFAULT)) {
						
						System.out.println(this.name + " collided with food. delta = " + delta);
					}
				}
			}
		}
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public int getClan() {
		return clan;
	}
	
	public double getMyX() {
		return this.getBoundsInParent().getMinX()+(this.getBoundsInParent().getWidth()/2);
	}
	
	public double getMyY() {
		return this.getBoundsInParent().getMinY()+(this.getBoundsInParent().getHeight()/2);
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
		this.generation + "\nAge: " + this.age + "\nRGB: " + this.rPigment + " " + 
		this.gPigment + " " + this.bPigment + "\nCurrent Coordinates: (" + myX + ", " + myY + ")";
	}

	public Microbe reproduceWith(Microbe partner) {
		this.age++;
		partner.setAge(partner.getAge()+1);

		//TODO update ages of grandparents

		return new Microbe(this, partner);
	}
}
