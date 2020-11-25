package bean;

import controller.FormulaController;

public class Exercise {
    private Formula[] exercise = new Formula[50];

    public Exercise(){
        for (int i = 0; i < exercise.length; i++) {
            this.exercise[i] = FormulaController.getRandomFormula();
        }
    }

    public Formula[] getExercise() {
        return this.exercise;
    }

}
