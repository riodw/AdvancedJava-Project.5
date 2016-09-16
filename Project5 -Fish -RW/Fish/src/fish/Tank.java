package fish;
/**
 * 10/20/2015
 * @author rdw77236
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;

public class Tank extends JPanel implements ActionListener
{
    private BufferedImage fishPic; // Fish Picture
    public static int FISHIES; // Number of Fish
    private int sleepTime; // Thread Sleep Time
    // To store the fish
    private Vector<Swimmers> fishes = new Vector<Swimmers>(FISHIES);
    
    /**
     * DEFAULT CONSTRUCTOR - Tank()
     */
    public Tank()
    {
        FISHIES = 200;
        sleepTime = 110;
        fishes = new Vector<Swimmers>(FISHIES);
        
        try {
            fishPic = ImageIO.read(getClass().getResource("/gnome_panel_fish.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
    /**
     * EXPLICIT CONSTRUCTOR - Tank()
     * @param fish_number = number of fish to create passed from main 
     * @param sleepTime = time thread sleeps passes from the main
     */
    public Tank(int fish_number, int sleepTime)
    {
        FISHIES = fish_number;
        this.sleepTime = sleepTime;
        fishes = new Vector<Swimmers>(fish_number);
        
        try {
            fishPic = ImageIO.read(getClass().getResource("/fish.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        for (int index = 0; index < FISHIES; index++)
            fishes.add(new Swimmers(fishPic, fishPic, this));
            
        //Thread runFish = new Thread(new RunFish(FISHIES, sleepTime));
        Thread runFish = new Thread(new RunFish());
        runFish.setDaemon(true);
        runFish.start();
        
    }
    
    /**
     * Was going to use this to control one fish with Keyboard input
     * @param evt Keyboard input - Not USED
     */
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        // Can implement the moving fish controled by keyboard
    }
    
    /**
     * Makes the Water and the Sand - and graphic fish
     * @param g Graphics Component
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Color water = new Color(100,150,220);
        g.setColor(water);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        Color sand = new Color(206,200,100);
        g.setColor(sand);
        double yy = getHeight()/1.2;
        g.fillRect(0, (int)(yy), getWidth(), getHeight());
        
        
        for (Swimmers swimmers : fishes)
            swimmers.drawFishImage(g);
        
        
        //Placement of fish
        Random rand = new Random();
        int x = rand.nextInt(getWidth());
        int size = 50;
        int y = rand.nextInt(getHeight());
        
        //Color of Fish
        int r = rand.nextInt(255 - 150) + 150; //RED
        int gg = rand.nextInt(255 - 150) + 150;//GREEN
        int b = rand.nextInt(200 - 150) + 150;//BLUE
        Color randColor = new Color(r,gg,b);// Set Colors
        //Dimentions of the Fish
        g.setColor(randColor);
        g.fillOval( x + size/2, y + size/2, size * 4, size );
        Polygon polygon = new Polygon();
        polygon.addPoint( x, y );
        polygon.addPoint( x + size, y + size );
        polygon.addPoint( x, y + 2 * size );
        polygon.addPoint( x + size/3, y + size );
        g.fillPolygon( polygon );
        g.setColor( Color.green );
        g.fillOval( x + size * 7 / 2, y + size * 4 / 5, size / 3, size / 3 );
    }
    
    /**
     * RunFish - Where the Program Runs and Activates the Threads
     */
    public class RunFish implements Runnable
    {
        @Override
        public void run()
        {
            //To Go Through the Array of Fish
            //int i = 1; 
            while(true)
            {
                for (Swimmers swimmers : fishes)
                    swimmers.move();

                try {
                    Thread.sleep(sleepTime);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                repaint();
            }
        }
    }
}