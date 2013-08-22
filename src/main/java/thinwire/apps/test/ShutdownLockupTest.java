package thinwire.apps.test;

import thinwire.ui.Application;
import thinwire.ui.MessageBox;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;

public class ShutdownLockupTest {
	public static void main(String[] args) {
		MessageBox.confirm("Click 'Ok' to initiate shutdown!", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				Application.current().getFrame().setVisible(false);
				
				//Application.current().getFrame().setTitle("Hello World");
				
				//TODO: Does this still happen now that we're not capturing the event processor thread?
				MessageBox.show("Shutting down, should be pegging the CPU now!");
			}
		});
		
		
	}
}
