package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercise {
    private ArrayList<Formula> operationList = new ArrayList<Formula>();
    class Answers implements Serializable {
        private static final long serialVersionUID = -7833709422448085208L;
        String content;
        boolean correct;
        public Answers(){content = ""; correct = false;}
        public Answers(String ans, boolean cr){
            content = ans;
            correct = cr;
        }
    }
    private List<Answers> answers = new ArrayList<>();
    private int current = 0;
    private ExerciseType currentType; //第7章新添加题目类型，为保存用
    public ExerciseType getCurrentType() {
        return currentType;
    }
    //生成加法习题
    public void generatePlusExercise(int operationCount){
        //先清空再生成
        Formula anOperation;
        setCurrentType(ExerciseType.PLUS); //设置题目类型
        operationList.clear();    //先清空再生成
        answers.clear();
        while (operationCount > 0) {
            do {
                anOperation = new PlusFormula();
            }while (contains(anOperation));
            answers.add(new Answers("",false));
            operationList.add(anOperation);
            operationCount--;
        }
    }
    //生成减法习题
    public void generateMinusExercise(int operationCount){
        //先清空再生成
        Formula anOperation;
        setCurrentType(ExerciseType.MINUS); //设置题目类型
        operationList.clear();    //先清空再生成
        answers.clear();
        while (operationCount > 0) {
            do {
                anOperation = new MinusFormula();
            }while (contains(anOperation));
            answers.add(new Answers("",false));
            operationList.add(anOperation);
            operationCount--;
        }
    }
    //生成随机加减法习题
    public void generateExercise(int operationCount) {
        //先清空再生成
        Formula anOperation;

        setCurrentType(ExerciseType.MIX); //设置题目类型
        operationList.clear();    //先清空再生成
        answers.clear();
        while (operationCount > 0) {
            do {
                anOperation = generateOperation();
            }while (contains(anOperation));
            answers.add(new Answers("",false));
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
    //返回链表长度
    public int getLength(){
        return operationList.size();
    }
    //用来检验链表是否为空
    public boolean hasNext(){
        return current <= operationList.size() - 1;
    }
    //返回链表所存算式
    public Formula next(){
        return operationList.get(current++);
    }

    //根据索引值返回算式
    public Formula getFormula(int index){
        if(index < operationList.size()) return operationList.get(index);
        else return null;
    }



    //设置习题类型
    private void setCurrentType(ExerciseType type) {
        this.currentType = type;
    }
    //根据类型生成算式
    public void generateType(int operationCount){
        switch(currentType){
            case PLUS:
                this.generatePlusExercise(operationCount);
                break;
            case MINUS:
                this.generateMinusExercise(operationCount);
                break;
            case MIX:
                this.generateExercise(operationCount);
                break;
        }
    }
    public Formula getOperation(int index){
        if(index < operationList.size()) return operationList.get(index);
        else return null;
    }

    public boolean getJudgement(int index){
        return answers.get(index).correct;
    }

    public void setAnswer(int index, String ans){
        Formula op;
        op = operationList.get(index);
        String result = String.valueOf(op.getValue());
        String tans = ans.trim();
        answers.set(index, new Answers(tans,result.equals(tans)));
    }
    public String getAnswer(int index){
        return answers.get(index).content;
    }
    public void clearAnswers(){
        for(int i=0; i<answers.size(); i++)
            answers.set(i,new Answers("",false));
    }
    public int Correct(){
        int count=0;
        for(int i=0; i<answers.size(); i++){
            if(answers.get(i).correct) count++;
        }
        return count;
    }

}

