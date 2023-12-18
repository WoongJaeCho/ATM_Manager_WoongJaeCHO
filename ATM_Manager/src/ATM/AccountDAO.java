package ATM;

import java.util.ArrayList;

public class AccountDAO {
	
	private ArrayList<Account> aList;
	private int cnt;
	Util util;

	public AccountDAO() {
		aList = new ArrayList<Account>();
		util = new Util();
	}
	
	public void deleteOneClientAcc(Client c) {
		for(int i = 0 ; i<aList.size() ;i+=1 ) {
			if(aList.get(i).getClientId().equals(c.getId())) {
				aList.remove(i);
				i-=1;
			}
		}
	}
	
	public void printMyPage(String id) {
		if(cnt==0) {
			Util.printNoData();
			return;
		}
		System.out.println("=============================");
		System.out.printf("%s\t%s\t\t%s\t\n","Id","Account","Money");
		System.out.println("-----------------------------");
		for(Account a : aList) {
			if(a.getClientId().equals(id)) {
				System.out.println(a);
			}
		}
		System.out.println("=============================");
	}
	
	public void printAllAccount() {
		if(cnt==0) {
			Util.printNoData();
			return;
		}
		System.out.println("=============================");
		System.out.printf("%s\t%s\t\t%s\t\n","Id","Account","Money");
		System.out.println("-----------------------------");
		for(Account a : aList) {
			System.out.println(a);
		}
		System.out.println("=============================");
	}
	
	public String saveToData() {
		if(cnt==0) return null;
		String data = "";
		for(Account a : aList) {
			data += a.saveToData();
		}
		return data;
	}
	
	public void loasToData(String data) {
		String[] temp = data.split("\n");
		cnt = temp.length;
		
		for(int i =0 ; i<temp.length;i+=1 ) {
			String[] info = temp[i].split("/");
			
			Account a = new Account(info[0],info[1],Integer.parseInt(info[2]));
			aList.add(a);
			
		}
	}
	
	
	
}
