package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import DAO.AccountDAO;
import DAO.ClientDAO;

public class FileManager {
	private final String CUR_PATH = System.getProperty("user.dir")+"\\src\\"+new Util().getClass().getPackageName()+"\\";
//	private final String CUR_PATH = System.getProperty("user.dir")+"/src/"+new Util().getClass().getPackageName()+"/";
	
	// account.txt , client.txt
	
	public void tempData(AccountDAO aDAO, ClientDAO cDAO) {
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
	
	private void saveDataToFile(String fileName,String data) {
		try (FileWriter fw = new FileWriter(CUR_PATH+fileName)){
			fw.write(data);
			System.out.println(fileName+" 파일 저장 성공");
		} catch (Exception e) {
			System.out.println(fileName+" 파일 저장 실패");
		}
		
	}
	
	public void saveTofile(AccountDAO aDAO, ClientDAO cDAO) {
		String aData = aDAO.saveToData();
		String cData = cDAO.saveToData();
		saveDataToFile("account.txt", aData);
		saveDataToFile("client.txt", cData);
	}
	
	private String loadFileToData(String fileName) {
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
	
	public void loadTofile(AccountDAO aDAO, ClientDAO cDAO) {
		String aData = loadFileToData("account.txt");
		String cData = loadFileToData("client.txt");
		aDAO.loasToData(aData);
		cDAO.loasToData(cData);
		cDAO.updataMaxNo();
	}
}
