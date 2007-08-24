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
import thinwire.ui.style.*;

public class LabelTest {
	public static void main(String[] args) {
		Dialog dlg = new Dialog("Label Test");
        dlg.setBounds(10, 10, 600, 400);
        Label lbl = new Label("<font bold='true'>Introduction</font><br/><font italic='true'>    A. Point   1</font><br />  <font italic='true'>B. Point 1</font>");
        lbl.setWrapText(true);
        lbl.setBounds(10, 10, 400, 100);
        Border b = lbl.getStyle().getBorder();
        b.setColor(Color.BLACK);
        b.setSize(1);
        b.setType(Border.Type.SOLID);
        dlg.getChildren().add(lbl);
        
        Button btn = new Button("<font bold='true'>Introduction</font><ol><li><font italic='true'>Point 1</font></li><li><font italic='true'>Point 1</font></li></ol>");
        btn.setBounds(10, 120, 400, 100);
        dlg.getChildren().add(btn);
        dlg.setVisible(true);
    }

}
