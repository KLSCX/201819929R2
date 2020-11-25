package bean;

import java.util.Arrays;

public class Formula {
    private int[] formula = new int[3];
    public Formula(int left,int right,int operator){
        this.formula[0] = left;
        this.formula[1] = right;
        this.formula[2] = operator;
    }

    public int[] getFormula() {
        return formula;
    }
    public int getResult(){
        if (formula[2]==1){
            return formula[0] + formula[1];

        }else {
            return formula[0] - formula[1];
        }
    }
    @Override
    public String toString() {
        if (formula[2]==1){
            return String.format("%3d + %3d =", formula[0], formula[1]);

        }else {
            return String.format("%3d - %3d =", formula[0], formula[1]);
        }
    }
}
