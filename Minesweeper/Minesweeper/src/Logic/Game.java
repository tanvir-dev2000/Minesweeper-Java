package Logic;


import GameStates.GameState;
import GameStates.Leaderboard;
import GameStates.Menu;
import GameStates.Playing;
import java.awt.*;
import static GameStates.GameState.*;
import static Utilz.Constants.BoardConstants.*;


public class Game implements Runnable {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Playing playing;
    private Menu menu;
    private Leaderboard leaderboard;
    private Thread gameThread;

    private final int FPS_SET=120;
    private final int UPS_SET=200;

    public final static int TILES_DEFAULT_SIZE=40;
    public final static float SCALE= 1.5f;
    public final static int SPACE= 6;
    public final static int TILES_IN_WIDTH=16;
    public final static int TILES_IN_HEIGHT=9;
    public final static int TILES_SIZE=(int)(TILES_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH=TILES_SIZE*TILES_IN_WIDTH;
    public final static int GAME_HEIGHT=TILES_SIZE*TILES_IN_HEIGHT+(int)(SPACE*SCALE)*10;

    public static int secU=0;
    public static int secT=0;
    public static int secH=0;
    public static int time=0;

    public Game() {
        initClasses();
        gamePanel=new GamePanel(this);
        gameWindow=new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing=new Playing(this);
        leaderboard=new Leaderboard(this);
    }

    private void startGameLoop()
    {
        gameThread=new Thread(this);
        gameThread.start();
    }


    public void render(Graphics g)
    {
        switch (GameState.state){

            case PLAYING -> {
                playing.draw(g);
            }
            case MENU -> {
                menu.draw(g);
            }
            case SCORE ->{
                leaderboard.draw(g);
            }
        }
    }
    @Override
    public void run() {//we will have this method working on a separate thread
        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate= 1000000000.0/UPS_SET;


        long previousTime=System.nanoTime();

        long lastCheck=System.currentTimeMillis();

        double deltaU=0;
        double deltaF=0;

        while(true)
        {
            long currentTime=System.nanoTime();

            deltaU+=(currentTime-previousTime)/timePerUpdate;
            deltaF+=(currentTime-previousTime)/timePerFrame;
            previousTime=currentTime;

            if(deltaU>=1)
            {

                deltaU--;
            }
            if(deltaF>=1)
            {
                gamePanel.repaint();

                deltaF--;
            }

            if(System.currentTimeMillis()-lastCheck>=1000)
            {
                if(System.currentTimeMillis()-lastCheck>=1000 && GameState.state==PLAYING && happy && !StopTimer)
                {
                    ++secU;
                    if(secU>9)
                    {
                        ++secT;
                        secU=0;
                    }
                    if(secT>9)
                    {
                        ++secH;
                        secT=0;
                    }
                    if(secH>9)
                    {

                        happy=false;
                        secH=0;
                    }
                    time=secH*100+secT*10+secU;
                }
                lastCheck=System.currentTimeMillis();
            }

        }
    }

    public Menu getMenu()
    {
        return  menu;
    }

    public Playing getPlaying()
    {
        return  playing;
    }

    public Leaderboard getLeaderboard(){return leaderboard;}


}
