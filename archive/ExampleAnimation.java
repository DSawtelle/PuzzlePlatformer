import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ExampleAnimation extends Application {

    private final Image IMAGE = new Image("images/gumball.png");

    private static final int COLUMNS	= 6; // number of columns in sprite sheet
    
    // ****** if you change the COUNT = 12
    // it will do the first 6 on row 1,
    // then the next 6 on row 2 of the
    // gumball.png sprite sheet ******
    private static final int COUNT 		= 6; // the amount of sprites in the sprite sheet you
    									 	 // want to animate (will go to next row if needed)
    
    private static final int OFFSET_X 	= 0; // top left x position
    private static final int OFFSET_Y	= 0; // top left y position
    private static final int WIDTH		= 32; // width of rectangle
    private static final int HEIGHT 	= 32; // height off rectangle

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Example Animation");
        Button startButton = new Button("Start Animation");
        Button stopButton = new Button("Stop Animation");
        BorderPane pane = new BorderPane();
        
        // ImageView made from the sprite sheet
        final ImageView imageView = new ImageView(IMAGE);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
        
        Group group = new Group(imageView);
        pane.setTop(startButton);
        pane.setCenter(group);
        pane.setBottom(stopButton);
        
        // animation via SpriteAnimation class
        final Animation animation = new SpriteAnimation(
                imageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        
        animation.setCycleCount(Animation.INDEFINITE);
        
        // start button
        startButton.setOnAction(e -> {
        	imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT)); // reset viewport to original position, looks cleaner
        	animation.play();
        });
        
        // stop button
        stopButton.setOnAction(e -> {
        	imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT)); // reset viewport to original position, looks cleaner
        	animation.stop();
        });

        primaryStage.setScene(new Scene(pane, 300, 300));
        primaryStage.show();
    }
}