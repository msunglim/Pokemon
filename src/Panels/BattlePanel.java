package Panels;

import Support.AI;
import Support.Battle;
import Support.Player;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import javax.swing.*;
import javax.xml.stream.EventFilter;
import java.security.Key;
import java.util.Timer;
import java.util.TimerTask;

public class BattlePanel extends BorderPane {

    //middle
    private Pane middle = new Pane();
    private StackPane south = new StackPane();

    private ImageView iv;
    private ImageView iv2;

    private Player p1;
    private Player p2;

    private InfoPanel ip;
    private InfoPanel ip2;

    private Battle battle;
    private TextGenerator tg;

    private ImageView pi1;
    private ImageView pi2;
    public BattlePanel(Player p1, Player p2) {


        this.p1 = p1;
        this.p2 = p2;

        AI ai = new AI(p2.getPokemon());

        setCenter(middle);
        south.setPrefSize(1000, 100);
        south.setAlignment(Pos.CENTER);
        setBottom(south);


        setPrefSize(1280, 720);
        //시작시 동그란거두개생성
        setEllipse();
        //캐릭터 Ellipse에 얹기기
        moveCharacter();

        //ae means actionEvent

         tg = new TextGenerator("로켓단 조무래기가 승부를 걸어왔다!");
         battle = new Battle(this,p1.getPokemon(),ai,south,tg);
        Timer tm = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    south.getChildren().add(tg);
                    iv.setImage(new Image("file:data/Player2.png"));

                    iv2.setImage(new Image("file:data/Enemy2.png"));

                    moveImage(iv, new MoveTo(180, 480), new LineTo(-300, 480), 2);
                    moveImage(iv2, new MoveTo(1080, 100), new LineTo(1400, 100), 2);

                    Circle c = new Circle(10);
                    c.setFill(Color.RED);
                    moveImage(c, new MoveTo(100, 400), new QuadCurveTo(100, 400, 200, 550), 2);

                    middle.getChildren().add(c);

                    Circle c2 = new Circle(10);
                    c2.setFill(Color.RED);
                    moveImage(c2, new MoveTo(1240, 100), new QuadCurveTo(1080, 100, 1080, 150), 2);

                    middle.getChildren().add(c2);

                    TimerTask tt2 = new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                tg.setText("상대는 " + p2.getPokemon().getName() + "을(를) 내보냈다!\n나는 " + p1.getPokemon().getName() + "을(를) 내보냈다!");

                                TimerTask tt3 = new TimerTask() {
                                    @Override
                                    public void run() {
                                        Platform.runLater(() -> {


                                             ip = new InfoPanel(p1.getPokemon());
                                            //    ip.setXY(700, 500); it will distrupt the path below
                                            middle.getChildren().add(ip);


                                             ip2 = new InfoPanel(p2.getPokemon());
                                            //  ip2.setXY(200, 100);
                                            middle.getChildren().add(ip2);

                                            moveImage(ip, new MoveTo(1380, 500), new LineTo(700, 500), 1);

                                            moveImage(ip2, new MoveTo(-300, 100), new LineTo(500, 100), 1);


                                            pi1 = p1.getPokemon().getIv();
                                            pi1.setX(100);
                                            pi1.setY(450);


                                             pi2 = p2.getPokemon().getIv();
                                            pi2.setX(980);
                                            pi2.setY(30);

                                            middle.getChildren().addAll(pi1, pi2);

                                            FadeTransition ft = new FadeTransition(Duration.seconds(1), pi1);
                                            ft.setFromValue(0);
                                            ft.setToValue(1);
                                            ft.play();
                                            ft = new FadeTransition(Duration.seconds(1), pi2);
                                            ft.setFromValue(0);
                                            ft.setToValue(1);
                                            ft.play();
                                            middle.getChildren().removeAll(c, c2);

                                            askWhatToDo();

                                        });
                                    }
                                };
                                tm.schedule(tt3, 1000);

                            });
                        }
                    };
                    tm.schedule(tt2, 2000);
                });
            }
        };
        tm.schedule(tt, 2000);


        requestFocus();
    }

    public void askWhatToDo(){
        tg.setText("무엇을 하시겠습니까?");

        EventHandler filter = new EventHandler<KeyEvent>(){
            public void handle(KeyEvent event){
                if (event.getCode() == KeyCode.SPACE) {

                    //이래야 나중에 새롭게 스페이스키를 인식함. 이거없으면 스페이스가 계속 스택처럼 쌓여서 한번에 2번씩눌리게됨..
                    event.consume();
                    removeEventFilter(KeyEvent.KEY_PRESSED, this);

                    south.getChildren().remove(tg);
                    //  tg.setText("배틀하자");

                    HBox hb = new HBox();
                    hb.setAlignment(Pos.CENTER);
                    hb.setSpacing(100);
                    Button b1 = new Button("싸운다");
                    Button b2 = new Button("가방");
                    Button b3 = new Button("도망쳐!");
                    hb.getChildren().addAll(b1, b2, b3);

                    south.getChildren().add(hb);

                    b1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            hb.getChildren().removeAll();
                            south.getChildren().remove(hb);

                            HBox hb = new HBox();
                            hb.setAlignment(Pos.CENTER);
                            hb.setSpacing(100);
                            Button b1 = new Button(p1.getPokemon().getSkill(0).getName());
                            Button b2 = new Button(p1.getPokemon().getSkill(1).getName());
                            Button b3 = new Button(p1.getPokemon().getSkill(2).getName());
                            Button b4 = new Button(p1.getPokemon().getSkill(3).getName());
                            hb.getChildren().addAll(b1, b2, b3, b4);

                            south.getChildren().add(hb);

                            b1.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {

                                    battle.fight(0);

                                    hb.getChildren().clear();
                                }
                            });
                            b2.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {

                                    battle.fight(1);

                                    hb.getChildren().clear();
                                }
                            });
                            b3.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {

                                    battle.fight(2);

                                    hb.getChildren().clear();
                                }
                            });
                            b4.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {

                                    battle.fight(3);

                                    hb.getChildren().clear();
                                }
                            });
                        }
                    });
                }
            }
        };
        addEventFilter(KeyEvent.KEY_PRESSED, filter);

    }
    private void moveCharacter() {

        iv = p1.getIv();
        middle.getChildren().add(iv);
        iv2 = p2.getIv();
        middle.getChildren().add(iv2);
        moveImage(iv, new MoveTo(1200, 480), new LineTo(180, 480), 2);
        moveImage(iv2, new MoveTo(0, 100), new LineTo(1080, 100), 2);

    }


    public void moveImage(Object iv, MoveTo mt, Object lt, int sec) {

        Path path = new Path();
        //move to: from, line to: to
        if (lt instanceof LineTo) {
            path.getElements().addAll(mt, (LineTo) lt);
        } else if (lt instanceof QuadCurveTo) {

            path.getElements().addAll(mt, (QuadCurveTo) lt);
        }
        PathTransition pathTransition = new PathTransition();
        if (iv instanceof ImageView) {
            pathTransition.setNode((ImageView) iv);
        } else if (iv instanceof InfoPanel) {
            pathTransition.setNode((InfoPanel) iv);
        } else if (iv instanceof Circle) {
            pathTransition.setNode((Circle) iv);
        }
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.seconds(sec));

        //setting up the cycle count
        pathTransition.setCycleCount(1);


        //Playing path transition
        pathTransition.play();
    }

    private void setEllipse() {
        Ellipse e1 = new Ellipse();
        e1.setFill(Color.AQUA);
        e1.setCenterX(200);
        e1.setCenterY(550);
        e1.setRadiusX(100);
        e1.setRadiusY(50);

        middle.getChildren().add(e1);

        Ellipse e2 = new Ellipse();
        e2.setFill(Color.AQUA);
        e2.setCenterX(1080);
        e2.setCenterY(150);
        e2.setRadiusX(100);
        e2.setRadiusY(50);

        middle.getChildren().add(e2);
    }

    public InfoPanel getIp(){
        return ip;
    }
    public InfoPanel getIp2(){
        return ip2;
    }

    public Pane getMiddle(){
        return middle;
    }

    public ImageView getPi1(){
        return pi1;
    }
    public ImageView getPi2(){
        return pi2;
    }
}
