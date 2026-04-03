package itwu.Ui;

import itwu.domain.*;
import itwu.domain.Character;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FightingGame {
    public void start(User u) {
       /* System.out.println("╔════════════════════════════════════════╗");
        System.out.println("  🎮    " + u.getUsername() + "欢迎来到文字格斗游戏    🎮");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("创建你的角色：");
        System.out.println("你的角色名为:" + u.getUsername());
        HeroCharacter hero = new HeroCharacter(u.getUsername(), 100, 10, 0);*/
        HeroCharacter hero = allocatePoints(u);
        ArrayList<String> skillList = hero.getSkillList();  // 直接从这里拿
        //定义一个集合保存敌人
        /*ArrayList<Enemy> enemies = createEnemy(new ArrayList<>());*/
        //将Character对象转为HEROCharacter对象
        //创建敌人
        /*Enemy enemy = randomEnemy(enemies);*/
        fightStart(hero, skillList);
    }

    //创建敌人集合
    public ArrayList<EnemyCharater> createEnemy(ArrayList<EnemyCharater> EList) {

        ArrayList<EnemyCharater> enemies = new ArrayList<>();
        enemies.add(new EnemyCharater("牧羊人", 100, 30, 50, "放牧", 1.7));
        enemies.add(new EnemyCharater("敏捷刺客", 60, 20, 5, "背刺", 2));
        enemies.add(new EnemyCharater("重装坦克", 120, 10, 20, "防御架势", 0));
        enemies.add(new EnemyCharater("秘法师", 50, 40, 3, "秘法", 1.9));
        enemies.add(new EnemyCharater("初级战士", 80, 15, 10, "猛击", 1.5));
        return enemies;
    }

    public int enemyCalculateDamage(HeroCharacter hero, EnemyCharater enemy, double skillDamage) {
        int actualDamage = 0;
        actualDamage = hero.TakeDamage((int) (enemy.getAttack() * skillDamage), enemy);
        System.out.println("⚔\uFE0F " + enemy.getName() + "对" + hero.getName() + "使用了" + enemy.getSkill() + "造成了" + actualDamage + "点伤害");
        return actualDamage;
    }

    public int heroCalculateDamage(HeroCharacter hero, EnemyCharater enemy, double skillDamage) {
        int actualDamage = 0;
        actualDamage = enemy.TakeDamage((int) (hero.getAttack() * skillDamage), hero);
        System.out.println("⚔\uFE0F " + hero.getName() + "对" + enemy.getName() + "使用了普通攻击" + "造成了" + actualDamage + "点伤害");
        return actualDamage;
    }

    //敌人随机使用普通攻击或者技能
    public void enemyTurn(EnemyCharater enemy, HeroCharacter hero) {
        /*Random r = new Random();
        int choice = r.nextInt(2);
        int actualDamage = 0;
        switch (choice) {
            case 0:
                System.out.println(enemy.getName() + " 对 " + hero.getName() + " 使用了普通攻击");
                actualDamage = hero.TakeDamage(enemy.getAttack(), hero);
                System.out.println("造成了" + actualDamage + "点伤害");
                return;
            case 1:
                if (enemy.getSkill().equals("防御架势")) {
                    enemy.setDefending(true);
                    System.out.println(enemy.getName() + " 使用了技能 " + enemy.getSkill() + " 开始防御");
                } else {
                    System.out.println(enemy.getName() + " 使用技能 " + enemy.getSkill() +
                            " 攻击了 " + hero.getName());
                    actualDamage = hero.TakeDamage((int) ((double) enemy.getAttack() * enemy.getSkillDamage()), hero);
                    System.out.println("造成了" + actualDamage + "点伤害");
                }
                return;
            default:
                System.out.println(enemy.getName() + " 对 " + hero.getName() + " 使用了普通攻击");
                actualDamage = hero.TakeDamage(enemy.getAttack(), hero);
                System.out.println("造成了" + actualDamage + "点伤害");
                return;
        }*/
        String action = "普通攻击";
        int actualDamage = 0;
        Random r = new Random();
        int random = r.nextInt(2);
        if (random == 0) {
            action = "普通攻击";
        } else {
            action = enemy.getSkill();
        }
        switch (action) {
            default:
                System.out.println("没有这个操作,默认使用普通攻击");
            case "普通攻击":
                hero.TakeDamage(enemy.getAttack(), hero);
                System.out.println("⚔\uFE0F " + enemy.getName() +
                        " 对 " + hero.getName() + " 使用了普通攻击,造成了" + (enemy.getAttack() - hero.getDefense()) + "点伤害");
                return;
            case "放牧":
                enemyCalculateDamage(hero, enemy, 1.7);
                return;
            case "背刺":
                enemyCalculateDamage(hero, enemy, 2);
                return;
            case "防御架势":
                enemy.setDefending(true);
                System.out.println(enemy.getName() + "使用了技能 " + enemy.getSkill() + " 开始防御 \uD83D\uDEE1\uFE0F");
                return;
            case "秘法":
                enemyCalculateDamage(hero, enemy, 1.9);
                return;
            case "猛击":
                enemyCalculateDamage(hero, enemy, 1.5);
                return;
        }
    }

    //随机选择敌人`
    public EnemyCharater randomEnemy(ArrayList<EnemyCharater> enemies) {
        Random r = new Random();
        return enemies.get(r.nextInt(enemies.size()));
    }

    //分配点数
    public HeroCharacter allocatePoints(User u) {
        HeroCharacter c = new HeroCharacter(u.getUsername(), 100, 10, 0, 30);
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("    🎮 " + u.getUsername() + "欢迎来到文字格斗游戏 🎮");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("创建你的角色：");
        System.out.println("你的角色名为:" + u.getUsername());
        System.out.println("请分配你的技能点数：");
        System.out.print("1.生命力(每点+10 HP)  ");
        System.out.print("2.攻击力(每点+2 ATK)  ");
        System.out.print("3.防御力(每点+1 DEF)  ");
        String[] arr = {"生命力", "攻击力", "防御力"};
        Scanner sc = new Scanner(System.in);
        int[] points = new int[3];
        int point = 20;
        while (true) {
            int count = 0;
            for (int i = 0; i < points.length; ) {
                count++;
                System.out.println();
                System.out.println("请进行第" + count + "次分配,此次分配的是" + arr[i] + "点数");
                while (true) {
                    int userInput = sc.nextInt();
                    if (userInput < 0 || userInput > 20 || userInput > point) {
                        System.out.println("技能点数分配错误，请重新进行第" + count + "次分配,此次分配的是" + arr[i] + "点数");
                        continue;
                    }
                    points[i] = userInput;
                    point -= points[i];
                    break;
                }
                System.out.println("剩余" + point + "点");
                i++;
            }
            int sum = 0;
            for (int i = 0; i < points.length; i++) {
                sum += points[i];
            }
            if (!(sum == 20)) {
                System.out.println("技能点数分配错误，请重新分配");
                continue;
            }
            break;
        }
        c.setHP(c.getHP() + (points[0] * 10));
        c.setAttack(c.getAttack() + (points[1] * 2));
        c.setDefense(c.getDefense() + (points[2] * 1));
        System.out.println("角色已创建成功！");
        c.show();
        return c;
    }

    //开始战斗的方法
    public void fightStart(HeroCharacter hero, ArrayList<String> skillList) {
        ArrayList<EnemyCharater> enemies = createEnemy(new ArrayList<>());
        int count = 0;
        int wins = 0;
        int round = 0;
        EnemyCharater enemy = randomEnemy(enemies);
        System.out.println("════════════════════════════════════════");
        System.out.println("遭遇" + enemy.getName() + "袭击");
        enemy.show();
        while (true) {
            System.out.println("⚔\uFE0F 正在进行第" + ++count + "场战斗");
            System.out.println("⚔\uFE0F 第" + ++round + "回合开始!");
            if (hero.isAlive()) {
                System.out.println(getHealthBar(hero));
                System.out.println("------------------------------------");
                System.out.println(getHealthBar(enemy));
               /* System.out.println("====" + hero.getName() + "的回合====");
                heroChooseAttack(hero, enemy, skillList);
                if (enemy.isDefending()) {
                    enemy.setDefending(false);
                }*/
                playerTurn(hero, enemy, skillList);
                if (enemy.isAlive()) {
                    System.out.println(getHealthBar(enemy));
                } else {
                    System.out.println("🎉 你击败了" + enemy.getName() + "！");
                    round = 0;
                    //恢复20点生命值
                    hero.Heal(hero.getMaxHP() / 10);
                    hero.HealMP((hero.getMaxMP() / 10) * 3);
                    System.out.println("\uD83D\uDC9A 战斗结束！你恢复了" + (hero.getMaxHP() / 10) + "点生命值和" + ((hero.getMaxMP() / 10) * 3) + "点MP");
                    getConsumable(hero);
                    if (hero.getHP() > hero.getMaxHP()) {
                        hero.setHP(hero.getMaxHP());
                    }
                    System.out.println("🏆 当前胜场: " + ++wins);
                    System.out.println("════════════════════════════════════════");
                    if (wins % 3 == 0 && wins != 0) {
                        System.out.println("是否继续战斗？(y/n)");
                        Scanner sc = new Scanner(System.in);
                        String choice = sc.next();
                        if (choice.equals("y")) {
                            //给角色加属性 分别是30 5 3
                            System.out.println("等级提升！" + hero.getName() + "变的更强了!");
                            levelUP(hero, 30, 5, 3, 5);
                            enemy = randomEnemy(enemies);
                            levelUP(enemy, 10, 3, 2, 3);
                            System.out.println("遭遇" + enemy.getName() + "袭击");
                            enemy.show();
                            continue;
                        }
                        return;
                    }
                    enemy = randomEnemy(enemies);
                    if (wins != 0) {
                        levelUP(enemy, 10, 3, 2, 3);
                    }
                    System.out.println("----遭遇" + enemy.getName() + "袭击----");
                    enemy.show();
                    continue;
                }
                System.out.println("====" + enemy.getName() + "的回合====");
                enemyTurn(enemy, hero);
                if (!hero.isAlive()) {
                    System.out.println("💀" + hero.getName() + "死亡");
                    System.out.println(enemy.getName() + ":" + enemyTalk());
                    System.out.println("游戏结束！");
                    return;
                }
            }
        }
    }

    public void playerTurn(HeroCharacter hero, EnemyCharater enemy, ArrayList<String> skillList) {
        System.out.println("====" + hero.getName() + "的回合====");
        heroChoose(hero, enemy, skillList);
        if (enemy.isDefending()) {
            enemy.setDefending(false);
        }
    }

    public String getHealthBar(Character c) {
        //计算需要打印的方块
        int maxHealth = 20;
        int health = (int) (c.getHP() * 1.0 / c.getMaxHP() * maxHealth);
        StringBuilder sb = new StringBuilder();
        sb.append(c.getName()).append(":[");
        for (int i = 0; i < maxHealth; i++) {
            if (i < health) {
                sb.append("█");
            } else {
                sb.append(" ");
            }
        }
        sb.append("]" + c.getHP() + "/" + c.getMaxHP() + "HP");


        return sb.toString();
    }

    public Character levelUP(Character c, int HP, int ATK, int DEF, int MP) {
        if (c instanceof HeroCharacter) {
            HeroCharacter hero = (HeroCharacter) c;
            hero.setMaxHP(hero.getMaxHP() + HP);
            hero.setHP(hero.getMaxHP());
            hero.setAttack(hero.getAttack() + ATK);
            hero.setDefense(hero.getDefense() + DEF);
            hero.setMaxMP(hero.getMaxMP() + MP);
            hero.setMP(hero.getMaxMP());
        } else {
            c.setMaxHP(c.getMaxHP() + HP);
            c.setHP(c.getMaxHP());
            c.setAttack(c.getAttack() + ATK);
            c.setDefense(c.getDefense() + DEF);
        }
        return c;
    }

    //玩家选择攻击方式
    public void heroChoose(HeroCharacter hero, EnemyCharater enemy, ArrayList<String> skillList) {
        printSelect(skillList);
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int actualDamage = 0;
        while (true) {
            int choice = 0;
            while (true) {
                choice = sc.nextInt();
                if (choice == 2 || choice == 3) {
                    if (hero.getMP() <= 10) {
                        System.out.println("MP不足以释放全力一击或生命汲取，请重新选择");
                        continue;
                    }
                }
                break;
            }
            switch (choice) {
                default:
                    System.out.println("没有这个操作,默认使用普通攻击");
                case 1:
                    //普通攻击
                    heroCalculateDamage(hero, enemy, 1);
                    return;
                case 2:
                    //全力一击
                    hero.setMP(hero.getMP() - 10);
                    actualDamage = enemy.TakeDamage((int) ((double) hero.getAttack() * 1.8), enemy);
                    System.out.println("💥 消耗10MP，你对 " + enemy.getName() + " 使用了强力一击，" + actualDamage + " 点伤害！");
                    return;
                case 3:
                    //技能汲取
                    hero.setMP(hero.getMP() - 10);
                    System.out.println(hero.getName() + " 对 " + hero.getName() + "使用了" + skillList.get(2));
                    System.out.println("使用了" + 10 + "点蓝量");
                    int healHP = hero.Heal(r.nextInt(21));
                    System.out.println("\uD83D\uDC9A" + hero.getName() + " 恢复了" + healHP + "点血量");
                    hero.show();
                    return;
                case 4:
                    //使用道具
                    if (hero.getPackageList().size() == 0) {
                        System.out.println("没有道具,请重新选择");
                        printSelect(skillList);
                        continue;
                    }
                    System.out.println("请选择道具：");
                    for (int i = 0; i < hero.getPackageList().size(); i++) {
                        System.out.println((i + 1) + "." + hero.getPackageList().get(i).getName()+"  道具效果:回复"+hero.getPackageList().get(i).getNum()+"点血量");
                    }
                    while (true) {
                        int choiceConsumable = sc.nextInt();
                        if (choiceConsumable == 0) {
                            continue;
                        }
                        if (choiceConsumable > hero.getPackageList().size() || choiceConsumable < 1) {
                            System.out.println("没有这个道具,请重新选择");
                            continue;
                        }
                        Consumable consumable = hero.getPackageList().get(choiceConsumable - 1);
                        useConsumableEffect(hero, consumable);
                        break;
                    }
                    return;
            }
        }
    }

    public void printSelect(ArrayList<String> skillList) {
        System.out.println("拥有技能:" + "1." + skillList.get(0) +
                "2." + skillList.get(1) + "3." + skillList.get(2));
        System.out.println("请选择攻击方式：");
        System.out.println("1." + skillList.get(0) + "2." +
                skillList.get(1) + "3." + skillList.get(2) + "4.使用道具");
    }

    //判断是否有此道具,

    //定义一个使用道具对应的效果
    public void useConsumableEffect(HeroCharacter hero, Consumable consumable) {
        int actualHeal = 0;
        switch (consumable.getName()) {
            case "桃子":
                actualHeal = hero.Heal(consumable.getNum());
                System.out.println("\uD83D\uDC9A " + hero.getName()
                        + "使用了" + consumable + " 恢复了" + actualHeal + "点血量");
                //删除道具
                hero.getPackageList().remove(consumable);
                return;
            case "煎蛋":
                actualHeal = hero.Heal(consumable.getNum());
                System.out.println("\uD83D\uDC9A " + hero.getName() +
                        "使用了 " + consumable.getName() + " 恢复了" + actualHeal + "点血量");
                hero.getPackageList().remove(consumable);
                return;
            case "花酿鸡":
                actualHeal = hero.Heal(consumable.getNum());
                System.out.println("\uD83D\uDC9A " + hero.getName() +
                        "使用了 " + consumable.getName() + " 恢复了" + actualHeal + "点血量");
                hero.getPackageList().remove(consumable);
                return;
            case "黑背鲈鱼":
                actualHeal = hero.Heal(consumable.getNum());
                System.out.println("\uD83D\uDC9A " + hero.getName() +
                        "使用了 " + consumable.getName() + " 恢复了" + actualHeal + "点血量");
                hero.getPackageList().remove(consumable);
                return;
            case "白玉汤":
                actualHeal = hero.Heal(consumable.getNum());
                System.out.println("\uD83D\uDC9A " + hero.getName() +
                        "使用了 " + consumable.getName() + " 恢复了" + actualHeal + "点血量");
                hero.getPackageList().remove(consumable);
                return;
            default:
                System.out.println("没有这个道具");
                return;

        }
    }

    //敌人嘲讽话术集合
    public String enemyTalk() {
        String[] enemyTalk = {
                "就这点实力吗？", "你太弱了！", "回家练练再来吧！", "这就是你的全部力量？", "不堪一击！",
                "你也想挑战我？", "别做梦了！", "你赢不了我的！", "放弃吧！", "太让我失望了！", "还没用力你就倒下了？", "真是浪费时间！", "你这是在开玩笑吗？", "我一只手就能打败你！", "别不自量力了！", "你的攻击给我挠痒都不够！", "今天就是你的败北之日！", "见识到真正的力量了吧！", "你还差得远呢！", "想打败我？下辈子吧！"
        };
        Random random = new Random();
        return enemyTalk[random.nextInt(enemyTalk.length)];
    }

    //战斗获胜随机获取消耗品道具存入背包的方法
    public void getConsumable(HeroCharacter hero) {
        ArrayList<Consumable> consumables = initConsumable();
        Random r = new Random();
        int index = r.nextInt(consumables.size());
        int count = r.nextInt(4) + 1;
        for (int i = 0; i < count; i++) {
            hero.getPackageList().add(consumables.get(index));
        }
        System.out.println("恭喜你获得" + consumables.get(index).getName() + "x" + count);
    }

    //初始化道具
    public ArrayList<Consumable> initConsumable() {
        ArrayList<Consumable> consumablesList = new ArrayList<>();
        consumablesList.add(new Consumable("桃子", 10));
        consumablesList.add(new Consumable("煎蛋", 20));
        consumablesList.add(new Consumable("花酿鸡", 30));
        consumablesList.add(new Consumable("黑背鲈鱼", 40));
        consumablesList.add(new Consumable("白玉汤", 50));
        return consumablesList;
    }

}

