/*
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
import thinwire.ui.layout.*;

public class ModalDialogTest {
	public static void main(String[] args) {
		new ModalDialogTest();
	}
	
	public ModalDialogTest() {
		final Dialog dlg1 = createModalDialog("Dialog 1", 10, 10, 300, 200);
		final Dialog dlg2 = createModalDialog("Dialog 2", 100, 100, 300, 200);
		dlg2.setLayout(new TableLayout(new double[][] {{0}, {0}}));
		dlg2.getChildren().add(new TextArea().setLimit("0, 0"));
		final Dialog dlg3 = createModalDialog("Dialog 3", 200, 200, 300, 200);
		
		Application.current().addTimerTask(new Runnable() {
			public void run() {
				dlg1.setVisible(false);
				dlg3.setVisible(true);
			}
		}, 500);
		dlg1.setVisible(true);
		dlg2.setVisible(true);
	}
	
	private Dialog createModalDialog(String name, int x, int y, int width, int height) {
		Dialog dlg = (Dialog) new Dialog(name).setBounds(x, y, width, height);
		dlg.setModal(true);
		return dlg;
	}

}
