package GameStates;


import Logic.Game;
import UI.Score;
import Utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;

import static Logic.Game.*;
import static Utilz.Constants.BoardConstants.timeY;
import static Utilz.Constants.PAGE;


public class Leaderboard extends State implements StateMethods{
    private Score score;
    public Leaderboard(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {

        score= new Score();
    }


    @Override
    public void draw(Graphics g) {
        score.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>=SPACE*10 && e.getY()>=GAME_HEIGHT/2 && e.getX()<=SPACE*10+40 && e.getY()<=GAME_HEIGHT/2+40 && PAGE>1)
        {
            PAGE--;
        }
        else if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>=GAME_WIDTH-SPACE*16 && e.getY()>=GAME_HEIGHT/2 && e.getX()<=GAME_WIDTH-SPACE*16+40 && e.getY()<=GAME_HEIGHT/2+40 && LoadSave.ENTRIES>14*PAGE)
        {
            PAGE++;
        }
        else if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>= (SPACE*6) && e.getY()>= (SPACE*5-2) && e.getX()<=((SPACE*12)) && e.getY()<=((timeY+16)*2))
        {
            LoadSave.reset();
            GameState.state=GameState.MENU;
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        score.getMousePos(e.getX(),e.getY());
    }

}
