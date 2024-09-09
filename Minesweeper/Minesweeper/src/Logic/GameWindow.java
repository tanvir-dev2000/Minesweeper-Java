package Logic;

import javax.swing.*;


public class GameWindow extends JFrame{

    public GameWindow(GamePanel gamePanel) {

        this.add(gamePanel);
        this.setTitle("MINESWEEPER");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
