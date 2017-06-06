package Battle;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Created by USER on 2017/6/4.
 */
public class BattleFight {
       private PApplet parent;
       private PImage bg;

       public BattleFight(PApplet parent){
              this.parent = parent;
              this.bg = parent.loadImage("img/fight.jpg");
       }


       public void display(){
              parent.image(bg, 0, 0, BattleSetting.backgroundWidth, BattleSetting.backgroundHeight);
       }

}
