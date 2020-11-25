package controller;

import java.util.Random;

public class RandomGenerate {
    //生成100以内的整数
    public static int operand(){
        Random random = new Random();
        int operand = random.nextInt(101);
        return operand;
    }
    public static int operator(){
        Random random = new Random();
        int operand = random.nextInt(2);
        return operand;
    }
}
