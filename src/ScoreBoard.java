
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

// This class represents a ScoreBoard object. It is used for counting scores and crashes,
// and is responsible for displaying score and crash counts along with related images on the screen.
public class ScoreBoard extends Pane {
	
	// Static variables to keep track of score and crash counts globally.
	public static int Score = 0;
	public static int Crash = 0;

	private static Label scoreLabel;      // Label for displaying score
	private static Label CrashLabel;      // Label for displaying crashes
	private ImageView scoreImageV;        // ImageView for score icon
	private ImageView crashImageV;        // ImageView for crash icon

	// Constructor: initializes the scoreboard with images and labels.
	public ScoreBoard() {
		// Load crash and score images from resources
		Image crashImage = new Image("crash.png");
		Image scoreImage = new Image("score.png");

		// Create ImageView nodes for the images
		crashImageV = new ImageView(crashImage);
		scoreImageV = new ImageView(scoreImage);

		// Set default size for the icons
		crashImageV.setFitWidth(50);
		scoreImageV.setFitWidth(50);
		crashImageV.setFitHeight(50);
		scoreImageV.setFitHeight(50);

		// Create Labels with initial score and crash values, using images as graphic
		scoreLabel = new Label("" + Score, scoreImageV);
		CrashLabel = new Label("" + Crash, crashImageV);
	}

	// Resets score and crash counters to zero at the end of a level
	public static void resetScore() {
		Score = 0;
		Crash = 0;
	}

	// Creates and returns a Pane containing the updated scoreboard visuals
	public Pane createScoreBoard() {
		// Set new size for scoreboard display icons
		crashImageV.setFitWidth(75);
		scoreImageV.setFitWidth(75);
		crashImageV.setFitHeight(75);
		scoreImageV.setFitHeight(75);

		// Update Labels to show current score and crash counts with maximums
		scoreLabel = new Label("" + Score + "/" + Car.CarWin);
		CrashLabel = new Label("" + Crash + "/" + Car.CarCrash);

		// Apply styling to labels for better visibility
		scoreLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
		CrashLabel.setStyle("-fx-font-size: 20;-fx-font-weight: bold;");

		// (Optional) Manual position adjustment for labels, though layout is handled by StackPane
		scoreLabel.setLayoutX(50);
		scoreLabel.setLayoutY(25);
		CrashLabel.setLayoutX(50);
		CrashLabel.setLayoutY(25);

		// StackPane to overlay score label on top of score image
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(scoreImageV, scoreLabel);
		stackPane.setLayoutX(60);
		stackPane.setLayoutY(40);

		// StackPane to overlay crash label on top of crash image
		StackPane stackPane2 = new StackPane();
		stackPane2.getChildren().addAll(crashImageV, CrashLabel);
		stackPane2.setLayoutX(60);
		stackPane2.setLayoutY(40);

		// VBox to vertically align score and crash StackPanes
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(stackPane, stackPane2);
		vbox.setLayoutX(60);
		vbox.setLayoutY(40);

		return vbox;
	}

	// Increments the score and updates the corresponding label
	public static void UpdateScore() {
		Score++;
		scoreLabel.setText("" + Score + "/" + Car.CarWin);
	}

	// Increments the crash count and updates the corresponding label
	public static void UpdateCrash() {
		Crash++;
		CrashLabel.setText("" + Crash + "/" + Car.CarCrash);
	}

	// Returns the scoreboard UI component as a Node
	public Node getScoreboard() {
		return createScoreBoard();
	}
}
