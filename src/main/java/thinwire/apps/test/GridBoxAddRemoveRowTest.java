package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;

public class GridBoxAddRemoveRowTest {
    private static Integer rowCount = 1;

	public static void main(String[] args) {
		Frame f = Application.current().getFrame();
		final GridBox gb = new GridBox();

		GridBox.Row row = new GridBox.Row("Row " +  rowCount++);
		f.getChildren().add(gb.setBounds(10, 10, 300, 200));
		gb.getRows().add(row); //Adding row after causes double add

        Button add = new Button("Append New");
        Button removeSelected = new Button("Remove Selected");

        add.addActionListener(Button.ACTION_CLICK, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                GridBox.Row row = new GridBox.Row("Row " +  rowCount++);
                gb.getRows().add(row);
            }
        });

        removeSelected.addActionListener(Button.ACTION_CLICK, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                gb.getRows().remove(gb.getSelectedRow());
            }
        });
		
        f.getChildren().add(add.setBounds(10, 220, 100, 30));
        f.getChildren().add(removeSelected.setBounds(120, 220, 100, 30));
	}
}
