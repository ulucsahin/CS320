package Controller;
import System.DBGetter;
import System.Student;

public class LoginController {
		
		private DBGetter dbgetter = new DBGetter();
	
		public boolean login(String username, String password){
			Student tempStudent = dbgetter.getStudent(username);
			if(tempStudent != null && tempStudent.getPassword().equals(password)){
				return true;
			}else{
				return false;
			}
		} 
		
		public Student getLoggedInUser(String username){
			return dbgetter.getStudent(username);
		}
}