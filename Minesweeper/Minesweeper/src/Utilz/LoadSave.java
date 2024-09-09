package Utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static Utilz.Constants.PAGE;

public class LoadSave {
    public static final String MENU = "menu.png";
    public static final String MAIN_BG = "main_bg.gif";
    public static final String NEXT = "next.png";
    public static final String BACK = "back.png";
    public static final String FLAG = "flag.png";
    public static int ENTRIES=0;
    public static final ArrayList<String> USER_NAME=new ArrayList<>();
    public static final ArrayList<String> DATE =new ArrayList<>();
    public static final ArrayList<String> TIME =new ArrayList<>();
    public static final ArrayList<String> DAY_NIGHT =new ArrayList<>();
    public static final ArrayList<Integer> TIME_TAKEN=new ArrayList<>();
    public static void reset()
    {
        PAGE=1;
        USER_NAME.clear();
        DATE.clear();
        TIME.clear();
        TIME_TAKEN.clear();
        DAY_NIGHT.clear();
        ENTRIES=0;
    }

    public static BufferedImage GetMineSweeper(String fileName)
    {
        BufferedImage img=null;
        InputStream is=LoadSave.class.getResourceAsStream("/"+fileName);
        try {
            img= ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                is.close();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return img;
    }

    public static void WriteData(String name, int time, String date)
    {
        try(
                FileOutputStream  fileOutputStream=new FileOutputStream("res/leaderboard.txt",true);
                PrintWriter printWriter=new PrintWriter(fileOutputStream);)
        {
            printWriter.print(name+" ");
            printWriter.print(time+" ");
            printWriter.println(date);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void ReadData()
    {
        try(
                FileInputStream fileInputStream=new FileInputStream("res/leaderboard.txt");
                Scanner scanner=new Scanner(fileInputStream)
                )
        {
            while(scanner.hasNext())
            {
                USER_NAME.add(scanner.next());
                TIME_TAKEN.add(scanner.nextInt());
                DATE.add(scanner.next());
                TIME.add(scanner.next());
                DAY_NIGHT.add(scanner.next());
                ENTRIES++;
            }

        }
        catch (Exception e)
        {
            System.out.println("Error "+e.getMessage());
        }
        Sort();
    }

    public static void Sort()
    {
        for(int x=0;x<TIME_TAKEN.size();x++)
        {
            for(int y=x+1;y<TIME_TAKEN.size();y++)
            {
                if(TIME_TAKEN.get(x)>TIME_TAKEN.get(y))
                {
                    int temp_int=TIME_TAKEN.get(x);
                    TIME_TAKEN.set(x, TIME_TAKEN.get(y));
                    TIME_TAKEN.set(y, temp_int);
                    String temp_name= USER_NAME.get(x);
                    USER_NAME.set(x, USER_NAME.get(y));
                    USER_NAME.set(y, temp_name);

                    String temp_date=DATE.get(x);
                    DATE.set(x, DATE.get(y));
                    DATE.set(y, temp_date);

                    String temp_time=TIME.get(x);
                    TIME.set(x, TIME.get(y));
                    TIME.set(y, temp_time);

                    String temp_dn=DAY_NIGHT.get(x);
                    DAY_NIGHT.set(x, DAY_NIGHT.get(y));
                    DAY_NIGHT.set(y, temp_dn);

                }
            }
        }
    }
}
