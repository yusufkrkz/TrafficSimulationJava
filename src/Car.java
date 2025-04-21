import java.util.ArrayList;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// This class represents car objects.
public class Car extends Pane {
	// Constants defining the appearance of the car
	private final int ARCHEIGHT_OF_CAR = 7;
	private final int ARCWIDTH_OF_CAR = 7;
	private final int HEIGHT_OF_CAR = 8;
	private final int WIDTH_OF_CAR = 16;
	private Color COLOR_OF_CAR = Color.BLACK;
	private final double DISTANCE_TO_CRASHED_CARS = 21;

	// Initial position is set to -50 to avoid briefly showing the car at (0, 0)
	private final int INITIAL_POSITION_OF_CAR = -50;

	// Each car has a PathTransition object to control its animation on the path
	public PathTransition pathTransition;

	// Index represents the specific path this car follows
	public int index;

	// The Rectangle object visually represents the car in the GUI
	public Rectangle Car;

	// Shared counters to track the number of cars that crash or succeed in a level
	public static int CarCrash = 0;
	public static int CarWin = 0;

	// Constructor that assigns the path and index to the car and builds its shape
	public Car(int index, PathTransition pt) {
		this.index = index;
		buildCar(); // Initialize the car’s shape and appearance
		this.pathTransition = pt;
	}

	// Method to initialize and style the car (as a rounded rectangle)
	public void buildCar() {
		Rectangle rectangle = new Rectangle();

		rectangle.setX(INITIAL_POSITION_OF_CAR); // Start off-screen
		rectangle.setY(INITIAL_POSITION_OF_CAR);

		rectangle.setArcHeight(ARCHEIGHT_OF_CAR); // Rounded edges
		rectangle.setArcWidth(ARCWIDTH_OF_CAR);
		rectangle.setHeight(HEIGHT_OF_CAR);
		rectangle.setWidth(WIDTH_OF_CAR);
		rectangle.setFill(COLOR_OF_CAR); // Car color

		this.Car = rectangle; // Save reference for collision checking etc.
	}

	// Getter for the car’s path index
	public int getIndex() {
		return this.index;
	}

	// Getter for the car's Rectangle shape
	public Rectangle getCar() {
		return this.Car;
	}

	// Checks whether any red traffic lights intersect with the car's position
	// If there is a red light ahead, car stops by pausing animation
	public void checkTrafficLights(ArrayList<TrafficLight> trArrayList) {
		boolean isTrafficLightNearby = false;

		for (int i = 0; i < trArrayList.size(); i++) {
			// Check if car overlaps with a red light
			if (trArrayList.get(i).getTrNode().getBoundsInParent().intersects((Car.getBoundsInParent()))
					&& trArrayList.get(i).getColor().equals(Color.RED)) {
				pathTransition.pause(); // Stop car if red light is nearby
				isTrafficLightNearby = true;
				break;
			}
		}

		// If no red light, continue moving
		if (!isTrafficLightNearby) {
			pathTransition.play();
		}
	}

	// Checks for nearby crashed cars and pauses this car if any are too close
	public void checkCollisions(ArrayList<Car> crashedCarList) {
		for (int i = 0; i < crashedCarList.size(); i++) {
			// Calculate distance to each crashed car
			double distance = Main.calculateDistance(Car.getTranslateX(), Car.getTranslateY(),
					crashedCarList.get(i).Car.getTranslateX(), crashedCarList.get(i).Car.getTranslateY());

			// If another crashed car is too close, pause this car’s animation
			if (distance < DISTANCE_TO_CRASHED_CARS) {
				pathTransition.pause();
				break; // No need to check further, one nearby crash is enough
			}
		}
	}
}
