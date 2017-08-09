import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

 class selectAllOnFocus extends JTextField {
        selectAllOnFocus(String def, int columns ){
            super.setColumns(columns); //allowing column size to still be passed in upon initialization
            super.setText(def);
        }
        {
            addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    selectAllOnFocus.this.select(0, getText().length()); //select all text upon gaining focus.
                }

                @Override
                public void focusLost(FocusEvent e) {
                    selectAllOnFocus.this.select(0,0); //When focus is lost, do not select anything.
                }

            });
        }
    }

