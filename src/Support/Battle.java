package Support;

import Panels.BattlePanel;
import Panels.TextGenerator;
import Pokemon.Pokemon;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

import java.util.Timer;
import java.util.TimerTask;

public class Battle {

    private Pokemon p1;
    private AI p2;
    private StackPane south;
    private TextGenerator tg;
    private BattlePanel bp;

    public Battle(BattlePanel bp, Pokemon p1, AI p2, StackPane south, TextGenerator tg) {

        this.p1 = p1;
        this.p2 = p2;
        this.south = south;
        this.tg = tg;
        this.bp = bp;
    }

    public void fight(int i){
        Skill skill = p2.useSkill();

        Timer tm = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    tg.setText("상대의 " +p2.getPokemon().getName() +"이(가) "+ skill.getName()+ "을(를) 사용했다!");

                    south.getChildren().add(tg);
                    if(p1.getType().getRelation(skill.getType()) == -1){
                       // System.out.println("효과가 굉장했다!");
                        p1.setCurrHP(p1.getCurrHP() - (skill.getDamage()*2));
                        Timer tm11 = new Timer();
                        TimerTask tt11 = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(()->{
                                    tg.setText("효과가 굉장했다!");
                                });
                            }
                        };
                        tm11.schedule(tt11, 1500);
                    }else if (p1.getType().getRelation(skill.getType()) == 1) {
                  //      System.out.println("효과가 별로인듯하다..");
                        p1.setCurrHP(p1.getCurrHP() - (skill.getDamage()/2));
                        Timer tm11 = new Timer();
                        TimerTask tt11 = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(()->{
                                    tg.setText("효과가 별로인듯하다..");
                                });
                            }
                        };
                        tm11.schedule(tt11, 1500);
                    }else {
                        p1.setCurrHP(p1.getCurrHP() - skill.getDamage());
                    }
                    if(p1.getCurrHP() <= 0){

                        bp.getIp().updateHP(0);

                        Timer tm2 = new Timer();
                        TimerTask tt2 = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(()->{
                                    tg.setText(p1.getName()+"은(는) 쓰러졌다!\n"+p1.getName()+" 시합 불가능!");
                                    stopFight(0);
                                });
                            }
                        };
                        tm2.schedule(tt2, 2000);
                    }else {
                        bp.getIp().updateHP(p1.getCurrHP());

                        Timer tm2 = new Timer();
                        TimerTask tt2 = new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {

                                    tg.setText("자신의 " + p1.getName() + "이(가) " + p1.getSkill(i).getName() + "을(를) 사용했다!");

                                    //  south.getChildren().add(tg);

                                    if(p2.getPokemon().getType().getRelation(p1.getSkill(i).getType()) == -1){
                                     //   System.out.println("효과가 굉장했다!");
                                        p2.getPokemon().setCurrHP(p2.getPokemon().getCurrHP() - p1.getSkill(i).getDamage()*2);
                                        Timer tm11 = new Timer();
                                        TimerTask tt11 = new TimerTask() {
                                            @Override
                                            public void run() {
                                                Platform.runLater(()->{
                                                    tg.setText("효과가 굉장했다!");
                                                });
                                            }
                                        };
                                        tm11.schedule(tt11, 1500);
                                    }else if (p2.getPokemon().getType().getRelation(p1.getSkill(i).getType()) == 1) {
                                        System.out.println("효과가 별로인듯하다..");
                                        p2.getPokemon().setCurrHP(p2.getPokemon().getCurrHP() - (p1.getSkill(i).getDamage()/2));
                                        Timer tm11 = new Timer();
                                        TimerTask tt11 = new TimerTask() {
                                            @Override
                                            public void run() {
                                                Platform.runLater(()->{
                                                    tg.setText("효과가 별로인듯하다..");
                                                });
                                            }
                                        };
                                        tm11.schedule(tt11, 1500);
                                    }else {
                                        p2.getPokemon().setCurrHP(p2.getPokemon().getCurrHP() - p1.getSkill(i).getDamage());
                                    }
                                 //   p2.getPokemon().setCurrHP(p2.getPokemon().getCurrHP() - p1.getSkill(i).getDamage());

                                    if(p2.getPokemon().getCurrHP() <=0){
                                        bp.getIp2().updateHP(0);
                                        Timer tm3 = new Timer();
                                        TimerTask tt3 = new TimerTask() {
                                            @Override
                                            public void run() {
                                                Platform.runLater(()->{
                                                    tg.setText(p2.getPokemon().getName()+"은(는) 쓰러졌다!\n"+p2.getPokemon().getName()+" 시합 불가능!");
                                                    stopFight(1);
                                                });
                                            }
                                        };
                                        tm3.schedule(tt3, 2000);
                                    }
                                    else {
                                        bp.getIp2().updateHP(p2.getPokemon().getCurrHP());

                                        Timer tm3 = new Timer();
                                        TimerTask tt3 = new TimerTask() {
                                            @Override
                                            public void run() {
                                                Platform.runLater(() -> {
                                                    bp.askWhatToDo();
                                                });
                                            }
                                        };
                                        tm3.schedule(tt3, 2000);
                                    }
                                });
                            }
                        };
                        tm2.schedule(tt2, 2000);
                    }
                });
            }
        };
        tm.schedule(tt, 0);




    }
    private void stopFight(int i){
        switch (i){
            case 0:
                bp.moveImage(bp.getPi1(), new MoveTo(bp.getPi1().getX()+bp.getPi1().getFitWidth()/2,bp.getPi1().getFitHeight()/2+bp.getPi1().getY()), new LineTo(bp.getPi1().getX()+bp.getPi1().getFitWidth()/2, bp.getPi1().getFitHeight()+bp.getPi1().getY()+50), 1);

                Timer tm = new Timer();
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(()->{
                            bp.getMiddle().getChildren().remove(bp.getPi1());
                        });
                    }
                };
                tm.schedule(tt, 1000);


                 break;
            case 1:
                bp.moveImage(bp.getPi2(), new MoveTo(bp.getPi2().getX()+bp.getPi2().getFitWidth()/2,bp.getPi2().getFitHeight()/2+bp.getPi2().getY()), new LineTo(bp.getPi2().getX()+bp.getPi2().getFitWidth()/2, bp.getPi2().getFitHeight()+bp.getPi2().getY()+50), 1);

                // bp.moveImage(bp.getPi2(), new MoveTo(980,30), new LineTo(980, 230), 1);
                Timer tm2 = new Timer();
                TimerTask tt2 = new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(()->{
                            bp.getMiddle().getChildren().remove(bp.getPi2());
                        });
                    }
                };
                tm2.schedule(tt2, 1000);

                break;

        }
    }
}
