package thinwire.apps.test;

import thinwire.ui.*;
import thinwire.ui.layout.*;
import thinwire.ui.event.*;

//Tests the selectionBeginIndex and selectionEndIndex properties of TextArea
//Just enter in a multi-line block of text, fill out the fields as follows, select some text and click 'Do It':
// Add Before: Place a value here to prepend a value to the selection.
// Add After: Place a value here to append a value to the selection.
// Replace: Place a value here to replace the selected text.
public class TextAreaSelectionReplaceTest {
	public static void main(String[] args) {
		Frame f = Application.current().getFrame();
		
		f.setLayout(new TableLayout(new double[][]{{100,100,300},{25,25,25,25,100}}, 10, 10));
		final TextArea text = new TextArea();
		final TextField before = new TextField();
		final TextField after = new TextField();
		final TextField replace = new TextField();
		final Button doIt = new Button("Do It");
		
		f.getChildren().add(new Label("Add Before:").setLimit("0,0"));
		f.getChildren().add(before.setLimit("1,0"));
		f.getChildren().add(new Label("Add After:").setLimit("0,1"));
		f.getChildren().add(after.setLimit("1,1"));
		f.getChildren().add(new Label("Replace:").setLimit("0,2"));
		f.getChildren().add(replace.setLimit("1,2"));
		f.getChildren().add(doIt.setLimit("1,3"));
		f.getChildren().add(text.setLimit("2,0,1,5"));
		
		doIt.addActionListener("click", new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String value = text.getText();
				int begin = text.getSelectionBeginIndex();
				int end = text.getSelectionEndIndex();
				value = value.substring(begin, end);
				MessageBox.confirm("Selected value is: " + value);
				
				if (replace.getText().length() > 0) value = replace.getText();
				if (before.getText().length() > 0) value = before.getText() + value;
				if (after.getText().length() > 0) value += after.getText();
				
				text.setText(text.getText().substring(0, begin) + value + text.getText().substring(end));
			}
		});
	}
}
