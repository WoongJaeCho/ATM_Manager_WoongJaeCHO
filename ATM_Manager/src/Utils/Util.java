package Utils;

import java.util.Scanner;

public class Util {
	
	private static Scanner scan = new Scanner(System.in);
	
	private static Util instance = new Util();
	
	public static Util getInstance(){
		return instance;
	}
	
	void init() {
	}
	
	public static void printNoData() {
		System.err.println("[ no Data ]");
	}
	
	public static String getStringValue(String msg) {
		System.out.printf("%s 입력 : ",msg);
		String input = scan.next();
		scan.nextLine();
		return input;
	}
	
	public static int getIntValue(String msg, int start) {
		while(true) {
			try {
				System.out.printf("%s 입력: ",msg);
				int input = scan.nextInt();
				if(input<start) {
					System.out.println("양수 값 입력할 것.");
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
	
}
