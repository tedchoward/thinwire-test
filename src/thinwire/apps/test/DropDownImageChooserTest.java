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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thinwire.ui.Component;
import thinwire.ui.Dialog;
import thinwire.ui.DropDown;
import thinwire.ui.Image;
import thinwire.ui.Panel;
import thinwire.ui.TextField;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;
import thinwire.ui.style.Color;

public class DropDownImageChooserTest extends DropDown<ImageChooser> {
    
    public static void main(String[] args) {
        Dialog dlg = new Dialog("Simple Test");
        dlg.setBounds(10, 10, 600, 400);
        DropDownImageChooserTest ddic = new DropDownImageChooserTest();
        ddic.setBounds(10, 10, 300, 20);
        dlg.getChildren().add(ddic);
        dlg.setVisible(true);
        
    }
    
    private static class DefaultView extends DropDown.AbstractView<ImageChooser> {

        void init(DropDownImageChooserTest ddic, ImageChooser ic) {
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
            return (DropDownImageChooserTest)dd;
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

    DropDownImageChooserTest() {
        super(new DefaultView(), new ImageChooser());
        ((DefaultView) getView()).init(this, getComponent());
    }

}

class ImageChooser extends Panel {
    public static final String PROPERTY_IMAGE_SELECTED = "imageSelected";
    
    private ActionListener imageClickListener = new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            setSelectedImage((Image) ev.getSource());
        }
    };
    
    private Image selectedImage;
    private Map<String, Image> imageMap;
    
    ImageChooser() {
        imageMap = new HashMap<String, Image>();
        addImage("ayb.png");
        addImage("pirate.png");
        addImage("shield.png");
        addImage("mr-t.png");
        addImage("whatever.png");
        TextField dddb = new TextField();
        dddb.setBounds(5, getHeight() + 5, 50, 20);
        setHeight(getHeight() + dddb.getHeight() + 10);
        getChildren().add(dddb);
    }
    
    void addImage(String fileName) {
        Image img = new Image(Main.RES_PATH + fileName);
        img.setFocusCapable(true);
        img.setPosition(5, getHeight() + 5);
        if (getInnerWidth() < img.getWidth()) setWidth(img.getWidth() + 10);
        setHeight(getHeight() + img.getHeight() + 5);
        img.addActionListener(Image.ACTION_CLICK, imageClickListener);
        getChildren().add(img);
        imageMap.put(img.getImage(), img);
        setSelectedImage(img);
    }
    
    public void setSelectedImage(Image selectedImage) {
        Image oldImage = this.selectedImage;
        if (oldImage != null) oldImage.getStyle().getBackground().setColor(Color.TRANSPARENT);
        this.selectedImage = selectedImage;
        this.selectedImage.getStyle().getBackground().setColor(Color.HIGHLIGHT);
        firePropertyChange(this, PROPERTY_IMAGE_SELECTED, oldImage, this.selectedImage);
    }
    
    public Image getSelectedImage() {
        return selectedImage;
    }
    
    public Image getImage(String imageName) {
        return imageMap.get(imageName); 
    }
    
}
