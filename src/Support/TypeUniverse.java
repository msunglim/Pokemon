package Support;

import java.util.ArrayList;

public class TypeUniverse {

    private Type [] list = new Type[5];
    public TypeUniverse(){

        Type water = new Type("물");

        Type fire = new Type("불");
        Type electron = new Type("전기");
        Type iron = new Type("강철");
        Type normal = new Type("노말");


        water.setCounterList(electron);//전기
        water.setAntiCounterList(fire);//불

        fire.setCounterList(water);
        fire.setAntiCounterList(iron);//불이 강철을 카운터친데

        electron.setCounterList();
        electron.setAntiCounterList(water);

        iron.setCounterList(fire);
        iron.setAntiCounterList();
        list[0] = water;
        list[1] = fire;
        list[2] = electron;
        list[3] = iron;
        list[4] = normal;


    }

    public Type getType(int i){
        return list[i];
    }
}
