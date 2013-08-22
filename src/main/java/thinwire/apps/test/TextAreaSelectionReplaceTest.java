package thinwire.apps.test;

import thinwire.ui.Application;
import thinwire.ui.Button;
import thinwire.ui.Frame;
import thinwire.ui.Label;
import thinwire.ui.MessageBox;
import thinwire.ui.TextArea;
import thinwire.ui.TextField;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;
import thinwire.ui.layout.TableLayout;

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
				String val = text.getText();
				final int begin = text.getSelectionBeginIndex();
				final int end = text.getSelectionEndIndex();
				final String value = val.substring(begin, end);
				MessageBox.confirm("Selected value is: " + value, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ev) {
						String val = value;
						
						if (replace.getText().length() > 0) val = replace.getText();
						if (before.getText().length() > 0) val = before.getText() + val;
						if (after.getText().length() > 0) val += after.getText();
						
						text.setText(text.getText().substring(0, begin) + val + text.getText().substring(end));
					}
				});
			}
		});
	}
}
