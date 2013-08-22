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

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;

import thinwire.ui.Application;
import thinwire.ui.DropDownGridBox;
import thinwire.ui.GridBox;
import thinwire.ui.MessageBox;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;

public class Main { //extends thinwire.render.web.WebServlet {
	static final String RES_PATH = "class:///" + Main.class.getName() + "/resources/";
    
    static String getSimpleClassName(Class<?> type) {
        String text = type.getName();
        text = text.substring(text.lastIndexOf('.') + 1);
        return text;
    }	
	
	public static void main(String[] args) throws Exception {
        for (String arg : args) {
            System.out.println(arg);
        }
        
        GridBox.Column col = new GridBox.Column();
        col.add(BackgroundImageTest.class);
        col.add(BorderImageTest.class);
        col.add(DragAndDropTest.class);
        col.add(LocalizationTest.class);
        col.add(ModalDialogTest.class);
        col.add(ClientSideIncludeTest.class);
        col.add(TableLayoutTest.class);
        col.add(GridBoxRowSelectionTest.class);
        col.add(FileChooserTest.class);
        col.add(TextFieldTest.class);
        col.add(TextUpdateTest.class);
        col.add(LabelTest.class);
        col.add(DropDownTest.class);
        col.add(TabFolderTest.class);
        col.add(DualListenerTest.class);
        col.add(DualSubclassingTest.class);
        col.add(HyperLinkTest.class);
        col.add(TabFolderTabTransitionTest.class);
        col.add(ClientSideCallTest.class);
        col.add(ContainerSetTest.class);
        col.add(GridBoxPositionShiftTest.class);
        col.add(DropDownEmptyCPUTest.class);
        col.add(DropDownLoadTest.class);
        col.add(DropDownAutoCompleteTest.class);
        col.add(GridBoxDoubleRowTest.class);
        col.add(LayoutResizeTest.class);
        col.add(DialogModalLayerTest.class);
        col.add(ShutdownLockupTest.class);
        col.add(TableLayoutSizeTest.class);
        col.add(ScrollbarPositionUpdateTest.class);
        col.add(MenuOnDialogIssue.class);
        col.add(SecurityRunInvalidEvents.class);
        col.add(DropDownSortTest.class);
        col.add(RadioButtonCheckedEventTest.class);
        col.add(FocusLoopTest.class);
        col.add(ImageMaximizeTest.class);
        col.add(ApocalypseTest.class);
        col.add(TextAreaSelectionReplaceTest.class);
        col.add(GridBoxAddRemoveRowTest.class);
        
        Collections.sort(col, new Comparator<Object>() {
        	public int compare(Object c1, Object c2) {
        		return ((Class<?>)c1).getName().compareToIgnoreCase(((Class<?>)c2).getName());
        	}
        });

        final DropDownGridBox ddgb = (DropDownGridBox)new DropDownGridBox().setSize(300, 25);
        ddgb.setEditAllowed(false);
        ddgb.getComponent().getColumns().add(col);
        
        
        MessageBox.confirm(null, Application.getPlatformVersionInfo().get("productVersion") + ": Select A Test to Run", ddgb, null, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				GridBox.Row row = null;
				
				if (((Integer) ev.getSource()) == 0) {
					if (ddgb.getText().length() > 0) {
		                row = ddgb.getComponent().getSelectedRow();

		                if (row != null) {
			                Class<?> clazz = (Class<?>)row.get(0);
			                try {
								clazz.getMethod("main", new Class[] { String[].class }).invoke(clazz, new Object[] { new String[] {} });
							} catch (IllegalAccessException
									| IllegalArgumentException
									| InvocationTargetException
									| NoSuchMethodException | SecurityException e) {
								if (e instanceof RuntimeException) throw (RuntimeException)e;
								throw new RuntimeException(e);
							}
		                }
		            }
				}
				
				if (row == null) {
		            MessageBox.confirm("You did not select a test!", new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ev) {
							Application.current().getFrame().setVisible(false);
						}
		            });
		        }
			}
        });
        
                
                
    }
}
