import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Spykexx on 7/28/2017.
 */
public class WriteFile {
    DecimalFormat df = new DecimalFormat("#0.00");

    WriteFile(List<SalesRep> l, String filename){
        BufferedWriter bw;
            try{
                if(filename.equals("stars.txt")){
                    bw = new BufferedWriter(new FileWriter(filename, false));
                }else {
                    bw = new BufferedWriter(new FileWriter(filename, true));
                }
                for (int i = 0; i < l.size(); i++) {

                    bw.write(l.get(i).getSalesID() + " ");
                    bw.write( cap(l.get(i).getFName()) + " ");
                    bw.write(cap(l.get(i).getLName()) + " ");
                    bw.write(df.format(l.get(i).getSuppliesTotal()) + " "); //supplies
                    bw.write(df.format(l.get(i).getBooksTotal()) + " "); //books
                    bw.write(df.format(l.get(i).getPaperTotal()) + " "); //Paper
                    bw.write(l.get(i).getDistrict() + " ");
                    bw.write(l.get(i).getContact());
                    bw.newLine();

                }
                bw.close();
            }catch (IOException e){
                e.printStackTrace(); //print error
            }

        }
    private String cap(String line){
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
    }
