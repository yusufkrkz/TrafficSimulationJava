// This class represents possible spawn points for each car and is initialized at the beginning of the paths.
public class SpawnPoint {

	// X coordinate of the Spawn Point, also used as the X value for the MoveTo element in a path.
	private double x;

	// Y coordinate of the Spawn Point, also used as the Y value for the MoveTo element in a path.
	private double y;

	// Indicates if a car can spawn here. True if no other car is nearby.
	public boolean IsFree = false;

	// Constructor initializes spawn point with given coordinates.
	public SpawnPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Getter and Setter Methods Of this class.

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}
