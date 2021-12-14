package main.tetris.control;

import main.tetris.entity.user.User;

public class UserManager {
    private static final DatabaseManager db = DatabaseManager.getInstance();
    private static User currentUser = null;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isUserExists(final String name) {
        return db.getUserByName(name) != null;
    }

    public static boolean register(final String name, final String password) {
        if (isUserExists(name)) {
            return false;
        }
        final User user = new User(name, password, 0);
        db.updateUser(user);
        return true;
    }

    public static boolean login(final String name, final String password) {
        if (!isUserExists(name)) {
            return false;
        }
        final User user = db.getUserByName(name);
        if (!user.checkPassword(password)) {
            return false;
        }
        currentUser = user;
        return true;
    }

    public static void update() {
        if (currentUser != null) {
            db.updateUser(currentUser);
        }
    }

    public static void logout() {
        currentUser = null;
    }
}
