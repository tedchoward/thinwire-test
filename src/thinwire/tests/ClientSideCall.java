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

import java.util.Date;

import thinwire.render.web.WebApplication;
import thinwire.ui.*;
import thinwire.ui.event.*;
import thinwire.ui.layout.TableLayout;

public class ClientSideCall implements UITest {
    public void run() {
        final WebApplication app = (WebApplication)Application.current();
        Frame f = app.getFrame();
        f.setLayout(new TableLayout(new double[][]{{100, 100, 100, 0},{200, 30, 0}}, 5, 5));
        
        final TextArea ta = new TextArea();
        f.getChildren().add(ta.setLimit("0, 0, 3, 1"));
        
        Button b = new Button("Run");
        f.getChildren().add(b.setLimit("0, 1"));
        
        b.addActionListener("click", new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                StringBuilder sb = new StringBuilder();
                sb.append("Start time: " + new Date(System.currentTimeMillis()));
                sb.append('\n');
                String value = app.clientSideFunctionCallWaitForReturn("tw_getTime");
                sb.append("Return time: " + new Date(System.currentTimeMillis()));
                sb.append('\n');
                sb.append("Value: '" + value + "'");
                ta.setText(sb.toString());
            }
        });
    }
}
