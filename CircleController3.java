

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CircleController3 extends GridPane implements CircleEvents, EventHandler<ActionEvent> {

	private SimpleIntegerProperty circleCounter;

	private SimpleStringProperty inputError = new SimpleStringProperty("radius must be > 0.0");

	private Label radiusLabel = new Label(eventType.RADIUS.toString());
	private Label filledLabel = new Label(eventType.FILLED.toString());
	private Label areaLabel = new Label(eventType.AREA.toString());
	private Label colorLabel = new Label(eventType.COLOR.toString());

	private TextField tfRadius = new TextField();

	private ComboBox<String> cboFilled = new ComboBox<>();
	private ComboBox<String> cboCalculateArea = new ComboBox<>();
	private String[] trueFalseStringArray = { "false", "true" };

	private ColorPicker btChooseColor = new ColorPicker();

	private ObservableList<String> trueFalseChoiseItems = FXCollections.observableArrayList(trueFalseStringArray);
	private ObservableList<String> calculateAreaChoiseItems = FXCollections.observableArrayList(trueFalseStringArray);

	private CircleModel3 model;

	public CircleController3(SimpleIntegerProperty circleCounter) {

		setCircleCounter(circleCounter);

		this.setPadding(new Insets(15, 15, 15, 15));
		this.setHgap(10);
		this.setVgap(10);

		cboFilled.getItems().addAll(trueFalseChoiseItems);
		cboCalculateArea.getItems().addAll(calculateAreaChoiseItems);

		cboFilled.setPrefWidth(200);
		cboFilled.setValue("false");

		cboCalculateArea.setPrefWidth(200);
		cboCalculateArea.setValue("false");

		btChooseColor.setValue(Color.BLACK);
		btChooseColor.setPrefWidth(200);

		add(radiusLabel, 0, 0);
		add(tfRadius, 1, 0);
		add(filledLabel, 0, 1);
		add(cboFilled, 1, 1);
		add(areaLabel, 0, 2);
		add(cboCalculateArea, 1, 2);
		add(colorLabel, 0, 3);
		add(btChooseColor, 1, 3);

		tfRadius.setOnAction(this);
		cboFilled.setOnAction(this);
		cboCalculateArea.setOnAction(this);
		btChooseColor.setOnAction(this);

	}

	public SimpleIntegerProperty getCircleCounter() {
		return circleCounter;
	}

	public void setCircleCounter(SimpleIntegerProperty circleCounter) {
		this.circleCounter = circleCounter;
	}

	@Override
	public void handle(ActionEvent e) {
		if (model == null)
			return;
		if (e.getSource() == tfRadius) {
			try {
				SimpleDoubleProperty radius = new SimpleDoubleProperty(Double.parseDouble(tfRadius.getText()));
				if (radius.doubleValue() <= 0) {
					tfRadius.setText(inputError.getValue());
					return;
				}
				model.setRadius(radius);
			} catch (Exception ex) {
				tfRadius.setText(inputError.getValue());
				return;
			}
		} else if (e.getSource() == cboFilled)

			if (trueFalseChoiseItems.indexOf(cboFilled.getValue()) == 0) {
				model.setFilled(new SimpleBooleanProperty(false));
			} else {
				model.setFilled(new SimpleBooleanProperty(true));
			}

		else if (e.getSource() == btChooseColor) {
			// Color selectedColor = JColorChooser.showDialog(this,
			// chooseAcolor, jlblColor.getBackground());
			Color selectedColor = btChooseColor.getValue();
			if (selectedColor != null) {
				// lblColor.setBackground(selectedColor);
				model.setColor(selectedColor);
			}
		} else if (e.getSource() == cboCalculateArea) {
			if (calculateAreaChoiseItems.indexOf(cboCalculateArea.getValue()) == 0) {
				model.setCalculateArea(new SimpleBooleanProperty(false));
			} else {
				model.setCalculateArea(new SimpleBooleanProperty(true));
			}
		}

	}

	public void setModel(CircleModel3 model) {
		this.model = model;
	}

	public CircleModel3 getModel() {
		return model;
	}

}
