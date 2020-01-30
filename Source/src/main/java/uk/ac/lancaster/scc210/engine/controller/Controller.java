package uk.ac.lancaster.scc210.engine.controller;

import org.jsfml.window.Joystick;

public class Controller {
    private static final int UNKNOWN_CONTROLLER = -1;

    // From what I understand SFML detects the XBox360 Controller as having (at least) 11 buttons
    private static final int XBOX_BUTTONS = 11;

    static int getControllerId() {
        for (int i = 0; i < Joystick.JOYSTICK_COUNT; i++) {
            if (Joystick.isConnected(i) && Joystick.getButtonCount(i) >= XBOX_BUTTONS) {
                return i;
            }
        }

        return UNKNOWN_CONTROLLER;
    }

    public static boolean hasController() {
        return getControllerId() > UNKNOWN_CONTROLLER;
    }

    static boolean hasController(final int controllerID) {
        return controllerID > UNKNOWN_CONTROLLER;
    }
}
