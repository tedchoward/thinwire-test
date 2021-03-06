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
import thinwire.ui.layout.*;
import thinwire.ui.style.Color;

public class TextFieldTest {

	public static void main(String[] args) {
        Dialog dlg = new Dialog("TextField Test");
        dlg.setBounds(10, 10, 600, 400);
        dlg.setLayout(new TableLayout(new double[][] {{1, 0, 1}, {0, 20, 0}}, 0, 5));
        TextField tf = new TextField();
        tf.setLimit("1, 1");
        tf.setAlignX(TextField.AlignX.RIGHT);
        //tf.getStyle().getBorder().setSize(1);
        dlg.getStyle().getBackground().setColor(Color.BLACK);
        dlg.getChildren().add(tf);
        dlg.setVisible(true);
    }

}
