package Logic;

import Inputs.MouseInputs;
import java.awt.*;
import javax.swing.*;
import static Logic.Game.GAME_HEIGHT;
import static Logic.Game.GAME_WIDTH;
import static Utilz.Constants.BoardConstants.createMines;


public class GamePanel extends JPanel{

   private Game game;
    private  MouseInputs mouseInputs;


    public GamePanel(Game game) {
        createMines();

        this.game=game;
        setPanelSize();
        mouseInputs=new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        this.setBackground(Color.DARK_GRAY);
    }

    private void setPanelSize() {
        Dimension dimension=new Dimension(GAME_WIDTH,GAME_HEIGHT);
        setPreferredSize(dimension);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        game.render(g);
        //cleaning surface and allowing us to paint again without anything carrying on from the previous frame

    }
    //repaint() works kind of like a loop, but it uses paintComponent with updated values

    public Game getGame()
    {
        return game;
    }
}
