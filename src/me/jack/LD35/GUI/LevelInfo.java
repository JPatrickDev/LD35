package me.jack.LD35.GUI;

import me.jack.LD35.Level.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 16/04/2016.
 */
public class LevelInfo {

    public static void render(Graphics g, Level level){
      //  g.setColor(Color.red);
       // g.fillRect(322,480,100,64);
        g.setColor(Color.black);
        g.fillRect(322,480,100,16);
        g.setColor(Color.white);
        float percentage = level.getPlayer().getExp()/level.getPlayer().getExpToNextLevel();
        g.fillRect(322,480,100*percentage,16);
        g.drawString(level.getPlayer().getExp() + "/" + level.getPlayer().getExpToNextLevel(),322,495);
        g.drawString("Level:" + ((int)level.getPlayer().getLevel()+1),322,510);

    }
}
