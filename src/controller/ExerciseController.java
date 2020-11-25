package controller;

import bean.Exercise;
import bean.Formula;

public class ExerciseController {
    static Formula[] exercise = new Exercise().getExercise();
    public static void exercisePrint(){

        for (int i = 0; i < exercise.length; i++) {
            System.out.print(exercise[i] + "   ");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }
    public static void exerciseAnswerPrint(){

        for (int i = 0; i < exercise.length; i++) {
            System.out.print(exercise[i] +" "+ exercise[i].getResult()+"    ");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }

    public static void clear() {
        exercise = new Exercise().getExercise();
    }
}
