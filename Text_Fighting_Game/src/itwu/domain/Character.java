package itwu.domain;

public class Character {

    private String name;
    private int HP;
    private int maxHP;
    private int attack;
    private int defense;

    public Character() {
    }

    public Character(String name, int HP, int attack, int defense) {
        this.name = name;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }


    public boolean isAlive() {
        if (this.HP <= 0) {
            return false;
        }
        return true;
    }

    public int Heal(int amount) {
        int actualHeal = 0;
        this.HP += amount;
        if (this.HP > this.maxHP) {
            actualHeal = this.HP - this.maxHP;
            this.HP = this.maxHP;
        }else{
            actualHeal = amount;
        }
        return actualHeal;
    }

    public int TakeDamage(int damage, Character C) {
        int actualDamage = damage - this.defense;
        if (actualDamage <= 0) {
            actualDamage = 1;
        }
        if (C instanceof EnemyCharater) {
            EnemyCharater enemy = (EnemyCharater) C;
            if (enemy.isDefending()) {
                actualDamage = actualDamage / 2;
                System.out.println(enemy.getName() + "\uD83D\uDEE1\uFE0F 正在防御，伤害减半");
                enemy.setDefending(false);
            }
        }
        this.HP = this.HP - actualDamage;
        if (this.HP < 0) {
            this.HP = 0;
        }
        /*  System.out.println(this.name + " 受到了 " + actualDamage + "点伤害，还剩" + this.HP + "点血量");*/
        return actualDamage;
    }

   /* public void Attack(Character target, int attack) {
        int actualDamage = target.TakeDamage(attack,this);
        System.out.println(this.name + " 对 " + target.getName() + " 造成了 " + actualDamage + " 点伤害");
        if (!isAlive()) {
            System.out.println(target.getName() + " 死亡");
        }else{
            System.out.println(target.getName() + " 剩余 " + target.getHP() + " 点血量");
        }
    }*/

    public void show() {
        System.out.print("[" + "HP：" + this.HP + "/" + this.maxHP + " ");
        System.out.print("ATK：" + this.attack + " ");
        System.out.print("DEF：" + this.defense + "]");
        System.out.println();
    }
}
