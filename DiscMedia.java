/***
Name: Myranda Miller
Date: 11/22/22
Course/Section: IT 206-002
Assignment: PA5
Program Description: This class will create the DiscMedia object as well as instantiate all of its variables. This will be done by way of sending LibraryItem variables to the superclass LibraryItem with a super key and then setting additional variables specific to the DiscMedia subclass. It will also aid the implementation class in accessing, mutating, and printing any info generated within the object.
Required input: ISBN, title, numCopies, availableCopies, String[] checkoutHoldersIDs, String[] dueDates, type, duration
Expected output: a DiscMedia object
***/

public class DiscMedia extends LibraryItem{
    //Instance Variables
    private String type;
    private int duration;
    public static int numDiscs = 0;

    //Constructors
    DiscMedia(){
        super();
        numDiscs++;
    }
    DiscMedia(String ISBN, String title, int numCopies, int availableCopies, String[] checkoutHoldersIDs, String[] dueDates, String type, int duration){
        super(ISBN, title, numCopies, availableCopies, checkoutHoldersIDs, dueDates);
        setType(type);
        setDuration(duration);
    }

    //Mutators
    public void setType(String type){
        if(!type.equalsIgnoreCase("CD") && !type.equalsIgnoreCase("DVD") && !type.equalsIgnoreCase("BLURAY")){
            throw new IllegalArgumentException("Error: Disc Media type must be CD, DVD, or BLURAY." + "\n\n");
        }
        this.type = type;
    }

    public void setDuration(int duration){
        if(duration==0){
            throw new IllegalArgumentException("Error: Duration cannot be null." + "\n\n");
        }
        else if(duration<0){
            throw new IllegalArgumentException("Error: Duration cannot be negative." + "\n\n");
        }
        this.duration = duration;
    }

    //Accessors
    public String getType(){
        return this.type;
    }

    public int getDuration(){
        return this.duration;
    }
    //Special Method Purposes
    public String toString(){
        String output = super.toString();
        output += "        Type: " + getType() + "\n" +
                    "        Duration: " + getDuration() + " minutes" + "\n" +
                    super.checkoutHolderToString();
        return output;
    }
}
