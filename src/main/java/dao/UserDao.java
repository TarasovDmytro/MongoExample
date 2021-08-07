package dao;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {

    private final static String USER_ID = "_id";
    private final static String COLLECTION_NAME = "users";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String AGE = "age";
    private final static String CITY = "city";
    private final static String ACCOUNTS = "accounts";


    public UserDao(MongoDatabase database) {

        super(database, COLLECTION_NAME);
    }

    public ArrayList<User> findByName(String firstName, String lastName) {

        return super.readByFieldsData(
                Filters.and(Filters.eq(FIRST_NAME, firstName), Filters.eq(LAST_NAME, lastName)), User.class);
    }

    public ArrayList<User> findByCity(String city) {

        return super.readByFieldsData(Filters.eq(CITY, city), User.class);
    }

    public ArrayList<User> findByAge(int age) {

        return super.readByFieldsData(Filters.lte(AGE, age), User.class);
    }

    public List<User> findByAccountsCount(int countOfAccounts) {

        String query = "this.bankAccount.length > " + countOfAccounts;
        return super.readByFieldsData((Filters.where(query)), User.class);
    }

    public void updateUserById(User updateUser, String userId) {

        super.updateData(updateUser, (Filters.eq(USER_ID, userId)));
    }
}
