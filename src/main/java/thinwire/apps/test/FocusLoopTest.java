package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.event.*;

//Test for: 1806089 - Focus looping bug
//Resolution: Fixed.  Problem appears to only occur in Firefox.  To recreate, use builds RC2 to r578 and run the
//  following test. Then simply tab once and the CPU will peg.
public class FocusLoopTest {
	public static void main(String[] args) {
		final TextField tf = new TextField("Hi");
		final Button b = new Button("Click to Disable");
		
		Frame f = Application.current().getFrame();
		f.getChildren().add(tf.setBounds(10, 10, 150, 25));
		f.getChildren().add(b.setBounds(10, 40, 150, 25));
		
		b.addActionListener("click", new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				b.setEnabled(false);
			}
		});
	}
}
