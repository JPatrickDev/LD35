package me.jack.LD35.Level;

import me.jack.LD35.Entity.TestMob;
import me.jack.LD35.Level.Tile.Tile;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

/**
 * Created by Jack on 16/04/2016.
 */
public class Level{

    private int[] tiles;
    private int width,height;

    TestMob mob = new TestMob(5,22*Tile.TILE_SIZE);
    ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();

    public Level(int width,int height){
        this.width = width;
        this.height = height;
        tiles = new int[width*height];
        for(int x = 0;x!= 25;x++){
            setTileAt(x,20,1);
            setTileAt(x,28,1);
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getTileAt(int x,int y){
        if(x < 0 || x > this.width)return -1;
        if(y< 0 || y > this.height) return -1;
        return tiles[x+y*this.width];
    }

    public void setTileAt(int x,int y, int i){
        System.out.println("Setting tile " + x + ":" + y + ":" + width + ":" + height);
        if(x < 0 || x > width)return;
        if(y< 0 || y > height) return;
        hitboxes.add(new Rectangle(x * Tile.TILE_SIZE,y*Tile.TILE_SIZE,Tile.TILE_SIZE,Tile.TILE_SIZE));
        tiles[x+y*width] = i;
    }

    public void render(Graphics g){
        for(int x = 0;x!= width;x++){
            for(int y = 0;y!= height;y++){
                int i = getTileAt(x,y);
                if(i != -1){
                    Tile.render(g,x,y,i);
                 //   System.out.println("Render: " + i);
                }
            }
        }
        mob.render(g);
    }

    public void update(){
        mob.update(this);
    }

    public Rectangle canMove(float x, float y, int width,int height) {
        Rectangle r = new Rectangle(x,y,width,height);

        for(Rectangle hit : hitboxes){
            if(r.intersects(hit))return hit;
        }
        return null;
    }
}
