package UI;

import Utilz.LoadSave;

import java.awt.*;

import static Logic.Game.*;


public class MenuButtons {
    private int Xaxis, Yaxis;

    public MenuButtons() {
    }

    public void getMousePos(int x, int y){
        this.Xaxis=x;
        this.Yaxis=y;
    }


    public void render(Graphics g)
    {
        g.drawImage(LoadSave.GetMineSweeper(LoadSave.MAIN_BG),0,0,GAME_WIDTH,GAME_HEIGHT,null);

        paintStart(g);
        paintScore(g);
        paintExit(g);
    }

    public void paintStart(Graphics g)
    {
        if(!(Xaxis>=GAME_WIDTH/2-SPACE*11 && Yaxis>=GAME_HEIGHT/2-SPACE*21 && Xaxis<GAME_WIDTH/2+SPACE*11 && Yaxis<GAME_HEIGHT/2-SPACE*9))
        {
            g.setColor(Color.WHITE);
            g.fillRect(GAME_WIDTH/2-SPACE*10-5,GAME_HEIGHT/2-SPACE*20-5,130,70);
            g.setColor(Color.GRAY);
            g.fillRect(GAME_WIDTH/2-SPACE*10,GAME_HEIGHT/2-SPACE*20,120,60);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tahoma", Font.BOLD,30));
            g.drawString("START",GAME_WIDTH/2-SPACE*8,GAME_HEIGHT/2-SPACE*13);
        }
        else {
            g.setColor(Color.WHITE);
            g.fillRect(GAME_WIDTH/2-SPACE*10-5,GAME_HEIGHT/2-SPACE*20-5,130,70);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(GAME_WIDTH/2-SPACE*10,GAME_HEIGHT/2-SPACE*20,120,60);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tahoma", Font.BOLD,30));
            g.drawString("START",GAME_WIDTH/2-SPACE*8,GAME_HEIGHT/2-SPACE*13);
        }
    }

    public void paintScore(Graphics g)
    {
        if(!(Xaxis>=GAME_WIDTH/2-SPACE*11 && Yaxis>=GAME_HEIGHT/2-SPACE*6 && Xaxis<GAME_WIDTH/2+SPACE*11 && Yaxis<GAME_HEIGHT/2+SPACE*6))
        {
            g.setColor(Color.WHITE);
            g.fillRect(GAME_WIDTH/2-SPACE*10-5,GAME_HEIGHT/2-SPACE*5-5,130,70);
            g.setColor(Color.GRAY);
            g.fillRect(GAME_WIDTH/2-SPACE*10,GAME_HEIGHT/2-SPACE*5,120,60);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tahoma", Font.BOLD,30));
            g.drawString("SCORE",GAME_WIDTH/2-SPACE*8-2,GAME_HEIGHT/2+SPACE*2);
        }
        else{
            g.setColor(Color.WHITE);
            g.fillRect(GAME_WIDTH/2-SPACE*10-5,GAME_HEIGHT/2-SPACE*5-5,130,70);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(GAME_WIDTH/2-SPACE*10,GAME_HEIGHT/2-SPACE*5,120,60);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tahoma", Font.BOLD,30));
            g.drawString("SCORE",GAME_WIDTH/2-SPACE*8-2,GAME_HEIGHT/2+SPACE*2);
        }
    }

    public void paintExit(Graphics g)
    {
        if(!(Xaxis>=GAME_WIDTH/2-SPACE*11 && Yaxis>=GAME_HEIGHT/2+SPACE*9 && Xaxis<GAME_WIDTH/2+SPACE*11 && Yaxis<GAME_HEIGHT/2+SPACE*21))
        {
            g.setColor(Color.WHITE);
            g.fillRect(GAME_WIDTH/2-SPACE*10-5,GAME_HEIGHT/2+SPACE*10-5,130,70);
            g.setColor(Color.GRAY);
            g.fillRect(GAME_WIDTH/2-SPACE*10,GAME_HEIGHT/2+SPACE*10,120,60);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tahoma", Font.BOLD,30));
            g.drawString("EXIT",GAME_WIDTH/2-SPACE*6,GAME_HEIGHT/2+SPACE*17);
        }
        else{
            g.setColor(Color.WHITE);
            g.fillRect(GAME_WIDTH/2-SPACE*10-5,GAME_HEIGHT/2+SPACE*10-5,130,70);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(GAME_WIDTH/2-SPACE*10,GAME_HEIGHT/2+SPACE*10,120,60);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tahoma", Font.BOLD,30));
            g.drawString("EXIT",GAME_WIDTH/2-SPACE*6,GAME_HEIGHT/2+SPACE*17);
        }
    }

}
