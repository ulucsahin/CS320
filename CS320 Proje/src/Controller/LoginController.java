package Controller;
import System.DBGetter;
import System.Student;

public class LoginController {
		
		DBGetter dbgetter = new DBGetter();
	
		public void login(String username, String password){
			Student tempStudent = dbgetter.getStudent(username);
			if(tempStudent.getPassword() == password){
				System.out.println("Login succesfull.");
			}else{
				System.out.println("Login failed.");
			}
		} 
}
