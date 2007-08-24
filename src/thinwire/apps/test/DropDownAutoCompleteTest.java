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
import java.util.Arrays;

public class DropDownAutoCompleteTest {
	public static void main(String[] args) {
        final DropDownGridBox ddgb = new DropDownGridBox();
        Application.current().getFrame().getChildren().add(ddgb.setBounds(10, 10, 200, 25));

        final String[] values = new String[]{"Josh", "Jordan", "Jack", "Ted", "Tom", "Terry", "David", "Delwin", "Derik"};
        Arrays.sort(values);
        
        ddgb.addPropertyChangeListener(DropDownGridBox.PROPERTY_TEXT, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (ddgb.getComponent().getColumns().size() > 0) {
                    ddgb.getComponent().getColumns().clear();
                } else {
                    GridBox.Column col = new GridBox.Column();
                    
                    if (ev.getNewValue().toString().length() == 0) {
                        ddgb.getComponent().setVisible(false);
                        
                        for (String s : values) {
                            col.add(s);
                        }
                    } else {
                        String value = ((String)ev.getNewValue()).toLowerCase();
                        ddgb.getComponent().setVisible(true);
                        
                        for (String s : values) {
                            if (s.toLowerCase().startsWith(value)) col.add(s);
                        }
                    }
                    
                    ddgb.getComponent().getColumns().add(col);
                }
            }
        });
	}

}
