package Support;

import java.util.ArrayList;

public class Type {

    //ex) 이 타입이 물일때
    private ArrayList<Type> counterList; //이 속성을 카운터치는 속성들, ex) 전기
    private ArrayList<Type> antiCounterList;//이 속성이 카운터치는 속성들 ex) 불
    private String typeName;
    public Type(String typeName){
        this.typeName = typeName;
        counterList = new ArrayList<>();
        antiCounterList = new ArrayList<>();
    }

    public void setCounterList(Type ... objects){
        if(objects ==null){
            return;
        }
        for(Type t : objects){
            counterList.add(t);
        }
    }
    public void setAntiCounterList(Type ... objects){
        if(objects ==null){
            return;
        }
        for(Type t : objects){
            antiCounterList.add(t);
        }
    }
    public String getTypeName(){
        return typeName;
    }

    //@return 카운터를 당했을때 return -1, 카운터를 쳤을때 return 1, else return 0.
    public int getRelation(Type t){

        if(counterList.contains(t)){
            return -1;
        }
        if(antiCounterList.contains(t)){
            return 1;
        }
        else{
            return 0;
        }
    }

}
