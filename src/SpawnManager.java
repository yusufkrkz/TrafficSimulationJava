
import java.util.ArrayList;
import javafx.scene.shape.Circle;

// This class is used for determining whether spawn points are free or not.
// It also returns a randomly selected free path for path transition.
// If no path is available, it returns no path, meaning no cars should spawn at that moment.

public class SpawnManager {

	// List of cars currently on the map.
	public ArrayList<Car> carsOnMap;

	// List of spawn points, each corresponding to a path index.
	// The index in this list matches the index of the path in main initialization.
	public ArrayList<SpawnPoint> spawnPoints;

	// Maximum number of attempts to find a free spawn point before giving up.
	private final int MAX_ITERATION_FOR_FINDING_PATH = 5;

	public SpawnManager() {
		carsOnMap = new ArrayList<>();
		spawnPoints = new ArrayList<>();
	}

	// Checks whether a specific spawn point is free by checking if any car intersects with it.
	public boolean IsSpawnPointFree(double x, double y, int pathIndex) {
		// Draws a circle around the spawn point as a hitbox for collision detection.
		Circle circle = new Circle();
		circle.setCenterX(x);
		circle.setCenterY(y);
		circle.setRadius(25); // Radius based on car size.

		// Iterates through cars on the map.
		for (int i = 0; i < carsOnMap.size(); i++) {
			// Only checks cars that belong to the same path index.
			if (carsOnMap.get(i).index == pathIndex) {
				// If the car intersects with the spawn point, return false (not free).
				if (circle.getBoundsInParent().intersects(carsOnMap.get(i).Car.getBoundsInParent())) {
					return false;
				}
			}
		}
		// No intersecting car found, spawn point is free.
		return true;
	}

	// Returns the index of a randomly selected free spawn point.
	public int returnFreeSpawn() {
		// Updates IsFree status of each spawn point.
		for (int i = 0; i < spawnPoints.size(); i++) {
			spawnPoints.get(i).IsFree = IsSpawnPointFree(spawnPoints.get(i).getX(), spawnPoints.get(i).getY(), i);
		}

		// Counts how many spawn points are free.
		int count = 0;
		for (int i = 0; i < spawnPoints.size(); i++) {
			if (spawnPoints.get(i).IsFree == true)
				count++;
		}

		// If no spawn points are free, return -1 to indicate no car should spawn.
		if (count == 0) {
			return -1;
		}

		// Attempts to find a free spawn point up to MAX_ITERATION_FOR_FINDING_PATH times.
		// This avoids deterministic spawning and ensures some randomness.
		for (int i = 0; i < MAX_ITERATION_FOR_FINDING_PATH; i++) {
			int random = (int) (Math.random() * (spawnPoints.size()));
			if (spawnPoints.get(random).IsFree == true) {
				return random;
			}
		}

		// If no free spawn point found in the allowed attempts, return -1.
		return -1;
	}
}
