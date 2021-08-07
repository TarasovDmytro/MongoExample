package service;

import dao.AccountDao;
import dao.UserDao;
import model.BankAccount;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserDao userDao;
    private final AccountDao accountDao;

    public UserService(UserDao userDao, AccountDao accountDao) {

        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    public void run() {

//        createDataBase();
//        findByName("UserFN1", "UserLN1").forEach(System.out::println);
//        findByCity("City2").forEach(System.out::println);
//        findByAge(32).forEach(System.out::println);
        findByAccountsCount(2).forEach(System.out::println);
//        updateUserById();
    }

    public void createDataBase() {

        List<User> users = new ArrayList<>();
        List<BankAccount> allAccounts = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            User user = new User();
            user.setFirstName("UserFN" + (i + 1));
            user.setLastName("UserLN" + (i + 1));
            user.setCompany("Company" + (i + 1));
            user.setAge(20 + i * 5);
            user.setCity("City" + (i + 1));
            List<BankAccount> accounts = new ArrayList<>();
            BankAccount account = new BankAccount();
            account.setNumber("Account Number #" + (i + 1));
            account.setNameOfBank("Bank #" + (i + 1));
            account.setBalance(i * 1000);
            account.setUserId(user.get_id());
            accounts.add(account);
            allAccounts.add(account);
            user.setBankAccount(accounts);
            users.add(user);
        }

        for (int i = 0; i < 5; i = i + 2) {

            BankAccount account = new BankAccount();
            account.setNumber("Account Number #1" + i);
            account.setNameOfBank("Bank #1" + (i + 1));
            account.setBalance(i * 1000L);
            account.setUserId(users.get(i).get_id());
            users.get(i).setCity("City" + (i + 2));
            users.get(i).getBankAccount().add(account);
        }

        BankAccount account = new BankAccount();
        account.setNumber("Account Number #001");
        account.setNameOfBank("Bank #001");
        account.setBalance(100000);
        account.setUserId(users.get(4).get_id());
        users.get(4).getBankAccount().add(account);

        userDao.createData(users);
        accountDao.createData(allAccounts);
    }

    public ArrayList<User> findByName(String firstName, String lastName) {

        return userDao.findByName(firstName, lastName);
    }

    public ArrayList<User> findByCity(String city) {

        return userDao.findByCity(city);
    }

    public ArrayList<User> findByAge(int age) {

        return userDao.findByAge(age);
    }

    public List<User> findByAccountsCount(int countOfAccount) {

        return userDao.findByAccountsCount(countOfAccount);
    }

    public void updateUser() {

        User currentUser = userDao.findByName("UserFN2", "UserLN2").get(0);
        String userId = currentUser.get_id();
        currentUser.setCity("Kharkiv");
        userDao.updateUserById(currentUser, userId);
    }
}
