/**
 * Created by USER on 2017/6/3.
 */
import Battle.Battle;
import processing.core.PApplet;

public class Main extends PApplet{
    Battle battle;

    public static void main(String[] args) {
        PApplet.main(new String[]{ Main.class.getName()});
    }

    @Override
    public void setup(){
      battle = new Battle(this);
      frameRate(1000);
      battle.reset();
    }

    @Override
    public void draw(){
        if (frameCount % 2 == 0) {
            battle.display();
            textSize(30);
            text(frameRate, 50, 50);
        }
    }

    @Override
    public void mousePressed(){
        System.out.println("mouse Pressed");
        battle.checkMouseClicked();
//        thread("test");
    }

    public void test(){
        battle.checkMouseClicked();
    }

    @Override
    public void settings() {
        super.settings();
        size(840, 600);
    }

}
