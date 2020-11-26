package controller;

import bean.Formula;

public class FormulaController {



    public static Formula getRandomFormula(){

        Formula formula;
        do{
            formula = new Formula(RandomGenerate.operand(),RandomGenerate.operand(),RandomGenerate.operator());
        }while (!check(formula));

        return formula;
    }
    public static Formula getRandomFormula(char op){

        Formula formula;
        if(op=='+') {
            do {
                formula = new Formula(RandomGenerate.operand(), RandomGenerate.operand(), '+');
            } while (!check(formula));

            return formula;
        }else {
            do {
                formula = new Formula(RandomGenerate.operand(), RandomGenerate.operand(), '-');
            } while (!check(formula));

            return formula;
        }
    }

    public static boolean check(Formula formula) {
        if (formula.getResult() < 0 || formula.getResult() > 100) {
            return false;
        }

        return true;
    }

}
