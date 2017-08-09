import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SalesRep {

        DecimalFormat df = new DecimalFormat("#0.00");
        Boolean error;
        int SalesID;
        String FName;
        String LName;
        double suppliesTotal;
        double booksTotal;
        double paperTotal;
        String district;
        String contact;
    SalesRep(){

    }

    SalesRep(String[] info){ //Creating a new object with input data from previous read file.
            ValidateText vt = new ValidateText();
            if(vt.test(info) == false) {
                setSalesID(Integer.parseInt(info[0]));
                setFName(info[1]);
                setLName(info[2]);
                setSuppliesTotal(Double.parseDouble(info[3]));
                setBooksTotal(Double.parseDouble(info[4]));
                setPaperTotal(Double.parseDouble(info[5]));
                setDistrict(info[6]);
                setContact(info[7]);
                setError(false);
            }else{
                setError(true);
            }
    }
    public boolean getError(){
        return error;
    }
    public void setError(boolean error){
        this.error = error;
    }

    public int getSalesID() {
        return SalesID;
    }

    public void setSalesID(int salesID) {
        this.SalesID = salesID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public double getSuppliesTotal() {
        return suppliesTotal;
    }

    public void setSuppliesTotal(double suppliesTotal) {
        this.suppliesTotal = suppliesTotal;
    }

    public double getBooksTotal() {
        return booksTotal;
    }

    public void setBooksTotal(double booksTotal) {
        this.booksTotal = booksTotal;
    }

    public double getPaperTotal() {
        return paperTotal;
    }

    public void setPaperTotal(double paperTotal) {
        this.paperTotal = paperTotal;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact.trim();
    }


    public void Stars(List<SalesRep> l) {
        int counter = 0;
        List<SalesRep> rep = new ArrayList<SalesRep>();
        for (int i = 0; i < l.size(); i++) { //loop through salesrep parameter passed in.
            if ((l.get(i).getSuppliesTotal() + l.get(i).getBooksTotal() + l.get(i).getPaperTotal()) > 8000) { //add all sales, if over 8000 print to new file.

                rep.add(counter, l.get(i));
                counter++;

            }
        }
        WriteFile wf = new WriteFile(rep, "stars.txt");

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rep Sales ID: " + SalesID + " \n" +
                "Rep Name: " + FName + " " + LName + "\n" +
                "Supplies Sold: " + df.format(suppliesTotal) + "\n" +
                "Books Sold: " + df.format(booksTotal) + "\n" +
                "Paper Sold: " + df.format(paperTotal) + "\n" +
                "Sales District: " + district + "\n" +
                "Preferred Contact: " + contact + "\n\n");

        return sb.toString();
    }
}

