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
import thinwire.ui.layout.*;

public class FileChooserTest {

	public static void main(String[] args) {
		Dialog dlg = new Dialog("FileChooserTest");
		dlg.setBounds(10, 10, 300, 200);
		dlg.setLayout(new TableLayout(new double[][] {{0, 75}, {20, 0, 30}}, 5, 5));
		final FileChooser fc = new FileChooser();
		dlg.getChildren().add(fc.setLimit("0, 0, 2, 1"));
		Button ok = new Button("Ok");
		dlg.getChildren().add(ok.setLimit("1, 2"));
		ok.addActionListener(Button.ACTION_CLICK, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				FileChooser.FileInfo fi = fc.getFileInfo();
				MessageBox.confirm(fi.getName());
			}
		});
		
		dlg.setVisible(true);
	}

}
