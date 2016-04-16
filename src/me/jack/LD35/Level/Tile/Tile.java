package me.jack.LD35.Level.Tile;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.HashMap;

/**
 * Created by Jack on 16/04/2016.
 */
public class Tile{


    public static HashMap<Integer,Tile> tileLookup = new HashMap<Integer,Tile>();
    public static int TILE_SIZE = 16;

    private int ID;
    private Color color;

    static{
        new Tile(1,new Color(255,255,255));
    }


    public Tile(int ID, Color color) {
        this.ID = ID;
        this.color = color;
        Tile.tileLookup.put(ID,this);
    }

    public int getID() {
        return ID;
    }

    public Color getColor() {
        return color;
    }

    public  void render(int x,int y,Graphics g){
        g.setColor(getColor());
        g.fillRect(x*TILE_SIZE,y*TILE_SIZE,TILE_SIZE,TILE_SIZE);
        g.setColor(Color.white);
    }

    public static void render(Graphics g, int x,int y,int i){
        if(tileLookup.get(i) != null)
            tileLookup.get(i).render(x,y,g);
    }
}
