package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.layout.TableLayout;

//Test for: 1755335 - #weird Horizontal Scrollbar behavior
//Resolution: Works for Me
public class ImageMaximizeTest {
	public static void main(String[] args) {
		Image img = new Image(Main.RES_PATH + "ayb.png");
		img.setSize(640, 480);
		Panel p = new Panel();
		//p.setLayout(new TableLayout(new double[][]{{0},{0}}));
		p.getChildren().add(img);
		p.setScrollType(Panel.ScrollType.AS_NEEDED);
		
		Frame f = Application.current().getFrame();
		f.setLayout(new TableLayout(new double[][]{{0},{0}}));
		f.getChildren().add(p);
	}
}
