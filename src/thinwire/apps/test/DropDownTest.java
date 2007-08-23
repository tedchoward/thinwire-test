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

public class DropDownTest {
	public static void main(String[] args) {
		new DropDownTest();
	}
	
	public void DropDownTest() {
        Frame f = Application.current().getFrame();
        f.setScrollType(Frame.ScrollType.AS_NEEDED);
        
        Dialog d = new Dialog();
        d.setBounds(100, 100, 640, 480);
        
        Panel p = new Panel();
        p.setBounds(25, 25, 320, 200);
        d.getChildren().add(p);
        d.setScrollType(Frame.ScrollType.AS_NEEDED);
        DropDownGridBox ddgb = new DropDownGridBox();
        ddgb.setBounds(10, 20, 200, 25);
        GridBox gb = ddgb.getComponent();
        //GridBox gb = new GridBox();
        //gb.setBounds(10, 10, 200, 200);
        populateGridBox(gb, 3, 8, "T1:");
        
        for (GridBox.Row row : gb.getRows()) {
            GridBox cgb = populateGridBox(null, 2, 10, "T2:");
            
            for (GridBox.Row crow : cgb.getRows()) {
                crow.setChild(populateGridBox(null, 1, 15, "T3:"));
            }
            
            row.setChild(cgb);
        }
        
        p.getChildren().add(ddgb);
        d.setVisible(true);
        //f.getChildren().add(gb);
    }
    
    private GridBox populateGridBox(GridBox gb, int columns, int rows, String prefix) {
        if (gb == null) gb = new GridBox();
        
        while (gb.getColumns().size() < columns) {
            gb.getColumns().add(new GridBox.Column());
        }
        
        for (int i = 0; i < rows; i++) {
            GridBox.Row row = new GridBox.Row();
            
            for (int ii = 0; ii < columns; ii++) {
                row.add(prefix + "C" + ii + ":R" + i);
            }
            
            gb.getRows().add(row);
        }
        
        return gb;
    }
}
