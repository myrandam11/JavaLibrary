/***
Name: Myranda Miller
Date: 11/22/22
Course/Section: IT 206-002
Assignment: PA5
Program Description: This class will create the Book object as well as instantiate all of its variables. This will be done by way of sending LibraryItem variables to the superclass LibraryItem with a super key and then setting additional variables specific to the Book subclass. It will also aid the implementation class in accessing, mutating, and printing any info generated within the object.
Required input: ISBN, title, numCopies, availableCopies, String[] checkoutHoldersIDs, String[] dueDates, author, edition, publishingYear
Expected output: a Book object
***/

public class Book extends LibraryItem{
    //Instance Variables
    private String author;
    private int edition;
    private int publishingYear;
    public static int numBooks = 0;

    //Constructors
    Book(){
        super();
        numBooks++;
    }
    Book(String ISBN, String title, int numCopies, int availableCopies, String[] checkoutHoldersIDs, String[] dueDates, String author, int edition, int publishingYear){
        super(ISBN, title, numCopies, availableCopies, checkoutHoldersIDs, dueDates);
        setAuthor(author);
        setEdition(edition);
        setPublishingYear(publishingYear);
    }

    //Mutators
    public void setAuthor(String author){
        if(author==null || author.equals("") || author.isEmpty()){
            throw new IllegalArgumentException("Error: Author cannot be null." + "\n\n");
        }
        this.author = author;
    }

    public void setEdition(int edition){
        if(edition==0){
            throw new IllegalArgumentException("Error: Edition cannot be null." + "\n" +
                                                    "If only one edition is available, enter \"1\" as it is the 1st edition." + "\n\n");
        }
        else if(edition<0){
            throw new IllegalArgumentException("Error: Edition cannot be negative." + "\n\n");
        }
        this.edition = edition;
    }

    public void setPublishingYear(int publishingYear){
        if(publishingYear==0){
            throw new IllegalArgumentException("Error: Publishing year cannot be null." + "\n\n");
        }
        else if(publishingYear<1000 || publishingYear>9999){
            throw new IllegalArgumentException("Error: Publishing year must be four digits in the format YYYY." + "\n\n");
        }
        this.publishingYear = publishingYear;
    }

    //Accessors
    public String getAuthor(){
        return this.author;
    }

    public int getEdition(){
        return this.edition;
    }

    public int getPublishingYear(){
        return this.publishingYear;
    }
    //Special Method Purposes
    public String toString(){
        String output = super.toString();
        output += "        Author: " + getAuthor() + "\n" +
                    "        Edition: " + getEdition() + "\n" +
                    "        Publishing Year: " + getPublishingYear() + "\n" +
                    super.checkoutHolderToString();
        return output;
    }
}
