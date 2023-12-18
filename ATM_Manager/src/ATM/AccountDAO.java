package ATM;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountDAO {
	
	private ArrayList<Account> aList;
	private int cnt;
	Util util;

	public AccountDAO() {
		aList = new ArrayList<Account>();
		util = new Util();
	}
	
	private int checkOneClientAccount(String ClientId) {
		int count = 0;
		for(Account a : aList) {
			if(a.getClientId().equals(ClientId)) {
				cnt+=1;
			}
		}
		return cnt;	
	}
	
	
	
	public void addAccount(String ClientId) {
		int count = checkOneClientAccount(ClientId);
		if(count>3) {
			System.out.println("회원당 계좌는 3개까지 가능합니다.");
			return;
		}
		String accNumber = Util.getStringValue("추가할 계좌번호");
		String accPattern = "^\\d{4}-\\d{4}-\\d{4}&";
		Pattern p = Pattern.compile(accPattern);
		Matcher m = p.matcher(accNumber);
		if(!m.matches()) {
			System.out.println("계좌번호는 (1111-1111-1111)의 형태로 입력하시오.");
			return;
		}
		if(count!=0 && getIdxAccount(accNumber)==-1) {
			System.out.println("중복된 계좌번호가 있습니다.");
			return;
		}
		Account a = new Account(ClientId, accNumber, 0);
		aList.add(a);
		cnt+=1;
		System.out.println("[ 계좌번호 생성 완료 ]");
	}
	
	private int getIdxAccount(String accNumber) {
		for(int i=0; i<cnt; i+=1 ) {
			if(aList.get(i).getAccNumber().equals(accNumber)) {
				return i;
			}
		}
		return -1;
	}
	
	private void printOneClientAccount(String clientId) {
		for(Account a : aList) {
			if(a.getClientId().equals(clientId)) {
				System.out.println(a);
			}
		}
	}
	
	public void deleteAccount(String clientId) {
		int count = checkOneClientAccount(clientId);
		if(count==0) {
			Util.printNoData();
			return;
		}
		printOneClientAccount(clientId);
		String accNumber = Util.getStringValue("삭제할 계좌번호");
		String accPattern = "^\\d{4}-\\d{4}-\\d{4}&";
		Pattern p = Pattern.compile(accPattern);
		Matcher m = p.matcher(accNumber);
		if(!m.matches()) {
			System.out.println("계좌번호는 (1111-1111-1111)의 형태로 입력하시오.");
			return;
		}
		int delidx = getIdxAccount(accNumber);
		if(delidx == -1) {
			System.out.println("해당 계좌번호가 없습니다.");
			return;
		}
		aList.remove(delidx);
		cnt-=1;
		System.out.println("[ 계좌번호 삭제 완료 ]");
	}
	
	public void depositAccount(String clientId) {
		int count = checkOneClientAccount(clientId);
		if(count==0) {
			Util.printNoData();
			return;
		}
		printOneClientAccount(clientId);		
		String accNumber = Util.getStringValue("입금할 계좌번호");
		String accPattern = "^\\d{4}-\\d{4}-\\d{4}&";
		Pattern p = Pattern.compile(accPattern);
		Matcher m = p.matcher(accNumber);
		if(!m.matches()) {
			System.out.println("계좌번호는 (1111-1111-1111)의 형태로 입력하시오.");
			return;
		}
		int idx = getIdxAccount(accNumber);
		if(idx == -1) {
			System.out.println("해당 계좌번호가 없습니다.");
			return;
		}
		int deposit = Util.getIntValue("입금할 금액", 0);
		int money = aList.get(idx).getMoney() + deposit;
		aList.set(idx, new Account(clientId,accNumber,money));
		System.out.println("[ 입금 완료 ]");
	}
	
	public void withdrawAccount(String clientId) {
		int count = checkOneClientAccount(clientId);
		if(count==0) {
			Util.printNoData();
			return;
		}
		printOneClientAccount(clientId);		
		String accNumber = Util.getStringValue("출금할 계좌번호");
		String accPattern = "^\\d{4}-\\d{4}-\\d{4}&";
		Pattern p = Pattern.compile(accPattern);
		Matcher m = p.matcher(accNumber);
		if(!m.matches()) {
			System.out.println("계좌번호는 (1111-1111-1111)의 형태로 입력하시오.");
			return;
		}
		int idx = getIdxAccount(accNumber);
		if(idx == -1) {
			System.out.println("해당 계좌번호가 없습니다.");
			return;
		}
		int withdraw = Util.getIntValue("출금할 금액", 0);
		int money = aList.get(idx).getMoney() - withdraw;
		aList.set(idx, new Account(clientId,accNumber,money));
		System.out.println("[ 출금 완료 ]");
	}
	
	public void transferAccount(String clientId) {
		if(cnt<=1) {
			System.out.println("이체 가능한 계좌가 없습니다.");
			return;
		}
		int count = checkOneClientAccount(clientId);
		if(count==0) {
			Util.printNoData();
			return;
		}
		printOneClientAccount(clientId);		
		String myaccNumber = Util.getStringValue("본인 계좌번호");
		String accPattern = "^\\d{4}-\\d{4}-\\d{4}&";
		Pattern p = Pattern.compile(accPattern);
		Matcher m1 = p.matcher(myaccNumber);
		if(!m1.matches()) {
			System.out.println("계좌번호는 (1111-1111-1111)의 형태로 입력하시오.");
			return;
		}
		int myidx = getIdxAccount(myaccNumber);
		if(myidx == -1) {
			System.out.println("해당 계좌번호가 없습니다.");
			return;
		}
		
		String youraccNumber = Util.getStringValue("이체받을 계좌번호");
		Matcher m2 = p.matcher(youraccNumber);
		if(!m2.matches()) {
			System.out.println("계좌번호는 (1111-1111-1111)의 형태로 입력하시오.");
			return;
		}
		int youridx = getIdxAccount(youraccNumber);
		if(youridx == -1) {
			System.out.println("해당 계좌번호가 없습니다.");
			return;
		}
		int transfer = Util.getIntValue("이체할 금액", 0);
		String yourId = aList.get(youridx).getClientId();
		int myMoney = aList.get(myidx).getMoney() - transfer;
		int yourMoney = aList.get(myidx).getMoney() + transfer;
		
		aList.set(yourMoney, new Account(yourId, youraccNumber, yourMoney));
		aList.set(myidx, new Account(clientId, myaccNumber, myMoney));
		System.out.println("[ 이체 완료 ]");
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
			info[2] = info[2].substring(0, info[2].length()-1);
			Account a = new Account(info[0],info[1],Integer.parseInt(info[2]));
			aList.add(a);
			
		}
	}
	
	
	
}
