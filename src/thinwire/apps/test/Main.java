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

import java.util.Arrays;

import thinwire.ui.*;
import thinwire.ui.event.*;
import thinwire.ui.layout.*;

public class Main {
    public static void main(String[] args) throws Exception {
        for (String arg : args) {
            System.out.println(arg);
        }
        
        GridBox.Column col = new GridBox.Column();
        //col.add(AdminTest.class);
        col.add(BackgroundImage.class);
        col.add(BorderImage.class);
        col.add(DragAndDrop.class);
        col.add(Localization.class);
        col.add(ModalDialogTest.class);
        col.add(TableLayoutTest.class);
        col.add(GridBoxRowSelection.class);
        col.add(FileChooserTest.class);
        col.add(TextFieldTest.class);
        col.add(LabelTest.class);
        //col.add(GridSortTest.class);
        col.add(DropDownTest.class);
        col.add(TabFolderTest.class);
        col.add(DualListenerBug.class);
        col.add(DualSubclassingBug.class);
        col.add(TabFolderTabTransition.class);
        col.add(ClientSideCall.class);
        col.add(ContainerSetTest.class);
        col.add(GridBoxPositionShiftTest.class);
        col.add(DropDownEmptyCPUTest.class);
        col.add(DropDownLoadTest.class);

        DropDownGridBox ddgb = (DropDownGridBox)new DropDownGridBox().setSize(300, 25);
        ddgb.setEditAllowed(false);
        ddgb.getComponent().getColumns().add(col);
        GridBox.Row row = null;
        
        if (MessageBox.confirm(null, Application.getPlatformVersionInfo().get("productVersion") + ": Select A Test To Run", ddgb, null) == 0) {
            if (ddgb.getText().length() > 0) {
                row = ddgb.getComponent().getSelectedRow();
                if (row != null) ((UITest)(((Class)row.get(0)).newInstance())).run();
            }
        }
        
        System.out.println("CONTINUING EXECUTION!!!");
        
        if (row == null) {
            //MessageBox.confirm("You did not select a test!");
            Application.current().getFrame().setVisible(false);
        }
        
        //new MemoryTest().run();
        
        //new GridBoxRowSelection().run();
        
        
        /*final DropDownGridBox ddgb = new DropDownGridBox();
        Application.current().getFrame().getChildren().add(ddgb.setBounds(10, 10, 200, 25));

        final String[] values = new String[]{"Josh", "Jordan", "Jack", "Ted", "Tom", "Terry", "David", "Delwin", "Derik"};
        Arrays.sort(values);
        
        ddgb.addPropertyChangeListener(DropDownGridBox.PROPERTY_TEXT, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent ev) {
                if (ddgb.getComponent().getColumns().size() > 0) {
                    ddgb.getComponent().getColumns().clear();
                } else {
                    GridBox.Column col = new GridBox.Column();
                    
                    if (ev.getNewValue().toString().length() == 0) {
                        ddgb.getComponent().setVisible(false);
                        
                        for (String s : values) {
                            col.add(s);
                        }
                    } else {
                        String value = ((String)ev.getNewValue()).toLowerCase();
                        ddgb.getComponent().setVisible(true);
                        
                        for (String s : values) {
                            if (s.toLowerCase().startsWith(value)) col.add(s);
                        }
                    }
                    
                    ddgb.getComponent().getColumns().add(col);
                }
            }
        });*/
        
    }
}
