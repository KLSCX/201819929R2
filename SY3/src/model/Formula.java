package model;

import java.util.Random;

//抽象父类算式类
//定义了两个抽象方法checkCalculation与calculate
public abstract class Formula {
    //定义变量，左操作数，右操作数，运算符
    static final int UPPER = 100;
    static final int LOWER = 0;
    private int leftOperand = 0;
    private int rightOperand = 0;
    private char operate = '+';
    private int value = 0;

    public void generateFormula(char anOperator) {
        int left, right, result = 0;
        Random random = new Random();
        left = random.nextInt(UPPER + 1);
        do {
            right = random.nextInt(UPPER + 1);
            result = calculate(left, right);
        } while (!(checkCalculation(result)));
        leftOperand = left;
        rightOperand = right;
        operate = anOperator;
        value = result;
    }

    abstract boolean checkCalculation(int anInteger);

    abstract int calculate(int left, int right);

    public boolean equals(Formula anFormula) {
        return leftOperand == anFormula.getLeftOperand() &
                rightOperand == anFormula.getRightOperand() &
                operate == anFormula.getOperate();
    }

    //getter方法
    public int getLeftOperand() {
        return leftOperand;
    }


    public int getRightOperand() {
        return rightOperand;
    }


    public char getOperate() {
        return operate;
    }

    //返回结果值
    public int getValue() {
        return value;
    }

    //算式输出格式
    @Override
    public String toString() {
        return String.format("%3d", this.getLeftOperand()) + "  " +
                this.getOperate() + "  " +
                String.format("%3d", this.getRightOperand()) + " = ";
    }

    public String asString() {

        return String.format("%3d", this.getLeftOperand()) + "  " +
                this.getOperate() + "  " +
                String.format("%3d", this.getRightOperand());


    }

    public String fullString() {
        return String.format("%3d", this.getLeftOperand()) + "  " +
                this.getOperate() + "  " +
                String.format("%3d", this.getRightOperand()) + " = " +
                String.format("%3d", this.getValue());
    }
}

//加法类继承抽象父类算式类，重写父类方法
class PlusFormula extends Formula {
    PlusFormula(){
        generateFormula('+');
    }

    @Override
    public boolean checkCalculation(int anInteger) {
        return anInteger <= UPPER;
    }

    @Override
    int calculate(int left, int right) {
        return left + right;
    }

}

////减法类继承抽象父类算式类，重写父类方法
class MinusFormula extends Formula {
    MinusFormula() {
        generateFormula('-');
    }

    @Override
    public boolean checkCalculation(int anInteger) {
        return anInteger >= LOWER;
    }

    @Override
    int calculate(int left, int right) {
        return left - right;
    }

}