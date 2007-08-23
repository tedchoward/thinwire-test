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
import thinwire.ui.layout.TableLayout;

public class MemoryTest implements UITest {
    public void run() {
        final Frame f = Application.current().getFrame();
        
        /*final Dialog d = new Dialog("Hello World");
        d.setBounds(25, 25, 300, 200);
        d.setVisible(true);*/
        
        final Panel p = new Panel();
        p.setBounds(25, 25, 400, 400);
        f.getChildren().add(p);
        
        testTree(p);
    }
    
    public void testRadioButton(final Container<Component> c) {
        c.setLayout(new TableLayout(new double[][]{{100, 100, 100, 100}, {25, 25, 25, 25}}, 10, 5));
        final RadioButton rb1 = new RadioButton("Dude!");
        final RadioButton rb2 = new RadioButton("Cool!");
        final RadioButton rb3 = new RadioButton("Bad!");
        final RadioButton rb4 = new RadioButton("Leak!");
        //rb1.getStyle().getBorder().setImage("");
        //rb2.getStyle().getBorder().setImage("");
        //rb3.getStyle().getBorder().setImage("");
        //rb4.getStyle().getBorder().setImage("");
        /*final CheckBox rb1 = new CheckBox("Dude!");
        final CheckBox rb2 = new CheckBox("Cool!");
        final CheckBox rb3 = new CheckBox("Bad!");
        final CheckBox rb4 = new CheckBox("Leak!");*/
        c.getChildren().add(rb1.setLimit("1, 1"));
        c.getChildren().add(rb2.setLimit("2, 1"));
        c.getChildren().add(rb3.setLimit("1, 2"));
        c.getChildren().add(rb4.setLimit("2, 2"));
        
        Button b = new Button("Toggle Visibility");
        c.getChildren().add(b.setLimit("2, 3"));
        
        b.addActionListener("click", new ActionListener() {
            boolean visible = true;
            public void actionPerformed(ActionEvent ev) {
                if (visible) {
                    System.out.println("Removing component");
                    c.getChildren().remove(rb1);
                    c.getChildren().remove(rb2);
                    c.getChildren().remove(rb3);
                    c.getChildren().remove(rb4);
                    //c.getChildren().remove(comp);
                } else {
                    System.out.println("Adding component");
                    //c.getChildren().add(comp);
                    c.getChildren().add(rb1);
                    c.getChildren().add(rb2);
                    c.getChildren().add(rb3);
                    c.getChildren().add(rb4);
                }
                
                visible = !visible;
            }
        });
    }
    
    private Tree newTree(int cnt1, int cnt2, int cnt3) {
        Tree comp = new Tree();
        Tree.Item root = comp.getRootItem();
        root.setText("Root");
        comp.setRootItemVisible(true);
        
        for (int ri = 0; ri < cnt1; ri++) {                
            Tree.Item L1 = new Tree.Item();
            L1.setText("Level 1 > Item " + ri);
            
            if (ri % 4 == 0) {
                for (int i1 = 0; i1 < cnt2; i1++) {
                    Tree.Item L2 = new Tree.Item();
                    L2.setText("Level 2 > Item " + i1);
                    
                    if (i1 % 4 == 0) {
                        for (int i2 = 0; i2 < cnt3; i2++) {
                            Tree.Item L3 = new Tree.Item();
                            L3.setText("Level 3 > Item " + i2);
                            L2.getChildren().add(L3);
                        }
                    }
                    
                    L1.getChildren().add(L2);
                }
            }

            root.getChildren().add(L1);
        }

        return comp;
    }
    
    public void testTree(final Container<Component> c) {
        c.setLayout(new TableLayout(new double[][]{{100, 100, 100, 100}, {300, 25}}, 10, 5));
        
        final Tree comp = newTree(20, 20, 10);        
        c.getChildren().add(comp.setLimit("1, 0, 2, 1"));
        
        Button b = new Button("Toggle Visibility");
        c.getChildren().add(b.setLimit("1, 1"));

        b.addActionListener("click", new ActionListener() {
            boolean visible = true;
            public void actionPerformed(ActionEvent ev) {
                if (visible) {
                    System.out.println("Removing component");
                    c.getChildren().remove(comp);
                } else {
                    System.out.println("Adding component");
                    //c.getChildren().add(comp);
                    c.getChildren().add(newTree(10, 10, 10).setLimit("1, 0, 2, 1"));
                }
                
                visible = !visible;
            }
        });
    }
}
