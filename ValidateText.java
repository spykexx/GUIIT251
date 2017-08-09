import javax.swing.*;

public class ValidateText {
    ValidateText(){
    }
        public boolean test(String[] field){

            //Creating a method to validate all text so that it can be verified before displaying and writing.
            //Makes everything easier within the reading/writing methods.
            boolean error = false; //set error to false so that an error can manipulate to flag an error
            StringBuilder errorType = new StringBuilder(); //using a string building to create the error message to be shown
            field[0] = field[0].replaceAll("\\s", ""); //delete all spaces
            if (field[0].matches("\\D*")){errorType.append("Please enter a numeric sales ID! \n"); error = true;} //if text matches a non-digit character append error
            field[1] = field[1].replaceAll("\\s", "");
            cap(field[1]);
            if(field[1].equals("") || field[1].matches("\\d*")){errorType.append("Please enter an alphabetic first name! \n"); error = true;} //if text is empty or is numeric append error
            field[2] = field[2].replaceAll("\\s", "");
            cap(field[2]);
            if(field[2].equals("") || field[2].matches("\\d*")){errorType.append("Please enter an alphabetic last name! \n"); error = true;} //if text is empty of is numeric append error

            if(field[3].equals("0.00") && field[4].equals("0.00") && field[5].equals("0.00")){errorType.append("Please enter a sale within supplies, books or paper! \n"); error = true;} //see if every field is empty for totals; we want at least one field filled
            else {
                if(field[5].matches("[^0-9]+")){ //is not 0-9
                    errorType.append("Please enter only numeric paper information! \n");
                    error = true;
                }
                if(field[4].matches("[^0-9]+")){//is not 0-9
                    errorType.append("Please enter only numeric books information! \n");
                    error = true;
                }
                if(field[3].matches("[^0-9]+")){//is not 0-9
                    errorType.append("Please enter only numeric supplies information! \n");
                    error = true;
                }
            }

            if(field[7].equals("None")){errorType.append("Please select a contact preference! \n"); error = true;} //is buttongroup has no selection append error
            if(field[6].equals("None")){errorType.append("Please select a region! \n"); error = true;} //if buttongroup has no selection append error

            if(error == true){
                JOptionPane.showMessageDialog(null, errorType.toString());} //if any validations throw error, display stringbuilder message.
            return error;
        }
        private String cap(String line){
            return Character.toUpperCase(line.charAt(0)) + line.substring(1);
        }


    }

