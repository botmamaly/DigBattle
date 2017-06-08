package Battle;

/**
 * Created by USER on 2017/6/3.
 */
import processing.core.PApplet;

public class Main extends PApplet{
    Battle battle;

    public static void main(String[] args) {
        PApplet.main(new String[]{ Main.class.getName()});
    }

    @Override
    public void setup(){
      battle = new Battle(this);
    }

    @Override
    public void draw(){
        battle.display();
    }

    @Override
    public void mousePressed(){
        System.out.println("mouse Pressed");
        battle.checkMouseClicked();
    }

    @Override
    public void settings() {
        super.settings();
        size(840, 600);
    }

}
