package ATM;
// 한 회원마다 계좌 3개 까지 만들 수 있음.
public class Account {
	private String clientId;
	private String accNumber;
	private int money;
	
	public Account(String clientId, String accNumber, int money) {
		this.clientId = clientId;
		this.accNumber = accNumber;
		this.money = money;
	}
	
	
	public String getClientId() {
		return clientId;
	}


	public String getAccNumber() {
		return accNumber;
	}


	public int getMoney() {
		return money;
	}


	@Override
	public String toString() {
		return clientId + "\t" + accNumber + "\t" + money;
	}
	
	public String saveToData() {
		return "%s/%s/%d\n".formatted(clientId,accNumber,money);
	}
}
