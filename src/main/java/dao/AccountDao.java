package dao;

import com.mongodb.client.MongoDatabase;

public class AccountDao extends AbstractDao {

    private final static String COLLECTION_NAME = "bankAccounts";
    private final static String NAME_OF_BANK = "nameOfBank";
    private final static String NUMBER = "number";
    private final static String BALANCE = "balance";
    private final static String USER_ID = "userId";


    public AccountDao(MongoDatabase database) {

        super(database, COLLECTION_NAME);
    }
}
