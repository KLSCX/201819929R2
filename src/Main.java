import bean.Exercise;
import bean.Formula;
import controller.ExerciseController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("输入1生成习题，输入2生成习题答案,输入其他整数生成新的习题");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        while (number == 1 || number == 2 || number == 3) {
            if(number == 1){
                ExerciseController.exercisePrint();
            }else if (number == 2) {
                ExerciseController.exerciseAnswerPrint();
            } else {
                ExerciseController.clear();
            }
            number = input.nextInt();
        }

    }
}
