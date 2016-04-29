package com.apeelingtech.game.events.types;

import com.apeelingtech.game.events.Event;

public class KeyReleasedEvent extends KeyEvent {

    public KeyReleasedEvent(int keyCode) {
        super(keyCode, Event.Type.KEY_RELEASED);
    }

}
