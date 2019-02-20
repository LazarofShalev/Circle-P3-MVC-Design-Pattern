


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MultiplyCircleMVC3 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Button btControllerView = new Button("Show Controller and View");
		SimpleIntegerProperty circleCounter = new SimpleIntegerProperty(0);

		HBox controllerViewPane = new HBox();
		controllerViewPane.setSpacing(10);
		controllerViewPane.setAlignment(Pos.CENTER);

		controllerViewPane.getChildren().add(btControllerView);

		btControllerView.setOnAction(e -> {

			CircleModel3 CircleModel = new CircleModel3(new SimpleIntegerProperty(circleCounter.intValue()));

			CircleController3 CircleController = new CircleController3(new SimpleIntegerProperty(circleCounter.intValue()));

			CircleView3 circuleView = new CircleView3(new SimpleIntegerProperty(circleCounter.getValue()));

			/** controller **/

			CircleController.setModel(CircleModel);
			circuleView.setModel(CircleModel);

			Stage controllerStage = new Stage();
			Scene controllerScene = new Scene(CircleController);

			controllerStage.setTitle("Controller number " + (circleCounter.intValue() + 1));
			controllerStage.setScene(controllerScene);
			controllerStage.show();
			controllerStage.setAlwaysOnTop(true);
			controllerStage.setResizable(false);

			/** view **/

			Stage viewStage = new Stage();
			Scene viewScene = new Scene(circuleView, 400, 150);

			viewStage.setTitle("View number " + (circleCounter.intValue() + 1));
			viewStage.setScene(viewScene);
			viewStage.show();
			viewStage.setAlwaysOnTop(true);

			circleCounter.setValue(circleCounter.intValue() + 1);

		});

		Scene mainScene = new Scene(controllerViewPane, 200, 350);
		primaryStage.setTitle("MultiplyCircleMVC3");
		primaryStage.setScene(mainScene);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> Platform.exit());

	}

	public static void main(String[] start) {
		launch(start);

	}

}