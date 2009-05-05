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

public class BackgroundImageTest {
	public static void main(String[] args) {
        Frame f = Application.current().getFrame();
        TextField tf = new TextField();
        tf.setBounds(5, 5, 200, 25);
        tf.getStyle().getBackground().setImage("class:///" + BackgroundImageTest.class.getCanonicalName() + "/resources/BackgroundImage.png");
        tf.getStyle().getBackground().setRepeat(Background.Repeat.Y);
        tf.getStyle().getBackground().setPosition(Background.Position.LEFT_TOP);
        tf.getStyle().getBorder().setSize(1);
        tf.getStyle().getBorder().setType(Border.Type.SOLID);
        tf.getStyle().getBorder().setColor(Color.valueOf("rgb(45, 66, 98)"));
        f.getChildren().add(tf);
    }
}
