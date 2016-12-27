import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Microbe extends StackPane {
	private static final int RADIUS_DEFAULT = 18;

	private int age, generation, name, clan;
	private double rPigment, gPigment, bPigment;
	private Microbe parent1, parent2;

	private Circle body;

	//private int stomachSize, stomachSpace, hungerRate;
	//private int strength, speed, vision, fertility;

	public Microbe() {
		this.body = new Circle(0, 0, RADIUS_DEFAULT);
		getChildren().add(this.body);
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

		System.out.println("ADDED:");
		System.out.println(this.toString());
		System.out.println();
	}

	public Microbe(Microbe parent1, Microbe parent2) {
		this();

		this.parent1 = parent1;
		this.parent2 = parent2;

		//need to make this genetic**********************
		this.rPigment = ((parent1.getrPigment() + parent2.getrPigment()) / 2);
		this.bPigment = ((parent1.getbPigment() + parent2.getbPigment()) / 2);
		this.gPigment = ((parent1.getgPigment() + parent2.getgPigment()) / 2);
		//***********************************************
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

	public String toString() {
		String parentString;
		if (parent1 == null) {
			parentString = "inoculation";
		}
		else {
			parentString = this.parent1.getName() + " " + this.parent2.getName();
		}

		return "Name: " + this.name +"\nParents: " + parentString + "\nClan: " + this.clan + "\nGeneration: " + 
		this.generation + "\nAge: " + this.age + "\nRGB: " + this.rPigment + " " + 
		this.gPigment + " " + this.bPigment;
	}

	public Microbe reproduceWith(Microbe partner) {
		this.age++;
		partner.setAge(partner.getAge()+1);

		return new Microbe(this, partner);
	}
}
