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
import thinwire.ui.event.*;

public class DialogModalLayerTest {
	public static void main2(String[] args) {
		final Dialog d2 = new Dialog("Second Dialog");
		d2.setBounds(200, 200, 320, 200);

		Dialog d1 = new Dialog("First Dialog");
		d1.setBounds(100, 100, 640, 480);
		Label l = new Label(
				"To recreate:<br/>" + 
				"1) Click 'Show Second Dialog' button<br/>" +
				"2) Close second dialog with 'X' button<br/>" +
				"3) Click frame's white background<br/>" +
				"4) Click 'Show Second Dialog' button again<br/>" + 
				"5) Close second dialog with 'X' button<br/>" + 
				"6) Click frame's white background<br/>" +
				"7) MODAL LAYER IS NOW LOCKED!");
		l.setWrapText(true);
		
		Button b = new Button("Show Second Dialog");
		b.addActionListener(Button.ACTION_CLICK, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				d2.setVisible(true);
			}
		});
		
		d1.getChildren().add(l.setBounds(10, 10, 400, 300));
		d1.getChildren().add(b.setBounds(10, 350, 100, 30));
		d1.setVisible(true);
	}
	
	public static void main(String[] args) {
		createDialog(50, 1);
	}
	
	//Written in response to: https://sourceforge.net/forum/message.php?msg_id=4634027
	private static void createDialog(final int pos, final int number) {
		Dialog d = new Dialog("Dialog " + number);
		d.setBounds(pos, pos, 320, 200);
		Button b = new Button("Next Dialog");
		
		b.addActionListener("click", new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				createDialog(pos + 50, number + 1);
			}
		});
		
		d.getChildren().add(b.setBounds(100, 100, 100, 25));
		d.setVisible(true);
	}
}
