package com.apeelingtech.game.display.gui;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseEvent;

/** A group allows you to group elements, especially if they are related, like radio buttons. The width and height
  *       of the group depends on the placement of the elements inside (However, this may be changed in the future).
  *       You can give the group a border and a title if you wish. 
  *       The positions of the elements inside the group are relative to the position of the group.
  *       The group also handles all input for the elements inside it. If the GUI reads that there was a click
  *       inside of the group, the GUI will give it off to the Group to handle.
  *
  * TODO: Bordering, Input Handling, Width and Height
  */
public class GUIGroup extends GUIElement implements IClickable { // Make this GUIElement that handles its own elements with input and rendering
	
	protected GUIText title;
	protected GUIRadioButton[] elements;
	protected int currentEnabled = -1; // -1 = none are enabled
	
	public GUIGroup(int x, int y, int width, int height, GUIRadioButton... elements) {
		super(x, y, width, height);
		this.elements = elements;
		currentEnabled = -1;
	}
	
	public GUIGroup(String title, Color color, int fontSize, int x, int y, int width, int height, GUIRadioButton... elements) {
		super(x, y, width, height);
		this.title = new GUIText(title, color, x + (width / 2), y + fontSize, fontSize); // TODO: Make more constructors to handle all possibilities.
		this.title.setX(x + (width / 2) - ((int)this.title.getStringBounds().getWidth() / 2));
		this.elements = elements;
		currentEnabled = -1;
	}
	
	/** Toggles between the radio buttons. Enables the next in list. */
	public void toggle() { // TODO: Make where if the next one is hidden, it will go through list until one is not hidden (unless all of them are hidden, then set to -1)
		if (currentEnabled == -1) {
			currentEnabled = 0;
		}
		
		elements[currentEnabled].disable();
		currentEnabled++;
		if (currentEnabled == elements.length) {
			currentEnabled = 0;
		}
		elements[currentEnabled].enable();
	}
	
	/** If using the methods in this class for enabling and disabling elements, you should not have to use this very often, is at all. */
	public void disableAll() {
		for (GUIRadioButton element : elements) {
			element.disable();
		}
	}
	
	public void enable(int index) {
		if (index < elements.length) {
			if (currentEnabled != -1) {
				elements[currentEnabled].disable();
			}
			currentEnabled = index;
			elements[currentEnabled].enable();
		} else {
			System.out.println("WARNING: element not enabled. Index exceded length of elements in group.");
		}
	}
	
	public void disable(int index) {
		if (currentEnabled == index) {
			currentEnabled = -1;
		}
		elements[index].disable();
	}
	
	@Override
	public void addAction(ButtonAction buttonAction) {
	}
	
	@Override
	public void click(MouseEvent e) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].isHidden()) continue;
			if (elements[i] instanceof GUIRadioButton) {
				if (elements[i].bounds.contains(e.getPoint())) {
					enable(i);
					((GUIRadioButton) elements[i]).click(e);
					continue;
				}
			}
			if (elements[i] instanceof IClickable) {
				if (elements[i].bounds.contains(e.getPoint())) {
					((IClickable) elements[i]).click(e);
				}
			}
		}
	}
	
	@Override
	public void mouseEnter(MouseEvent e) {
	}
	
	@Override
	public void mouseExit(MouseEvent e) {
	}
	
	@Override
	public void render(Graphics2D g) {
		if (title != null) {
			title.render(g);
		}
		for (GUIRadioButton element : elements) {
			if (element.isHidden()) continue;
			element.render(g);
		}
	}
	
	public int getCurrentEnabled() {
		return currentEnabled;
	}
	
}