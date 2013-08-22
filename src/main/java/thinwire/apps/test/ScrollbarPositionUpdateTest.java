package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.layout.TableLayout;

//Test to recreate: 1847785 - Scrollbars position doesn't update on frame resize
//Result: Works for Me in IE6 under Windows XP & FF2 under OSX 10.5
public class ScrollbarPositionUpdateTest {
	public static void main(String[] args) {
		TabSheet tab1 = new TabSheet("Tab 1");
		tab1.setLayout(new TableLayout(new double[][]{{0},{0}}, 10));
		tab1.getChildren().add(new TextArea());

		TabSheet tab2 = new TabSheet("Tab 2");
		tab2.setLayout(new TableLayout(new double[][]{{0},{0}}, 10));
		GridBox gb = new GridBox();
		GridBox.Column gbc = new GridBox.Column("Column 1", true, 600, new Object[]{});
		for (int i = 4; --i >= 0;) gbc.add("value " + i);
		gb.getColumns().add(gbc);
		tab2.getChildren().add(gb);
		
		TabSheet tab3 = new TabSheet("Tab 3");
		tab3.setLayout(new TableLayout(new double[][]{{0},{0}}, 10));
		tab3.getChildren().add(new TextArea());
		
		TabFolder tf = new TabFolder();
		tf.getChildren().add(tab1);
		tf.getChildren().add(tab2);
		tf.getChildren().add(tab3);
		
		Frame f = Application.current().getFrame();
		f.setLayout(new TableLayout(new double[][]{{0},{0}}, 100));
		f.getChildren().add(tf);
	}
}
