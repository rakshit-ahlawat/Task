package com.example.task.utility;

import java.util.Random;

public class PasswordGeneratorUtility {

    public static String generatePassword(){
        Random random=new Random();
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<9;i++){
            int digit= random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
