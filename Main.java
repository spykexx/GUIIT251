import javax.swing.*; //Here we import all needed packages
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Here we finally specify our window's settings and finally call it with .setVisible(true)
        Gui prod = new Gui();
        prod.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prod.setSize(670, 425); //setting window size
        prod.setLocationRelativeTo(null);
        prod.setResizable(false);
        prod.setVisible(true); //calling our JFrame Panel

    }
}
class Gui extends JFrame { //We must extend JFrame to be able to use Swing and create a GUI
    //Here we declare all JFrame elements to be added to our panel
    List<SalesRep> rep = new ArrayList<SalesRep>();
    SalesRep oRep = new SalesRep();

    private JTextField fieldID;
    private JTextField fieldFName;
    private JTextField fieldLName;
    private JTextField fieldSupplies;
    private JTextField fieldBooks;
    private JTextField fieldPaper;
    private JTextArea txtFieldResults;
    private JTextArea txtFieldStars;
    private JScrollPane scroll;
    private JRadioButton rbNorth;
    private JRadioButton rbEast;
    private JRadioButton rbSouth;
    private JRadioButton rbWest;
    private JRadioButton rbPhone;
    private JRadioButton rbEmail;
    private JRadioButton rbVisit;
    private ButtonGroup bgLocation;
    private ButtonGroup bgContact;

    public Gui() {
        super("Sales Information");
        JPanel master = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(7, 2, 5, 10));//We construct our first layout for the labels and fields
        JPanel rightPanel = new JPanel(new GridLayout(7, 2, 5, 10));
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 5, 10));//constructing our second layout housing our JTextArea

        JLabel labelID = new JLabel("  Representative ID:");            //We begin constructing all of our elements
        JLabel labelFName = new JLabel("  Representative First Name:");
        JLabel labelLName = new JLabel("  Representative Last Name:");
        JLabel labelSupplies = new JLabel("   Total Supplies Sold:");
        JLabel labelBooks = new JLabel("   Total Books Sold:");
        JLabel labelPaper = new JLabel("   Total Paper Sold:");
        JLabel labelContact = new JLabel(" Preferred Contact Method:");

        fieldID = new selectAllOnFocus("",10);
        fieldFName = new selectAllOnFocus("",10);
        fieldLName = new selectAllOnFocus("",10);
        fieldSupplies = new selectAllOnFocus("0.00",10);
        fieldBooks = new selectAllOnFocus("0.00",10);
        fieldPaper = new selectAllOnFocus("0.00",10);

        txtFieldResults = new JTextArea(null, 12, 10);
        txtFieldResults.setEditable(false);
        txtFieldStars = new JTextArea(null, 12, 10);
        txtFieldStars.setEditable(false);
        scroll = new JScrollPane(txtFieldStars);

        rbNorth = new JRadioButton("North District");
        rbEast = new JRadioButton("East District");
        rbSouth = new JRadioButton("South District");
        rbWest = new JRadioButton("West District");
        bgLocation = new ButtonGroup();
        bgLocation.add(rbNorth);
        bgLocation.add(rbEast);
        bgLocation.add(rbSouth);
        bgLocation.add(rbWest);

        rbPhone = new JRadioButton("Phone");
        rbEmail = new JRadioButton("Email");
        rbVisit = new JRadioButton("Visit");
        bgContact = new ButtonGroup();
        bgContact.add(rbPhone);
        bgContact.add(rbEmail);
        bgContact.add(rbVisit);

        JButton bEnter = new JButton("Enter!");
        JButton bQuit = new JButton("Quit!");
        JButton bEval = new JButton("Evaluate!");
        JButton bDisplay = new JButton("Display!");

        leftPanel.add(labelID);
        leftPanel.add(fieldID);
        leftPanel.add(labelFName);
        leftPanel.add(fieldFName);
        leftPanel.add(labelLName);
        leftPanel.add(fieldLName);
        leftPanel.add(labelContact);
        leftPanel.add(rbPhone);
        leftPanel.add(rbNorth);
        leftPanel.add(rbEast);
        leftPanel.add(bEnter);
        leftPanel.add(bQuit);

        rightPanel.add(labelSupplies);
        rightPanel.add(fieldSupplies);
        rightPanel.add(labelBooks);
        rightPanel.add(fieldBooks);
        rightPanel.add(labelPaper);
        rightPanel.add(fieldPaper);
        rightPanel.add(rbEmail);
        rightPanel.add(rbVisit);
        rightPanel.add(rbSouth);
        rightPanel.add(rbWest);
        rightPanel.add(bEval);
        rightPanel.add(bDisplay);
        bottomPanel.add(txtFieldResults);
        bottomPanel.add(scroll);
        bottomPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());

        master.add(leftPanel, BorderLayout.WEST);
        master.add(rightPanel, BorderLayout.CENTER);
        master.add(bottomPanel, BorderLayout.SOUTH);
        this.getContentPane().add(master); //add master panel to Jpanel

        bEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    rep.clear();
                    String[] newRep = {fieldID.getText(), fieldFName.getText(), fieldLName.getText(), fieldSupplies.getText(), fieldBooks.getText(), fieldPaper.getText(), getDistrictInfo(), getContactInfo()};
                    txtFieldResults.setText("");//Clear main textArea
                    rep.add(new SalesRep(newRep));
                    if (!rep.get(0).getError()) {
                        txtFieldResults.setText(rep.get(0).toString());
                        WriteFile wf = new WriteFile(rep, "salesrep.txt");
                    }
                }
        });
        bQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int yes = JOptionPane.showConfirmDialog(null, "Are you sure you wish to close the application?", "", JOptionPane.YES_NO_OPTION);
                if(yes == JOptionPane.YES_OPTION) System.exit(0); //if user clicks yes, end program

            }
        });
        bEval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadFile rf = new ReadFile("salesrep.txt");
                oRep.Stars(rf.returnInfo());
            }
        });
        bDisplay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ReadFile rf = new ReadFile("stars.txt");
                txtFieldStars.setText("--- " + rf.returnInfo().size() + " All Star Reps! ---\n\n");
                txtFieldStars.append(rf.returnInfo().toString().replace("[", "").replace("]", "").replace(",", ""));
                txtFieldStars.setCaretPosition(0);
                if(rf.returnInfo().size() > 0) {
                    TableGui tgui = new TableGui(rf.returnInfo());
                    tgui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    tgui.setSize(770, 425); //setting window size
                    tgui.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x, GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y);
                    tgui.setResizable(false);
                    tgui.setVisible(true); //calling our JFrame Panel
                }

            }
        });
    }

    public String getDistrictInfo(){
        String district = null;
        if(rbNorth.isSelected()) district = "NORTH";
        else if (rbEast.isSelected()) district = "EAST";
        else if (rbWest.isSelected()) district = "WEST";
        else if (rbSouth.isSelected()) district = "SOUTH";
        else district = "None";
        return district;
    }
    public String getContactInfo(){
        String contact = null;
        if(rbPhone.isSelected()) contact = "Phone";
        else if(rbEmail.isSelected()) contact = "Email";
        else if(rbVisit.isSelected()) contact = "Visit";
        else contact = "None";
        return contact;
    }

}
