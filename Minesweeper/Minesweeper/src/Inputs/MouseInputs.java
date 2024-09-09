package Inputs;

import GameStates.GameState;
import Logic.GamePanel;


import java.awt.event.*;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel=gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseClicked(e);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().mouseClicked(e);
            }
            case SCORE -> {
                gamePanel.getGame().getLeaderboard().mouseClicked(e);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseMoved(e);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().mouseMoved(e);
            }
            case SCORE -> {
                gamePanel.getGame().getLeaderboard().mouseMoved(e);
            }
        }
    }
}
