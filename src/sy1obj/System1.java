package sy1obj;
import java.util.Random;
//抽象类二元运算
abstract class BinaryOperation{
    static final int UPPER = 100;
    static final int LOWER = 0;
    private int left_operand = 0;
    private int right_operand = 0;
    private char operator = '+';
    private int value = 0;
    protected void generateBinaryOperation(char anOperator){
        int left,right,result;
        Random random = new Random();
        left = random.nextInt(UPPER+1);
        do{
            right = random.nextInt(UPPER+1);
            result = left + right;
        }while (!(checkCalculation(result)));
        left_operand = left;
        right_operand = right;
        operator = anOperator;
        value = result;
    }
    public int getLeft_operand() {
        return left_operand;
    }
    public int getRight_operand() {
        return right_operand;
    }
    public char getOperator() {
        return operator;
    }
    public int getValue() {
        return value;
    }
    abstract boolean checkCalculation(int anInteger);
    abstract int calculate(int left,int right);
    public boolean equals(BinaryOperation anOperation){
        return left_operand == anOperation.getLeft_operand() &
                right_operand == anOperation.getRight_operand() &
                operator == anOperation.getOperator();
    }
}

class PlusOperation extends BinaryOperation{
    PlusOperation(){
        generateBinaryOperation('+');
    }
    public boolean checkCalculation(int anInteger){
        return anInteger <= UPPER;
    }
    int calculate(int left,int right){
        return left + right;
    }
}

class MinusOperation extends BinaryOperation{
    MinusOperation(){
        generateBinaryOperation('-');
    }
public boolean checkCalculation(int anInteger){
        return anInteger <= UPPER;
    }
    int calculate(int left,int right){
        return left + right;
    }
}
