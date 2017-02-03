import javafx.scene.paint.Color;

public class Config {

	// Petri Dish Render Settings
	public static final double SCENE_HEIGHT = 600;
	public static final double SCENE_WIDTH = 1000;
	public static final String SCENE_STYLE = "-fx-background-color: azure";
	public static final String SCENE_TITLE = "Petri Dish";

	// Default Environment Settings
	public static final int STARTING_FOOD = 10;
	public static final int STARTING_SNACKS = 15;
	public static final int STARTING_MICROBES = 3;

	// Microbe Settings
	public static final int RADIUS_DEFAULT = 18;
	public static final int MAX_VISION_FIELD = 3;

	// Microbe Brain Settings
	public static final int BRAIN_HIDDEN_LAYERS = 1;
	public static final int BRAIN_HIDDEN_LAYER_PERCEPTRONS = 1;
	public static final double DELTA_REGULATOR = 1;

	// Collision penalties
	public static final int FOOD_PENALTY = 5;
	public static final int SNACK_PENALTY = 2;
	public static final int MICROBE_PENALTY = 1; // will make this dynamic later to get them to interact
	public static final int WALL_PENALTY = 1;
	public static final int BOUNCER_PENALTY = -5;
	
	// VisionLine Settings
	public static final Color SENSATION_NEUTRAL = Color.BLACK;
	public static final Color SENSATION_NEGATIVE = Color.RED;
	public static final Color SENSATION_POSITIVE = Color.GREEN;

	// Processing Function
	public static double sigmoid(double x)
	{
		return -1 + 2*(1/(1 + Math.exp(-x)));
	}
}