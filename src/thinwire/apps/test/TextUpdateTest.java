package thinwire.apps.test;

import java.io.File;

import com.sun.org.apache.xml.internal.utils.URI;
import com.sun.org.apache.xml.internal.utils.URI.MalformedURIException;

import thinwire.ui.*;
import thinwire.ui.event.*;

public class TextUpdateTest {
	//A test for 1592200 - TextField: bad content on "Enter" keyPressListener
	//Also tests other data-update related issues
	public static void main(String[] args) {
		final TextField tf = new TextField();
		tf.setEditMask("###,###.##");
		Frame f = Application.current().getFrame();
		f.getChildren().add(tf.setBounds(100, 100, 150, 25));
		
		tf.addKeyPressListener("Enter", new KeyPressListener() {
			public void keyPress(KeyPressEvent ev) {
				MessageBox.confirm("Enter Key Text field value is:" + tf.getText());
			}
		});
		
		tf.addPropertyChangeListener("focus", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent ev) {
				if (ev.getNewValue() == Boolean.TRUE) return;
				MessageBox.confirm("Lose Focus Text field value is:" + tf.getText());
			}
		});
		
		Button b = new Button("Other Focus Target");
		f.getChildren().add(b.setBounds(100, 150, 150, 25));
	}
}
