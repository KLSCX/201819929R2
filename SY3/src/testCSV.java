import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import model.Exercise;
import model.Formula;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

//按照本章需求，主要完成两个部分的任务。第一，随机生成多套习题并保存在外存中，每天从中随机取三套习题；第二，程序读入小明练习结果，评判并打分，将成绩和习题保存在外存中。
//1、批量产生习题，并用csv格式文件存储。文件命名按照文件命名规则编写；
//1）文件包括习题文件、练习文件和批改文件三种类型；
//2）练习文件和批改文件应该能够找到所属的习题文件；
//3）批量产生的习题应包含加法习题集、减法习题集和混合习题集类型
//2、抽取某一习题文件作为一次练习
//1）练习结果以csv格式存储在练习文件中；
//2）练习过程可以中断，保存练习结果，再次打开练习可以恢复；
//3、批改练习题并存储
//1）习题自动对练习文件和习题文件进行批改，得到的评价结果存入批改文件中；
//4、完成各阶段类设计和编码的单元测试活动

public class testCSV {
	private static final String pathTest = "D:\\ScCsv\\Test\\";//练习文件存放位置
	private static final String pathExercise = "D:\\ScCsv\\Exercise\\";//习题文件存放位置
	private  static final String pathCorrection = "D:\\ScCsv\\Correction\\";//批改文件存放位置

	public static void main(String[] args) throws IOException {
		System.out.print("请选择相关功能" + "\n" + "1、生成30套习题集存在外存中" + "\n" + "2、选择进行习题集的练习"
		+ "\n" + "3、批改已做过的习题集" + "\n");
		Scanner input = new Scanner(System.in);
		int sc = input.nextInt();
		if (sc == 1){
			System.out.println("已选择功能1");
			if(checkExerciseSheetNum()){
				System.out.println("题库中已存在30道习题集");
			}else{
				generateExerciseSheet();
			}
		}else if(sc == 2){
			System.out.println("已选择功能2");
			practiceTest();
		}else if(sc == 3){
			System.out.println("已选择功能3");
			correctTest();
		}
	}

	public static void generateExerciseSheet() {//产生习题文件函数,随机产生的习题数量设为30道
		Formula formula;
		try {
			for (int i = 0; i < 30; i++) {
				String[] arr = new String[4];
				String file = (pathExercise + "exercise" + (i + 1) + ".csv");
				createFile(file);
				Exercise exercise = new Exercise();
				exercise.generateExercise(50);
				CsvWriter csvWriter = new CsvWriter((pathExercise + "exercise" + (i + 1) + ".csv"), ',', Charset.forName("GB2312"));
				String[] csvHeader = {"左", "运算符", "右运算数", "等号"};
				csvWriter.writeRecord(csvHeader);
				for (int j = 0; j < 50; j++) {
					while (exercise.hasNext()) {
						formula = exercise.next();
						arr[0] = (String.valueOf(formula.getLeftOperand()));
						arr[1] = (String.valueOf(formula.getOperate()));
						arr[2] = (String.valueOf(formula.getRightOperand()));
						arr[3] = "=";
						csvWriter.writeRecord(arr);
					}
				}
				csvWriter.close();
			}
			System.out.println("--------------------已随机生成30套习题集--------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkExerciseSheetNum(){//检查题库中已存习题集数量，到达30返回true,负责返回false
		int count = 0;
		File file = new File("D:\\ScCsv\\Exercise");//定义文件对象file
		File[]  fileArray = file.listFiles();//返回该路径下的file对象数组
		assert fileArray != null;
		for(File f : fileArray){//增强for循环，遍历
			if(f.isFile()){//判断是否为文件
				if(f.getName().endsWith(".csv")){//如果文件后缀名是 “.csv” ,则计数加一
					count++;
				}
			}
		}
		return count==30;
	}
//批改已经做过的习题
	public static void correctTest() throws IOException {

		for (int i = 0; i < 30; i++) {
			String filePathname1 = (pathTest + "test" + (i + 1) + ".csv");
			String filePathname2 = (pathCorrection + "correction" + (i + 1) + ".csv");
			File testFile = new File(filePathname1);
			if(testFile.exists()){
				createFile(filePathname2);
				CsvReader reader = new CsvReader(filePathname1, ',', Charset.forName("GB2312"));
				CsvWriter writer = new CsvWriter(filePathname2,',', Charset.forName("GB2312"));
				ArrayList<String[]> lstFile = new ArrayList<>();
				reader.readHeaders();
				while (reader.readRecord()) {
					lstFile.add(reader.getValues());
				}
				reader.close();
				String[] csvHeader = {"左", "运算符", "右运算数", "等号", "你的答案","正确答案"};
				writer.writeRecord(csvHeader);
				String[] arr = new String[6];
				for (String[] strings : lstFile) {
					int[] value = new int[3];
					arr[0] = strings[0];
					arr[1] = strings[1];
					arr[2] = strings[2];
					arr[3] = strings[3];
					arr[4] = strings[4];
					value[0] = Integer.parseInt(arr[0]);
					value[1] = Integer.parseInt(arr[2]);
					String c = String.valueOf(arr[1]);
					if (c.equals("+")) {
						value[2] = value[0] + value[1];
					} else {
						value[2] = value[0] - value[1];
					}

					if (strings[4].equals("") || (value[2] != Integer.parseInt(strings[4]))) {
						arr[5] = String.valueOf(value[2]);
					}
					writer.writeRecord(arr);
				}
				writer.close();
			}
		}
	}

	public static void  practiceTest() throws IOException {//从题库中选取3道没有做过的习题进行练习，可中断练习
		int count = 0;
		int next;
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			String filepath = (pathTest + "test" + (i + 1) + ".csv");
			File testFile = new File(filepath);
			if (testFile.exists()) {
				count++;//判断练习文件是否存在
				CsvReader reader = new CsvReader(filepath, ',', Charset.forName("GB2312"));
				reader.readHeaders();
				while (reader.readRecord()) {
					if (reader.getValues()[4].equals("")) {
						arrayList.add(i + 1);
						break;
					}
				}
				reader.close();
			}
		}
		int size = arrayList.size();
		System.out.println("前" + count + "套习题集已经被做过");
		System.out.print("其中第");
		if (arrayList.size() == 0) System.out.print(0);
		for (Integer integer : arrayList) {
			System.out.print(integer);
			if(--size!=0){
				System.out.print("、");
			}
		}
		System.out.println("套习题集未被完全做完");
		if(arrayList.size()!=0){
			Scanner sc1 = new Scanner(System.in);
		System.out.println("你要选择继续新的练习还是完成未被完全做完的习题集,开始新的练习输入1，完成旧的练习输入2");
		    next = sc1.nextInt();
		}else {
			System.out.println("即将开始新的练习");
			next = 1;
		}
		if (next == 2) {
			int choiceNum;
			Scanner sc = new Scanner(System.in);
         while(arrayList.size()!=0){
				System.out.println("请从未完成的习题集中选取一套，输入序号即可");
				ArrayList<String[]> lstFile = new ArrayList<>();
				choiceNum = sc.nextInt();
				for (int a = 0; a < arrayList.size();a++ )
				{
					if(choiceNum == arrayList.get(a)) arrayList.remove(a);
				}
				String reader = (pathTest + "test" + choiceNum + ".csv");
				CsvReader read = new CsvReader(reader, ',', Charset.forName("GB2312"));
				read.readHeaders();
				while (read.readRecord()){
					lstFile.add(read.getValues());
				}
				readFile(reader);
				System.out.println("请重新输入你的答案");
				String[] arr = new String[5];
				CsvWriter csvWriter = new CsvWriter(reader, ',', Charset.forName("GB2312"));
				Scanner inputString = new Scanner(System.in);
					String input = inputString.nextLine();
					String[] stringArray = input.split(" ");
					int[] num = new int[stringArray.length];
				writeFile(choiceNum, lstFile, arr, csvWriter, stringArray, num);
			}
		}else if(next == 1){
			for (int i = count + 1; i <= count + 3; i++) {
				ArrayList<String[]> lstFile = new ArrayList<>();
				System.out.println("接下来选取的习题集的文件名为" + "exercise" + i);
				String reader = pathExercise + "exercise" + i + ".csv";
				File file1 = new File(reader);
				CsvReader csvReader = new CsvReader(reader,',', Charset.forName("GB2312"));
				if(!file1.exists()){
					System.out.println("题库中的题目已经全部做完");
					break;
				}
				readFile(reader);
				csvReader.readHeaders();
				while(csvReader.readRecord())
				{
					lstFile.add(csvReader.getValues());
				}
				String[] arr = new String[5];
				System.out.println("请输入你的答案，答案之间以空格隔开");
				String file2 = pathTest + "test" + i + ".csv";
				createFile(file2);
				CsvWriter csvWriter = new CsvWriter(file2, ',', Charset.forName("GB2312"));
				Scanner input = new Scanner(System.in);
				String inputString = input.nextLine();
				String[] stringArray = inputString.split(" ");
				int[] num = new int[stringArray.length];
				writeFile(i, lstFile, arr, csvWriter, stringArray, num);
			}
			}
		}

	private static void writeFile(int choiceNum, ArrayList<String[]> lstFile, String[] arr, CsvWriter csvWriter, String[] stringArray, int[] num) throws IOException {
		for (int k = 0; k < stringArray.length; k++) {
			num[k] = Integer.parseInt(stringArray[k]);
		}
		String[] csvHeader = {"左", "运算符", "右运算数", "等号", "你的答案"};
		csvWriter.writeRecord(csvHeader);
		for (int row = 0; row < lstFile.size(); row++) {
			System.arraycopy(lstFile.get(row), 0, arr, 0, lstFile.get(row).length);
			if (row < num.length) {
				arr[4] = String.valueOf(num[row]);
			} else {
				arr[4] = "";
			}
			csvWriter.writeRecord(arr);
		}
		csvWriter.close();
		System.out.println("已为您保存" + "test" + choiceNum + ".csv文件的答案");
	}

	//根据文件路径名读取内容
	public static void readFile(String pathname) throws IOException {
		ArrayList<String[]> lstFile = new ArrayList<>();
		CsvReader reader = new CsvReader(pathname, ',', Charset.forName("GB2312"));
		reader.readHeaders();
		while (reader.readRecord()) {
			lstFile.add(reader.getValues());
		}
		reader.close();
		for (int row = 0; row < lstFile.size(); row++) {
			for (int col = 0; col < lstFile.get(row).length; col++) {
				String cell = lstFile.get(row)[col];
				System.out.print(cell + "\t");
			}
			System.out.print("\t");
			if ((row + 1) % 5 == 0) {
				System.out.println();
			}
		}
	}
	//根据文件路径创建文件
	public static void createFile(String pathname) throws IOException {
		File file = new File(pathname);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()) {
			 fileParent.mkdirs();
		}
		if (file.exists()) {
			 file.delete();
		}
		file.createNewFile();
	}
}



