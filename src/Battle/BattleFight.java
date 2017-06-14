package Battle;

import Window.MessageBox;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Created by USER on 2017/6/4.
 */
public class BattleFight {
       private PApplet parent;
       private PImage bg;
       private static PImage loseImg;
       private static PImage winImg;
       private static PImage waitImg;
       private MessageBox message;
       private int atkCnt;
       private int defCnt;
       private FightState state;


       public BattleFight(PApplet parent){
              this.parent = parent;
              this.bg = parent.loadImage("img/fight.jpg");
              BattleFight.loseImg = parent.loadImage("img/gamelose.jpg");
              BattleFight.winImg  = parent.loadImage("img/gamewin.jpg");
              BattleFight.waitImg  = parent.loadImage("img/gamewait.jpg");
              message = new MessageBox(parent, (BattleSetting.backgroundWidth-BattleSetting.windowWidth)/2, (BattleSetting.backgroundHeight-BattleSetting.windowHeight)/2, BattleSetting.windowWidth, BattleSetting.windowHeight, "CHECK");
              atkCnt = 0;
              defCnt = 0;
              state = FightState.WAIT;
       }

       public void reset(){
              atkCnt = 0;
              defCnt = 0;
              state = FightState.WAIT;
       }

       public void calResult() {
              for (int i = 0; i < Battle.totalAtk; i++) {
                     int tmp = (int)(Math.random()*101);
                     System.out.println("tmp = " + tmp);
                     if(tmp < 60) atkCnt++;
              }

              for (int i = 0; i < Battle.totalDef; i++) {
                     int tmp = (int)(Math.random()*101);
                     System.out.println("tmp = " + tmp);
                     if(tmp < Battle.totalLuk) defCnt++;
              }
       }

       public int result() {
              if (state == FightState.WIN) return 2;
              else if (state == FightState.LOSE) return 1;
              else return 0;
       }

       public void checkMousePressed(){
              int check = message.checkMousePressed();
              if (state == FightState.CHECK && (check == 1 || check == 2)) {
                     if (atkCnt > defCnt) state = FightState.LOSE;
                     else state = FightState.WIN;
              }
       }

       public void display(){
              if (state == FightState.WAIT) {
                     parent.image(waitImg, 0, 0, BattleSetting.backgroundWidth, BattleSetting.backgroundHeight);
                     state = FightState.CHECK;
                     parent.delay(4000);
              }
              else if (state == FightState.CHECK) {
                     message.display("DEF: " + defCnt + ", ATK: " + atkCnt);
              }
              else if (state == FightState.WIN) {
                     parent.image(winImg, 0, 0, BattleSetting.backgroundWidth, BattleSetting.backgroundHeight);
              }
              else {
                     parent.image(loseImg, 0, 0, BattleSetting.backgroundWidth, BattleSetting.backgroundHeight);
              }
       }

}

enum FightState{
       WAIT, CHECK, LOSE, WIN;
}