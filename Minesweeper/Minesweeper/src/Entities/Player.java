package Entities;

import Utilz.LoadSave;
import java.awt.*;
import static Logic.Game.*;
import static Utilz.Constants.BoardConstants.*;



public class Player {
    private int Xaxis, Yaxis;
    public Player(float x, float y) {

    }

    public void render(Graphics g)
    {
        paintBoard(g);
        paintSmile(g);
        paintTimer(g);
        paintReset(g);
        paintFlag(g);
        paintMenuIcon(g);
        if(pause)
        {
            pauseDialogueBox(g);
        }
    }

    private void paintFlag(Graphics g) {


        g.drawImage(LoadSave.GetMineSweeper(LoadSave.FLAG),GAME_WIDTH-GAME_WIDTH/3,timeY+10, 40,40,null);

        g.setColor(Color.ORANGE);
        g.setFont(new Font("Tahoma", Font.BOLD,30));
        g.drawString(Integer.toString(FLAG_COUNT),GAME_WIDTH-GAME_WIDTH/4-4*SPACE,SPACE*7+15);
    }

    private void pauseDialogueBox(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(GAME_WIDTH/2-GAME_WIDTH/4,GAME_HEIGHT/2-GAME_HEIGHT/4, GAME_WIDTH/2,GAME_HEIGHT/2);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD,35));
        g.drawString("QUIT TO MAIN MENU?",(GAME_WIDTH/2-GAME_WIDTH/4)+SPACE*7+2,(GAME_HEIGHT/2-GAME_HEIGHT/4)+SPACE*20);


        {
            if(Xaxis>= 283 && Yaxis>= 337 && Xaxis<= 388 && Yaxis<= 368)
            {
                g.setColor(Color.RED);
                g.fillRect((GAME_WIDTH/2-GAME_WIDTH/4)+SPACE*7+2,(GAME_HEIGHT/2-GAME_HEIGHT/4)+SPACE*30, 105,32);

            }
            else{
                g.setColor(Color.GRAY);
                g.fillRect((GAME_WIDTH/2-GAME_WIDTH/4)+SPACE*7+2,(GAME_HEIGHT/2-GAME_HEIGHT/4)+SPACE*30, 105,32);

            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Tahoma", Font.BOLD,30));
            g.drawString("YES",(GAME_WIDTH/2-GAME_WIDTH/4)+SPACE*11+3,(GAME_HEIGHT/2-GAME_HEIGHT/4)+SPACE*34+3);
        }


        {
            if(Xaxis>= 570 && Yaxis>= 337 && Xaxis<= 675 && Yaxis<= 368)
            {
                g.setColor(Color.RED);
                g.fillRect((GAME_WIDTH/2-GAME_WIDTH/4)+SPACE*55,(GAME_HEIGHT/2-GAME_HEIGHT/4)+SPACE*30, 105,32);
            }
            else{
                g.setColor(Color.GRAY);
                g.fillRect((GAME_WIDTH/2-GAME_WIDTH/4)+SPACE*55,(GAME_HEIGHT/2-GAME_HEIGHT/4)+SPACE*30, 105,32);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Tahoma", Font.BOLD,30));
            g.drawString("NO",(GAME_WIDTH/2-GAME_WIDTH/4)+SPACE*60+1,(GAME_HEIGHT/2-GAME_HEIGHT/4)+SPACE*34+3);
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

    public void getMousePos(int x, int y){
        this.Xaxis=x;
        this.Yaxis=y;
    }

    private void paintBoard(Graphics g)
    {
        for(int x=0;x<TILES_IN_WIDTH;x++)
            for(int y=0;y<TILES_IN_HEIGHT;y++){

                g.setColor(Color.pink);

                if(revealed[y][x])
                {
                    firstMove=false;
                    g.setColor(Color.WHITE);
                    if(bombs[y][x])
                    {
                        g.setColor(Color.RED);
                    }
                }


                if( Xaxis>=(x*TILES_SIZE+SPACE) && Xaxis<((x+1)*TILES_SIZE-SPACE) && Yaxis>=(y*TILES_SIZE+SPACE*15) && Yaxis<((y+1)*TILES_SIZE+SPACE*13) && !revealed[y][x] && happy && !pause && !FLAGGED[y][x])
                {
                    g.setColor(SystemColor.GRAY);
                }

                g.fillRect(x*TILES_SIZE+SPACE,y*TILES_SIZE+SPACE*15,TILES_SIZE-2*SPACE, TILES_SIZE-2*SPACE);
                if(FLAGGED[y][x] && !revealed[y][x])
                {
                    g.drawImage(LoadSave.GetMineSweeper(LoadSave.FLAG),x*TILES_SIZE+SPACE*2,y*TILES_SIZE+SPACE*16-2,40, 40,null);
                }

                if(revealed[y][x])
                {
                    g.setColor(Color.BLACK);
                    if(!bombs[y][x])
                    {
                        g.setFont(new Font("Tahoma", Font.BOLD,30));
                        if(neighbors[y][x]!=0)
                        {
                            if(neighbors[y][x]==1)
                            {
                                g.setColor(Color.BLUE);
                            }
                            if(neighbors[y][x]==2)
                            {
                                g.setColor(Color.GREEN);
                            }
                            if(neighbors[y][x]==3)
                            {
                                g.setColor(Color.red);
                            }
                            if(neighbors[y][x]==4)
                            {
                                g.setColor(new Color(0,0,128));
                            }
                            if(neighbors[y][x]==5)
                            {
                                g.setColor(new Color(178,34,34));
                            }
                            if(neighbors[y][x]==6)
                            {
                                g.setColor(new Color(72,209,204));
                            }
                            if(neighbors[y][x]==8)
                            {
                                g.setColor(Color.DARK_GRAY);
                            }

                            g.drawString(Integer.toString(neighbors[y][x]),x*TILES_SIZE+SPACE*3+2,y*TILES_SIZE+SPACE*22-7);
                        }
                    }
                    else{
                        for(int i=0;i<TILES_IN_WIDTH;i++)
                            for(int j=0;j<TILES_IN_HEIGHT;j++)
                            {
                                if(bombs[j][i] && !firstMove)
                                {
                                    revealed[j][i]=true;
                                    TILES--;
                                    g.fillRect(i*TILES_SIZE+SPACE*5-7+4, j*TILES_SIZE+SPACE*18-2-4,5 ,26);
                                    g.fillRect(i*TILES_SIZE+SPACE+4  +4, j*TILES_SIZE+SPACE*18+8-4,30 ,5);
                                    g.fillRect(i*TILES_SIZE+SPACE*3-1+4, j*TILES_SIZE+SPACE*18+2-4,17 ,17);

                                }
                            }
                        happy=false;

                    }
                }
            }
    }

    public void paintSmile(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillOval(smileyX, smileyY, 70, 70);
        g.setColor(Color.BLACK);
        g.fillOval(smileyX+15,smileyY+15,10,10);
        g.fillOval(smileyX+45,smileyY+15,10,10);
        if(happy)
        {
            g.fillRect(smileyX+25,smileyY+51,22,5);
            g.fillRect(smileyX+21,smileyY+48,5,5);
            g.fillRect(smileyX+45,smileyY+48,5,5);
            g.fillRect(smileyX+16,smileyY+43,5,5);
            g.fillRect(smileyX+50,smileyY+43,5,5);
        }
        else{
            g.fillRect(smileyX+25,smileyY+44,22,5);
            g.fillRect(smileyX+21,smileyY+48,5,5);
            g.fillRect(smileyX+45,smileyY+48,5,5);
            g.fillRect(smileyX+16,smileyY+53,5,5);
            g.fillRect(smileyX+50,smileyY+53,5,5);
        }
    }

    public void paintTimer(Graphics g)
    {
        g.setFont(new Font("Tahoma", Font.BOLD,30));

        g.setColor(Color.BLACK);
        g.fillRect(timeX-SPACE*2,timeY, 40,60);
        if(secH<10)
        {
            g.setColor(Color.RED);
            g.drawString(Integer.toString(secH),timeX,timeY+SPACE*7);
        }
        else {
            g.setColor(Color.RED);
            g.drawString(Integer.toString(secH),timeX,timeY+SPACE*7);
        }


        g.setColor(Color.BLACK);
        g.fillRect(timeX+SPACE*6,timeY, 40,60);
        g.setColor(Color.RED);
        g.drawString(Integer.toString(secT),timeX+SPACE*8,timeY+SPACE*7);

        g.setColor(Color.BLACK);
        g.fillRect(timeX+SPACE*14,timeY, 40,60);
        g.setColor(Color.RED);
        g.drawString(Integer.toString(secU),timeX+SPACE*16,timeY+SPACE*7);


    }

    public void paintReset(Graphics g)
    {
        if(Xaxis>= (SPACE*30) && Yaxis>= (timeY+15) && Xaxis<=((SPACE*47)+2) && Yaxis<=((timeY+16)*2) && !pause)
        {
            g.setColor(Color.RED);
            g.fillRect(SPACE*30,timeY+15, 105,32);
        }
        else{
            g.setColor(Color.GRAY);
            g.fillRect(SPACE*30,timeY+15, 105,32);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD,30));
        g.drawString("RESET",SPACE*31,SPACE*7+15);

    }



    public int inXBox()
    {
        for(int x=0;x<TILES_IN_WIDTH;x++)
            for(int y=0;y<TILES_IN_HEIGHT;y++){
                if( Xaxis>=(x*TILES_SIZE+SPACE) && Xaxis<((x+1)*TILES_SIZE-SPACE) && Yaxis>=(y*TILES_SIZE+SPACE*15) && Yaxis<((y+1)*TILES_SIZE+SPACE*13))
                {
                    return y;
                }
            }
        return -1;
    }

    public int inYBox()
    {
        for(int x=0;x<TILES_IN_WIDTH;x++)
            for(int y=0;y<TILES_IN_HEIGHT;y++){
                if( Xaxis>=(x*TILES_SIZE+SPACE) && Xaxis<((x+1)*TILES_SIZE-SPACE) && Yaxis>=(y*TILES_SIZE+SPACE*15) && Yaxis<((y+1)*TILES_SIZE+SPACE*13))
                {
                    return x;
                }
            }
        return -1;
    }
}
