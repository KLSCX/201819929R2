package controller;

import bean.Formula;

public class FormulaController {

    /**
     *
     * @return
     */
    public static Formula getRandomFormula(){
        Formula formula;
        do{
            formula = new Formula(RandomGenerate.operand(),RandomGenerate.operand(),RandomGenerate.operator());
        }while (!check(formula));
        return formula;
    }

    /**
     *
     * @param formula
     * @return
     */
    public static boolean check(Formula formula){
        int[] form = formula.getFormula();
        if(form[2] == 1){
            if(form[0] + form[1] < 100 && form[0] + form[1] >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if(form[0] - form[1] < 100 && form[0] - form[1] >= 0) {
                return true;
            } else {
                return false;
            }
        }

    }
}
