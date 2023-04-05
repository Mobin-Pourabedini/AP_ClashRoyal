package controller;

public class MyUtilities {
    public static boolean isPasswordValid(String password) {
        if (password.length() < 8 || password.length() > 20) {
            return false;
        }
        if (password.contains(" ")) return false;
        if (!password.matches(".*[a-z].*") ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[0-9].*") ||
                !password.matches(".*[!@#$%^&*].*")) {
            return false;
        }
        if (password.matches("[0-9].*")) {
            return false;
        }
        return true;
    }

    public static boolean isUsernameValid(String username) {
        return username.matches("[a-zA-Z]+");
    }
}
