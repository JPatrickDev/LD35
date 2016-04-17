package me.jack.LD35.GUI;

import me.jack.LD35.Level.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 17/04/2016.
 */
public class HealthInfo {

    public static void render(Graphics g, Level level){
        g.fillRect(422,480,150,120);
        g.setColor(Color.black);
        g.drawString("Health: " + level.getPlayer().health,422,480);
        g.setColor(Color.white);
    }
}
