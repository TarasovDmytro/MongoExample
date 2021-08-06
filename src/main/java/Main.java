import com.mongodb.client.MongoDatabase;
import dao.AccountDao;
import dao.UserDao;
import service.UserService;
import utils.DBConnectionUtil;

public class Main {

    private static final MongoDatabase db = DBConnectionUtil.connect("alevel");

    public static void main(String[] args) {

        UserDao userDao = new UserDao(db);
        AccountDao accountDao = new AccountDao(db);
        UserService userService = new UserService(userDao, accountDao);
        userService.run();
    }
}