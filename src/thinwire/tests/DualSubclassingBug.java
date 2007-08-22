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

public class DualSubclassingBug implements UITest {
    public void run() {
        //new DialogSubB().setVisible(true);
        new DoubleInheritanceBugDemo();
    }
}

class DialogSubA extends Dialog {

}

class DialogSubB extends DialogSubA {
    public DialogSubB() {
        Label label = new Label("Hello there");
        label.setBounds(5, 5, 200, 25);
        TextField input = new TextField();
        input.setBounds(5, 35, 200, 25);
        Button button = new Button("Ok");
        button.setBounds(55, 65, 100, 25);

        getChildren().add(label);
        getChildren().add(input);
        getChildren().add(button);
    }
}

class DoubleInheritanceBugDemo {
    abstract class MyButton extends Button {
        public MyButton(String text) {
            super(text);
        }
    }

    class MyMyButton extends MyButton {
        public MyMyButton() {
            super("MyMyButton");
        }
    }

    public DoubleInheritanceBugDemo() {
        Dialog dlg = new Dialog();
        dlg.setModal(true);
        dlg.setWaitForWindow(true);
        dlg.setBounds(100, 100, 200, 200);
        Button bt = new MyMyButton();
        bt.setBounds(10, 60, 100, 40);
        dlg.getChildren().add(bt);
        dlg.setVisible(true);
        if (!dlg.isVisible()) dlg.setVisible(true);
    }
}
