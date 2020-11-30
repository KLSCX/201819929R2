package controller;

import bean.Formula;

public class FormulaController {


    //生成随机算式
    public static Formula getRandomFormula(){

        Formula formula;
        do{
            formula = new Formula(RandomGenerate.operand(),RandomGenerate.operand(),RandomGenerate.operator());
        }while (!check(formula));

        return formula;
    }

    //生成随机加法算式或减法，平衡加减法数量时需要用到
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

    //检查算式结果不能大于100，不能小于0
    public static boolean check(Formula formula) {
        if (formula.getResult() < 0 || formula.getResult() > 100) {
            return false;
        }

        return true;
    }

}
