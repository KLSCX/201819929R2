package SY1;
import java.util.Random;
public class PracticeSystem3 {

    public static void main(String[] args) {
        int[][] Exercise;
        Exercise = Equation();
        System.out.println("打印习题");
        printExercise( Exercise);
        System.out.println("打印答案");
        printCalculate(Exercise);
    }
    //产生算式
    public static int[] formula(){

        Random random = new Random();
        int[] Equation = new int[3];
        Equation[2] = random.nextInt(2);
        if(Equation[2]==1){
            plus(Equation);
        }else {
            minus(Equation);
        }
        return Equation;
    }
    //约束条件检测函数
    public static boolean check(int anInteger,int low,int high){
        return anInteger >= low && anInteger <= high ;
    }
    //加法运算
    public static int[] plus(int a[]){
        do {
            a[0] = operand();
            a[1] = operand();
        }while (!check(a[0],0,100)||!check(a[1],0,100) ||!check(a[0]+a[1],0,100));

        return a;
    }
    //减法运算
    public static int[] minus(int a[]){
        do {
            a[0] = operand();

            a[1] = operand();

        }while (!check(a[0],0,100)||!check(a[1],0,100) ||!check(a[0]-a[1],0,100));
        return a;
    }
    //生成习题
    public static int[][] Equation(){
        int[][] Exercise=new int[50][3];
        for (int i = 0; i < 50; i++) {
            Exercise[i] = formula();
        }
        return Exercise;
    }
    //生成100以内的整数
    public static int operand(){
        Random random = new Random();
        int operand = random.nextInt(101);
        return operand;
    }

    //打印习题
    public static void printExercise(int a[][]){
       for (int i = 0; i < 50; i++) {
           if (a[i][2]==1){
            System.out.printf("%-3d + %-3d =            ", a[i][0], a[i][1]);
        }else {
            System.out.printf("%-3d - %-3d =            ", a[i][0], a[i][1]);
        }
            if ((i+1) % 5 == 0) {
                System.out.println();
            }
        }
    }
    //打印答案
    public static void printCalculate(int a[][]){
        for (int i = 0; i < 50; i++) {
            if (a[i][2]==1){
                System.out.printf("%-3d + %-3d =    %-3d     ", a[i][0], a[i][1],a[i][0]+a[i][1]);
            }else {
                System.out.printf("%-3d - %-3d =    %-3d     ", a[i][0], a[i][1],a[i][0]-a[i][1]);
            }

            if ((i+1) % 5 == 0) {
                System.out.println();
            }
        }
    }
}