package main.tetris.control;

import main.tetris.entity.user.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String USER_DB_PATH = "users.db";

    private static final DatabaseManager instance;

    private DatabaseManager() {}

    static {
        try {
            instance = new DatabaseManager();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance.");
        }
    }

    public static DatabaseManager getInstance() {
        return instance;
    }


    public ArrayList<User> getUsers() {
        try {
            final FileInputStream   fis   = new FileInputStream(USER_DB_PATH);
            final ObjectInputStream ois   = new ObjectInputStream(fis);
            final ArrayList<User>   users = (ArrayList<User>)ois.readObject();
            ois.close();
            System.out.println("getUser: " + users);
            return users;
        } catch (Exception e) {
//            System.out.println(e);
            return new ArrayList<User>();
        }
    }

    public User getUserByName(final String name) {
        final ArrayList<User> users = getUsers();
        for (User u : users) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    private boolean setUsers(final ArrayList<User> users) {
        try {
            final FileOutputStream   fos = new FileOutputStream(USER_DB_PATH);
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            System.out.println("setUser: " + users);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean updateUser(final User user) {
        final ArrayList<User> users = getUsers();
        users.removeIf(u -> u.getName().equals(user.getName()));
        users.add(user);
        return setUsers(users);
    }
}
