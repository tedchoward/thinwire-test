package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.event.*;
import thinwire.ui.layout.*;

public class HyperLinkTest {
	public static void main(String[] args) {
		Label lblLocation = new Label("Location:"); 
		final TextField location = new TextField("http://www.google.com");
		Label lblTarget = new Label("Target:"); 
		final TextField target = new TextField("myBrowser");
		final CheckBox visibleChrome = new CheckBox("Visible Chrome");
		final CheckBox resizeAllowed = new CheckBox("Resize Allowed");
		
		Button bOpenLoc = new Button("Hyperlink.openLocation()");
		
		bOpenLoc.addActionListener("click", new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Hyperlink.openLocation(location.getText(), target.getText(), visibleChrome.isChecked(), resizeAllowed.isChecked());
			}
		});
		
		Button bChangeLink = new Button("Set Hyperlink Property");
		final Hyperlink hl = new Hyperlink("Click Me to Launch!");
		
		bChangeLink.addActionListener("click", new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				hl.setLocation(location.getText());
				hl.setTarget(target.getText());
				hl.setVisibleChrome(visibleChrome.isChecked());
				hl.setResizeAllowed(resizeAllowed.isChecked());
			}
		});
		bChangeLink.fireAction("click");
		
		Frame f = Application.current().getFrame();
		f.setLayout(new TableLayout(new double[][]{{0,150,150,0},{0,25,25,25,25,25,0}}));
		f.getChildren().add(lblLocation.setLimit("1,1"));
		f.getChildren().add(location.setLimit("2,1"));
		f.getChildren().add(lblTarget.setLimit("1,+1"));
		f.getChildren().add(target.setLimit("2,+0"));
		f.getChildren().add(visibleChrome.setLimit("1,+1"));
		f.getChildren().add(resizeAllowed.setLimit("2,+0"));
		f.getChildren().add(hl.setLimit("1,+1,2,1"));
		f.getChildren().add(bChangeLink.setLimit("1,+1"));
		f.getChildren().add(bOpenLoc.setLimit("2,+0"));
	}
}
