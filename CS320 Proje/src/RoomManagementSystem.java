import Controller.MainController;
import View.MainView;

public class RoomManagementSystem {
	public static void main(String[] args){
		MainController controller = new MainController();
		MainView view = new MainView();
		view.attachController(controller);
		view.start();
	}

}
