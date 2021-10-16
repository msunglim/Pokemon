package Panels;

import Pokemon.Pokemon;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InfoPanel extends VBox {

    //컨트롤 시프트 엘은 정렬이다.. 혹시 까먹을까봐 적는다..
    private Label hp;
    private Pokemon p;
    private double widthOfEachBox;
    private  int numberOfBox;
    private Rectangle r[];
    private HBox hb;
    public InfoPanel(Pokemon p) {
        this.p = p;
        setMinSize(220, 60);
        setStyle("-fx-border-color: black");
        Label name = new Label(p.getName());
        hp = new Label("HP: " + p.getCurrHP() +"/"+p.getHp());
        setAlignment(Pos.TOP_LEFT);
        getChildren().addAll(name, hp);



        hb = new HBox();
        numberOfBox= p.getHp()/10; //100이면 박스 10개 180개면 18개
         widthOfEachBox = 350.0/p.getHp();

         r = new Rectangle[numberOfBox];
        for(int i = 0; i < numberOfBox; i++){
            r[i] = new Rectangle(widthOfEachBox *5, 30);
            r[i].setFill(Color.LIGHTGREEN);
            hb.getChildren().add(r[i]);
        }

        getChildren().add(hb);

    }

    public void updateHP(int updatedHP) {
        hb.getChildren().clear();

        hp.setText("HP: " + p.getCurrHP() +"/"+p.getHp());
        for(int i = 0; i < numberOfBox; i++){
            r[i] = new Rectangle(widthOfEachBox *5, 30);
            if(p.getCurrHP() > i*10) {
                r[i].setFill(Color.LIGHTGREEN);

            }else{

                r[i].setFill(Color.RED);
            }
            hb.getChildren().add(r[i]);
        }

    }

    public void setXY(int x, int y) {
        setLayoutX(x);
        setLayoutY(y);
    }
}
