package thinwire.apps.test;

import thinwire.ui.*;

public class ShutdownLockupTest {
	public static void main(String[] args) {
		MessageBox.confirm("Click 'Ok' to initiate shutdown!");
		
		Application.current().getFrame().setVisible(false);
		
		//Application.current().getFrame().setTitle("Hello World");
		MessageBox.confirm("Shutting down, should be pegging the CPU now!");
	}
}
