package me.jack.LD35.GUI;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Shape.*;
import me.jack.LD35.States.InGameState;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jack on 16/04/2016.
 */
public class ShapeSelect {

    static SpriteSheet icons;
    static SpriteSheet guiElements;
    static ArrayList<Image> iconImages = new ArrayList<Image>();
    static ArrayList<Shape> shapes = new ArrayList<Shape>();
    static Image selected, locked;
    static HashMap<Rectangle, Integer> hitboxLookup = new HashMap<Rectangle, Integer>();

    static {
        shapes.add(new SquareShape());
        shapes.add(new CircleShape());
        shapes.add(new OctagonShape());
        shapes.add(new DiamondShape());
        for (int i = 0; i != 4; i++) {
            Rectangle r = new Rectangle(i * 64, 480, 64, 64);
            hitboxLookup.put(r, i);
        }
    }

    public static void render(Graphics g, Level level) {
        if (icons == null) {
            try {
                icons = new SpriteSheet("res/shapeIcons.png", 64, 64);
                guiElements = new SpriteSheet("res/guiElements.png", 64, 64);
                selected = guiElements.getSprite(0, 0);
                locked = guiElements.getSprite(1, 0);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            for (int i = 0; i != 5; i++) {
                iconImages.add(icons.getSprite(i, 0));
            }
        }
        g.fillRect(0, 480, 256, 64);
        for (int i = 0; i != 4; i++) {
            g.setColor(Color.black);
            g.drawRect(i * 64, 480, 64, 64);
            g.drawImage(iconImages.get(i), i * 64, 480);
            g.setColor(Color.white);
            g.drawString((i + 1) + "", i * 64, 548);
            if (level.getPlayer().level < i) {
                g.drawImage(locked, i * 64, 480);
            }
            if (i != 0 && i < shapes.size()) {
                g.setColor(Color.red);
                float factor = level.getPlayer().getCharge() / shapes.get(i).getChargeNeeded();
                if (factor >= 1) {
                    factor = 1;
                    g.setColor(Color.green);
                }
                g.fillRect(i * 64, 538, 64 * factor, 10);
                g.setColor(Color.white);
            }
        }
        g.drawImage(selected, level.getPlayer().currentShape * 64, 480);
        g.setColor(Color.white);
    }


    public static void mouseClicked(int x, int y) {
        Rectangle me = new Rectangle(0, 480, 320, 64);
        if (!me.contains(x, y))
            return;

        for(Rectangle r : hitboxLookup.keySet()){
            if(r.contains(x,y)){
                InGameState.showInfo(hitboxLookup.get(r));
                return;
            }
        }

    }
}
