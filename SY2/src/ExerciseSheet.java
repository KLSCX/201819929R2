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
