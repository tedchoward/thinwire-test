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
import thinwire.ui.event.*;

public class Localization implements UITest {
    public void run() {
        Frame f = Application.current().getFrame();
        final TextArea ta = new TextArea();
        ta.setText("\u00E4 \u00E5");
        ta.setBounds(5, 5, 200, 300);
        Button b = new Button("Ok");
        b.setBounds(5, ta.getHeight() + ta.getY() + 5, 100, 25);
        f.getChildren().add(ta);
        f.getChildren().add(b);
        
        b.addActionListener("click", new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String value = ta.getText();
                System.out.println(value);
                ta.setText("");
                
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                
                ta.setText(value);
            }
        });
    }
}
