package model;

import java.util.ArrayList;
import java.util.Random;

public class Exercise {
    private ArrayList<Formula> operationList = new ArrayList<Formula>();
    private int current = 0;
    //生成加法习题
    public void generatePlusExercise(int operationCount){
        Formula anOperation;
        while (operationCount > 0) {
            do {
                anOperation = new PlusFormula();
            }while (contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }
    //生成减法习题
    public void generateMinusExercise(int operationCount){
        Formula anOperation;
        while (operationCount > 0) {
            do {
                anOperation = new MinusFormula();
            }while (contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }
    //生成随机加减法习题
    public void generateExercise(int operationCount) {
        Formula anOperation;
        while (operationCount > 0) {
            do {
                anOperation = generateOperation();
            }while (contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
    }
    //判断传入的算式是否和链表里已有的算式相重复
    private boolean contains (Formula anOperation){
        boolean found = false;
        for (int i = 0; i < operationList.size(); i++) {
            if(anOperation.equals(operationList.get(i))){
                found = true;
                break;
            }
        }
        return found;
    }
    //根据随机产生的数字产生随机加法或减法算式
    private Formula generateOperation(){
        Random random = new Random();
        int opValue = random.nextInt(2);
        if(opValue == 1){

            return new PlusFormula();
        }else {

            return new MinusFormula();

        }

    }
    //用来检验链表是否为空
    public boolean hasNext(){
        return current <= operationList.size() - 1;
    }
    //返回链表所存算式
    public Formula next(){
        return operationList.get(current++);
    }

}
