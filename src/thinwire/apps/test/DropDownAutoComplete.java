package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.event.*;
import java.util.Arrays;

public class DropDownAutoComplete {
	public static void main(String[] args) {
        final DropDownGridBox ddgb = new DropDownGridBox();
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
        });
	}

}
