package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.event.*;

//Test for: 1838042 - Rendering of RadioButton triggers "checked" event
//Resolution: Fixed - Turns out that the property changes for RadioButton's would fire twice when done
// in a certain sequence. The cause was a bug in RadioButton.js. The setChecked method did not utilize
// a second sendEvent parameter.
public class RadioButtonCheckedEventTest {
	public static void main(String[] args) {
		RadioButton rb1 = new RadioButton("Radio 1");
		rb1.setChecked(true);
		addListener(rb1);
		RadioButton rb2 = new RadioButton(rb1, "Radio 2");
		addListener(rb2);
		
		Frame f = Application.current().getFrame();
		f.getChildren().add(rb1.setBounds(10, 10, 100, 25));
		f.getChildren().add(rb2.setBounds(10, 40, 100, 25));
		rb2.setChecked(true); //This incorrectly triggers the propertyChange for each RadioButton to fire twice.
	}
	
	private static void addListener(final RadioButton rb) {
		rb.addPropertyChangeListener("checked", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent pce) {
				System.out.println("rb=" + rb.getText() + ", checked=" + pce.getNewValue());
			}
		});
	}
}
