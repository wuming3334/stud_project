import itwu.Ui.FightingGame;
import itwu.Ui.Login;
import itwu.domain.User;

public class App {
    private static Login Login;

    public static void main(String[] args) {
        //调用启动方法
        Login l = new Login();
        l.start();
    }
}
