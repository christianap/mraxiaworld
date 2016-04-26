package com.apeelingtech.game.events.types;

public class KeyPressedEvent extends KeyEvent {

    public KeyPressedEvent(int keyCode) {
        super(keyCode, Type.KEY_PRESSED);
    }

}
