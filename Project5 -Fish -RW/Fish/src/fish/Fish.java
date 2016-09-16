package fish;
/**
 * @date 10/13/2015
 * @author rdw77236
 */
import java.awt.Color;
import javax.swing.*;

public class Fish extends JFrame
{
    JPanel pane; // The Panel
    JLabel imageLabel = new JLabel(); // The FIsh Image - Orange
    JLabel headerLabel = new JLabel(); // The GIF Seaweed image
 
    /**
     * * MAIN *
     */
    public static void main(String[] args)
    {
        new Fish(); // make instance of this class with the Panel
        
//        BankAccount b = new BankAccount(0);
//        DepositThread dt = new DepositThread(b, 100);
//        WithdrawThread wt = new WithdrawThread(b, 100);
//        dt.start();
//        wt.start();
    }
    
    public Fish() {
        try {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pane = (JPanel) getContentPane();
            setSize(800, 600);
            pane.setOpaque(false);
            //setLayout(null);
            setLayout(new OverlayLayout(pane) );
            setBackground(Color.BLUE);
            setTitle("Aquarium - Project 5");
            
            // New Tank with Panel
            Tank tank = new Tank(10, 110);

            // add the GIF label - SEAWEED
            ImageIcon i1 = new ImageIcon(this.getClass().getResource("/seaweed.gif"));
            imageLabel.setIcon(i1);
            imageLabel.setAlignmentX(10);
            imageLabel.setAlignmentY(10);
            pane.add(imageLabel);
            
            //Second Label - for Seaweed GIF - DOES NOT WORK
            JLabel li2 = new JLabel();
            ImageIcon i2 = new ImageIcon(this.getClass().getResource("/seaweed.gif"));
            li2.setIcon(i2);
            li2.setAlignmentX(20);
            li2.setAlignmentY(10);
            // Try to add a second GIF - DOES NOT WORK
            pane.add(li2);
            //pane.add(li2, BorderLayout.CENTER); - has no effect
            //Add the TANK with the Fish
            pane.add(tank);
            
            // place it in middle
            this.setLocationRelativeTo(null);
            // show it
            this.setVisible(true);

        } catch (Exception exception) {}
    }
}
