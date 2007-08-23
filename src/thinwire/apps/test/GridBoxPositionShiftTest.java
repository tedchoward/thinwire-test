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
import thinwire.ui.layout.*;
import thinwire.ui.style.Color;

public class GridBoxPositionShiftTest implements UITest {
    public void run() {
        final Frame frame = Application.current().getFrame();
        frame.setTitle("Grid Test");

        Panel rightPanel = new Panel();
        rightPanel.setLayout(new TableLayout(new double[][] { { 100, 0, 100 }, { 20, 25, 20, 0, 20 } }));

        GridBox table = new GridBox();
        table.setVisibleCheckBoxes(false);
        table.setVisibleHeader(true);

        int numCols = 20;
        GridBox.Column col = null;
        for (int cols = 0; cols < numCols; cols++) {
            table.getColumns().add(col = new GridBox.Column());
            col.setName("Col " + cols);
            col.setWidth(100);
        }

        for (int row = 0; row < 50; row++) {
            Object[] data = new Object[numCols];
            for (int c = 0; c < numCols; c++) {
                data[c] = row;
            }

            table.getRows().add(new GridBox.Row(data));
        }
        table.setLimit("1,3,1,1");
        rightPanel.getChildren().add(table);

        Panel mainPanel = new Panel();
        mainPanel.setBounds(0, 0, 800, 600);
        //mainPanel.getStyle().getBorder().setImage("class:///thinwire.tests.BackgroundImage/resources/BorderImage2.png");
        mainPanel.getStyle().getBorder().setSize(10);
        mainPanel.getStyle().getBackground().setColor(Color.TRANSPARENT);
        mainPanel.setLayout(new TableLayout(new double[][] { { 10, 60, 0, 10 }, { 5, 60, 5, 20, 0, 10 } }));

        Panel temp = new Panel();
        temp.setLayout(new SplitLayout(250, true));
        Panel leftPanel = new Panel();
        leftPanel.getStyle().getBackground().setColor(Color.AZURE);
        temp.getChildren().add(leftPanel);
        temp.getChildren().add(rightPanel);

        frame.setLayout(new TableLayout(new double[][] { { 10, 0, 10 }, { 10, 0, 10 } }));
        mainPanel.getChildren().add(temp.setLimit("2,4,1,1"));
        frame.getChildren().add(mainPanel.setLimit("1,1,1,1"));
        frame.setVisible(true);
    }
}
