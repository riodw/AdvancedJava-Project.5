package fish;
/**
 *
 * @author rdw77236
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

public class Swimmers extends Thread
{
    private final Component tank;
    private final Image image1, image2;
    private final Point location, velocity;
    private final Random random;
        
    public Swimmers(Image image1, Image image2, Component tank)
    {
        random = new Random();
        this.tank = tank;
        this.image1 = image1;
        this.image2 = image2;
        this.location = new Point(
                        100 + (Math.abs(random.nextInt()) % 300),
                        100 + Math.abs(random.nextInt()) % 100);
        this.velocity = new Point(random.nextInt(), random.nextInt());
        // FOR DEBUGGING
        System.out.println(location);
    }
    
    public void draw(Graphics g)
    {       
        //Size of Drawn Fish
        int size = 50;
        int x = 100;
        int y = 100;
        
        //Color of Fish
        Random rand = new Random();
        int r = rand.nextInt(255 - 100) + 100;
        int cc = rand.nextInt(255 - 100) + 100;
        int b = rand.nextInt(200 - 100) + 100;
        Color randColor = new Color(r,cc,b);
        //FISH
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
    
    public void move()
    {
        Rectangle edges = tank.getBounds();

        if (random.nextInt() % 7 <= 1)
        {
            velocity.x += random.nextInt();
            velocity.x = Math.min(velocity.x, 8);
            velocity.x = Math.max(velocity.x, -8);
            velocity.y += random.nextInt();
            velocity.y = Math.min(velocity.y, 8);
            velocity.y = Math.max(velocity.y, -8);
        }

        //add the velocity to the location of the fish to make it move
        location.x += velocity.x;
        location.y += velocity.y;

        if (location.x < edges.x)
        {
            location.x = edges.x;
            velocity.x = -velocity.x;
        }

        if ((location.x + image1.getWidth(tank)) > (edges.x + edges.width))
        {
            //location.x = edges.x + edges.width - image1.getWidth(tank);
            location.x = edges.x + edges.width - image1.getWidth(tank);
            velocity.x = -velocity.x;
        }

        if (location.y < edges.y)
        {
            location.y = edges.y;
            velocity.y = -velocity.y;
        }

        if ((location.y + image1.getHeight(tank)) > (edges.y + edges.height))
        {
            location.y = edges.y + edges.height - image1.getHeight(tank);
            //location.y = edges.y - image1.getHeight(tank);
            velocity.y = -velocity.y;
        }
    }
    
    public void drawFishImage(Graphics g)
        {
//            if (velocity.x > 0)
//            {
                g.drawImage(image1, location.x, location.y, tank);
//            } else {
                g.drawImage(image2, location.x, location.y, tank);
            //}
        }
}
