package com.apeelingtech.game.events.types;

import com.apeelingtech.game.events.Event;

/**
 * Created by krixa on 4/16/2016.
 */
public class KeyEvent extends Event {

    protected int keyCode;

    public KeyEvent(int keyCode, Type type) {
        super(type);
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

}
