package itwu.Ui;

import itwu.domain.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Login {
    ArrayList<User> userList = new ArrayList<>();

    public Login() {
        initDefaultUsers();
    }

    // 初始化默认用户数据
    private void initDefaultUsers() {
        userList.add(new User("zhangsan", "123456", "12345678910"));
        userList.add(new User("lisi", "abcdef", "12345678911"));
        userList.add(new User("wangwu", "password", "12345678912"));
    }

    public void start() {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("    🎮 欢迎来到文字格斗游戏 🎮   ");
        System.out.println("╚════════════════════════════════╝");
        System.out.println("请选择操作：1登录 2注册 3忘记密码 4退出");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                forgotPassword();
                break;
            case 4:
                System.out.println("退出登录");
                System.exit(0);
                break;
            default:
                System.out.println("输入错误,请重新输入");
                start();
        }
    }

    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("登录");
        String username = null;
        while (true) {
            System.out.println("请输入用户名：");
            username = sc.next();
            if (!contains(userList, username)) {
                System.out.println("用户不存在,请先注册");
                start();
            }
            break;
        }

        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.get(i);
            if (user.getUsername().equals(username)) {
                if (!user.isState()) {
                    System.out.println("用户" + user.getUsername() + "被锁定" + "请连线客服热线xxx-xxx-xxx");
                    start();
                }
                int count = 0;
                while (true) {
                    if (count == 3) {
                        System.out.println("密码错误次数过多，用户:" + username + " 已被锁定");
                        user.setStatus(false);
                        userList.set(i, user);
                        count = 0;
                        start();
                    }
                    System.out.println("请输入密码：");
                    String password = sc.next();
                    while (true) {
                        String captcha = captcha();
                        System.out.println("验证码：" + captcha);
                        System.out.println("请输入验证码：");
                        String userCaptcha = sc.next();
                        if (userCaptcha.equalsIgnoreCase(captcha)) {
                            System.out.println("验证码正确");
                            break;
                        } else {
                            System.out.println("验证码错误,请重新输入");
                        }
                    }
                    if (user.getPassword().equals(password)) {
                        System.out.println(username + "登录成功,正在跳转...用户ID: " + user.getId());
                        //调用启动游戏的方法~~
                        FightingGame fightingGame = new FightingGame();
                        fightingGame.start(user);
                        return;
                    } else {
                        System.out.println("密码错误");
                        count++;
                        System.out.println("请重新输入密码：");
                    }
                }
            }
        }
        System.out.println("用户不存在");
        start();
    }

    public int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = (User) list.get(i);
            if (user.getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(ArrayList list, String username) {

        for (int i = 0; i < list.size(); i++) {
            User user = (User) list.get(i);
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCaptcha(String captcha, String userCaptcha) {
        return captcha.equals(userCaptcha);
    }

    //验证码
    public String captcha() {
        String[] captchaArr = new String[5];
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int r1 = r.nextInt(2);
            captchaArr[i] = r1 % 2 == 0 ? (char) (r.nextInt(97, 123))
                    + "" : (char) r.nextInt(65, 91) + "";
        }
        captchaArr[4] = r.nextInt(10) + "";
        //让数字随机一个索引 与索引上的数据交换
        int index = r.nextInt(4);
        String temp = captchaArr[index];
        captchaArr[index] = captchaArr[4];
        captchaArr[4] = temp;
        //使用api将数组转为字符串并返回
        String captcha = String.join("", captchaArr);
        return captcha;
    }
    //数组打乱

    public void register() {
        Scanner sc = new Scanner(System.in);
        String phone = "";
        while (true) {
            System.out.println("请输入手机号：");
            phone = sc.next();
            if (!validatePhone(phone)) {
                System.out.println("手机号格式错误,请重新输入");
                continue;
            }
            if (phoneContains(userList, phone)) {
                System.out.println("手机号已存在,请重新输入");
                continue;
            }           /* if (phone.charAt(0) != '1' && phone.length() != 11) {
                System.out.println("手机号格式错误,请重新输入");
                continue;
            }*/
            /*for (int i = 0; i < userList.size(); i++) {
                User user = (User) userList.get(i);
                if (user.getPhone().equals(phone)) {
                    System.out.println("手机号已存在,请重新输入");
                    register();
                }
            }*/

            break;
        }
        String username = "";
        while (true) {
            System.out.println("请输入用户名：");
            username = sc.next();

            // 验证用户名格式
            if (!validateUsername(username)) {
                System.out.println("用户名不符合规范 长度必须在 3-16 之间，且不能只由数字组成，也不能含有特殊字符");
                continue;
            }
            break;

        }

        // 检查用户名是否存在
        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.get(i);
            if (user.getUsername().equals(username)) {
                System.out.println("用户名已存在");
                start();
                return;
            }
        }

        while (true) {
            System.out.println("请输入密码：");
            String password = sc.next();

            // 验证密码格式
            if (validatePassword(password)) {
                System.out.println("请输入确认密码：");
                String confirmPassword = sc.next();
                if (!password.equals(confirmPassword)) {
                    System.out.println("密码不一致");
                    continue;
                }
                User user = new User(username, password, phone);
                userList.add(user);
                System.out.println("注册成功！用户 ID: " + user.getId());
                login();
            } else {
                System.out.println("密码不符合规范 长度必须在 3-8 之间，且只由数字与字母组成");
            }
        }
    }

    public void forgotPassword() {
        String phone = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            /*System.out.println("请输入用户名：");
            username = sc.next();
            if (!contains2(userList, username)) {
                System.out.println("用户名不存在");
                continue;
            }*/
            User user = null;
            while (true) {
                System.out.println("请输入手机号：");
                phone = sc.next();
                if (!validatePhone(phone)) {
                    System.out.println("手机号格式错误");
                    continue;
                }
                // 检查手机号是否注册
                if (!phoneContains(userList, phone)) {
                    System.out.println("手机号未注册");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.println("请输入新密码：");
                String newPassword = sc.next();
                if (!validatePassword(newPassword)) {
                    System.out.println("密码不符合规范 长度必须在 3-8 之间，且只由数字与字母组成");
                    continue;
                }
                System.out.println("请输入确认密码：");
                String confirmNewPassword = sc.next();
                if (!newPassword.equals(confirmNewPassword)) {
                    System.out.println("密码不一致");
                    continue;
                }
                user = phoneFindUser(userList, phone);
                user.setPassword(newPassword);
                System.out.println("修改成功,返回登录！");
                break;
            }
            break;
        }
        login();
    }

    //用于验证手机号格式是否正确
    public boolean validatePhone(String phone) {
        return phone.charAt(0) == '1' && phone.length() == 11;
    }

    //用于判断是否有重复
    public boolean phoneContains(ArrayList<User> list, String phone) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    // 统计字符串中字母、数字、其他字符的数量
    private int[] countCharTypes(String str) {
        int charCount = 0;
        int numCount = 0;
        int otherCount = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z' ||
                    str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                charCount++;
            } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                numCount++;
            } else {
                otherCount++;
            }
        }

        return new int[]{charCount, numCount, otherCount};
    }

    // 验证用户名格式
    private boolean validateUsername(String username) {
        int[] counts = countCharTypes(username);
        int charCount = counts[0];
        int numCount = counts[1];
        int otherCount = counts[2];

        return username.length() >= 3 && username.length() <= 16 &&
                charCount > 0 && numCount <= 0 && otherCount == 0;
    }

    // 验证密码格式
    private boolean validatePassword(String password) {
        int[] counts = countCharTypes(password);
        int passwordChar = counts[0];
        int passwordNum = counts[1];
        int passwordOther = counts[2];

        return password.length() >= 3 && password.length() <= 8 &&
                passwordChar > 0 && passwordNum > 0 && passwordOther == 0;
    }

    //写一个根据手机号查找该用户的方法
    public User phoneFindUser(ArrayList<User> list, String phone) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getPhone().equals(phone)) {
                return user;
            }
        }
        return null;
    }


}



