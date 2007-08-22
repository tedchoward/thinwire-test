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
package thinwire.tests;

import thinwire.ui.*;

public class TabFolderTabTransition implements UITest {
    public void run() {
        TabFolder tf = new TabFolder();
        tf.setBounds(10, 10, 400, 300);
        
        TabSheet ts1 = new TabSheet("Tab 1");
        TabSheet ts2 = new TabSheet("Tab 2");
        
        tf.getChildren().add(ts1);
        tf.getChildren().add(ts2);
        
        ts1.getChildren().add(new TextField().setBounds(10, 10, 100, 20));
        ts2.getChildren().add(new TextField().setBounds(10, 10, 100, 20));
        
        Application.current().getFrame().getChildren().add(tf);
    }
}
