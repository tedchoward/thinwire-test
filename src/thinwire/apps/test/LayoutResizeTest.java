package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.layout.*;

//Recreates issue "1759577 - scrollbars don't update correctly in Firefox"
//Confirmed to happen in RC1 as well.
public class LayoutResizeTest {
	public static void main(String[] args) {
		Frame f = Application.current().getFrame();
		f.setScrollType(Frame.ScrollType.AS_NEEDED);
				
		f.setLayout(new TableLayout(new double[][]{{0},{0,15}}));
		Panel p1 = new Panel();
		f.getChildren().add(p1.setLimit("0, 1"));

		Panel p2 = new Panel();
		f.getChildren().add(p2.setLimit("0, 0"));
	}
}
