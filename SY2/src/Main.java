import model.Exercise;
import tools.ExerciseSheet;

public class Main {
    public static void main(String[] args) {
        ExerciseSheet sheet = new ExerciseSheet();

        Exercise exercise1 = new Exercise();
        exercise1.generatePlusExercise(50);
        System.out.println("----生成和显示加法习题 ----");
        sheet.formattedDisplay(exercise1, 5);

        Exercise exercise2= new Exercise();
        exercise2.generateMinusExercise(50);
        System.out.println("----生成和显示减法习题 ----");
        sheet.formattedDisplay(exercise2, 5);

        Exercise exercise = new Exercise();
        exercise.generateExercise(50);
        System.out.println("----生成和显示加减法混合习题 ----");
        sheet.formattedDisplay(exercise, 5);
    }
}
