package com.example.task.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.util.Base64;                                       instead of this use own logic
public class PasswordGeneratorUtility {

    private static Logger logger = LoggerFactory.getLogger(PasswordGeneratorUtility.class);


    public static String generatePassword(String username) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <username.length() && sb.length()<9; i++) {
            char x = (char) (username.charAt(i) + 5);
            sb.append(x);
        }
        while(sb.length()<9){
            sb.append('0');
        }

        logger.trace("class:PasswordGenratorUtility , method:generatepassword , message= generating a password with user name [ASCII value % 10]");
        return sb.toString();
    }

    public static String decode(String password){

        StringBuilder sb = new StringBuilder();

        while (!sb.isEmpty() && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }
        for(int i = 0; i < password.length(); i++){
            char y = (char) (password.charAt(i) - 5);
            sb.append(y);
        }

        logger.trace("class:PasswordGeneratorUtility, method:decodePassword, message=Decoding password ");
        return sb.toString();

    }
}


//    public static String encoded() {
//        return Base64.getEncoder().encodeToString(generatePassword().getBytes());
//    }
//    public static String decoded(String encoded) {
//        byte[] decode = Base64.getDecoder().decode(encoded());
//        return new String(decode);
//    }

