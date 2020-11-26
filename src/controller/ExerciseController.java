package controller;

import bean.Exercise;
import bean.Formula;

public class ExerciseController {
    static Formula[] exercise = new Exercise().getExercise();
    public static void exercisePrint(){
        for (int i = 0; i < exercise.length; i++) {
            System.out.print(exercise[i].toString() + "        ");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }
    public static void exerciseAnswerPrint(){

        for (int i = 0; i < exercise.length; i++) {
            System.out.print(exercise[i].toString() + " "
                    + String.format("%3d",exercise[i].getResult()) + "      ");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }

    }



    public static void clear() {
        exercise = new Exercise().getExercise();
    }
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