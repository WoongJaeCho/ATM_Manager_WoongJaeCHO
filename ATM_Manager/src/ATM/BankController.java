package ATM;

public class BankController {
	
	private final String bankName = "한국은행";
	private ClientDAO cDAO;
	private AccountDAO aDAO;
	private String log;
	
	private void init() {
		cDAO = new ClientDAO();
		aDAO = new AccountDAO();
		//Util.tempData(aDAO,cDAO);
		Util.loadTofile(aDAO, cDAO);
	}
	
	private void MainMenu() {
		System.out.println("[1] 관리자\n[2] 사용자\n[0] 종료");
	}
	
	private void adminMenu() {
		System.out.printf("===[ %s 관리자 ]===\n",bankName);
		System.out.println("[1] 회원목록\n[2] 회원 수정\n[3] 회원 삭제\n[4] 데이터 저장\n[5] 데이터 불러오기\n[6] 전체 계좌 목록\n[0] 뒤로가기");
	}
	
	private void userMenu() {
		System.out.println("[1] 회원가입\n[2] 로그인\n[0] 뒤로가기");
	}
	
	private void loginMenu() {
		System.out.println("[1] 계좌추가\n[2] 계좌삭제\n[3] 입금\n[4] 출금\n[5] 이체\n[6] 탈퇴\n[7] 마이페이지\n[0] 로그아웃");
	}
	public void run() {
		init();
		while(true) {
			System.out.println("====[ "+bankName+" ATM ]====");
			MainMenu();
			int sel = Util.getIntValue("메뉴", 0, 2);
			if(sel == 1) {
				while(true) {
					adminMenu();
					sel = Util.getIntValue("메뉴", 0, 6);
					if(sel == 1) {
						System.out.println("[ 회원 목록 ]");
						cDAO.printClient();
					} else if(sel == 2) {
						System.out.println("[ 회원 수정 ]");
						cDAO.modifyClient();
					} else if(sel == 3) {
						System.out.println("[ 회원 삭제 ]");
						cDAO.deleteClient();
					} else if(sel == 4) {
						System.out.println("[ 데이터 저장 ]");
						Util.saveTofile(aDAO, cDAO);
					} else if(sel == 5) {
						System.out.println("[ 데이터 불러오기 ]");
						Util.loadTofile(aDAO, cDAO);
					} else if(sel == 6) {
						System.out.println("[ 전체 계좌 목록 ]");
						aDAO.printAllAccount();
					} else if(sel == 0) {
						break;
					}
				}
			} else if(sel == 2) {
				while(true) {
					if(log==null) {
						userMenu();
						sel=Util.getIntValue("메뉴", 0, 2);
						if (sel == 1) {
							System.out.println("[ 회원 가입 ]");
							cDAO.joinClient();
						}else if(sel == 2) {
							System.out.println("[ 로그인 ]");
							log = cDAO.login(aDAO);
						}else if(sel == 0) {
							break;
						}
					}else if(log!=null) {
						System.out.printf("[ %s 님 로그인 ]\n",log);
						loginMenu();
						sel=Util.getIntValue("메뉴", 0, 7);
						if (sel == 1) {
							System.out.println("[ 계좌 추가 ]");
							
						}else if(sel == 2) {
							System.out.println("[ 계좌 삭제 ]");
							
						}else if(sel == 3) {
							System.out.println("[ 입   금 ]");
							
						}else if(sel == 4) {
							System.out.println("[ 출   금 ]");
							
						}else if(sel == 5) {
							System.out.println("[ 이   체 ]");
							
						}else if(sel == 6) {
							System.out.println("[ 탈   퇴 ]");
							cDAO.deleteOneClient(aDAO, log);
							log=null;
						}else if(sel == 7) {
							System.out.println("[ 마이 페이지 ]");
							aDAO.printMyPage(log);
						}else if(sel == 0) {
							log=null;
						}
					}
				}
			}else if(sel==0) {
				System.out.println("프로그램 종료");
				return;
			}
		}
	}
	
	// [1]관리자 [2]사용자 [0] 종료
	
	// 관리자
	// [1] 회원목록 [2] 회원 수정 [3] 회원 삭제 [4]데이터 저장 [5] 데이터 불러오기
	
	// 회원 수정 : 회원 아이디 검색 / 이름 , 비밀번호 수정가능
	// 회원 삭제 : 회원 아이디 검색
	// 데이터 저장 ; account.txt , client.txt
	
	// 사용자메뉴
	// [1] 회원가입 [2] 로그인 [0] 뒤로가기
	
	// 회원가입 : 회원 아이디 중복 확인
	
	// 로그인 메뉴
	// [1] 계좌추가 [2] 계좌삭제 [3]입금 [4]출금 [5] 이체 [6]탈퇴 [7] 마이페이지 [0] 로그아웃
	
	// 계좌 추가 (숫자4개-숫자4개-숫자4개) 일치할때 추가 가능 : 중복체크
	// 계좌 삭제 : 본인 회원 계좌만 가능
	
	// 입금 : accList에 계좌가 있을때만 입금 가능 : 100원 이상 입금/이체/출금 : 계좌 잔고 만큼만
	// 이체 : 이체할 계좌랑 이체받을 계좌만 일치안하면 됨
	
	// 탈퇴 : 페스워드 다시 입력 받아 -> 탈퇴 가능
	// 마이페이지 : 내 계좌 목록(잔고) 확인
	
}
