package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormulaTest {


    @Test
    void testEquals() {
        MinusFormula formula = new MinusFormula();
        if(formula.equals(formula)){
            System.out.println("equal函数测试成功");
        }
    }

    @Test
    void testToString() {
        System.out.println("算式输出格式一");
        MinusFormula formula = new MinusFormula();
        System.out.println(formula.toString());
    }

    @Test
    void asString() {
        System.out.println("算式输出格式二");
        MinusFormula formula = new MinusFormula();
        System.out.println(formula.asString());
    }

    @Test
    void fullString() {
        System.out.println("算式输出格式三");
        MinusFormula formula = new MinusFormula();
        System.out.println(formula.fullString());
    }
}