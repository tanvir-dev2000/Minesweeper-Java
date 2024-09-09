package UI;

import Utilz.LoadSave;

import java.awt.*;

import static Logic.Game.*;
import static Logic.Game.SPACE;
import static Utilz.Constants.BoardConstants.timeY;
import static Utilz.Constants.PAGE;

public class Score {

    private int Xaxis, Yaxis;

    public Score()
    {

    }

    public void getMousePos(int x, int y){
        this.Xaxis=x;
        this.Yaxis=y;
    }

    public void render(Graphics g) {
        g.drawImage(LoadSave.GetMineSweeper(LoadSave.MAIN_BG),0,0,GAME_WIDTH,GAME_HEIGHT,null);

        paintScores(g);
        paintMenuIcon(g);
        if(LoadSave.ENTRIES>14*PAGE)
        {
            paintNext(g);
        }
        if(PAGE>1)
        {
            paintBack(g);
        }
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD,20));
        g.drawString(Integer.toString(PAGE),GAME_WIDTH/2,GAME_HEIGHT-GAME_HEIGHT/24);

    }

    private void paintBack(Graphics g) {
        if(Xaxis>=SPACE*10 && Yaxis>=GAME_HEIGHT/2 && Xaxis<=SPACE*10+40 && Yaxis<=GAME_HEIGHT/2+40)
        {
            g.setColor(Color.RED);
            g.fillRect(SPACE*10,GAME_HEIGHT/2,40,40);
        }
        else{
            g.setColor(Color.ORANGE);
            g.fillRect(SPACE*10,GAME_HEIGHT/2,40,40);
        }

        g.drawImage(LoadSave.GetMineSweeper(LoadSave.BACK),SPACE*8+3,GAME_HEIGHT/2-8,58,58,null);
    }

    private void paintNext(Graphics g) {
        if(Xaxis>=GAME_WIDTH-SPACE*16 && Yaxis>=GAME_HEIGHT/2 && Xaxis<=GAME_WIDTH-SPACE*16+40 && Yaxis<=GAME_HEIGHT/2+40)
        {
            g.setColor(Color.RED);
            g.fillRect(GAME_WIDTH-SPACE*16,GAME_HEIGHT/2,40,40);
        }
        else{
            g.setColor(Color.ORANGE);
            g.fillRect(GAME_WIDTH-SPACE*16,GAME_HEIGHT/2,40,40);
        }
        g.drawImage(LoadSave.GetMineSweeper(LoadSave.NEXT),GAME_WIDTH-SPACE*17-2,GAME_HEIGHT/2-8,58,58,null);
    }

    private void paintScores(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(GAME_WIDTH/8,GAME_HEIGHT/8-SPACE*5-3,723,33);
        g.setColor(Color.GRAY);
        g.fillRect(GAME_WIDTH/8+3,GAME_HEIGHT/8-SPACE*5,717,26);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.PLAIN,20));
        g.drawString("Player",SPACE*24,SPACE*9+15);
        g.drawString("Date"  ,SPACE*77,SPACE*9+15);
        g.drawString("Time"  ,SPACE*108,SPACE*9+15);
        g.drawString("Score" ,SPACE*128,SPACE*9+15);

        int x=0;

        for(int i = 14*(PAGE-1); i<14*PAGE; i++)
        {
            try{
                if(i<LoadSave.ENTRIES)
                {
                    if(x>14)
                    {
                        x=0;
                    }
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(GAME_WIDTH/8  ,x*(110-83+9)+GAME_HEIGHT/8+3,723,33);
                    if(i==0)
                    {
                        g.setColor(Color.ORANGE);
                    } else if(i==1)
                    {
                        g.setColor(Color.YELLOW);
                    }
                    else if(i==2)
                    {
                        g.setColor(Color.CYAN);
                    }
                    else g.setColor(Color.GRAY);

                    g.fillRect(GAME_WIDTH/8+3,x*(113-86+9)+GAME_HEIGHT/8+SPACE,717,26);
                    if(i==0 || i==1 || i==2)
                    {
                        g.setColor(Color.BLACK);
                    }
                    else g.setColor(Color.WHITE);
                    g.setFont(new Font("Tahoma", Font.PLAIN,20));

                    g.drawString(LoadSave.USER_NAME.get(i)                 ,SPACE*24   ,x*(113-86+9)+SPACE*15+15);
                    g.drawString(LoadSave.DATE.get(i)                      ,SPACE*72   ,x*(113-86+9)+SPACE*15+15);
                    g.drawString(LoadSave.TIME.get(i)                      ,SPACE*102  ,x*(113-86+9)+SPACE*15+15);
                    g.drawString(LoadSave.DAY_NIGHT.get(i)                 ,SPACE*117  ,x*(113-86+9)+SPACE*15+15);
                    g.drawString(String.valueOf(LoadSave.TIME_TAKEN.get(i)+"s"),SPACE*131-3,x*(113-86+9)+SPACE*15+15);

                    x++;
                }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println(e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        }




    }

    private void paintMenuIcon(Graphics g) {
        if(Xaxis>= (SPACE*6) && Yaxis>= (SPACE*5-2) && Xaxis<=((SPACE*12)) && Yaxis<=((timeY+16)*2))
        {
            g.setColor(Color.RED);
            g.fillRect(SPACE*6,timeY+15, 36,32);
        }
        else{
            g.setColor(Color.GRAY);
            g.fillRect(SPACE*6,timeY+15, 36,32);
        }
        g.drawImage(LoadSave.GetMineSweeper(LoadSave.MENU),SPACE*6,SPACE*5-2,35,36,null);
    }

}
