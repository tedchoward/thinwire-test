package thinwire.apps.test;

import thinwire.render.web.WebApplication;
import thinwire.ui.*;
import thinwire.ui.event.*;

public class SecurityRunInvalidEvents {
	public static void main(String[] args) {
		final WebApplication app = (WebApplication)Application.current();
		final Button disabledButton = new Button("Disabled Button");
		disabledButton.setEnabled(false);
		disabledButton.addActionListener("click", new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MessageBox.confirm("Ran disabled button handler, disabledButton.isEnabled()=" + disabledButton.isEnabled());
			}
		});
				
		final Menu menu = new Menu();
		final Menu.Item root = menu.getRootItem();
		
		for (int i = 0; i < 5; i++) {
			String text = "Root " + i;
			Menu.Item branch = new Menu.Item(text);
			root.getChildren().add(branch);
			
			for (int ii = 0; ii < 5; ii++) {
				branch.getChildren().add(new Menu.Item("Branch " + ii + " of " + text));
			}
		}
		
		root.getChildren().get(1).getChildren().get(2).setEnabled(false);
		
		menu.addActionListener("click", new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MessageBox.confirm("You clicked '" + ((Menu.Item)ev.getSource()).getText() + "', item.isEnabled()=" + ((Menu.Item)ev.getSource()).isEnabled());
			}
		});
		
		
		Hyperlink hlClientEnable = new Hyperlink("Toggle Client-Side Enable");
		hlClientEnable.addActionListener("click", new ActionListener() {
			private boolean state;
			
			public void actionPerformed(ActionEvent ev) {
				Integer id = app.getComponentId(disabledButton);
				state = state ? false : true;
				
				if (id != null) app.clientSideMethodCall(id, "setEnabled", state);
				id = app.getComponentId(menu);
				if (id != null) app.clientSideMethodCall(id, "itemSetEnabled", "1.2", state);
			}
		});
		
		Hyperlink hlServerEnable = new Hyperlink("Toggle Server-Side Enable");
		hlServerEnable.addActionListener("click", new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				disabledButton.setEnabled(!disabledButton.isEnabled());
				Menu.Item item = root.getChildren().get(1).getChildren().get(2);
				item.setEnabled(!item.isEnabled());
			}
		});
		
		Frame f = app.getFrame();
		f.getChildren().add(hlClientEnable.setBounds(10, 10, 150, 20));
		f.getChildren().add(hlServerEnable.setBounds(10, 30, 150, 20));
		f.getChildren().add(disabledButton.setBounds(10, 50, 150, 25));
		f.getChildren().add(menu.setBounds(10, 80, 300, 25));
	}
}
