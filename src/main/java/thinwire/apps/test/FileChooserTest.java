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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import thinwire.ui.*;
import thinwire.ui.event.*;
import thinwire.ui.layout.*;

public class FileChooserTest {

	public static void main(String[] args) {
		Dialog dlg = new Dialog("FileChooserTest and Print Selected Text file");
		dlg.setBounds(10, 10, 300, 200);
		dlg.setLayout(new TableLayout(new double[][] {{0, 75}, {20, 0, 30}}, 5, 5));
		final FileChooser fc = new FileChooser();
		dlg.getChildren().add(fc.setLimit("0, 0, 2, 1"));
		Button ok = new Button("Ok");
		dlg.getChildren().add(ok.setLimit("1, 2"));
		ok.addActionListener(Button.ACTION_CLICK, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				FileChooser.FileInfo fi = fc.getFileInfo();
//                MessageBox.confirm(fi.getName());
				MessageBox.show(fi.getName());
                InputStream inputStream = fi.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(isr);
                String line;
                try {
                    line = br.readLine();

                    while (line != null) {
                        System.out.println(line);
                        line = br.readLine();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FileChooserTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
		});
		
		dlg.setVisible(true);
	}

}
