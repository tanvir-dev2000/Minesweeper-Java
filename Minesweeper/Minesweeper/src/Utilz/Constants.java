package Utilz;

import GameStates.GameState;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Logic.Game.*;


public class Constants {
    public static int PAGE=1;

    public static class BoardConstants{
        public static boolean[][] revealed= new boolean[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        public static boolean[][] bombs= new boolean[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        public static int[][] neighbors= new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        public static final int MINES =25;
        public static int FLAG_COUNT=MINES;
        public static boolean[][] FLAGGED = new boolean[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        public static int smileyX =GAME_WIDTH/2-SPACE;
        public static int smileyY =10;
        public static int timeX =GAME_WIDTH-SPACE*22;
        public static int timeY =15;
        public static boolean happy=true;
        public static boolean firstMove=true;
        public static int TILES=0;
        public static boolean pause=false;
        public static boolean StopTimer=false;

        public static LocalDateTime date;

        public static void createMines() {
            int mineCount = 1;
            while (mineCount <= MINES) {

                int randCol = (int) (Math.random() * TILES_IN_WIDTH);
                int randRow = (int) (Math.random() * TILES_IN_HEIGHT);
                if (!(bombs[randRow][randCol])) {
                    bombs[randRow][randCol]=true;
                    mineCount++;
                }
            }
        }
        public static void isNeighbours(int row, int col) {
            if(!happy)
            {
                return;
            }
            if (row < 0 || col < 0 || row >= TILES_IN_HEIGHT || col >= TILES_IN_WIDTH) {
                return;
            }
            if(revealed[row][col] || FLAGGED[row][col])
            {
                return;
            }
            if(TILES==0)
            {
                firstTouch(row,col);
            }
            revealed[row][col]=true;
            int mines=0;

            for(int x=col-1;x<=col+1;x++)
            {
                mines+=checkMine(row-1,x);
                mines+=checkMine(row+1,x);
            }

            mines+=checkMine(row,col-1);
            mines+=checkMine(row,col+1);
            TILES++;
            if(mines>0)
            {
                neighbors[row][col]=mines;
            }
            else
            {
                neighbors[row][col]=mines;

                for(int x=col-1;x<=col+1;x++)
                {
                    isNeighbours(row-1,x);
                    isNeighbours(row+1,x);
                }
                isNeighbours(row,col-1);
                isNeighbours(row,col+1);
            }
            if(TILES==TILES_IN_HEIGHT*TILES_IN_WIDTH-MINES)
            {
                Victory();
            }
        }

        public static void Victory()
        {
            StopTimer=true;
            String userName=null;
            String finalUserName=null;
            Object[] option = {"Enter","Skip"};
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel winLabel = new JLabel("       YOU WIN!");
            JLabel timeLabel = new JLabel("  Time Taken: " + time + "s");
            JLabel nameLabel = new JLabel("      Enter name: ");
            JTextField textField = new JTextField(10);
            textField.setDocument(new PlainDocument() {
                @Override
                public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                    if (getLength() + str.length() <= 50) {
                        super.insertString(offs, str, a);
                    }
                }
            });

            panel.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(winLabel);
            panel.add(Box.createHorizontalStrut(80));
            panel.add(timeLabel);
            panel.add(nameLabel);
            panel.add(textField);

            int result = JOptionPane.showOptionDialog(
                    null,
                    panel,
                    "VICTORY",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    option,
                    null);
            if (result == JOptionPane.YES_OPTION){

                userName= textField.getText();
                if(textField.getText().isEmpty())
                {
                    userName= "(BLANK)";
                }

            }
            if(result==JOptionPane.NO_OPTION)
            {
                userName= "(BLANK)";
            }
            if(userName!=null)
            {
                finalUserName=userName.replaceAll("\\s","");
            }


            date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
            String formattedDateTime = date.format(formatter);
            LoadSave.WriteData(finalUserName, time, formattedDateTime);

            GameState.state=GameState.MENU;
            reset();
        }

        public static int checkMine(int row, int col) {
            if (row < 0 || col < 0 || row >= TILES_IN_HEIGHT || col >= TILES_IN_WIDTH) {
                return 0;
            }
            if (bombs[row][col]) {
                return 1;
            }
            return 0;
        }

        public static void firstTouch(int row, int col) {

            for (int i = col - 1; i <= col + 1; i++) {
                firstSpread(row - 1, i);
                firstSpread(row, i);
                firstSpread(row + 1, i);
            }
        }

        public static void firstSpread(int row, int col) {
            if (row < 0 || col < 0 || row >= TILES_IN_HEIGHT || col >= TILES_IN_WIDTH) {
                return;
            }
            else if (bombs[row][col]) {
                while (true) {
                    int randCol = (int) (Math.random() * TILES_IN_WIDTH);
                    int randRow = (int) (Math.random() * TILES_IN_HEIGHT);
                    if (!(randRow ==row-1 || randRow ==row+1 || randCol==col-1 || randCol==col+1)) {
                        if (!(bombs[randRow][randCol])) {
                            bombs[randRow][randCol]=(true);
                            bombs[row][col]=(false);
                            break;
                        }
                    }

                }
            }
        }

        public static  void reset()
        {
            happy=true;
            firstMove=true;
            pause=false;
            StopTimer=false;
            secU=0;
            secT=0;
            secH=0;
            TILES=0;
            FLAG_COUNT=MINES;
            for(int x=0;x<TILES_IN_HEIGHT;x++)
                for(int y=0;y<TILES_IN_WIDTH;y++){
                    revealed[x][y]=false;
                    bombs[x][y]=false;
                    FLAGGED[x][y]=false;
                    neighbors[x][y]=0;
                }
            createMines();

        }
    }


}
