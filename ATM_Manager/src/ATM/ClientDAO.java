package ATM;

import java.util.ArrayList;

public class ClientDAO {
	
	private ArrayList<Client> cList;
	private int cnt;
	private int maxNo;

	public ClientDAO() {
		cList = new ArrayList<Client>();
	}
	
	public void deleteOneClient(AccountDAO aDAO, String id) {
		while(true) {
			Client c = checkId(id);
			String pw = Util.getStringValue("비밀번호");
			if(!c.getPw().equals(pw)) {
				System.err.println("비밀번호 불일치");
				continue;
			}
			aDAO.deleteOneClientAcc(c);
			int idx = checkIdx(c);
			cList.remove(idx);
			System.out.printf("[ %s 회원 탈퇴 완료 ]\n",id);
			return;
		}
	}
	
	public String login(AccountDAO aDAO) {
		while(true) {
			String id = Util.getStringValue("아이디");
			Client c = checkId(id);
			if(c==null) {
				System.err.println("존재하지 않는 아이디");
				continue;
			}
			String pw = Util.getStringValue("비밀번호");
			if(!c.getPw().equals(pw)) {
				System.err.println("비밀번호 불일치");
				continue;
			}
			
			return id;
		}
	}
	public void joinClient() {
		while(true) {
			String id = Util.getStringValue("아이디");
			Client c = checkId(id);
			if(c!=null) {
				System.err.println("중복된 아이디가 존재함.");
				continue;
			}
			String pw = Util.getStringValue("비밀번호");
			String name = Util.getStringValue("이름");
			
			cList.add(new Client(maxNo++, id, pw, name));
			cnt+=1;
			return;
		}
	}
	
	public void printClient() {
		if(cnt==0) {
			Util.printNoData();
			return;
		}
		System.out.println("=============================");
		System.out.printf("%s\t%s\t%s\t%s\n","No","Id","Pw","Name");
		System.out.println("-----------------------------");
		for(Client c : cList) {
			System.out.println(c);
		}
		System.out.println("=============================");
	}
	
	private Client checkId (String id) {
		for(Client c : cList) {
			if(c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}
	private int checkIdx(Client c) {
		for(int i=0; i<cnt ; i+=1) {
			if(c.getClientNo() == cList.get(i).getClientNo()) {
				return i;
			}
		}
		return -1;
	}
 	public void modifyClient() {
 		if(cnt == 0) {
 			Util.printNoData();
 			return;
 		}
		String id = Util.getStringValue("아이디");
		Client c = checkId(id);
		if(c==null) {
			System.err.println("존재하지 않는 아이디");
			return;
		}
		String name = Util.getStringValue("[수정] 이름");
		while(true) {
			String pw = Util.getStringValue("[수정] 비밀번호");
			if(c.getPw().equals(pw)) {
				System.err.println("동일한 비밀번호로 수정 불가능");
				continue;
			}
			//c = new Client(c.getClientNo(), c.getId(), pw, name);
			c.setName(name);
			c.setPw(pw);
			System.out.printf("[ %s 회원 수정 완료 ]\n",name);
			return;
		}
	}
	
 	public void deleteClient() {
 		if(cnt==0) {
 			Util.printNoData();
 			return;
 		}
 		String id = Util.getStringValue("아이디");
		Client c = checkId(id);
		if(c==null) {
			System.err.println("존재하지 않는 아이디");
			return;
		}
		int idx = checkIdx(c); 
		cList.remove(idx);
		cnt-=1;
		System.out.printf("[ %s 회원 삭제 완료 ]\n",c.getName());
 	}
 	
	public String saveToData() {
		if(cnt==0) return null;
		String data = "";
		for(Client c : cList) {
			data += c.saveToData();
		}
		return data;
	}
	public void updataMaxNo() {
		int maxNo=0;
		for(Client c : cList) {
			if(maxNo < c.getClientNo()) {
				maxNo = c.getClientNo();
			}
		}
		this.maxNo = maxNo+1;
	}
	public void loasToData(String data) {
		String[] temp = data.split("\n");
		cnt = temp.length;
		
		for(int i =0 ; i<temp.length;i+=1 ) {
			String[] info = temp[i].split("/");
			info[2] = info[2].substring(0, info[2].length()-1);
			Client c = new Client(Integer.parseInt(info[0]), info[1], info[2], info[3]);
			cList.add(c);
		}
	}
	
}
