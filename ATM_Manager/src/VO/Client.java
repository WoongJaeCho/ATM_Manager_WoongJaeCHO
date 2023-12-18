package VO;

public class Client {
	private int clientNo; // 1001 부터 자동 증가
	private String id;
	private String pw;
	private String name;
	
	public Client(int clientNo, String id, String pw, String name) {
		this.clientNo = clientNo;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	public int getClientNo() {
		return clientNo;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return clientNo + "\t" + id + "\t" + pw + "\t" + name;
	}
	
	public String saveToData() {
		return "%d/%s/%s/%s\n".formatted(clientNo,id,pw,name);
	}
	
}
