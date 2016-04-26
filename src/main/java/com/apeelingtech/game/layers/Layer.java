package com.apeelingtech.game.layers;

import com.apeelingtech.game.events.Event;
import com.apeelingtech.game.events.EventListener;

import java.awt.*;

public abstract class Layer implements EventListener {

    @Override
    public abstract void onEvent(Event event);

    public abstract void onUpdate();

    public abstract void onRender(Graphics g);

}
