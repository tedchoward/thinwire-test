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
import thinwire.ui.style.*;

public class TabFolderTest {
	public static void main(String[] args) {
        Frame f = Application.current().getFrame();
        f.setLayout(new TableLayout(new double[][]{{100, 100, 100, 100}, {300, 25}}, 10, 5));
        final TabFolder tf = new TabFolder();
        f.getChildren().add(tf.setLimit("0, 0, 4, 1"));
        
        Color[] COLOR = new Color[]{Color.LIGHTCORAL, Color.LIGHTGRAY, Color.LIGHTSTEELBLUE, Color.LIGHTGREEN, Color.LIGHTYELLOW};
        
        for (int i = 0; i < 5; i++) {
            TabSheet ts = new TabSheet("Tab Sheet " + i);
            ts.getStyle().getBackground().setColor(COLOR[i]);
            tf.getChildren().add(ts);
        }
        
        final TextField tab = new TextField();
        f.getChildren().add(tab.setLimit("1, 1"));
        
        Button b = new Button("Toggle Visibility");
        f.getChildren().add(b.setLimit("2, 1"));
        
        b.addActionListener("click", new ActionListener() {
            private boolean visible = true;
            
            public void actionPerformed(ActionEvent ev) {
                if (tab.getText().length() == 0) {
                    visible = !visible;
                    
                    for (TabSheet ts : tf.getChildren()) {
                        if (ts != tf.getChildren().get(tf.getCurrentIndex())) {
                            ts.setVisible(visible);
                        }
                    }
                } else {
                    int value = Integer.parseInt(tab.getText());
                    TabSheet ts = tf.getChildren().get(value);
                    ts.setVisible(!ts.isVisible());
                }
            }
        });
        
    }
}
