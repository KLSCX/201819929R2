package tools;

import model.Exercise;

public class ExerciseSheet {
    private static final short COLUMN = 5;
    //每行打印输出columns个算式ex.next()
    public void formattedDisplay(Exercise exercise, int columns) {

            int count = 0;
            while(exercise.hasNext()) {
                System.out.print(exercise.next()+"    ");
                count++;
                if (count % columns == 0) {
                    System.out.println();
                }
            }
    }

    public void formattedDisplay(Exercise exercise) {
        formattedDisplay(exercise, COLUMN);
    }

    private static void print(String str) {
        System.out.println(str);
    }
}
