import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

// This class represents Traffic Light objects used in the simulation.
public class TrafficLight extends Pane {

	// Coordinates defining the endpoints of the traffic light's pole.
	private double x1;
	private double y1;
	private double x2;
	private double y2;

	// Constant for the circle's radius representing the light.
	private final double RADIUS_OF_TRAFFIC_LIGHT_CIRCLE = 6;

	// Current color of the traffic light (used for car behavior logic).
	private Color color = Color.GREEN;

	// Grouped node containing both line and circle to represent the light.
	Node trNode;

	// Constructor initializing coordinates and building the traffic light.
	public TrafficLight(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		constructTrafficLight();
	}

	// Constructs the visual elements: a line (pole) and a circle (light).
	// Also sets up interactivity for toggling light color on click.
	public void constructTrafficLight() {
		Line line = new Line();
		line.setStroke(Color.BLACK);
		line.setStartX(x1);
		line.setEndX(x2);
		line.setStartY(y1);
		line.setEndY(y2);

		// Circle positioned at the midpoint of the line.
		Circle circle = new Circle(getCenterX(), getCenterY(), RADIUS_OF_TRAFFIC_LIGHT_CIRCLE, Color.GREEN);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.GREEN); // Initially green.

		// Toggle color between GREEN and RED on user click.
		circle.setOnMouseClicked(e -> {
			if (circle.getFill().equals(Color.GREEN)) {
				this.color = Color.RED;
				circle.setFill(Color.RED);
			} else {
				this.color = Color.GREEN;
				circle.setFill(Color.GREEN);
			}
		});

		// Group the line and circle into one node to represent the full traffic light.
		Group p = new Group();
		p.getChildren().addAll(line, circle);
		this.trNode = p;
	}

	// Returns the grouped visual node representing the traffic light.
	public Node getTrNode() {
		return this.trNode;
	}

	// Computes the X coordinate of the center between x1 and x2.
	public double getCenterX() {
		return (x1 + x2) / 2;
	}

	// Computes the Y coordinate of the center between y1 and y2.
	public double getCenterY() {
		return (y1 + y2) / 2;
	}

	// Returns the current color of the traffic light.
	public Color getColor() {
		return this.color;
	}
}
