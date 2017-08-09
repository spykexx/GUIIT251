import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Spykexx on 8/7/2017.
 */
public class NumRender extends DefaultTableCellRenderer {
    private DecimalFormat df = new DecimalFormat("$00.00");

    public void setValue(Object value){
        try {
            if (value != null) {
                value = df.format(value);
            }
        }catch(IllegalArgumentException e) {}

        super.setValue(value);
        }

    public NumRender(NumberFormat format){
        setHorizontalAlignment(RIGHT);
    }
    public static NumRender getCurrencyFormat(){
        return new NumRender(NumberFormat.getCurrencyInstance());
    }




    }

