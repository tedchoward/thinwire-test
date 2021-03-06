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

import java.util.logging.Logger;

import thinwire.ui.Application;
import thinwire.ui.Button;
import thinwire.ui.Component;
import thinwire.ui.Container;
import thinwire.ui.Dialog;
import thinwire.ui.Frame;
import thinwire.ui.MessageBox;
import thinwire.ui.Panel;
import thinwire.ui.TextComponent;
import thinwire.ui.TextField;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;
import thinwire.ui.layout.TableLayout;
import thinwire.ui.style.Color;

public class TableLayoutTest {
    private static final Logger log = Logger.getLogger(TableLayoutTest.class.getName());
    
    //TODO: rewrite with better UI
    public static void main(String[] args) {
        Frame f = Application.current().getFrame();
        f.setTitle(TableLayoutTest.class.getName());
        
        MessageBox.confirm(null, null, "Create TableLayout example in Frame, Dialog or Panel?", "Frame|Dialog|Panel", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				int containerType = (Integer) ev.getSource();
				
				final Container<Component> container;
		        
		        if (containerType == 1) {
		            Dialog diag = new Dialog("Simple Test");
		            diag.setBounds(100, 100, 300, 300);
		            diag.setResizeAllowed(true);
		            //diag.setWaitForWindow(true);
		            container = diag;
		        } else if (containerType == 0) {
		            container = Application.current().getFrame();
		        } else {
		            container = new Panel();
		        }
		        
		        MessageBox.confirm(null, null, "Which TableLayout test would you like to run?", "Simple|Complex|Grid", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ev) {
						
						switch ((Integer) ev.getSource()) {
			            	case 1: complex(container); break;
			            	case 2: grid(container); break;
			            	default: simple(container); break;
						}
			        
				        if (container instanceof Dialog) {
				            container.getStyle().getBackground().setColor(Color.WINDOW);
				            ((Dialog)container).setVisible(true);
				        } else if (container instanceof Panel) {
				            Application.current().getFrame().getChildren().add(container.setBounds(5, 5, 400, 300));
				        }
				        
				        for (Component comp : container.getChildren()) {
				            String s = "class=" + comp.getClass();
				            if (comp instanceof TextComponent) s += ",text=" + ((TextComponent)comp).getText();
				            s += ",limit=" + comp.getLimit();
				            System.out.println(s);
				        }
					}
		        });
		        
		        
			}
        });
        
        
    }
    
    static void simple(Container<Component> container) {
        container.setLayout(new TableLayout(new double[][] {{10, .1, 20, 0, 20, .2, 10}, {10, .2, 20, 0, 20, .2, 10}}));
        Button btn;
        container.getChildren().add(btn = new Button("Center"));
        btn.setLimit("3, 3, 1, 1"); //Test setting the limit after add
        container.getChildren().add(new Button("Right").setLimit("5, 3, 1, 1"));
        container.getChildren().add(new Button("Left").setLimit("1, 3, 1, 1"));
        container.getChildren().add(new Button("Bottom").setLimit("1, 5, 5, 1"));
        container.getChildren().add(new Button("Top").setLimit("1, 1,   5, 1"));
        //container.getChildren().add(new Button("Overlap").setLimit("3, 3, 1, 3"));
    }
    
    static void complex(Container<Component> container) {
        container.setLayout(new TableLayout(new double[][] {{0, 0, 0, 0}, {0, 0, 0, 0, 0}}, 0, 5));        
        container.getChildren().add(new Button("Button 1").setLimit("0, 0, 1, 1"));
        container.getChildren().add(new Button("Button 2").setLimit("1, 0, 1, 1"));
        container.getChildren().add(new Button("Button 3").setLimit("2, 0, 1, 1"));
        container.getChildren().add(new Button("Button 4").setLimit("3, 0, 1, 1"));
        container.getChildren().add(new Button("Button 5").setLimit("0, 1, 4, 1"));
        container.getChildren().add(new Button("Button 6").setLimit("0, 2, 3, 1"));
        container.getChildren().add(new Button("Button 7").setLimit("3, 2, 1, 1"));
        container.getChildren().add(new Button("Button 8").setLimit("0, 3, 1, 2"));
        container.getChildren().add(new Button("Button 9").setLimit("1, 3, 3, 1"));
        container.getChildren().add(new Button("Button 10").setLimit("1, 4, 3, 1"));
    }
    
    static void grid(Container<Component> container) {
        TableLayout layout = new TableLayout();
        //XXX Adding filled out columns after layout does not work
        //container.setLayout(layout);
        
        /*for (int ci = 0; ci < 5; ci++) {
            TableLayout.Column col = new TableLayout.Column();

            for (int ri = 0; ri < 8; ri++) {
                String text = "C" + ci + ",R" + ri;
                System.out.println("Adding column and row: " + text);
                col.add(new TextField(text));
            }
            
            layout.getColumns().add(col);
        }*/
        
        for (int ci = 0; ci < 5; ci++) {
            layout.getColumns().add(new TableLayout.Column());
        }
        
        for (int ri = 0; ri < 8; ri++) {
            TableLayout.Row row = new TableLayout.Row();

            for (int ci = 0; ci < 5; ci++) {
                String text = "C" + ci + ",R" + ri;
                System.out.println("Adding column and row: " + text);
                row.add(new TextField(text));
            }
            
            layout.getRows().add(row);
        }
        
        container.setLayout(layout);
    }
}