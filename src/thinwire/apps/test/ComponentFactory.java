/*
                           ThinWire(R) Playground Demo
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

  Users interested in finding out more about the ThinWire framework should visit
  the ThinWire framework website at http://www.thinwire.com. For those interested
  in discussing the details of how this demo was built, you can contact the 
  developer via email at "Joshua Gertzen" <josh at truecode dot org>.
*/
package thinwire.apps.test;

import java.util.*;

import thinwire.ui.*;

/**
 * @author Joshua J. Gertzen
 */
public class ComponentFactory {    
    static Component newInstance(Class<? extends Component> clazz, String... options) {
    	Arrays.sort(options);
        Component comp = newRawInstance(clazz);
        
        if (comp instanceof TextComponent) {
            ((TextComponent)comp).setText("Component Text");
        } else if (comp instanceof Menu) {
            Menu menu = (Menu)comp;            
            Menu.Item root = menu.getRootItem();
            
            String IMG_MENU = Main.RES_PATH + "Menu.png"; 
            String IMG_FILE = Main.RES_PATH + "File.png"; 
            String IMG_FOLDER = Main.RES_PATH + "Folder.png"; 

            for (int ri = 0; ri < 3; ri++) {                
                Menu.Item L1 = new Menu.Item("<i>Level 1</i> > Item " + ri, IMG_MENU);
                
                for (int i1 = 0; i1 < 4; i1++) {
                    Menu.Item L2 = new Menu.Item();
                    L2.setText("Level 2 > Item " + i1);
                    
                    if (i1 == 1) {
                        for (int i2 = 0; i2 < 5; i2++) {
                            Menu.Item L3 = new Menu.Item();
                            
                            if (i2 != 2) {
                                L3.setText("Level 3 > <b>Item</b> " + i2);
                                L3.setImage(IMG_FILE);
                            }

                            L2.getChildren().add(L3);
                        }
                        
                        if (ri == 2) L2.setEnabled(false);
                    } else if (ri == 0 && i1 == 2) {
                        L2.setEnabled(false);
                        L2.setKeyPressCombo("Ctrl-Shift-K");
                    }
                    
                    L2.setImage(L2.hasChildren() ? IMG_FOLDER : IMG_FILE);
                    L1.getChildren().add(L2);
                }

                root.getChildren().add(L1);
            }            
        } else if (comp instanceof Tree) {
            Tree tree = (Tree)comp;            
            Tree.Item root = tree.getRootItem();
            root.setText("Root");
            root.setImage(Main.RES_PATH + "Folder.png");
            tree.setRootItemVisible(true);
            
            String IMG_FILE = Main.RES_PATH + "File.png"; 
            String IMG_FOLDER = Main.RES_PATH + "Folder.png"; 

            for (int ri = 0; ri < 5; ri++) {                
                Tree.Item L1 = new Tree.Item();
                L1.setText("Level 1 > Item " + ri);
                
                if (ri == 2 || ri == 3 || ri == 5) {
                    for (int i1 = 0; i1 < 4; i1++) {
                        Tree.Item L2 = new Tree.Item();
                        L2.setText("Level 2 > <border size='1' color='red' type='solid'>Item</border> " + i1);
                        
                        if (i1 == 1 || i1 == 4) {
                            for (int i2 = 0; i2 < 5; i2++) {
                                Tree.Item L3 = new Tree.Item();
                                L3.setText("Level 3 > <s>Item</s> " + i2);
                                L3.setImage(IMG_FILE);
                                L2.getChildren().add(L3);
                            }
                        }
                        
                        L2.setImage(L2.hasChildren() ? IMG_FOLDER : IMG_FILE);
                        L1.getChildren().add(L2);
                    }
                }

                L1.setImage(L1.hasChildren() ? IMG_FOLDER : IMG_FILE);
                root.getChildren().add(L1);
            }            
        } else if (comp instanceof RangeComponent) {
            ((RangeComponent)comp).setCurrentIndex(50);
        }
        
        if (comp instanceof Image) {
            ((Image)comp).setImage(Main.RES_PATH + "pirate.png");
        } else if (comp instanceof Hyperlink) {
            ((Hyperlink)comp).setLocation("http://www.thinwire.com");
        } else if (comp instanceof WebBrowser) {
            ((WebBrowser)comp).setLocation("http://www.thinwire.com");
        } else if (comp instanceof TabSheet) {
            TabFolder tf = (TabFolder)(comp = newRawInstance(TabFolder.class));
            
            for (int i = 1; i <= 3; i++) {            
                tf.getChildren().add(new TabSheet("Tab Sheet " + i));
            }
        } else if (comp instanceof TabFolder) {
            TabFolder tf = (TabFolder)comp;
            
            for (int i = 1; i <= 3; i++) {            
                tf.getChildren().add(new TabSheet("Tab Sheet " + i));
            }
        } else if (comp instanceof DropDownGridBox || comp instanceof GridBox) {
            GridBox gb;            
            int columnCount;
            
            if (comp instanceof DropDownGridBox) {
                ((DropDownGridBox)comp).setEditAllowed(Arrays.binarySearch(options, "editAllowed") == 0);
                gb = ((DropDownGridBox)comp).getComponent();
                columnCount = 2;
            } else {
                gb = (GridBox)comp;
                gb.setVisibleHeader(true);
                columnCount = 3;
            }
            
            if (Arrays.binarySearch(options, "multiTiered") == 0) {
                populateGridBox(comp, gb, columnCount, 3, "");
                
                for (GridBox.Row row : gb.getRows()) {
                    GridBox child = new GridBox();
                    populateGridBox(comp, child, columnCount, 6, "R" + row.getIndex() + "C:");
                    row.setChild(child);
                }                
            } else {
                if (Arrays.binarySearch(options, "visibleCheckBoxes") == 0) gb.setVisibleCheckBoxes(true);
                populateGridBox(comp, gb, columnCount, 15, "");
            }
        }
        
        return comp;
    }
    
	private static Component newRawInstance(Class<? extends Component> clazz) {
        try {
        	if (clazz == null) throw new IllegalArgumentException("clazz == null");
            return clazz.newInstance();
        } catch (Exception e) {
            if (e instanceof RuntimeException) throw (RuntimeException)e;
            throw new RuntimeException(e);
        }        
    }
       
    private static void populateGridBox(Component comp, GridBox gb, int columnCount, int rowCount, String prefix) {
        for (int ci = 0; ci < columnCount; ci++) {
            GridBox.Column gbc = new GridBox.Column();
            if (comp instanceof GridBox) gbc.setName("Column <b>" + ci + "</b>");
            for (int i = 0; i < rowCount; i++) {
            	if (comp instanceof GridBox && ((GridBox)comp).isVisibleCheckBoxes() && prefix.length() == 0 && i % 2 == 0) {
            		gbc.add(prefix + "<background color='lightsteelblue'><i>C" + ci + ":R" + i + "</i></background>");
            	} else {
            		gbc.add(prefix + "C" + ci + ":R" + i);
            	}
            }
            gb.getColumns().add(gbc);
        }
    }
}
