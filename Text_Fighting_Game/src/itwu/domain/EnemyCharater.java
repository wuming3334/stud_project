package itwu.domain;

public class EnemyCharater extends Character {
    private String skill;
    private double skillDamage;
    private boolean defending;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public double getSkillDamage() {
        return skillDamage;
    }

    public void setSkillDamage(double skillDamage) {
        this.skillDamage = skillDamage;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public EnemyCharater() {
        super();
        this.defending = false;
    }

    public EnemyCharater(String name, int HP, int attack, int defense) {
        super(name, HP, attack, defense);
        this.defending = false;
    }

    public EnemyCharater(String skill, double skillDamage) {
        this.skill = skill;
        this.skillDamage = skillDamage;
        this.defending = false;
    }

    public EnemyCharater(String name, int HP, int attack, int defense, String skill, double skillDamageg) {
        super(name, HP, attack, defense);
        this.skill = skill;
        this.skillDamage = skillDamage;
        this.defending = false;
    }

}
