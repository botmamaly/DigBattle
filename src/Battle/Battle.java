package Battle;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * Created by USER on 2017/5/18.
 */
public class Battle{
       private BattleState state;
       private BattleShop shop;
       private BattleFight fight;
       private PApplet parent;
       private Timer timer;
       static int totalDef;
       static int totalLuk;
       static int totalAtk;
       private int nightCnt;

       public Battle(PApplet parent) {
              this.parent = parent;
              timer = new Timer(parent);
              setup();
       }

       public void reset(){
              nightCnt++;
              Battle.totalAtk = 20;
              state = BattleState.SHOP;
              Battle.totalDef = 0;
              Battle.totalLuk = 0;
              timer = new Timer(parent);
              shop.updateShop();
              timer.start();
       }

       public void checkMouseClicked(){
              if (state == BattleState.SHOP) {
                     shop.buyItem();
              }
              else {
                     fight.checkMousePressed();
              }
       }

       public void setup(){
              shop = new BattleShop(parent, 9, 840, 600);
              fight = new BattleFight(parent);
              state = BattleState.SHOP;
              Battle.totalDef = 0;
              Battle.totalLuk = 0;
              DefensiveItem.mine = new PImage[6];
              for (int i = 0; i < 6; i++)  DefensiveItem.mine[i] = parent.loadImage("img/mine" + i + ".png");
              nightCnt = 0;
              timer.start();
       }

       public void display(){
              if (state == BattleState.SHOP) {
                     shop.display();
                     timer.display(BattleSetting.backgroundWidth*2/3 + BattleSetting.leftSpace, BattleSetting.heightSpace, BattleSetting.backgroundWidth/3 - BattleSetting.leftSpace*2, BattleSetting.backgroundHeight/3 - BattleSetting.heightSpace*2);
                     if (timer.getRemainTime() == 0) {
                            state = BattleState.FIGHT;
                            fight.calResult();
                     }
              }
              else {
                     fight.display();
              }
       }

}

enum BattleState{
       SHOP, FIGHT;
}
