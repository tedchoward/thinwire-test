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
package thinwire.tests;

import java.util.HashMap;
import java.util.Map;

import thinwire.ui.Image;
import thinwire.ui.Panel;
import thinwire.ui.TextField;
import thinwire.ui.event.ActionEvent;
import thinwire.ui.event.ActionListener;
import thinwire.ui.style.Color;

public class ImageChooser extends Panel {
    
    public static final String PROPERTY_IMAGE_SELECTED = "imageSelected";
    
    private static final String IMG_PATH = "class:///thinwire.tests.ImageChooser/resources/";
    
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
        Image img = new Image(IMG_PATH + fileName);
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
