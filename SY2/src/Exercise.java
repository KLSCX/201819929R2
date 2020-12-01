import java.util.ArrayList;
import java.util.Random;

public class Exercise {
    private ArrayList<Formula> operationList = new ArrayList<Formula>();
    private int current = 0;
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
    private Formula generateOperation(){
        Random random = new Random();
        int opValue = random.nextInt(2);
        if(opValue == 1){

            return new PlusFormula();
        }else {

            return new MinusFormula();

        }

    }
    public boolean hasNext(){
        return current <= operationList.size() - 1;
    }
    public Formula next(){
        return operationList.get(current++);
    }
}
