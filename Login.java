package com.mycompany.mavenproject2;

public class Login {

    private String username;
    private String password;
    private String phoneNumber;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;
        int capitalCount = 0, numberCount = 0, specialCount = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c))           capitalCount++;
            else if (Character.isDigit(c))          numberCount++;
            else if (!Character.isLetterOrDigit(c)) specialCount++;
        }
        return capitalCount >= 1 && numberCount >= 1 && specialCount >= 1;
    }

    public boolean checkCellPhoneNumber(String phone) {
        return phone.matches("\\+27\\d{9}");
    }

    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted. "
                 + "Please ensure it contains an underscore "
                 + "and is no more than 10 characters long.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. "
                 + "Please ensure it contains at least "
                 + "8 characters, one capital letter, "
                 + "one number, and one special character.";
        }
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted "
                 + "or does not contain the international code.";
        }
        this.username    = username;
        this.password    = password;
        this.phoneNumber = phoneNumber;
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        return this.username != null &&
               this.username.equals(username) &&
               this.password.equals(password);
    }

    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + this.username + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}