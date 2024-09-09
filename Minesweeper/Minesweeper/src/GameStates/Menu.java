package GameStates;

import Logic.Game;
import UI.MenuButtons;
import Utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;

import static Logic.Game.*;



public class Menu extends State implements StateMethods{

    private MenuButtons menuButtons;
    public Menu(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        menuButtons= new MenuButtons();
    }

    @Override
    public void draw(Graphics g) {
        menuButtons.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>=GAME_WIDTH/2-SPACE*11 && e.getY()>=GAME_HEIGHT/2-SPACE*21 && e.getX()<GAME_WIDTH/2+SPACE*11 && e.getY()<GAME_HEIGHT/2-SPACE*9)
        {
            menuButtons.getMousePos(0,0);
            GameState.state=GameState.PLAYING;

        }
        else if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>=GAME_WIDTH/2-SPACE*11 && e.getY()>=GAME_HEIGHT/2-SPACE*6 && e.getX()<GAME_WIDTH/2+SPACE*11 && e.getY()<GAME_HEIGHT/2+SPACE*6)
        {
            LoadSave.ReadData();
            GameState.state=GameState.SCORE;
        }
        else if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>=GAME_WIDTH/2-SPACE*11 && e.getY()>=GAME_HEIGHT/2+SPACE*9 && e.getX()<GAME_WIDTH/2+SPACE*11 && e.getY()<GAME_HEIGHT/2+SPACE*21)
        {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        menuButtons.getMousePos(e.getX(),e.getY());
    }

}
