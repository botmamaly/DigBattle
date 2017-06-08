package Battle;

import Bag.BagMine;
import Window.MessageBox;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by USER on 2017/5/18.
 */
public class BattleShop {
    private PApplet parent;
    private int shopListSum;
    private int itemListSum;
    private int shelfWidth;
    private int shelfHeight;
    private int bgWidth;
    private int bgHeight;
    private ArrayList<DefensiveItem> defItemList;
    private ArrayList<Shelf> shopList;
    private PImage background;
    private boolean[] visit;
    private BagMine bag;
    private ShopState shopState;
    private MessageBox checkBox;

    private void generateList(int n){
        int []req = new int[6];
        for(int i = 0; i < n; i++){
            int luckyNum = (int)(Math.random()*51);
            int defNum = (int)(Math.random()*itemListSum);
            for(int j = 0; j < 6; j++) req[j] = (int)(Math.random() * 10);
            DefensiveItem tmp = new DefensiveItem(parent, luckyNum, defNum, i, req, BattleSetting.itemName[i]);
            defItemList.add(tmp);
        }
    }

    private void test(){
            bag = new BagMine(parent);
    }

    public BattleShop(PApplet parent, int maxItem, int bgWidth, int bgHeight){
        test();
        this.parent = parent;
        itemListSum = maxItem;
        shopListSum = 6;
        shopState = ShopState.SELECT;
        background = parent.loadImage("img/background.jpg");
        visit = new boolean[maxItem];
        defItemList = new ArrayList<DefensiveItem>();
        shopList = new ArrayList<Shelf>();
        generateList(itemListSum);
        this.bgHeight = bgHeight;
        this.bgWidth = bgWidth;
        shelfHeight = (bgHeight - 70) / (shopListSum);
        shelfWidth = bgWidth * 2 / 3 - 10;
        checkBox = new MessageBox(parent, (BattleSetting.backgroundWidth-BattleSetting.windowWidth)/2, (BattleSetting.backgroundHeight-BattleSetting.windowHeight)/2, BattleSetting.windowWidth, BattleSetting.windowHeight, "CHECK");
        updateShop();
    }

    public void updateShop(){
        shopList.clear();
        Arrays.fill(visit, false);
        Shelf tmpShelf;
        for(int i = 0; i < shopListSum;)
        {
            int itemNum = (int)(Math.random() * itemListSum);
            if (visit[itemNum] == false) {
                tmpShelf = new Shelf(parent, defItemList.get(itemNum), shelfWidth, shelfHeight, BattleSetting.leftSpace, BattleSetting.leftSpace + i * (shelfHeight + 10));
                shopList.add(tmpShelf);
                visit[itemNum] = true;
                i++;
            }
        }
        System.out.println(shopList.size());
    }

    private void showValue(float x, float y, float boxW, float boxH){
        parent.fill(244, 66, 75, 200);
        parent.rect(x, y, boxW, boxH);
        float textSz = (boxH - BattleSetting.heightSpace*3)/2;
        parent.textAlign(parent.BOTTOM, parent.LEFT);
        parent.textSize(textSz);
        parent.fill(0, 0, 0);
        parent.text("LUK: "+ Battle.totalLuk, x, y + textSz + BattleSetting.heightSpace);
        parent.text("DEF: "+ Battle.totalDef, x, y + textSz*2 + BattleSetting.heightSpace*2);
    }

    private void showBag(float x, float y, float bagW, float bagH){
            float x_interval = 5;
            float y_interval = 5;
            float gridW = (bagW - x_interval*3)/2;
            float gridH = (bagH - y_interval*4)/3;
            parent.fill(255,255,255);
            parent.rect(x, y, bagW, bagH);
            parent.textAlign(parent.BOTTOM, parent.LEFT);
            parent.textSize(gridH/4);

            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 2; c++){
                    float gridX = x + gridW*c + x_interval*(c+1);
                    float gridY = y + gridH*r + y_interval*(r+1);
                    parent.fill(206, 150, 101);
                    parent.rect(gridX, gridY, gridW, gridH);
                    parent.image(DefensiveItem.mine[r*2 + c], gridX + x_interval, gridY + y_interval, gridW - x_interval*2, gridH - y_interval*2);
                    parent.fill(0, 0, 0);
                    parent.text(bag.getNum(r*2+c), gridX, gridY + gridH - 2);
                }
            }
    }

    public void buyItem(){
         for(Shelf shelf : shopList) {
             if (shelf.checkMouseOnShelf()) {
                 if( shelf.isValidBuy(bag.getMine())){
                     Battle.totalDef += shelf.getItem().getDefValue();
                     Battle.totalLuk += shelf.getItem().getLuckyValue();
                     bag.takeMine(shelf.getItem().getRequire());
                 }
                 else{
                     parent.textAlign(parent.CENTER, parent.CENTER);
                     parent.textSize(BattleSetting.backgroundWidth/2);
                     parent.text("87", BattleSetting.backgroundWidth/2, BattleSetting.backgroundHeight/2);
                 }
             }
         }
    }

    public void display(){
        parent.image(background, 0, 0, bgWidth, bgHeight);
        for(int i = 0; i < shopList.size(); i++){
            shopList.get(i).display();
        }

        showBag(  BattleSetting.backgroundWidth*2/3 + BattleSetting.leftSpace, BattleSetting.backgroundHeight*5/9 + BattleSetting.heightSpace, BattleSetting.backgroundWidth/3 - BattleSetting.leftSpace*2, BattleSetting.backgroundHeight*4/9 - BattleSetting.heightSpace*2);
        showValue(BattleSetting.backgroundWidth*2/3 + BattleSetting.leftSpace,   BattleSetting.backgroundHeight/3 + BattleSetting.heightSpace, BattleSetting.backgroundWidth/3 - BattleSetting.leftSpace*2, BattleSetting.backgroundHeight*2/9 - BattleSetting.heightSpace*2);
        checkBox.display("8787");
        for(Shelf tmp : shopList){
            if(tmp.checkMouseOnShelf()) tmp.showValueWindos();
        }
    }

}

enum ShopState{
     SELECT, CHECK;
}
