package com.example.task.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.util.Base64;                                       instead of this use own logic
public class PasswordGeneratorUtility {
    private static Logger logger = LoggerFactory.getLogger(PasswordGeneratorUtility.class);

    public static String generatePassword(String username) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <username.length() && sb.length()<9; i++) {
            int digit = (username.charAt(i) % 9);
            sb.append(digit);
        }
        while(sb.length()<9){
            sb.append(0);
        }
        logger.trace("class:PasswordGenratorUtility , method:generatepassword ");
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

