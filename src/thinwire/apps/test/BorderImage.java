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
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;
import thinwire.ui.style.*;

public class BorderImage {
	public static void main(String[] args) {
        Frame f = Application.current().getFrame();
        f.getStyle().getBackground().setColor(Color.APPWORKSPACE);
        final Panel p = new Panel();
        p.setBounds(100, 100, 400, 300);
        //p.getStyle().getFX().setSizeChange(FX.Type.SMOOTH);
        //p.getStyle().getFX().setPositionChange(FX.Type.SMOOTH);
        p.getStyle().getBackground().setColor(Color.LIGHTBLUE);
        p.getStyle().getBorder().setSize(28);
        p.getStyle().getBorder().setImage("class:///thinwire.tests.BackgroundImage/resources/BorderImage1.png");
        /*p.getStyle().getBackground().setColor(Color.TRANSPARENT);
        p.getStyle().getBorder().setSize(10);
        p.getStyle().getBorder().setImage("class:///thinwire.tests.BackgroundImage/resources/BorderImage2.png");*/
        f.getChildren().add(p);
        
        Label lbl = new Label("Enter Your Name:");
        lbl.getStyle().getFont().setColor(Color.WHITESMOKE);
        lbl.setBounds(5, 20, 100, 25);
        TextField tf = new TextField("");
        //tf.getStyle().getBorder().setSize(10);
        //tf.getStyle().getBorder().setImage("class:///thinwire.tests.BackgroundImage/resources/BorderImage2.png");
        tf.setBounds(110, 20, 100, 25);
        
        Button btn = new Button("Change Size");
        btn.setBounds(5, 50, 100, 25);
        btn.addActionListener("click", new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                p.setWidth(p.getWidth() + 100);
                p.setHeight(p.getHeight() + 100);
                p.setX(p.getX() + 100);
                p.setY(p.getY() + 100);
            }
        });
        
        p.getChildren().add(lbl);
        p.getChildren().add(tf);
        p.getChildren().add(btn);
    }
}
