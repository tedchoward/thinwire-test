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

import java.util.List;

import thinwire.ui.*;
import thinwire.ui.layout.*;
import thinwire.ui.event.*;

public class GridBoxRowSelectionTest {
	public static void main(String[] args) {
		Application app = Application.current();
        Frame f = app.getFrame();

        final GridBox gb = new GridBox();
        gb.setVisibleHeader(true);
        gb.getColumns().add(new GridBox.Column());
        gb.addPropertyChangeListener(GridBox.Row.PROPERTY_ROW_SELECTED, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                System.out.println("PropertyChangeEvent(" + ev.getPropertyName() + "," + ((GridBox.Row)ev.getSource()).getIndex() + "," + ev.getOldValue() + "," + ev.getNewValue() + ")");
            }
        });
        
        final CheckBox visibleCheckBoxes = new CheckBox("CheckBoxes");
        visibleCheckBoxes.addPropertyChangeListener(CheckBox.PROPERTY_CHECKED, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                gb.setVisibleCheckBoxes(visibleCheckBoxes.isChecked());
            }
        });
        
        final CheckBox selectRow = new CheckBox("Select row");
        final CheckBox checkRow = new CheckBox("Check row");
        final TextField rowRange = new TextField();
        
        Button addRow = new Button("Add row");
        addRow.addActionListener(Button.ACTION_CLICK, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String count = rowRange.getText().trim();
                
                int cnt = count.length() == 0 ? 1 : Integer.parseInt(count);
                List<GridBox.Row> rows = gb.getRows();
                
                for (int i = 0; i < cnt; i++) {
                    GridBox.Row row = new GridBox.Row("+Row " + rows.size());
                    if (selectRow.isChecked()) row.setSelected(true);
                    if (checkRow.isChecked()) row.setChecked(true);
                    rows.add(row);
                }
                
                System.out.println("gb.getCheckedRows().size()=" + gb.getCheckedRows().size());
            }
        });

        Button setRow = new Button("Set row");
        setRow.addActionListener(Button.ACTION_CLICK, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String index = rowRange.getText().trim();
                int idx = index.length() == 0 ? gb.getSelectedRow().getIndex() : Integer.parseInt(index);
                GridBox.Row row = new GridBox.Row(gb.getRows().get(idx).get(0).toString().replace('+', '='));
                if (selectRow.isChecked()) row.setSelected(true);
                if (checkRow.isChecked()) row.setChecked(true);
                gb.getRows().set(idx, row);
                System.out.println("gb.getCheckedRows().size()=" + gb.getCheckedRows().size());
            }
        });

        Button removeRow = new Button("Remove row");
        removeRow.addActionListener(Button.ACTION_CLICK, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String index = rowRange.getText().trim();
                gb.getRows().remove(index.length() == 0 ? gb.getSelectedRow().getIndex() : Integer.parseInt(index));
                System.out.println("gb.getCheckedRows().size()=" + gb.getCheckedRows().size());
            }
        });
        
        Button clear = new Button("Clear rows");
        clear.addActionListener(Button.ACTION_CLICK, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                gb.getRows().clear();
                System.out.println("gb.getCheckedRows().size()=" + gb.getCheckedRows().size());
            }
        });
        
        f.setLayout(new TableLayout(new double[][]{{100, 100, 100, 100}, {300, 25, 25}}, 10, 5));
        f.getChildren().add(gb.setLimit("0, 0, 4, 1"));
        f.getChildren().add(visibleCheckBoxes.setLimit("0, 1"));
        f.getChildren().add(selectRow.setLimit("1, 1"));
        f.getChildren().add(checkRow.setLimit("2, 1"));
        f.getChildren().add(rowRange.setLimit("3, 1"));
        f.getChildren().add(addRow.setLimit("0, 2"));
        f.getChildren().add(setRow.setLimit("1, 2"));
        f.getChildren().add(removeRow.setLimit("2, 2"));
        f.getChildren().add(clear.setLimit("3, 2"));
    }
}
