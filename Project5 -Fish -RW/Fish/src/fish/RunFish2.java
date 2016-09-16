package fish;
/**
 * 10/22/2015
 * @author rdw77236
 */
import java.util.Vector;

public class RunFish2 implements Runnable
{
    private Vector<Swimmers> fishes;
    
    public static int FISHIES = 0;
    private int sleepTime;
    
//    RunFish()
//    {
//        sleepTime = 110;
//        fishes = new Vector<Swimmers>(200);
//    }
    
    RunFish2(int fish_number, int sleepTime)
    {
        FISHIES = fish_number;
        this.sleepTime = sleepTime;
        fishes = new Vector<Swimmers>(fish_number);
    }
    
    @Override
    public void run()
    {
        int i = 1;
        while(true)
        {
            for (Swimmers swimmers : fishes)
                swimmers.move();
                
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            // WONT WORK
           //repaint();
        }
    }
}
