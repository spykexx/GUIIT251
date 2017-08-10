import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    List<SalesRep> rep = new ArrayList<SalesRep>(); //list to hold all objects created.
    SalesRep oRep = new SalesRep();

    ReadFile(String filename){
        rep.clear();
        try {
            Scanner reader = new Scanner(new File(filename)); //open file
            String[] repInfo = new String[8]; //Array to hold string information

            while (reader.hasNextLine()) { //While there is more to read
                repInfo[0] = reader.next();//salesID
                repInfo[1] = reader.next();//Fname
                repInfo[2] = reader.next();//LName
                repInfo[3] = reader.next();//Supplies
                repInfo[4] = reader.next();//Books
                repInfo[5] = reader.next();//Paper
                repInfo[6] = reader.next();//District
                repInfo[7] = reader.nextLine();//contact //Consumes \n

                if(rep.size() != 0){ //while there are previous objects in list
                    for(int i = 0; i < rep.size(); i++ ){ //iterate through SalesRep objects
                        if(rep.get(i).getSalesID() == Integer.parseInt(repInfo[0])){ //if the salesID read matches the objects sales ID per the iterator
                            rep.get(i).setSuppliesTotal(rep.get(i).getSuppliesTotal() + Double.parseDouble(repInfo[3])); //add supplies to previous total
                            rep.get(i).setBooksTotal(rep.get(i).getBooksTotal() + Double.parseDouble(repInfo[4])); //add books to previous total
                            rep.get(i).setPaperTotal(rep.get(i).getPaperTotal() + Double.parseDouble(repInfo[5])); //add paper to previous total
                            break; //break the loop since done
                        }
                        else {
                            rep.add(new SalesRep(repInfo)); //add new rep since no matches found
                            break; //break the loop since done

                        }
                    }
                }else{
                    rep.add(new SalesRep(repInfo)); //since List of salesreps is empty add new one

                }
            }

            reader.close();
        }catch(FileNotFoundException F){
            JOptionPane.showMessageDialog(null, "Please create files first utilzing the form.");
        }
    }
    public List<SalesRep> returnInfo(){
        return rep;
    }
    }

