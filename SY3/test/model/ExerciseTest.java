package model;

import org.junit.Test;
import tools.ExerciseSheet;
//Exercise测试类
class ExerciseTest {
    ExerciseSheet sheet = new ExerciseSheet();
    @Test
    public void generatePlusExerciseTest() {
        System.out.println("输出50道加法习题");
        Exercise exercise = new Exercise();
        exercise.generatePlusExercise(50);
        sheet.formattedDisplay(exercise);

    }

    @Test
    public void generateMinusExerciseTest() {
        System.out.println("输出50道减法习题");
        Exercise exercise = new Exercise();
        exercise.generateMinusExercise(50);
        sheet.formattedDisplay(exercise);
    }

    @Test
    public void generateExerciseTest() {
        System.out.println("输出50道加减法混合");
        Exercise exercise = new Exercise();
        exercise.generateExercise(50);
        sheet.formattedDisplay(exercise);
    }

    @Test
    public void hasNextTest() {
        Exercise exercise1 = new Exercise();
        exercise1.generateExercise(50);
        int count = 0;
        while(exercise1.hasNext()){
            count++;
            exercise1.next();
        }
        if(count == 50){
            System.out.println("hasNext函数测试成功");
        }
    }

    @Test
    public void nextTest() {
        Exercise exercise2 = new Exercise();
        exercise2.generateExercise(50);
        int count = 0;
        while(exercise2.hasNext()){
            count++;
            exercise2.next();
        }
        if(count == 50){
            System.out.println("next函数测试成功");
        }
    }
}