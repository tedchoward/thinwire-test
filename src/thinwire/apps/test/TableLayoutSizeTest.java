package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.layout.TableLayout;
import thinwire.ui.style.Color;

public class TableLayoutSizeTest {
	//http://sourceforge.net/forum/message.php?msg_id=4642076
	public static void main(String[] args) {
		Frame frame = Application.current().getFrame();
		frame.setLayout(new TableLayout(
				new double[][] {
						{ 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
								0, 0 } }, 0, 0));

		for (int i = 0; i < 20; i++) {
			Panel panel = new Panel();
			panel.setLimit("1," + i);
			panel.getStyle().getBorder().setSize(2);
			panel.getStyle().getBorder().setColor(Color.BLACK);
			frame.getChildren().add(panel);
		}
	}

}
