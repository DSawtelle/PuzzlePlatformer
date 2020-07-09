import javafx.animation.*;
import javafx.util.Duration;
import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
public class Menu extends Application{
	private Stage primaryStage;
	private PPView view;
	public Menu(Stage primaryStage, PPView view) {
		this.primaryStage = primaryStage;
		this.view = view;
	}
	public void start(Stage stage){
		Pane pane = new Pane();
		pane.setId("menu");
		pane.setPrefSize(800, 500);

		Rectangle title = new Rectangle(300, 70);
		title.setStroke(Color.WHITE);
		title.setStrokeWidth(3);
		title.setFill(null);
		Text text = new Text("PUZZLE PLATFORMER");
		text.setFill(Color.WHITE);
		text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		StackPane stack = new StackPane();
		stack.setAlignment(Pos.CENTER);
		stack.getChildren().add(title);
		stack.getChildren().add(text);
		pane.getChildren().addAll(stack);
		
		stack.setTranslateX(50);
		stack.setTranslateY(100);
		FadeTransition titleFT = new FadeTransition(Duration.millis(1500), title);
		titleFT.setFromValue(1.0);
		titleFT.setToValue(0.2);
		titleFT.setCycleCount(Timeline.INDEFINITE);
		titleFT.setAutoReverse(true);
		titleFT.play();

		// Button newGame = new Button("New Game");
		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnMousePressed(event -> {
			System.out.println("New Game");
		});
		MenuItem loadGame = new MenuItem("Load Game");
		loadGame.setOnMousePressed(event -> {
			try {
				view.loadGame("");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		MenuItem saveGame = new MenuItem("Save Game");
		saveGame.setOnMousePressed(event -> {
			try {
				view.saveGame("");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		MenuItem quit = new MenuItem("Quit");
		quit.setOnMousePressed(event -> {
			primaryStage.close();
			stage.close();
		});
		VBox menuOptions = new VBox(
			newGame,
			createLine(),
			loadGame,
			createLine(),
			saveGame,
			createLine(),
			quit);

		menuOptions.setTranslateX(50);
		menuOptions.setTranslateY(200);
		pane.getChildren().add(menuOptions);
		Scene scene = new Scene(pane);
		scene.getStylesheets().addAll(this.getClass().getResource("data/style.css").toExternalForm());
		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setOnCloseRequest((e) ->{
			view.setRunning(true);
		});
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	
	}

	public Line createLine(){
		Line line = new Line();
		line.setEndX(200);
		line.setStroke(Color.WHITE);
		return line;		
	}
	public class MenuItem extends StackPane{
		public MenuItem(String name){
			Rectangle option = new Rectangle(180, 40);
			option.setOpacity(0.2);
			Text text = new Text(name);
			text.setFill(Color.BLACK);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,30));
			setAlignment(Pos.CENTER);
			setOnMouseEntered(event -> {
				option.setFill(Color.DARKGREY);
				option.setStroke(Color.WHITE);
				text.setFill(Color.WHITE);
			});
			setOnMouseExited(event -> {
				option.setFill(Color.BLACK);
				option.setStroke(null);
				text.setFill(Color.BLACK);
			});

			getChildren().addAll(option, text);
		}
	}
	
	
}