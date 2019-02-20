

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleView3 extends Pane implements CircleEvents {

	class RadiusEvent implements EventHandler<Event> {
		@Override
		public void handle(Event event) {
			eventTypeLabel.setText("Event Type: " + eventType.RADIUS.toString());
			
			circle.setRadius(model.getRadius().doubleValue());
			circleRadiusLabel.setText("Circle radius " + model.getRadius().intValue());
			if (model.getCalculateArea().getValue() == true) {
				areaLabel.setText("Circle Area "
						+ (Math.PI * (model.getRadius().doubleValue()) * model.getRadius().doubleValue()));
			}
		}
	}

	class ColorEvent implements EventHandler<Event> {
		@Override
		public void handle(Event event) {
			eventTypeLabel.setText("Event Type: " + eventType.COLOR.toString());
			
			if (model.isFilled().getValue() == true) {
				circle.setFill(model.getColor());
				circle.setStroke(model.getColor());
			} else {
				circle.setStroke(model.getColor());
			}
		}
	}

	class FilledEvent implements EventHandler<Event> {
		@Override
		public void handle(Event event) {
			eventTypeLabel.setText("Event Type: " + eventType.FILLED.toString());
			
			if(model.getColor() == null){
				model.setColor(Color.BLACK);
			}
			
			if (model.isFilled().getValue() == true) {
				circle.setFill(model.getColor());
			} else {
				circle.setStroke(model.getColor());
				circle.setFill(Color.TRANSPARENT);
			}
		}
	}

	class AreaEvent implements EventHandler<Event> {

		@Override
		public void handle(Event event) {
			eventTypeLabel.setText("Event Type: " + eventType.AREA.toString());
			
			if(model.getRadius().doubleValue() == 0){
				model.setRadius(new SimpleDoubleProperty(20));
				
			}
			
			if (model.getCalculateArea().getValue() == true) {
				areaLabel.setText("Circle Area "
						+ (Math.PI * (model.getRadius().doubleValue()) * model.getRadius().doubleValue()));
			} else {
				areaLabel.setText("");
			}
		}
	}

	private CircleModel3 model;
	private SimpleIntegerProperty circleCounter = new SimpleIntegerProperty();
	private SimpleStringProperty eventTitle = new SimpleStringProperty("DEFAULT");

	private Circle circle = new Circle(20);

	private Label eventTypeLabel = new Label();
	private Label circleNumberLabel = new Label();
	private Label circleRadiusLabel = new Label();
	private Label areaLabel = new Label();

	public CircleView3(SimpleIntegerProperty circleCounter) {
		this.circleCounter = circleCounter;
		
		eventTypeLabel.setText("Event Type: " + eventTitle.getValue());
		circleNumberLabel.setText("Circle number " + (circleCounter.intValue() + 1));
		circleRadiusLabel.setText("Circle radius " + 20);
			
		circle.centerXProperty().bind(widthProperty().divide(2));
		circle.centerYProperty().bind(heightProperty().divide(2));
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.TRANSPARENT);

		getChildren().add(circle);

		VBox labelsBox = new VBox(5);

		labelsBox.getChildren().add(eventTypeLabel);
		labelsBox.getChildren().add(circleNumberLabel);
		labelsBox.getChildren().add(circleRadiusLabel);
		labelsBox.getChildren().add(areaLabel);

		getChildren().add(labelsBox);

	}

	public SimpleIntegerProperty getCircleCounter() {
		return circleCounter;
	}

	public void setModel(CircleModel3 model) {
		this.model = model;
		
		if (model != null) {
			model.addListener(new RadiusEvent(), eventType.RADIUS);
			model.addListener(new ColorEvent(), eventType.COLOR);
			model.addListener(new FilledEvent(), eventType.FILLED);
			model.addListener(new AreaEvent(), eventType.AREA);
		}
	}

	public CircleModel3 getModel() {
		return model;
	}
}
