package uet.oop.bomberman.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputKeyboard implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keypress[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keypress[e.getKeyCode()] = false;
    }

    private boolean[] keypress = new boolean[1000];
    public boolean up, right, left, down, space;

    public void update() {
        up = keypress[KeyEvent.VK_UP];
        right = keypress[KeyEvent.VK_RIGHT];
        left = keypress[KeyEvent.VK_LEFT];
        down = keypress[KeyEvent.VK_DOWN];
        space = keypress[KeyEvent.VK_SPACE];
    }
}
