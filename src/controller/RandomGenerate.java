package controller;

import java.util.Random;

public class RandomGenerate {
    //生成100以内的整数
    public static int operand(){
        Random random = new Random();
        int operand = random.nextInt(101);
        return operand;
    }

    //随机生成加减号
    public static char operator(){
        Random random = new Random();
        int operand = random.nextInt(2);
        if(operand==1){
            return '+';
        }else {
            return '-';
        }

    }
}
