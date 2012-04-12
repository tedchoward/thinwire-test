package thinwire.apps.test;

import thinwire.ui.*;

public class GridBoxDoubleRowTest {
	public static void main(String[] args) {
		Frame f = Application.current().getFrame();
		GridBox gb = new GridBox();
		GridBox.Row row = new GridBox.Row("Row 1");		
		//gb.getRows().add(row); //Adding row before works fine
		f.getChildren().add(gb.setBounds(10, 10, 300, 200));
		gb.getRows().add(row); //Adding row after causes double add
		
		//GridBox.Column col = new GridBox.Column("Row 1", "Row 2", "Row 3");
		//gb.getColumns().add(col);
	}
}
