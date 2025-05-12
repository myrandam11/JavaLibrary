/***
Name: Myranda Miller
Date: 11/22/22
Course/Section: IT 206-002
Assignment: PA5
Program Description: This class will create the Librarian object as well as instantiate all of its variables. It will also aid the implementation class in accessing, mutating, and printing any info generated within the object.
Required input: ID, name, email, phone
Expected output: a Librarian object
***/

public class Librarian {
    //Instance Variables
    private String ID;
    private String name;
    private String email;
    private String phone;
    public static int numLibrarians = 0;

    //Constructors
    Librarian(){
        numLibrarians++;
    }

    Librarian(String ID, String name, String email, String phone){
        this();
        setID(ID);
        setName(name);
        setEmail(email);
        setPhone(phone);
    }

    //Mutators
    public void setID(String ID){
        if(ID==null || ID.equals("") || ID.isEmpty()){
            throw new IllegalArgumentException("Error: Librarian ID cannot be null." + "\n\n");
        }
        else if(ID.length()!=9 || ID.charAt(0)!='G'){
            throw new IllegalArgumentException("Error: Librarian ID must follow the GMU format." + "\n" +
                                                    "  - 9 characters long" + "\n" +
                                                    "  - start with G" + "\n" +
                                                    "  - all other characters are digits" + "\n\n");
        }
        else{
            for(int i=1; i<ID.length(); i++){
                if(!Character.isDigit(ID.charAt(i))){
                    throw new IllegalArgumentException("Error: Librarian ID must follow the GMU format." + "\n" +
                                                        "  - 9 characters long" + "\n" +
                                                        "  - start with G" + "\n" +
                                                        "  - all other characters are digits" + "\n\n");
                }
            }
        }
        this.ID = ID;
    }

    public void setName(String name){
        if(name==null || name.equals("") || name.isEmpty()){
            throw new IllegalArgumentException("Error: Librarian name cannot be null." + "\n\n");
        }
        this.name = name;
    }

    public void setEmail(String email){
        if(email==null || email.equals("") || email.isEmpty()){
            throw new IllegalArgumentException("Error: Librarian email cannot be null." + "\n\n");
        }
        else if(email.length()<12 || email.length()>16){ //"@gmu.edu"=8 char, prefix=4-8 char, min=12/max=16
            throw new IllegalArgumentException("Error: Librarian email prefix must be 4-8 characters long." + "\n\n");
        }
        else{
            String emailSubString = email.substring(email.length()-8);
            if(!emailSubString.equals("@gmu.edu")){
                throw new IllegalArgumentException("Error: Librarian email must end with \"@gmu.edu\"." + "\n\n");
            }
        }
        this.email = email;
    }

    public void setPhone(String phone){
        if(phone==null || phone.equals("") || phone.isEmpty()){
            throw new IllegalArgumentException("Error: Librarian phone number cannot be null." + "\n\n");
        }
        else if(phone.length()!=10){
            throw new IllegalArgumentException("Error: Librarian phone number must be 10 digits long." + "\n\n");
        }
        else{
            for(int i=0; i<phone.length(); i++){
                if(!Character.isDigit(phone.charAt(i))){
                    throw new IllegalArgumentException("Error: Librarian phone number must only contain digits." + "\n\n");
                }
            }
        }
        this.phone = phone;
    }

    //Accessors
    public String getID(){
        return this.ID;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }

    //Special Method Purposes
    public String toString(){
        String phone = getPhone();
        String phoneAreaCode = phone.substring(0, 3);
        String phoneNext3 = phone.substring(3, 6);
        String phoneLast4 = phone.substring(6);

        String output = "        - ID: " + getID() + "\n" +
                        "        - Name: " + getName() + "\n" +
                        "        - Email: " + getEmail() + "\n" +
                        "        - Phone Number: " + "(" + phoneAreaCode + ")" + phoneNext3 + "-" + phoneLast4 + "\n";
        return output;
    }
}
