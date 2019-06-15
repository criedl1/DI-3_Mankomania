package com.example.mankomania.dice;

import java.util.Random;

public class ResultSplitter {
    private static Random random = new Random();

    private ResultSplitter(){
        // is not needed, just for sonarcloud
    }

    public static int [] splitResult(int result){
        int[] ddiceResult = new int[2];
        if (result >= 7) {
            ddiceResult[0] = (result - 6 + random.nextInt(Math.abs(result - 12) + 1));
            ddiceResult[1] = result - ddiceResult[0];
        } else {
            ddiceResult[0] = random.nextInt(result - 1) + 1;
            ddiceResult[1] = result - ddiceResult[0];
        }
        return ddiceResult;
    }
}
