package ATM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
	private static final String CUR_PATH = System.getProperty("user.dir")+"\\src\\"+new Util().getClass().getPackageName()+"\\";
//	private static final String CUR_PATH = System.getProperty("user.dir")+"/src/"+new Util().getClass().getPackageName()+"/";
	private static Scanner scan = new Scanner(System.in);
	
	private static Util instance = new Util();
	
	public static Util getInstance(){
		return instance;
	}
	
	void init() {
	}
	
	public static void printNoData() {
		System.err.println("[ no Client Data ]");
	}
	
	public static String getStringValue(String msg) {
		System.out.printf("%s 입력 : ",msg);
		String input = scan.next();
		
		return input;
	}
	
	public static int getIntValue(String msg, int start, int end) {
		while(true) {
			try {
				System.out.printf("%s 입력 (%d-%d) : ",msg,start,end);
				int input = scan.nextInt();
				if(input<start || input >end) {
					System.out.println("입력 범위 오류");
					continue;
				}
				return input;
			} catch (Exception e) {
				System.out.println("정수 값 입력할 것.");
			} finally {
				scan.nextLine();
			}
		}
	}
	
	// account.txt , client.txt
	
	public static void tempData(AccountDAO aDAO, ClientDAO cDAO) {
		String userdata = "1001/test01/1111/김철수\n";
		userdata += "1002/test02/2222/이영희\n";
		userdata += "1003/test03/3333/신민수\n";
		userdata += "1004/test04/4444/최상민";
		
		String accountdata = "test01/1111-1111-1111/8000\n";
		accountdata += "test02/2222-2222-2222/5000\n";
		accountdata += "test01/3333-3333-3333/11000\n";
		accountdata += "test03/4444-4444-4444/9000\n";
		accountdata += "test01/5555-5555-5555/5400\n";
		accountdata += "test02/6666-6666-6666/1000\n";
		accountdata += "test03/7777-7777-7777/1000\n";
		accountdata += "test04/8888-8888-8888/1000";
		
		saveDataToFile("account.txt", accountdata);
		saveDataToFile("client.txt", userdata);
	}
	
	private static void saveDataToFile(String fileName,String data) {
		try (FileWriter fw = new FileWriter(CUR_PATH+fileName)){
			fw.write(data);
			System.out.println(fileName+" 파일 저장 완료");
		} catch (Exception e) {
			System.out.println(fileName+" 파일 저장 실패");
		}
		
	}
	
	public static void saveTofile(AccountDAO aDAO, ClientDAO cDAO) {
		String aData = aDAO.saveToData();
		String cData = cDAO.saveToData();
		saveDataToFile("account.txt", aData);
		saveDataToFile("client.txt", cData);
	}
	
	private static String loadFileToData(String fileName) {
		try(FileReader fr = new FileReader(CUR_PATH+fileName);
				BufferedReader br = new BufferedReader(fr);){
			String data = "";
			while(true) {
				int read = br.read();
				if(read == -1) break;
				
				data += (char)read;
			}
			System.out.println(fileName+" 파일 로드 성공");
			return data;
		} catch (IOException e) {
			System.out.println(fileName+" 파일 로드 실패");
			return null;
		}
	}
	
	public static void loadTofile(AccountDAO aDAO, ClientDAO cDAO) {
		String aData = loadFileToData("account.txt");
		String cData = loadFileToData("client.txt");
		aDAO.loasToData(aData);
		cDAO.loasToData(cData);
		cDAO.updataMaxNo();
	}
}
