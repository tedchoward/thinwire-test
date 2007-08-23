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

import thinwire.ui.Component;
import thinwire.ui.Dialog;
import thinwire.ui.DropDown;
import thinwire.ui.Image;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;

public class DropDownImageChooser extends DropDown<ImageChooser> {
    
    public static void main(String[] args) {
        Dialog dlg = new Dialog("Simple Test");
        dlg.setBounds(10, 10, 600, 400);
        DropDownImageChooser ddic = new DropDownImageChooser();
        ddic.setBounds(10, 10, 300, 20);
        dlg.getChildren().add(ddic);
        dlg.setVisible(true);
        
    }
    
    private static class DefaultView extends DropDown.AbstractView<ImageChooser> {

        void init(DropDownImageChooser ddic, ImageChooser ic) {
            super.init(ddic, ic);
            List<Component> kids = ddc.getChildren();
            for (Component c : kids) {
                if (c instanceof Image) {
                    Image img = (Image) c;
                    addCloseComponent(img);
                }
            }
            ddc.addPropertyChangeListener(ImageChooser.PROPERTY_IMAGE_SELECTED, new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent ev) {
                    dd.setText(getValue().toString());
                }
            });
        }
        
        public DropDown getDropDown() {
            return (DropDownImageChooser)dd;
        }

        public int getOptimalHeight() {
            return ddc.getHeight();
        }

        public Object getValue() {
            return ddc.getSelectedImage().getImage();
        }

        public void setValue(Object value) {
            String s;
            if (value == null) {
                s = "";
            } else if (value instanceof String) {
                s = (String) value;
            } else {
                s = value.toString();
            }
            ddc.setSelectedImage(ddc.getImage(s));
        }
        
    }

    DropDownImageChooser() {
        super(new DefaultView(), new ImageChooser());
        ((DefaultView) getView()).init(this, getComponent());
    }

}
