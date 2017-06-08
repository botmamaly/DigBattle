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
            mine[i] = 0;
        }
    }

    public int[] getMine(){
        return mine;
    }

    public int getNum(int id){
        return mine[id];
    }

    public void addMine(int id){
        mine[id]++;
    }

    public void takeMine(int []require){
           for(int i = 0; i < 6; i++)
               mine[i] -= require[i];
    }

    public void delMine(int id, int num){
        mine[id] -= num;
    }

}
