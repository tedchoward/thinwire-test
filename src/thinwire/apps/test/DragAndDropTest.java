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

import java.util.List;
import java.util.logging.Logger;

import thinwire.render.RenderStateEvent;
import thinwire.render.RenderStateListener;
import thinwire.render.web.WebApplication;
import thinwire.ui.*;
import thinwire.ui.style.*;

public class DragAndDropTest {

    private static final Logger log = Logger.getLogger(DragAndDropTest.class.getName());
    private static final String IMG_PATH = "class:///thinwire.tests.Main/resources/";
    
    public static void main(String[] args) {
        final WebApplication app = (WebApplication) Application.current();
        Frame f = app.getFrame();
        f.setTitle("ThinWire Test");
        
        //final Dialog dlg = new Dialog("Drag and Drop Test");
        //dlg.setBounds(10, 10, 600, 400);
        List<Component> kids = f.getChildren();
        
        final Label lbl = new Label("Drag to Ted");
        lbl.setBounds(10, 10, 100, 20);
        Border lblBorder = lbl.getStyle().getBorder();
        lblBorder.setColor(Color.WINDOWFRAME);
        lblBorder.setSize(1);
        lblBorder.setType(Border.Type.SOLID);
        lbl.getStyle().getBackground().setColor(Color.HIGHLIGHT);
        lbl.getStyle().getFont().setColor(Color.HIGHLIGHTTEXT);
        kids.add(lbl);
        
        final Image img = new Image(app.getBaseFolder() + "\\whatever.png");
        img.setPosition(200, 30);
        
        app.addRenderStateListener(img, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                int lblId = app.getComponentId(lbl);
                app.clientSideMethodCall(lblId, "addDragTarget", app.getComponentId(img));
            }
        });
        
        kids.add(img);
        
        final Image img2 = new Image("class:///thinwire.tests.Main/resources/mr-t.png");
        img2.setPosition(200, 200);
        
        app.addRenderStateListener(img2, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                int lblId = app.getComponentId(img2);
                app.clientSideMethodCall(lblId, "addDragTarget", app.getComponentId(lbl));
            }
        });
        
        kids.add(img2);
        
        final GridBox gb = new GridBox();
        gb.setBounds(10, 40, 100, 200);
        gb.setVisibleHeader(true);
        for (int ci = 0; ci < 3; ci++) {
            GridBox.Column gbc = new GridBox.Column();
            gbc.setName("Column " + ci);
            for (int i = 0; i < 15; i++) gbc.add("C" + ci + ":R" + i);
            gb.getColumns().add(gbc);
        }
        app.addRenderStateListener(gb, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(gb), "addDragTarget", app.getComponentId(img2));
            }
        });
        kids.add(gb);
        
        final TextField tf = new TextField();
        tf.setBounds(10, 250, 100, 20);
        app.addRenderStateListener(tf, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                int lblId = app.getComponentId(img);
                app.clientSideMethodCall(lblId, "addDragTarget", app.getComponentId(tf));
            }
        });
        
        kids.add(tf);
        
        final TextField tf2 = new TextField();
        tf2.setBounds(10, 275, 100, 20);
        app.addRenderStateListener(tf2, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                int lblId = app.getComponentId(tf2);
                app.clientSideMethodCall(lblId, "addDragTarget", app.getComponentId(img2));
            }
        });
        kids.add(tf2);
        
        final Tree t = new Tree();
        t.setBounds(300, 10, 200, 100);
        //Tree.Item root = t.getRootItem();
        //root.setText("Root");
        //root.setImage(IMG_PATH + "Folder.png");
        t.setRootItemVisible(true);
        
        String IMG_FILE = IMG_PATH + "File.png"; 
        String IMG_FOLDER = IMG_PATH + "Folder.png"; 

        for (int ri = 0; ri < 5; ri++) {                
            Tree.Item L1 = new Tree.Item();
            L1.setText("<b>Level 1</b> > <i>Item " + ri + "</i>");
            
            if (ri == 2 || ri == 3 || ri == 5) {
                for (int i1 = 0; i1 < 4; i1++) {
                    Tree.Item L2 = new Tree.Item();
                    L2.setText("Level 2 > Item " + i1);
                    
                    if (i1 == 1 || i1 == 4) {
                        for (int i2 = 0; i2 < 5; i2++) {
                            Tree.Item L3 = new Tree.Item();
                            L3.setText("Level 3 > Item " + i2);
                            L3.setImage(IMG_FILE);
                            L2.getChildren().add(L3);
                        }
                    }
                    
                    L2.setImage(L2.hasChildren() ? IMG_FOLDER : IMG_FILE);
                    L1.getChildren().add(L2);
                }
            }

            L1.setImage(L1.hasChildren() ? IMG_FOLDER : IMG_FILE);
            //root.getChildren().add(L1);
        }
        
        app.addRenderStateListener(t, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(t), "addDragTarget", app.getComponentId(gb));
            }
        });
        kids.add(t);
        /*
        gb.addDropListener(t, new DropListener() {
            public void dropPerformed(DropEvent ev) {
                
            }
        });
        */
        
        final DateBox db = new DateBox();
        db.setBounds(300, 120, 200, 200);
        app.addRenderStateListener(db, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(img), "addDragTarget", app.getComponentId(db));
                app.clientSideMethodCall(app.getComponentId(db), "addDragTarget", app.getComponentId(img2));
                app.clientSideMethodCall(app.getComponentId(db), "addDragTarget", app.getComponentId(db));
            }
        });
        kids.add(db);
        
        final Button b = new Button("Drag Me to T", IMG_FILE);
        b.setBounds(10, 300, 100, 25);
        app.addRenderStateListener(b, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(b), "addDragTarget", app.getComponentId(img2));
            }
        });
        kids.add(b);
        
        final RadioButton rb = new RadioButton("Mutually Exclusive Drag?");
        rb.setBounds(10, 330, 170, 20);
        app.addRenderStateListener(rb, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(rb), "addDragTarget", app.getComponentId(img2));
                app.clientSideMethodCall(app.getComponentId(img), "addDragTarget", app.getComponentId(rb));
            }
        });
        kids.add(rb);
        
        final CheckBox c = new CheckBox("Boolean Drag");
        c.setBounds(10, 355, 170, 20);
        app.addRenderStateListener(c, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(c), "addDragTarget", app.getComponentId(b));
                app.clientSideMethodCall(app.getComponentId(lbl), "addDragTarget", app.getComponentId(c));
            }
        });
        kids.add(c);
        
        final Hyperlink h = new Hyperlink("Click and Drag?", "http://www.tedchoward.com");
        h.setBounds(300, 355, 100, 20);
        app.addRenderStateListener(h, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(h), "addDragTarget", app.getComponentId(img2));
                app.clientSideMethodCall(app.getComponentId(img), "addDragTarget", app.getComponentId(h));
            }
        });
        kids.add(h);
        
        final ProgressBar pb = new ProgressBar(100, 50);
        pb.setBounds(300, 380, 100, 20);
        pb.getStyle().getFont().setColor(Color.ACTIVECAPTION);
        app.addRenderStateListener(pb, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(pb), "addDragTarget", app.getComponentId(img2));
                app.clientSideMethodCall(app.getComponentId(img), "addDragTarget", app.getComponentId(pb));
            }
        });
        kids.add(pb);
        
        final WebBrowser wb = new WebBrowser("http://www.tedchoward.com");
        wb.setBounds(510, 10, 400, 400);
        app.addRenderStateListener(wb, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(wb), "addDragTarget", app.getComponentId(img2));
                app.clientSideMethodCall(app.getComponentId(img), "addDragTarget", app.getComponentId(wb));
            }
        });
        kids.add(wb);
        
        final DropDownGridBox ddgb = new DropDownGridBox();
        ddgb.setBounds(510, 420, 100, 20);
        GridBox ddgbComp = ddgb.getComponent();
        for (int ci = 0; ci < 3; ci++) {
            GridBox.Column gbc = new GridBox.Column();
            gbc.setName("Column " + ci);
            for (int i = 0; i < 15; i++) gbc.add("C" + ci + ":R" + i);
            ddgbComp.getColumns().add(gbc);
        }
        app.addRenderStateListener(ddgb, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(ddgb), "addDragTarget", app.getComponentId(img2));
                app.clientSideMethodCall(app.getComponentId(img), "addDragTarget", app.getComponentId(ddgb));
            }
        });
        kids.add(ddgb);
        
        TabFolder tFolder = new TabFolder();
        tFolder.setBounds(10, 380, 290, 200);
        
        final TabSheet ts1 = new TabSheet("Tab 1");
        app.addRenderStateListener(ts1, new RenderStateListener() {
            public void renderStateChange(RenderStateEvent ev) {
                app.clientSideMethodCall(app.getComponentId(ts1), "addDragTarget", app.getComponentId(img2));
                app.clientSideMethodCall(app.getComponentId(img), "addDragTarget", app.getComponentId(ts1));
            }
        });
        tFolder.getChildren().add(ts1);
        TabSheet ts2 = new TabSheet("Tab 2");
        tFolder.getChildren().add(ts2);
        TabSheet ts3 = new TabSheet("Tab 3");
        tFolder.getChildren().add(ts3);
        
        kids.add(tFolder);
        //dlg.setVisible(true);
    }

}
