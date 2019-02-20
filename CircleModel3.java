

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class CircleModel3 implements CircleEvents {

	private SimpleIntegerProperty circleCounter;
	private SimpleDoubleProperty radius = new SimpleDoubleProperty(20);
	private SimpleBooleanProperty filled = new SimpleBooleanProperty();
	private SimpleBooleanProperty calculateArea = new SimpleBooleanProperty();
	private Color color;

	private HashMap<eventType, ArrayList<EventHandler<Event>>> circleHashMap = new HashMap<eventType, ArrayList<EventHandler<Event>>>();

	public CircleModel3(SimpleIntegerProperty circleCounter) {
		this.setCircleCounter(circleCounter);
		for (eventType et : eventType.values()) {
			circleHashMap.put(et, new ArrayList<EventHandler<Event>>());
		}
	}

	public SimpleIntegerProperty getCircleCounter() {
		return circleCounter;
	}

	public void setCircleCounter(SimpleIntegerProperty circleCounter) {
		this.circleCounter = circleCounter;
	}

	public SimpleDoubleProperty getRadius() {
		return radius;
	}

	public void setRadius(SimpleDoubleProperty radius) {
		this.radius = radius;
		processEvent(eventType.RADIUS, new ActionEvent());
	}

	public SimpleBooleanProperty isFilled() {
		return filled;
	}

	public void setFilled(SimpleBooleanProperty filled) {
		this.filled = filled;
		processEvent(eventType.FILLED, new ActionEvent());
	}

	public SimpleBooleanProperty getCalculateArea() {
		return calculateArea;
	}

	public void setCalculateArea(SimpleBooleanProperty calculateArea) {
		this.calculateArea = calculateArea;
		processEvent(eventType.AREA, new ActionEvent());
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		processEvent(eventType.COLOR, new ActionEvent());
	}

	public synchronized void addListener(EventHandler<Event> eventListener, eventType et) {
		ArrayList<EventHandler<Event>> al;
		al = circleHashMap.get(et);
		if (al == null)
			al = new ArrayList<EventHandler<Event>>();
		al.add(eventListener);
		circleHashMap.put(et, al);
	}

	public synchronized void removeActionListener(ActionEvent l, eventType et) {
		ArrayList<EventHandler<Event>> al;
		al = circleHashMap.get(et);
		if (al != null && al.contains(l))
			al.remove(l);
		circleHashMap.put(et, al);
	}

	private void processEvent(eventType et, ActionEvent e) {
		ArrayList<EventHandler<Event>> al;
		synchronized (this) {
			al = circleHashMap.get(et);
			if (al == null)
				return;
		}

		System.out.println("model number: " + (getCircleCounter().intValue() + 1) + " actionCommand: " + et.name()
				+ " array size is: " + al.size());

		for (int i = 0; i < al.size(); i++) {
			EventHandler<Event> handler = (EventHandler<Event>) al.get(i);
			handler.handle(e);
		}
	}
}
