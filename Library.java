/***
Name: Myranda Miller
Date: 11/22/22
Course/Section: IT 206-002
Assignment: PA5
Program Description: This class will create the Library object as well as instantiate all of its variables. It will also aid the implementation class in accessing, mutating, and printing any info generated within the object. It will be used as an aggregator for LibraryItem and Librarian.
Required input: name, address, assignedLibrarian, LibraryItem[] items, StudyRoom[] rooms
Expected output: a Library object
***/

public class Library{
    //Instance Variables
    private String name;
    private String address;
    private Librarian assignedLibrarian;
    public static int numRooms;
    public final static int MAX_NUM_ITEMS = 6000;
    public final static int MAX_NUM_ROOMS = 48;
    private  LibraryItem[] items = new LibraryItem[MAX_NUM_ITEMS];
    private static StudyRoom[] rooms = new StudyRoom[MAX_NUM_ROOMS];
    public static int numLibraries = 0;
    
    //Constructors
    Library(){
        numLibraries ++;
    }
    Library(String name, String address, Librarian assignedLibrarian, LibraryItem[] items, StudyRoom[] rooms){
        this();
        setName(name);
        setAddress(address);
        setLibrarian(assignedLibrarian);
        setLibraryItems(items);
        setStudyRooms(rooms);
    }

    //Mutators
    public void setName(String name){
        if(name==null || name.equals("") || name.isEmpty()){
            throw new IllegalArgumentException("Error: Library name cannot be null." + "\n\n");
        }
        this.name = name;
    }

    public void setAddress(String address){
        if(address==null || address.equals("") || address.isEmpty()){
            throw new IllegalArgumentException("Error: Library address cannot be null." + "\n\n");
        }
        this.address = address;
    }

    public void setLibrarian(Librarian assignedLibrarian){
        if(assignedLibrarian==null){
            throw new IllegalArgumentException("Error: Library must have an assigned librarian." + "\n\n");
        }
        this.assignedLibrarian = assignedLibrarian;
    }

    public void setLibraryItems(LibraryItem[] items){
        if(LibraryItem.numItems>=MAX_NUM_ITEMS){
            throw new IllegalArgumentException("Error: Maximum number of items have already been created." + "\n\n");
        }
        this.items = items;
    }

    public void setStudyRooms(StudyRoom[] rooms){
        if(numRooms>=MAX_NUM_ROOMS){
            throw new IllegalArgumentException("Error: Maximum number of study rooms have already been created." + "\n\n");
        }
        Library.rooms = rooms;
    }

    //Accessors
    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public Librarian getLibrarian(){
        return this.assignedLibrarian;
    }

    public LibraryItem[] getLibraryItems(){
        return this.items;
    }

    public StudyRoom[] getStudyRooms(){
        return rooms;
    }

    //Special Purpose Methods
    public String toString(){
        String output = "Library -" + "\n" +
                        "    Name: " + getName() + "\n" +
                        "    Address: " + getAddress() + "\n" +
                        "    Librarian: " + "\n" +
                            getLibrarian().toString() + "\n" +
                        "    Total number of items: " + LibraryItem.numItems +"\n" +
                        "    Total number of study rooms: " + numRooms + "\n\n";
        

        output += "Library Items -" + "\n";
        LibraryItem[] items = getLibraryItems();
        if(LibraryItem.numItems==0){
            output += "        No items are currently held at this library." + "\n\n";
        }
        else{
            for(int i=0; i<LibraryItem.numItems; i++){
                output += items[i].toString() + "\n";

            }
        }

        output += "Study Rooms -" + "\n";
        StudyRoom[] rooms = getStudyRooms();
        if(numRooms==0){
            output += "        No study rooms currently exist within this library." + "\n\n";
        }
        else{
            for(int i=0; i<numRooms; i++){
                output += rooms[i].toString() + "\n";
            }
        }
        return output;
    }
}