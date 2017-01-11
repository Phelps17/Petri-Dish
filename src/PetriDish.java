import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PetriDish extends Application {

	static final int STARTING_FOOD = 5;
	static final int STARTING_SNACKS = 15;
	static final int STARTING_MICROBES = 1;
	
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

		public DishPane() {
			// Create an animation for moving the bouncers
			animation = new Timeline(
					new KeyFrame(Duration.millis(50), e -> updateDish()));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play(); // Start animation
			setInitialBouncers();
			innoculateDish();
		}

		public void setInitialBouncers() {
			getChildren().add(new Bouncer(480, 380));
			getChildren().add(new Bouncer(70 , 380));
			getChildren().add(new Bouncer(70, 70));
			getChildren().add(new Bouncer(480, 70));
			getChildren().add(new Bouncer(275, 225));
			getChildren().add(new Bouncer(225, 525));
			getChildren().add(new Bouncer(775, 225));
			getChildren().add(new Bouncer(725, 525));

			for (int i = 0; i < STARTING_FOOD; i++) {
				getChildren().add(new Food((25 + Math.random()*900), (25 + Math.random()*500)));
			}
			
			for (int i = 0; i < STARTING_SNACKS; i++) {
				getChildren().add(new Snack((25 + Math.random()*900), (25 + Math.random()*500)));
			}
		}

		private void innoculateDish() {
			for (int i = 0; i < STARTING_MICROBES; i++) {
				getChildren().add(new Microbe());
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
			updateNonMicrobes();
		}

		private void updateNonMicrobes() {
			for (Node node: this.getChildren()) {
				if (!(node instanceof Microbe)) {
					NonMicrobe entity = (NonMicrobe)node;
					// Check boundaries
					if (entity.getCenterX() < entity.getRadius() || 
							entity.getCenterX() > getWidth() - entity.getRadius()) {
						entity.dx *= -1; // Change move direction
					}
					if (entity.getCenterY() < entity.getRadius() || 
							entity.getCenterY() > getHeight() - entity.getRadius()) {
						entity.dy *= -1;
					}
					if (entity.isColliding(this.getChildren())) {
						if (entity.dx > 0) {
							entity.dx = (-1 * (1 + Math.random()));
						}
						else {
							entity.dx = (1 + Math.random());
						}

						if (entity.dy > 0) {
							entity.dy = (-1 * (1 + Math.random()));
						}
						else {
							entity.dy = (1 + Math.random());
						}
					}

					// Adjust bouncer position
					entity.setCenterX(entity.getCenterX() + (entity.speed*entity.dx));
					entity.setCenterY(entity.getCenterY() + (entity.speed*entity.dy));
				}
			}
		}
		
		private void updateMicrobes() {
			for (Node node : this.getChildren()) {
				if (node instanceof Microbe) {
					Microbe microbe = (Microbe)node;
					
					
				}
			}
		}

	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}