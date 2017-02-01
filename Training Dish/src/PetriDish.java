import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import java.util.ArrayList;

public class PetriDish extends Application {

	static final int STARTING_FOOD = 10;
	static final int STARTING_SNACKS = 15;
	static final int STARTING_MICROBES = 4;
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		DishPane dishPane = new DishPane();
		dishPane.setStyle("-fx-background-color: azure");

		Button btAdd = new Button("+");
		Button btSubtract = new Button("-");
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(btAdd, btSubtract);
		hBox.setAlignment(Pos.CENTER);

		// Pause and resume animation
		dishPane.setOnMousePressed(e -> dishPane.pause());
		dishPane.setOnMouseReleased(e -> dishPane.play());

		// Use a scroll bar to control animation speed
		ScrollBar sbSpeed = new ScrollBar();
		sbSpeed.setMax(20);
		sbSpeed.setValue(7);
		dishPane.rateProperty().bind(sbSpeed.valueProperty());

		BorderPane pane = new BorderPane();
		pane.setCenter(dishPane);
		//pane.setBottom(hBox);

		// Create a scene and place the pane in the stage
		Scene scene = new Scene(pane, 1000, 600);
		primaryStage.setTitle("Petri Dish"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(false);
		primaryStage.show(); // Display the stage
	}

	private class DishPane extends Pane {
		private Timeline animation;
		private ArrayList<Microbe> microbes; 

		public DishPane() {
			microbes = new ArrayList<Microbe>();
			animation = new Timeline(
					new KeyFrame(Duration.millis(50), e -> updateDish()));
			animation.setCycleCount(Timeline.INDEFINITE);
			//animation.play(); // Start animation
			innoculateDish();
			updateDish();
		}

		private void innoculateDish() {
			for (int i = 0; i < STARTING_MICROBES; i++) {
				Microbe m = new Microbe();
				this.microbes.add(m);
			}
		}

		public void play() {
			animation.play();
		}

		public void pause() {
			animation.pause();
		}

		public DoubleProperty rateProperty() {
			return animation.rateProperty();
		}
		
		private void updateDish() {
			updateMicrobes();
		}
		
		private void updateMicrobes() {
			ArrayList<Shape> updatedBodies = new ArrayList<Shape>();
			
			for (Microbe microbe : this.microbes) {
				microbe.update(updatedBodies);
			}
			
			for (Shape shape : updatedBodies) {
				getChildren().add(shape);
			}
			
			System.out.println("DRAWN");
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}