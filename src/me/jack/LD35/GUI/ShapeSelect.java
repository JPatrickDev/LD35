package me.jack.LD35.GUI;

import me.jack.LD35.Level.Level;
import org.newdawn.slick.*;

import java.util.ArrayList;

/**
 * Created by Jack on 16/04/2016.
 */
public class ShapeSelect{

    static SpriteSheet icons;
    static SpriteSheet guiElements;
    static ArrayList<Image> iconImages = new ArrayList<Image>();
    static Image selected;
    public static void render(Graphics g, Level level){
        if(icons == null){
            try {
                icons = new SpriteSheet("res/shapeIcons.png",64,64);
                guiElements = new SpriteSheet("res/guiElements.png",64,64);
                selected = guiElements.getSprite(0,0);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            for(int  i = 0;i!= 5;i++) {
                iconImages.add(icons.getSprite(i, 0));
            }
        }
        g.fillRect(0,480,800,64);
        for(int i = 0;i!= 5;i++){
            g.setColor(Color.black);
            g.drawRect(i*64,480,64,64);
            g.drawImage(iconImages.get(i),i*64,480);
            g.setColor(Color.white);
            g.drawString((i+1) + "",i*64,548);
        }
        g.drawImage(selected,level.getPlayer().currentShape * 64,480);
        g.setColor(Color.white);
    }
}
