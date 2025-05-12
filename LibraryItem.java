/***
Name: Myranda Miller
Date: 11/22/22
Course/Section: IT 206-002
Assignment: PA5
Program Description: This class will create the LibraryItem object as well as instantiate all of its variables. It will also aid the implementation class in accessing, mutating, and printing any info generated within the object. It will be used as an parent class for Book and DiscMedia.
Required input: ISBN, title, numCopies, availableCopies, String[] checkoutHoldersIDs, String[] dueDates
Expected output: a LibraryItem object
***/

public class LibraryItem {
    //Instance Variables
    private String ISBN;
    private String title;
    private int numCopies;
    private int availableCopies;
    private String[] checkoutHoldersIDs;
    private String[] dueDates;
    public static int numItems=0;

    //Constructors
    LibraryItem(){
        numItems++;
    }
    LibraryItem(String ISBN, String title, int numCopies, int availableCopies, String[] checkoutHoldersIDs, String[] dueDates){
        this();
        setISBN(ISBN);
        setTitle(title);
        setNumCopies(numCopies);
        setAvailableCopies(availableCopies);
        setCheckoutHoldersIDs(checkoutHoldersIDs);
        setDueDates(dueDates);
    }

    //Mutators
    public void setISBN(String ISBN){
        if(ISBN==null || ISBN.equals("") || ISBN.isEmpty()){
            throw new IllegalArgumentException("Error: ISBN cannot be null." + "\n\n");
        }
        
        else if(ISBN.length()!=13){
            throw new IllegalArgumentException("Error: ISBN must be 13 digits long." + "\n\n");
        }
        else{
            for(int i=0; i<ISBN.length(); i++){
                if(!Character.isDigit(ISBN.charAt(i))){
                    throw new IllegalArgumentException("Error: ISBN must only contain digits." + "\n\n");
                }
            }
        }
        this.ISBN = ISBN;
    }

    public void setTitle(String title){
        if(title==null || title.equals("") || title.isEmpty()){
            throw new IllegalArgumentException("Error: Title cannot be null." + "\n\n");
        }
        this.title = title;
    }

    public void setNumCopies(int numCopies){
        if(numCopies<0){
            throw new IllegalArgumentException("Error: Number of copies cannot be negative." + "\n\n");
        }
        this.numCopies = numCopies;
    }

    public void setAvailableCopies(int availableCopies){
        if(availableCopies<0){
            throw new IllegalArgumentException("Error: Number of available copies cannot be negative." + "\n\n");
        }
        if(availableCopies>numCopies){
            throw new IllegalArgumentException("Error: Number of available copies cannot be greater than total number of copies." + "\n\n");
        }
        this.availableCopies = availableCopies;
    }

    public void setCheckoutHoldersIDs(String[] checkoutHoldersIDs){
        this.checkoutHoldersIDs = checkoutHoldersIDs;
    }

    public void setDueDates(String[] dueDates){
        this.dueDates = dueDates;
    }

    //Accessors
    public String getISBN(){
        return this.ISBN;
    }

    public String getTitle(){
        return this.title;
    }

    public int getNumCopies(){
        return this.numCopies;
    }

    public int getAvailableCopies(){
        return this.availableCopies;
    }

    public String[] getCheckoutHoldersIDs(){
        return this.checkoutHoldersIDs;
    }

    public String[] getDueDates(){
        return this.dueDates;
    }

    //Special Purpose Methods
    public String toString(){
        String output = "    Item -" + "\n" +
                        "        ISBN: " + getISBN() + "\n" +
                        "        Title: " + getTitle() + "\n";
        return output;
    }

    public String checkoutHolderToString(){
        String output = "        Total number of copies: " + getNumCopies() + "\n" +
                        "        Number of copies available: " + getAvailableCopies() + "\n" +
                        "        Checkout Holders IDs & Due Dates:" + "\n";

        String[] checkoutHolderIDs = getCheckoutHoldersIDs();
        String[] dueDates = getDueDates();
        if(numCopies==availableCopies){
            output += "            - None" + "\n";
        }
        else{
            for(int i=0; i<numCopies-availableCopies; i++){
                String month = dueDates[i].substring(0, 2);
                String day = dueDates[i].substring(2, 4);
                String year = dueDates[i].substring(4);

                output += "            - ID: " + checkoutHolderIDs[i] + 
                                        " | Due: " + month + "/" + day + "/" + year + "\n";
            }
        }
        return output;
    }
}
