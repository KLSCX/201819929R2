package bean;

public class Formula {
    //定义变量，左操作数，右操作数，运算符
    private int leftOperand;
    private int rightOperand;
    private char operate;

    //算式的构造方法
    public Formula(int left,int right,char operator){
        this.leftOperand = left;
        this.rightOperand = right;
        this.operate = operator;
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
    public int getResult(){
        if (operate == '+'){
            return this.leftOperand + this.rightOperand;

        }else {
            return this.leftOperand - this.rightOperand;
        }
    }

    //算式输出格式
    @Override
    public String toString() {

        return String.format("%3d", this.getLeftOperand()) + "  " +
                this.getOperate() + "  " +
                String.format("%3d", this.getRightOperand()) + " = ";


    }
}
