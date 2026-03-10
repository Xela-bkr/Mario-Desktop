package UIComponent;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;

public class GameMenuLayout implements LayoutManager{
	private Container container;
	private double containerSizeX, containerSizeY;
	private ArrayList<Component> component = new ArrayList<Component>();

	@Override
	public void addLayoutComponent(String name, Component comp) {
		component.add(comp);
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		component.remove(comp);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void layoutContainer(Container parent) {
		this.container = parent;
		this.containerSizeX = parent.getWidth();
		this.containerSizeY = parent.getHeight();
		
	}

}
