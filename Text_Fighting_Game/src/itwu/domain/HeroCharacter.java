package itwu.domain;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HeroCharacter extends Character {
    private ArrayList<String> skillList;
    private ArrayList<Consumable> packageList;
    private int MP;
    private int maxMP;

  /*  public HeroCharacter(ArrayList<String> skillList) {
        this.skillList = skillList;
    }*/

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int HealMP(int amount) {
        int actualHeal = 0;
        this.MP += amount;
        if (this.MP > this.maxMP) {
            actualHeal = this.MP - this.maxMP;
            this.MP = this.maxMP;
        } else {
            actualHeal = amount;
        }
        return actualHeal;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public HeroCharacter(String name, int HP, int attack, int defense, int MP) {
        super(name, HP, attack, defense);
        this.MP = MP;
        this.maxMP = MP;
        this.skillList = new ArrayList<>();
        skillList.add("普通攻击");
        skillList.add("全力一击");
        skillList.add("生命汲取");
        this.packageList = new ArrayList<>();
    }

/*    public void setSkillList(ArrayList<String> skillList) {
        this.skillList = skillList;
        skillList.add("普通攻击");
        skillList.add("全力一击");
        skillList.add("生命汲取");
    }*/

    public ArrayList<Consumable> getPackageList() {
        return packageList;
    }

    public void setPackageList(ArrayList<Consumable> packageList) {
        this.packageList = packageList;
    }
/*public HeroCharacter() {
        super();
        skillList = new ArrayList<>();
        skillList.add("普通攻击");
        skillList.add("全力一击");
        skillList.add("生命汲取");
    }*/

    public ArrayList<String> getSkillList() {
        return skillList;
    }

    /* public void setSkillList(ArrayList<String> skillList) {
         this.skillList = skillList;
     }*/
    /*public void heroChooseAttack(Character hero, Character enemy) {
        HeroCharacter h = (HeroCharacter) hero;
        ArrayList<String> skillList = h.getSkillList();
        System.out.println("拥有技能:" + "1." + skillList.get(0) +
                "2." + skillList.get(1) + "3." + skillList.get(2));
        System.out.println("请选择攻击方式：");
        System.out.println("1." + skillList.get(0) + "2." +
                skillList.get(1) + "3." + skillList.get(2));
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        while (true) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    hero.Attack(enemy, hero.getAttack() * 0);
                    break;
                case 2:
                    hero.setHP(hero.getHP() - 10);
                    hero.Attack(enemy, (int) ((double) hero.getAttack() * 1.8));
                    break;
                case 3:
                    hero.setHP(hero.getHP() - 10);
                    hero.setHP(hero.getHP() + r.nextInt(21));
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
            }
        }
    }*/
    @Override
    public void show() {
        System.out.print("[" + "HP：" + getHP() + "/" + getMaxHP() + " ");
        System.out.print("ATK：" + getAttack() + " ");
        System.out.println("MP :" + this.MP + "/" + this.maxMP + " ");
        System.out.print("DEF：" + getDefense() + "]");
        System.out.println();
    }
}
