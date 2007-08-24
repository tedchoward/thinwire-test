/*
                          ThinWire(R) Framework Tests
                 Copyright (C) 2006-2007 Custom Credit Systems

	This library is free software; you can redistribute it and/or modify it under
	the terms of the GNU Lesser General Public License as published by the Free
	Software Foundation; either version 2.1 of the License, or (at your option) any
	later version.

	This library is distributed in the hope that it will be useful, but WITHOUT ANY
	WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
	PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

	You should have received a copy of the GNU Lesser General Public License along
	with this library; if not, write to the Free Software Foundation, Inc., 59
	Temple Place, Suite 330, Boston, MA 02111-1307 USA

 */
package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.render.web.*;

public class ApocalypseTest {
	private static final int COLUMNS = 8;
	private static final int ROWS = 25;
	private static final int ROW_WIDTH = 100;
	private static final int COLUMN_HEIGHT = 100;
	private static final int COMP_COUNT = ROWS * COLUMNS;
	private static final int SLEEP_DELAY = 2000;
	private static final Class<? extends Component>[] TYPES = new Class[] {
		Label.class,
		Image.class,
		Divider.class,
		Button.class,
		CheckBox.class,
		RadioButton.class,
		Hyperlink.class,
		TextField.class,
		TextArea.class,
		DateBox.class,
		DropDownDateBox.class,
		DropDownGridBox.class,
		GridBox.class,
		Tree.class,
		Menu.class,
		WebBrowser.class,
		TabFolder.class,
		Panel.class,
		Slider.class,
		ProgressBar.class,
		//FileChooser.class
	};
	
	public static void main(String[] args) throws Exception {
		new ApocalypseTest();
	}
	
	private WebApplication app;
	private Frame f;
	
	ApocalypseTest() throws Exception {
		app = (WebApplication)Application.current();
		f = app.getFrame();
		f.setScrollType(Frame.ScrollType.AS_NEEDED);
		
		for (Class<? extends Component> type : TYPES) {
			String typeName = Main.getSimpleClassName(type);
			long time = testRenderComponent(type);
			System.out.println("Rendered " + COMP_COUNT + " " + typeName + "s in " + time + "ms");
			time = testDestroyComponent();
			Thread.sleep(500);
			System.out.println("Destroyed " + COMP_COUNT + " " + typeName + "s in " + time + "ms");
			System.out.println("Sleeping for " + SLEEP_DELAY + "ms");
			Thread.sleep(SLEEP_DELAY);
		}
	}
	
	long testRenderComponent(Class<? extends Component> clazz) {
		long beforeTime = Long.parseLong(app.clientSideFunctionCallWaitForReturn("tw_getTime"));
		
		for (int ri = 0; ri < ROWS; ri++) {
			for (int ci = 0; ci < COLUMNS; ci++) {
				Component comp = ComponentFactory.newInstance(clazz);
				comp.setBounds(ci * COLUMN_HEIGHT, ri * ROW_WIDTH, COLUMN_HEIGHT, ROW_WIDTH);
				f.getChildren().add(comp);
			}
		}

		long afterTime = Long.parseLong(app.clientSideFunctionCallWaitForReturn("tw_getTime"));
		return afterTime - beforeTime;
	}
	
	long testDestroyComponent() {
		long beforeTime = Long.parseLong(app.clientSideFunctionCallWaitForReturn("tw_getTime"));
		f.getChildren().clear();
		long afterTime = Long.parseLong(app.clientSideFunctionCallWaitForReturn("tw_getTime"));
		return afterTime - beforeTime;
	}
}
