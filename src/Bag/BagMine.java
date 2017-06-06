package Bag;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Created by Rober on 2017/5/24.
 */
public class BagMine {

    PApplet par;
    PImage bg;
    PImage[] mineIcon;

    int activeId = 1;
    int[] mine;
    int limitWeight = 60;
    int currentWeight = 0;

    public BagMine(PApplet par){
        this.par = par;
        mine = new int[6 + 10];
        for(int i = 0; i < 6; i++){
            mine[i] = (int)(Math.random()*100);
        }
    }

    public int getLimitWeight(){
        return limitWeight;
    }

    public void setLimitWeight(int v){
        limitWeight = v;
    }

    public int getNum(int id){
        return mine[id];
    }

    public void addMine(int id){
        mine[id]++;
    }

    public void delMine(int id, int num){
        mine[id] -= num;
    }

    public int getActiveId(){
        return activeId;
    }

    public void keyPressed(int key){
        if(key==par.UP   && activeId>3)  activeId -= 3;
        if(key==par.DOWN && activeId<=3) activeId += 3;
        if(key==par.RIGHT&& activeId<=5) activeId += 1;
        if(key==par.LEFT && activeId>=2) activeId -= 1;
        System.out.println(activeId);
    }
}
