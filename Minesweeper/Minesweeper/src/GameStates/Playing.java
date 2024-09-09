package GameStates;

import Entities.Player;
import Logic.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

import static Logic.Game.SPACE;
import static Utilz.Constants.BoardConstants.*;

public class Playing extends State implements StateMethods{
    private Player player;


    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {

        player= new Player(200,200);
    }


    @Override
    public void draw(Graphics g) {
        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1 && player.inXBox()!= -1 && player.inYBox()!=-1 && !pause)
        {
            if(!FLAGGED[player.inXBox()][player.inYBox()])
            {
                isNeighbours(player.inXBox(),player.inYBox());
            }
        }
        else if(e.getButton()==MouseEvent.BUTTON3 && player.inXBox()!= -1 && player.inYBox()!=-1 && !pause && !firstMove)
        {
            if(FLAGGED[player.inXBox()][player.inYBox()])
            {
                FLAGGED[player.inXBox()][player.inYBox()]=false;
                FLAG_COUNT++;
            }
            else{
                FLAGGED[player.inXBox()][player.inYBox()]=true;
                FLAG_COUNT--;
            }
        }
        else if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>= (SPACE*30) && e.getY()>= (timeY+15) && e.getX()<=((SPACE*47)+2) && e.getY()<=((timeY+16)*2) && !pause)
        {
            reset();
        }
        else if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>= (SPACE*6) && e.getY()>= (SPACE*5-2) && e.getX()<=((SPACE*12)) && e.getY()<=((timeY+16)*2))
        {
            if(pause)
            {
                pause=false;
                StopTimer=false;
            }
            else
            {
                StopTimer=true;
                pause=true;
            }
        }
        else if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>= 283 && e.getY()>= 337 && e.getX()<= 388 && e.getY()<= 368 && pause)
        {
            reset();
            GameState.state=GameState.MENU;
        }
        else if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>= 570 && e.getY()>= 337 && e.getX()<= 675 && e.getY()<= 368 && pause)
        {
            GameState.state=GameState.PLAYING;
            StopTimer=false;
            pause=false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        player.getMousePos(e.getX(),e.getY());
    }


}
