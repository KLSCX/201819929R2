package controller;

import bean.Exercise;
import bean.Formula;

public class ExerciseController {
    static Formula[] exercise = new Exercise().getExercise();
    //习题打印函数
    public static void exercisePrint(){
        for (int i = 0; i < exercise.length; i++) {
            System.out.print(exercise[i].toString() + "        ");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }
    //习题答案打印函数
    public static void exerciseAnswerPrint(){
        for (int i = 0; i < exercise.length; i++) {
            System.out.print(exercise[i].toString() + " "
                    + String.format("%3d",exercise[i].getResult()) + "      ");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }

    }

    //清楚函数，结束上次习题
    public static void clear() {
        exercise = new Exercise().getExercise();
    }

    //判断两个算式是否相等
    //1+2和2+1同样判断为相等
    public  static boolean isEqual(Formula a,Formula b){
        if(a.getLeftOperand()==b.getLeftOperand()
                && a.getRightOperand()==b.getRightOperand()
                && a.getOperate()==b.getOperate()){
            return true;
        }else if(a.getLeftOperand()==b.getRightOperand()
                && a.getRightOperand()==b.getLeftOperand()
                && a.getOperate()=='+' && b.getOperate()=='+'){
            return true;
        }else{
            return false;
        }
    }
}