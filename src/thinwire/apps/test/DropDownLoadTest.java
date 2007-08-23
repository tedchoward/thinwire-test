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

import thinwire.render.web.*;
import thinwire.ui.*;
import thinwire.ui.layout.*;

public class DropDownLoadTest implements UITest {
    public void run() {
        WebApplication app = (WebApplication)Application.current();
        Frame f = app.getFrame();
        TableLayout tl = new TableLayout();
        int ddNum = 0;
        
        long before = Long.parseLong(app.clientSideFunctionCallWaitForReturn("tw_getTime"));
        for (int ci = 0; ci < 7; ci++) {
            tl.getColumns().add(new TableLayout.Column(0));

            for (int ri = 0; ri < 10; ri++) {
                if (ci == 0) tl.getRows().add(new TableLayout.Row(0));
                ddNum++;
                DropDownGridBox ddgb = newDropDown(ddNum);                
                f.getChildren().add(ddgb.setLimit(ci + "," + ri));
            }
        }
        long after = Long.parseLong(app.clientSideFunctionCallWaitForReturn("tw_getTime"));
        System.out.println("time taken=" + (after - before));
        f.setLayout(tl);
    }
    
    public DropDownGridBox newDropDown(int ddNum) {
        DropDownGridBox ddgb = new DropDownGridBox();
        GridBox gb = ddgb.getComponent();
        
        for (int i = 0, cnt = 70; i < cnt; i++) {
            gb.getRows().add(new GridBox.Row("DD " + ddNum + " Option " + cnt));
        }
        
        return ddgb;
    }
}
