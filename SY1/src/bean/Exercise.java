package bean;

import controller.FormulaController;
import controller.ExerciseController;
public class Exercise {
    private  Formula[] exercise = new Formula[50];

    //生成习题
    public Formula[] getExercise() {
        int count = 0;
        float rate = 0;
        Formula formula;
        //加减法随机生成，如果某种算式数量大于一半，进行平衡
        //算式重复性判断，如果算式重复，那么再重新生成算式
        for (int i = 0; i < exercise.length; i++) {
            exercise[i] = FormulaController.getRandomFormula();
            if (i <= 24 && exercise[i].getOperate() == '+') {
                if (i > 0) {
                    formula = FormulaController.getRandomFormula();
                    for (int j = 0; j < i; j++) {
                        while (ExerciseController.isEqual(exercise[j], formula)) {
                            formula = FormulaController.getRandomFormula();
                        }
                    }
                        exercise[i] = formula;
                }
                count++;
            }
            if (i > 24) {
                if (rate >= 0.5) {

                    formula = FormulaController.getRandomFormula('+');
                    for (int j = 0; j < i; j++) {
                        if (ExerciseController.isEqual(exercise[j], formula)) {
                            i = i - 1;
                            break;
                        }
                    }
                    exercise[i] = formula;
                    count++;
                }

            } else {
                formula = FormulaController.getRandomFormula('-');
                for (int j = 0; j < i; j++) {
                    if (ExerciseController.isEqual(exercise[j], formula)) {
                        i = i - 1;
                        break;
                    }
                }
                exercise[i] = formula;
            }
            rate = (float) count / i;
        }
        return exercise;
    }
}



