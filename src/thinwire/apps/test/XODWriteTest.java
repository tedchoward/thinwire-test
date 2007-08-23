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

import java.util.ArrayList;
import java.util.List;

import thinwire.util.XOD;

public class XODWriteTest {
    
    public static class Style {
        private Font font = new Font();
        private int opacity;
        
        public Font getFont() {
            return font;
        }
        
        public int getOpacity() {
            return opacity;
        }
        
        public void setOpacity(int opacity) {
            this.opacity = opacity;
        }
        
        public boolean equals(Object obj) {
            if (!(obj instanceof Style)) return false;
            if (this == obj) return true;
            Style s = (Style)obj;
            return this.opacity == s.opacity && this.font.equals(s.font);
        }
    }
    
    public static class Font {
        private int size;
        private String family = "";
        
        public String getFamily() {
            return family;
        }
        
        public void setFamily(String family) {
            if (family == null) family = "";
            this.family = family;
        }
        
        public int getSize() {
            return size;
        }
        
        public void setSize(int size) {
            this.size = size;
        }
        
        public boolean equals(Object obj) {
            if (!(obj instanceof Font)) return false;
            if (this == obj) return true;
            Font f = (Font)obj;
            return this.size == f.size && this.family.equals(f.family);
        }
    }
    
    public static class Employee {
        private String firstName;
        private String lastName;
        
        public String getFirstName() {
            return firstName;
        }
        
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        
        public String getLastName() {
            return lastName;
        }
        
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
    
    public static class Company {
        private boolean incorporated = true;
        private String name;
        private List<Employee> emps = new ArrayList<Employee>();
        private Style style = new Style();
        
        boolean isIncorporated() {
            return incorporated;
        }
        
        void setIncorporated(boolean inc) {
            this.incorporated = inc;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public List<Employee> getEmployees() {
            return emps;
        }
        
        public Style getStyle() {
            return style;
        }
    }
    
    public static void main(String[] args) {
        //XOD xod = new XOD();
        //xod.getAliasMap().put("Company", Company.class);
        //xod.getAliasMap().put("Employee", Employee.class);
        
        Company c1 = new Company();
        c1.setName("Dude, Inc.");
        Employee e1 = new Employee();
        e1.setFirstName("Josh");
        e1.setLastName("Gertzen");
        c1.getEmployees().add(e1);
        Employee e2 = new Employee();
        e2.setFirstName("Ted");
        e2.setLastName("Howard");
        c1.getEmployees().add(e2);
        //xod.getRootObjects().add(c1);

        Company c2 = new Company();
        c2.setName("Cool, Inc.");
        c2.getStyle().getFont().setSize(2);
        //xod.getRootObjects().add(c2);
        
        //xod.writeTo(System.out);
    }
}
