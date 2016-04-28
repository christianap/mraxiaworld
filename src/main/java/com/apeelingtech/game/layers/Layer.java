package com.apeelingtech.game.layers;

import com.apeelingtech.game.events.Event;
import com.apeelingtech.game.events.EventListener;
import com.apeelingtech.game.display.Renderer;
import com.apeelingtech.game.display.gui.GUIElement;

import java.awt.Graphics2D;

public class Layer implements EventListener {

    private GUIElement[] elements;
    private Renderer renderer;

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
    
    public void render(Graphics2D g) {
        for (GUIElement element : elements) {
            element.render(g);
        }
        if (renderer != null) {
            renderer.render(g);
        }
    }
    
    public void onEvent(Event event) {
        for (GUIElement element : elements) {
            element.onEvent(event);
        }
    }

}
