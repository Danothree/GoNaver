package com.dano.kjm.global.util;

import java.util.Random;

/**
 * TempKey.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.11
 */
public class TempCode {
    private TempCode(){}

    public static String createCode(int size){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        int num = 0;

        while(num < size){
            sb.append((char) r.nextInt(9) + 1);
            num++;
        }
        return sb.toString();
    }
}
