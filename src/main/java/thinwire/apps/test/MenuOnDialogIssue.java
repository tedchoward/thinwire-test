package thinwire.apps.test;

import thinwire.ui.*;

//Recreates 1841882 - Error in positions of the children in dialogs with Menu
public class MenuOnDialogIssue {
	public static void main(String[] args) {
		Dialog dlg = new Dialog("Menu Test");
		dlg.setBounds(25, 25, 200, 200);
		dlg.setVisible(true);
		Menu mainMenu = new Menu();
		dlg.setMenu(mainMenu);
		Menu.Item fileItem = new Menu.Item();
		fileItem.setText("File");
		mainMenu.getRootItem().getChildren().add(fileItem);
		Menu.Item newItem = new Menu.Item("New");
		fileItem.getChildren().add(newItem);
		Menu.Item openItem = new Menu.Item("Open");
		fileItem.getChildren().add(openItem);


		Button button = new Button("Button");
		button.setBounds(0, 0, 75, 30);
		dlg.getChildren().add(button);

		dlg.setVisible(true);
	}
}
