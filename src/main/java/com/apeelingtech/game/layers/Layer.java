package com.apeelingtech.game.layers;

import com.apeelingtech.game.events.Event;
import com.apeelingtech.game.events.EventListener;
import com.apeelingtech.game.events.EventDispatcher;
import com.apeelingtech.game.events.types.*;
import com.apeelingtech.game.display.Renderer;
import com.apeelingtech.game.display.gui.GUIElement;

import java.awt.Graphics2D;

public class Layer implements EventListener {

    private GUIElement[] elements;
    private Renderer renderer;
    private int prevX = -1;
    private int prevY = -1;
    private int activeElement = 0;

    public Layer(GUIElement... elements) {
        this.elements = elements;
    }
    
    public Layer(Renderer renderer, GUIElement... elements) {
        this.elements = elements;
        this.renderer = renderer;
    }
    
    public GUIElement[] getElements() {
        return elements;
    }
    
    // TODO: If tick method added to element, add it here also!!
    
    public final void render(Graphics2D g) {
        for (GUIElement element : elements) {
            if (element.isHidden()) continue;
            element.render(g);
        }
        if (renderer != null) {
            renderer.render(g);
        }
    }
    
    public final void onEvent(Event event) {
        /*for (GUIElement element : elements) {
            if (element.isHidden()) continue;
            element.onEvent(event);
        }*/
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> mousePressed((MousePressedEvent) e));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> mouseReleased((MouseReleasedEvent) e));
        dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> mouseMoved((MouseMovedEvent) e));
        dispatcher.dispatch(Event.Type.KEY_PRESSED, (Event e) -> keyPressed((KeyPressedEvent) e));
        dispatcher.dispatch(Event.Type.KEY_RELEASED, (Event e) -> keyReleased((KeyReleasedEvent) e));
        dispatcher.dispatch(Event.Type.MOUSE_WHEEL_MOVED, (Event e) -> mouseWheelMoved((MouseWheelMovedEvent) e));
    }

    public final boolean mousePressed(MousePressedEvent event) {
        //for (GUIElement element : elements) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].isHidden()) continue;
            if (elements[i].bounds.contains(event.getX(), event.getY())) {
                if (elements[i].mousePressed(event)) return true;
                elements[i].setActive();
                // set previously active element to not active (TODO)
                elements[activeElement].setNotActive();
                activeElement = i;
            } /*else if (i == elements.length - 1) {
                activeElement = -1; // none are active
            }*/
        }

        return false;
    }

    public final boolean mouseReleased(MouseReleasedEvent event) {
        for (GUIElement element : elements) {
            if (element.isHidden()) continue;
            if (element.bounds.contains(event.getX(), event.getY())) {
                if (element.mouseReleased(event)) return true;
            }
        }
        return false;
    }

    public final boolean mouseMoved(MouseMovedEvent event) {
        // Handle mouseEntered and mouseExited here!
        for (GUIElement element : elements) {
            if (element.isHidden()) continue;
            if (element.bounds.contains(event.getX(), event.getY()) && !(element.bounds.contains(prevX, prevY))) {
                if (element.mouseEntered(event)) {
                    prevX = event.getX();
                    prevY = event.getY();
                    return true;
                }
            } else if (!(element.bounds.contains(event.getX(), event.getY())) && element.bounds.contains(prevX, prevY)) {
                if (element.mouseExited(event)) {
                    prevX = event.getX();
                    prevY = event.getY();
                    return true;
                }
            } else if (element.bounds.contains(event.getX(), event.getY()) && element.bounds.contains(prevX, prevY)) {
                if (element.mouseMoved(event)) {
                    prevX = event.getX();
                    prevY = event.getY();
                    return true;
                }
            }
        }

        prevX = event.getX();
        prevY = event.getY();

        return false;
    }

    public final boolean mouseWheelMoved(MouseWheelMovedEvent event) {
        for (GUIElement element : elements) {
            if (element.isHidden()) continue;
            // TODO: Check if inside element (should I also set active?) (use prevX and prevY???)
            if (element.mouseWheelMoved(event)) return true;
        }
        return false;
    }

    public final boolean keyPressed(KeyPressedEvent event) {
        for (GUIElement element : elements) {
            if (element.isHidden()) continue;
            if (element.bounds.contains(prevX, prevY) || element.isActive()) {
                if (element.keyPressed(event)) return true; // TODO: Should I be using prevX and prevY???
            }
        }
        return false;
    }

    public final boolean keyReleased(KeyReleasedEvent event) {
        for (GUIElement element : elements) {
            if (element.isHidden()) continue;
            if (element.bounds.contains(prevX, prevY) || element.isActive()) {
                if (element.keyReleased(event)) return true; // TODO: Should I be using prevX and prevY???
            }
        }
        return false;
    }

}
