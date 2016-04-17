package me.jack.LD35.GUI;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Jack on 17/04/2016.
 */
public class DisplayShapeInfo {

    static boolean displaying = false;
    public static Image background = null;
    public static void displayInfo(int shape){
        if(background == null){
            try {
                background = new Image("res/shapeInfoTemplate.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }


    public static void mouseClick(int x,int y){
        if(!displaying)return;

    }
}
