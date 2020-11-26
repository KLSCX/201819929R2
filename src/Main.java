
import controller.ExerciseController;
import java.util.Scanner;

public class Main {
    //运行程序，实现打印习题和打印习题答案
    public static void main(String[] args) {
        System.out.println("输入1生成习题，输入2生成习题答案,输入3本次习题结束");
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