package itwu.domain;

import java.util.ArrayList;

public class Consumable {
    //数量
    private String name;
    private int num;

    public Consumable(String name, int num) {
        this.name = name;
        this.num = num;
    }


    public Consumable() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void initConsumable(ArrayList<Consumable> consumablesList) {
        consumablesList.add(new Consumable("桃子", 10));
        consumablesList.add(new Consumable("煎蛋", 20));
        consumablesList.add(new Consumable("花酿鸡", 30));
        consumablesList.add(new Consumable("黑背鲈鱼", 40));
        consumablesList.add(new Consumable("白玉汤", 50));
    }
}
