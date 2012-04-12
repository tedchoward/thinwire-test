package thinwire.apps.test;

import thinwire.ui.*;
import java.util.Comparator;

//Created to test issue: 1838466 - setSortOrder and setSortComparator do not work in DropDown
//Resolution: Wont fix.  Works as intended.
public class DropDownSortTest {
	public static void main(String[] args) {
		GridBox gb = new GridBox();
		setupGridBox(gb);
		
		DropDownGridBox ddgb = new DropDownGridBox();
		setupGridBox(ddgb.getComponent());
		
		Frame f = Application.current().getFrame();
		f.getChildren().add(ddgb.setBounds(10, 10, 150, 25));
		f.getChildren().add(gb.setBounds(10, 40, 150, 200));
	}
	
	private static void setupGridBox(GridBox gb) {
		gb.setVisibleHeader(true);
		GridBox.Column col1 = new GridBox.Column("Name", true);
		for (int i = 20; --i >= 0;) col1.add("Name " + (char)(i + 65));
		gb.getColumns().add(col1);
		
		/*java.util.Collections.sort(col1, new Comparator() {
			public int compare(Object o1, Object o2) {
				return o2.toString().compareTo(o1.toString());
			}
		});*/
		
		col1.setSortComparator(new Comparator() {
			public int compare(Object o1, Object o2) {
				System.out.println(o1.toString());
				System.out.println(o2.toString());
				return o1.toString().compareTo(o2.toString());
			}
		});
		col1.setSortOrder(GridBox.Column.SortOrder.ASC);
	}
}
