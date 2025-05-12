/***
Name: Myranda Miller
Date: 11/22/22
Course/Section: IT 206-002
Assignment: PA5
Program Description: This class will prompt the user with a menu of choices including the option to create a library, create a librian, create a library item, create a study room, search for a library item, checkout a library item, reserve a study room, print library info, or quit the program. It will also include methods related to each of these tasks that will communicate with the DDCs in order to manipulate and/or print aspects of the library.
Required input: String[] args, LibraryItem[] items, StudyRoom[] rooms, Library aLibrary
Expected output: int, Library, Librarian, LibraryItem[], StudyRoom[], LibraryItem
***/

import javax.swing.JOptionPane;

public class CheckoutSystem {
    public static void main(String[] args){

        Library aLibrary = null;
        boolean libraryCreated = false;
        Librarian aLibrarian = null;
        LibraryItem[] items = new LibraryItem[Library.MAX_NUM_ITEMS];
        StudyRoom[] rooms = new StudyRoom[Library.MAX_NUM_ROOMS];

        int optionChosen = 0;
        do {
            optionChosen = menu();
            switch (optionChosen) {
                case 1: //add a library
                    aLibrary = createLibrary();
                    JOptionPane.showMessageDialog(null, "New Library!" + "\n\n" + aLibrary.toString() + "\n\n");
                    libraryCreated = true;
                    break;
                case 2: //add a librarian
                    if(libraryCreated){
                        aLibrarian = createLibrarian();
                        aLibrary.setLibrarian(aLibrarian);
                        JOptionPane.showMessageDialog(null, "New Librarian!" + "\n\n" + aLibrarian.toString() + "\n\n");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: No library has been created yet." + "\n\n");
                    }
                    break;
                case 3: //add library items
                    if(libraryCreated){
                        if(LibraryItem.numItems<Library.MAX_NUM_ITEMS){
                            items = aLibrary.getLibraryItems();
                            aLibrary.setLibraryItems(createLibraryItem(items));

                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Error: Maximum number of items have already been added to this library." + "\n\n");
                        }
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: No library has been created yet." + "\n\n");
                    }
                    break;
                case 4: //add study rooms
                    if(libraryCreated){
                        if(Library.numRooms<Library.MAX_NUM_ROOMS){
                            rooms = aLibrary.getStudyRooms();
                            aLibrary.setStudyRooms(createStudyRoom(rooms));
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Error: Maximum number of rooms have already been added to this library." + "\n\n");
                        }
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: No library has been created yet." + "\n\n");
                    }
                    break;
                case 5: //search library items
                    if(libraryCreated){
                        if(items[0]==null){
                            JOptionPane.showMessageDialog(null, "Error: No items have been created yet." + "\n\n");
                        }
                        else{
                            searchLibraryItem(items);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: No library has been created yet." + "\n\n");
                    }
                    break;
                case 6: //checkout an item
                    if(libraryCreated){
                        if(items[0]==null){
                            JOptionPane.showMessageDialog(null, "Error: No items have been created yet." + "\n\n");
                        }
                        else{
                            itemCheckout(items);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: No library has been created yet." + "\n\n");
                    }
                    break;
                case 7: //reserve a study room
                    if(libraryCreated){
                        if(Library.numRooms>0){
                            reserveRoom(rooms);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Error: No study rooms have been created yet." + "\n\n");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: No library has been created yet." + "\n\n");
                    }
                    break;
                case 8: //print library info
                    if(libraryCreated){
                        JOptionPane.showMessageDialog(null, aLibrary.toString() + "\n\n");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: No library has been created yet." + "\n\n");
                    }
                    break;
                case 9: //quit program
                    JOptionPane.showMessageDialog(null, "Thank you for using the GMU library system." + "\n\n");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error: Choose option 1-9." + "\n\n");
                    break;
            }
        } while (optionChosen!= 9);
    }

    public static int menu(){
        int optionChosen = 0;
        boolean exception = true;

        do {
            try {
                optionChosen = Integer.parseInt(JOptionPane.showInputDialog(null, "GMU Library System -" + "\n\n" +
                                                                                            "Choose one of the following options by number:" + "\n" +
                                                                                               "1. Add a Library" + "\n" +
                                                                                               "2. Add a Librarian" + "\n" +
                                                                                               "3. Add Library Items (Books or Disc Media)" + "\n" +
                                                                                               "4. Add Study Rooms" + "\n" +
                                                                                               "5. Search Library Items" + "\n" +
                                                                                               "6. Checkout an Item (Book or Disc Media)" + "\n" +
                                                                                               "7. Reserve a Study Room" + "\n" +
                                                                                               "8. Print Library Information" + "\n" +
                                                                                               "9. Quit" + "\n\n"));
                    if(optionChosen<1 || optionChosen>9){
                       throw new NumberFormatException();
                    }
                    exception=false;
            } catch (NumberFormatException e) {
                exception = true;
                JOptionPane.showMessageDialog(null, "Error: Choose option 1-9." + "\n\n");
            }
        } while (exception);
        
        

        return optionChosen;
    }

    //may be modified in the future to be utilized for multiple libraries, however for now, it will be piloted at Fenwick Library: only one library needed
    public static Library createLibrary(){
        String name = "name";
        String address = "address";
        Librarian assignedLibrarian = new Librarian("G00000000", "name", "sample@gmu.edu", "0000000000");
        Library aLibrary = new Library(name,address,assignedLibrarian,new LibraryItem[Library.MAX_NUM_ITEMS], new StudyRoom[Library.MAX_NUM_ROOMS] );
        boolean exception = true;

        //prompting for library name
        do {
            try {
                name =JOptionPane.showInputDialog("Create Library -" + "\n\n" +
                                                    "Enter Library Name:" + "\n\n");
                aLibrary.setName(name);
                exception = false;
            } catch (IllegalArgumentException e) {
                exception = true;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (exception);
        

        //prompting for library address
        exception = true;
        do {
            try {
                address =JOptionPane.showInputDialog("Create Library -" + "\n\n" +
                                                        "Enter Library Address:" + "\n\n");
                aLibrary.setAddress(address);
                exception = false;
            } catch (IllegalArgumentException e) {
                exception = true;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (exception);
        

        //prompting for library's assigned librarian
               exception = true;
        do {
            try {
                assignedLibrarian = createLibrarian();
                aLibrary.setLibrarian(assignedLibrarian);
                exception = false;
            } catch (IllegalArgumentException e) {
                exception = true;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (exception);
        return aLibrary;
    }

    //may be modified in the future to be utilized for multiple libraries, however for now, it will be piloted at Fenwick Library: only one librarian needed
    public static Librarian createLibrarian(){
        String ID = null;
        String name = null;
        String email = null;
        String phone = null;
        Librarian aLibrarian = new Librarian();
        boolean exception = true;

        //prompting for librarian ID
        do {
            try {
                ID =JOptionPane.showInputDialog("Add Librarian -" + "\n\n" +
                                                "Enter Librarian ID" + "\n" + 
                                                "(format: G12345678) " + "\n\n");
                aLibrarian.setID(ID);
                exception = false;
            } catch (IllegalArgumentException e) {
                exception = true;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (exception);
        
        

        //prompting for librarian name
        exception = true;
        do {
            try {
                name =JOptionPane.showInputDialog("Add Librarian -" + "\n\n" +
                                                    "Enter Librarian name" + "\n\n");
                aLibrarian.setName(name);
                exception = false;
            } catch (IllegalArgumentException e) {
                exception = true;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (exception);
        

        //prompting for librarian email
        exception = true;
        do {
            try {
                email =JOptionPane.showInputDialog("Add Librarian -" + "\n\n" +
                                                    "Enter Librarian email" + "\n" +
                                                    "(format: 4-8char@gmu.edu)" + "\n\n");
                aLibrarian.setEmail(email);
                exception = false;
            } catch (IllegalArgumentException e) {
                exception = true;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (exception);
        

        //prompting for librarian phone number
        exception = true;
        do {
            try {
                phone =JOptionPane.showInputDialog("Add Librarian -" + "\n\n" +
                                                    "Enter Librarian phone number" + "\n" +
                                                    "(format: 0123456789)" + "\n\n");
                aLibrarian.setPhone(phone);
                exception = false;
            } catch (IllegalArgumentException e) {
                exception = true;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (exception);
        return aLibrarian;
    }

    public static LibraryItem[] createLibraryItem(LibraryItem[] items){
        String ISBN = null;
        String title = null;
        int numCopies = 0;
        int availableCopies = 0;
        String author = null;
        int edition = 0;
        int publishingYear = 0;
        String type = null;
        int duration = 0;

        Book aBook = null;
        DiscMedia aDisc = null;

        int itemType = 0;
        boolean exception = true;
        int optionChosen = 0;
        
        do {
            do {
                try {
                    itemType = Integer.parseInt(JOptionPane.showInputDialog(null, "Add Item -" + "\n\n" +
                                                                                                "Choose item type:" + "\n" +
                                                                                                "(1) Book" + "\n" +
                                                                                                "(2) Disc Media" + "\n\n"));
                    
                    if(itemType<1 || itemType>2){
                        throw new NumberFormatException();
                    }
                    if(itemType==1){
                        aBook = new Book();
                    }
                    else if(itemType==2){
                        aDisc = new DiscMedia();
                    }
                    exception = false;
                } catch (NumberFormatException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, "Error: type \"1\" for Book or \"2\" for Disc Media" + "\n\n");
                }
                
            } while (exception);
            
            //prompting for item ISBN
            exception = true;
            do {
                try {
                    ISBN = JOptionPane.showInputDialog("Add Item -" + "\n\n" +
                                                        "Enter ISBN" + "\n" +  
                                                        "(format: 13 characters)" + "\n\n");
                    if(itemType==1){
                        aBook.setISBN(ISBN);
                    }
                    else if(itemType==2){
                        aDisc.setISBN(ISBN);
                    }
                    exception = false;
                } catch (IllegalArgumentException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } while (exception);
            
    
            //prompting for item title
            exception = true;
            do {
                try {
                    title =JOptionPane.showInputDialog("Add Item -" + "\n\n" +
                                                        "Enter title" + "\n\n");
                    if(itemType==1){
                        aBook.setTitle(title);
                    }
                    else if(itemType==2){
                        aDisc.setTitle(title);
                    }
                    exception = false;
                } catch (IllegalArgumentException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } while (exception);
            
            
            if(itemType==1){ //item==book

                //prompting for book author
                exception = true;
                do {
                    try {
                        author =JOptionPane.showInputDialog("Add Book -" + "\n\n" +
                                                            "Enter author" + "\n\n");
                        aBook.setAuthor(author);                              
                        exception = false;
                    } catch (IllegalArgumentException e) {
                        exception = true;
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } while (exception);
                
    
                //prompting for book edition
                exception = true;
                do {
                    try {
                        edition = Integer.parseInt(JOptionPane.showInputDialog("Add Book -" + "\n\n" +
                                                                                "Enter edition" + "\n\n"));
                        aBook.setEdition(edition);
                        exception = false;
                    } catch (NumberFormatException e) {
                        exception = true;
                        JOptionPane.showMessageDialog(null, "Error: Edition must be a digit." + "\n\n");
                    } catch (IllegalArgumentException e) {
                        exception = true;
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } while (exception);
                
    
                //prompting for book publishing year
                exception = true;
                do {
                    try {
                        publishingYear = Integer.parseInt(JOptionPane.showInputDialog("Add Book -" + "\n\n" +
                                                                                        "Enter publishing year" + "\n" +
                                                                                        "(format: YYYY)" + "\n\n"));
                        aBook.setPublishingYear(publishingYear);
                        exception = false;
                    } catch (NumberFormatException e) {
                        exception = true;
                        JOptionPane.showMessageDialog(null, "Error: Publishing year must be digits in format YYYY." + "\n\n");
                    } catch (IllegalArgumentException e) {
                        exception = true;
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } while (exception);
                
            }
            else if(itemType==2){ //item==discMedia
                
                //prompting for disc media type
                int typeChosen = 0;
                exception = true;
                do {
                    try {
                        typeChosen = Integer.parseInt(JOptionPane.showInputDialog("Add Disc Media -" + "\n\n" +
                                                                                "Choose disc type:" + "\n" +
                                                                                "(1) CD" + "\n" +
                                                                                "(2) DVD" + "\n" +
                                                                                "(3) BLURAY" + "\n\n"));
                        
                        if(typeChosen<1 || typeChosen>3){
                            throw new NumberFormatException();
                        }

                        if(typeChosen==1){ //disc==CD
                            type = "CD";
                        }
                        else if(typeChosen==2){ //disc==DVD
                            type = "DVD";
                        }
                        else if(typeChosen==3){ //disc==BLURAY
                            type = "BLURAY";
                        }
                        aDisc.setType(type);
                        exception = false;
                    } catch (NumberFormatException e) {
                        exception = true;
                        JOptionPane.showMessageDialog(null, "Error: type \"1\" for CD, \"2\" for DVD, or \"3\" for BLURAY" + "\n\n");
                    } catch (IllegalArgumentException e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } while (exception);
                
                
                //prompting for disc media duration
                exception = true;
                do {
                    try {
                        duration = Integer.parseInt(JOptionPane.showInputDialog("Add Disc Media -" + "\n\n" +
                                                                                 "Enter duration in minutes" + "\n\n"));
                        aDisc.setDuration(duration);
                        exception = false;
                    } catch (NumberFormatException e) {
                        exception = true;
                        JOptionPane.showMessageDialog(null, "Error: Duration must be digits." + "\n\n");
                    } catch (IllegalArgumentException e) {
                        exception = true;
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } while (exception);
                
            }

            //prompting for item num copies
            exception = true;
            do {
                try {
                    numCopies = Integer.parseInt(JOptionPane.showInputDialog("Add Item -" + "\n\n" +
                                                                            "Enter total number of copies in inventory" + "\n\n"));
                    if(itemType==1){
                        aBook.setNumCopies(numCopies);
                    }
                    else if(itemType==2){
                        aDisc.setNumCopies(numCopies);
                    }
                    exception = false;
                } catch (NumberFormatException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, "Error: Number of copies must be in digits." + "\n\n");
                } catch (IllegalArgumentException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } while (exception);
            
            availableCopies = numCopies; //the number of total copies is the amount of available copies before any have been checked out
            String[] checkoutHoldersIDs = new String[numCopies]; //the number of total copies is the maximum number of people who can check out the item
            String[] dueDates = new String[numCopies]; //the number of total copies is the maximum number of due dates for people who can check out the item
            if(itemType==1){
                aBook.setAvailableCopies(availableCopies);
                aBook.setCheckoutHoldersIDs(checkoutHoldersIDs);
                aBook.setDueDates(dueDates);
                items[LibraryItem.numItems-1] = aBook;
            }
            else if(itemType==2){
                aDisc.setAvailableCopies(availableCopies);
                aDisc.setCheckoutHoldersIDs(checkoutHoldersIDs);
                aDisc.setDueDates(dueDates);
                items[LibraryItem.numItems-1] = aDisc;
            }
            optionChosen = JOptionPane.showConfirmDialog(null, "Do you want to add another item?");
        } while (optionChosen==0 && (LibraryItem.numItems<Library.MAX_NUM_ITEMS)); //option "0"=yes

        return items;
    }

    public static StudyRoom[] createStudyRoom(StudyRoom[] rooms){
        int roomNum = 999;
        int size = 2;
        boolean available = true;
        
        boolean exception = true;
        int optionChosen = 0;

        do {
            StudyRoom aStudyRoom = new StudyRoom(roomNum, size, available); //study rooms are available upon creation until they are reserved
            do {
                try {
                    
                    roomNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Add Study Room -" + "\n\n" +
                                                                                                "Enter room number " + "\n\n"));                                                                        
                    aStudyRoom.setRoomNum(roomNum);
                    exception = false;
                } catch (NumberFormatException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, "Error: room number must be in digits." + "\n\n");
                } catch (IllegalArgumentException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } 
            } while (exception);
    
            do {
                try {
                    size = Integer.parseInt(JOptionPane.showInputDialog(null, "Add Study Room -" + "\n\n" +
                                                                                                "Enter room size (maximum number of participants)" + "\n" + 
                                                                                                "(format: can be 2, 4, 6, or 8) " + "\n\n"));
                    aStudyRoom.setSize(size);
                    if(size!=2){ //resetting room counts for this size because the placeholder room was of size 2
                        StudyRoom.rooms_w_size_2--;
                    }
                    if(size==2 && StudyRoom.rooms_w_size_2>=StudyRoom.MAX_ROOMS_W_SIZE_2){
                        throw new IllegalArgumentException();
                    }
                    if(size==4 && StudyRoom.rooms_w_size_4>=StudyRoom.MAX_ROOMS_W_SIZE_4){
                        throw new IllegalArgumentException();
                    }
                    if(size==6 && StudyRoom.rooms_w_size_6>=StudyRoom.MAX_ROOMS_W_SIZE_6){
                        throw new IllegalArgumentException();
                    }
                    if(size==8 && StudyRoom.rooms_w_size_8>= StudyRoom.MAX_ROOMS_W_SIZE_8){
                        throw new IllegalArgumentException();
                    }
                    exception = false;
                } catch (NumberFormatException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, "Error: room size must be in digits." + "\n\n");
                } catch (IllegalArgumentException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } 
            } while (exception);

            rooms[Library.numRooms-1] = aStudyRoom;
            optionChosen = JOptionPane.showConfirmDialog(null, "Do you want to add another study room?");
        } while (optionChosen==0 && (Library.numRooms<Library.MAX_NUM_ROOMS)); //option "0"=yes

        return rooms;
    }

    public static LibraryItem searchLibraryItem(LibraryItem[] items){
        boolean itemFound = false;
        int foundItemIndex = 0;

        String output = "Library Item Search -" + "\n\n";
        for(int i=0; i<LibraryItem.numItems; i++){
            output += "(" + (i+1) +") " + items[i].getTitle() + " | ISBN: " + items[i].getISBN() + "\n";
        }

        //prompting for item search method
        int optionChosen = 0;
        boolean exception = true;
        do {
            try {
                optionChosen = Integer.parseInt(JOptionPane.showInputDialog("Library Item Search -" + "\n\n" +
                                                                        "(1) Enter item ISBN" + "\n" +
                                                                        "(2) Enter item title" + "\n" +
                                                                        "(3) Search available items" + "\n\n"));
                
                if(optionChosen<1 || optionChosen>3){
                    throw new NumberFormatException();
                }
                exception = false;
            } catch (NumberFormatException e) {
                exception = true;
                JOptionPane.showMessageDialog(null, "Error: Choose option 1-3." + "\n\n");
            }
        } while (exception);

        
        if(optionChosen==1){ //search by ISBN
            String searchedISBN = JOptionPane.showInputDialog("Library Item Search -" + "\n\n" +
                                                            "Enter ISBN" + "\n" +  
                                                            "(format: 13 characters)" + "\n\n");
            for(int i=0; i<LibraryItem.numItems; i++){
                if(items[i].getISBN().equalsIgnoreCase(searchedISBN)){
                    itemFound = true;
                    foundItemIndex = i;
                    break;
                }
            }
                
            if(!itemFound){
                optionChosen = 3; //changing option chosen to allow for display of all items
                JOptionPane.showMessageDialog(null, "Error: No such ISBN was found in inventory." + "\n\n");
            }  
        }

        if(optionChosen==2){ //search by title
            int itemCount = 0;
            String searchedTitle = JOptionPane.showInputDialog("Library Item Search -" + "\n\n" +
                                                            "Enter item title" + "\n\n");

            String itemsMatchingTitle = "Library Item Search -" + "\n\n" +
                                        "Items matching title \"" + searchedTitle + "\":" + "\n";

            for(int i=0; i<LibraryItem.numItems; i++){
                if(items[i].getTitle().equalsIgnoreCase(searchedTitle)){
                    itemFound = true;
                    itemsMatchingTitle += "(" + (itemCount+1) + ") " + items[i].getTitle() + " | ISBN: " + items[i].getISBN() + "\n";
                    foundItemIndex = i;
                    itemCount++;
                }
            }

            if(!itemFound){
                optionChosen= 3; //changing option chosen to allow for display of all items
                JOptionPane.showMessageDialog(null, "Error: No such title was found in inventory." + "\n\n");
            }
            else if(itemFound && itemCount>1){
                int foundItemChosen = 0;
                try {
                    foundItemChosen = Integer.parseInt(JOptionPane.showInputDialog(null, itemsMatchingTitle + "\n" +
                                                                                    "Multiple titles match search criteria." + "\n" +
                                                                                    "Choose item by number (#):" + "\n\n"));
                    if(foundItemChosen<=0 || foundItemChosen>itemCount){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: Enter a corresponding number to view item details." + "\n\n");
                }
                
                itemCount = 0;
                for(int i=0; i<LibraryItem.numItems; i++){
                    if(items[i].getTitle().equalsIgnoreCase(searchedTitle)){
                        itemCount++;
                        if(itemCount==foundItemChosen){
                            foundItemIndex = i;
                            break;
                        }
                    }
                }
            }
        }

        if(optionChosen==3){ //search by displaying available items
            exception = true;
            do {
                try {
                    int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(null, output + "\n\n" + 
                                                                                    "Choose item by number (#): " + "\n\n"));
                    if(itemNumber<=0 || itemNumber>LibraryItem.numItems){
                        throw new NumberFormatException();
                    }
                    exception = false;
                    foundItemIndex = itemNumber-1;
                } catch (NumberFormatException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, "Error: Enter a corresponding number to view item details." + "\n\n");
                }
            } while (exception);
        }

        JOptionPane.showMessageDialog(null, "Library Item Search -" + "\n\n" +
                                                            "Item Found:" + "\n" +
                                                            items[foundItemIndex] + "\n\n");

        return items[foundItemIndex];

    }

    public static void itemCheckout(LibraryItem[] items){
        String gmuStudentID = null;
        String studentDueDate = null;
        LibraryItem foundItem = searchLibraryItem(items);
        boolean exception = true;

        if(foundItem==null){
            JOptionPane.showMessageDialog(null, "Error: No item was chosen for checkout, try again." + "\n\n");
        }
        else{
            for(int i=0; i<LibraryItem.numItems; i++){
                if(items[i]==foundItem){
                    if(items[i].getAvailableCopies() > 0){
                        do {
                            try {
                                gmuStudentID = JOptionPane.showInputDialog("Library Item Checkout -" + "\n\n" +
                                                                                "Enter your GMU student ID:" + "\n" +
                                                                                "(format: G12345678) " + "\n\n");
                                
                                if(gmuStudentID==null){
                                    throw new IllegalArgumentException();
                                }
                                else if(gmuStudentID.length()!=9 || gmuStudentID.charAt(0)!='G'){
                                    throw new IllegalArgumentException();
                                }
                                else{
                                    for(int j=1; j<gmuStudentID.length(); j++){
                                        if(!Character.isDigit(gmuStudentID.charAt(j))){
                                            throw new IllegalArgumentException();
                                        }
                                    }
                                }
                                exception = false;
                            } catch (IllegalArgumentException e) {
                                exception = true;
                                JOptionPane.showMessageDialog(null, "Error: Student ID must follow the GMU format." + "\n" +
                                                                                        "  - 9 characters long" + "\n" +
                                                                                        "  - start with G" + "\n" +
                                                                                        "  - all other characters are digits" + "\n\n");
                            }
                        } while (exception);
                        

                        exception = true;
                        do {
                            try {
                                studentDueDate = JOptionPane.showInputDialog("Library Item Checkout -" + "\n\n" +
                                                                                "Enter item due date" + "\n" +
                                                                                "(format: MMDDYYYY)" + "\n\n");    
                                if(studentDueDate==null){
                                    throw new NumberFormatException();
                                }
                                else if(studentDueDate.length()<8 || studentDueDate.length()>8){
                                    throw new NumberFormatException();
                                }
                                else{
                                    int month = Integer.parseInt(studentDueDate.substring(0, 2));
                                    int day = Integer.parseInt(studentDueDate.substring(2, 4));
                                    int year = Integer.parseInt(studentDueDate.substring(4));
                                    
                                    if(month <= 0 || month > 12){
                                        throw new IllegalArgumentException();
                                    }
                                    if(day <= 0 || day > 31){
                                        throw new IllegalArgumentException();
                                    }
                                    if(year <= 0 || year > 3000){ //I don't think we are allowed to import .util, but if we were I would check this against current year
                                        throw new IllegalArgumentException();
                                    }
                                    if(month==2){
                                        if((year % 4 == 0) && (year % 100 !=0) || (year % 400 == 0)){ //checking if february is in a leap year
                                            if(day>29){ //february cannot have more than 29 days (leap year)
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                        else{
                                            if(day>28){ //february cannot have more than 28 days (not leap year)
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                    }
                                    if(month==4 && day>30 || month==6 && day>30 || month==9 && day>30 || month==11 && day>30){ //30 days have september, april, june, and november
                                        throw new IllegalArgumentException();
                                    }
                                }
                                exception = false;
                            } catch(NumberFormatException e){
                                exception = true;
                                JOptionPane.showMessageDialog(null, "Error: Due date must be in the format MMDDYYYY." + "\n\n");
                            } catch (IllegalArgumentException e) {
                                exception = true;
                                JOptionPane.showMessageDialog(null, "Error: Invalid date." + "\n\n");
                            }
                        } while (exception);
                                                                
                                                                                            
                        String[] checkoutHoldersIDs = items[i].getCheckoutHoldersIDs();
                        String[] dueDates = items[i].getDueDates();
                        checkoutHoldersIDs[items[i].getNumCopies()-items[i].getAvailableCopies()]=gmuStudentID;
                        dueDates[items[i].getNumCopies()-items[i].getAvailableCopies()]=studentDueDate;

                        for(int j=0; j<LibraryItem.numItems; j++){
                            if(items[j]==foundItem){
                                items[j].setCheckoutHoldersIDs(checkoutHoldersIDs);
                                items[j].setDueDates(dueDates);
                                break;
                            }
                        }
                        foundItem.setAvailableCopies(foundItem.getAvailableCopies()-1);
                        break;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: All copies of this item are already checked out. Try again later." + "\n\n");
                    }
                }
            }
        }
    }

    public static void reserveRoom(StudyRoom[] rooms){
        boolean roomsAvailable = false;
            for(int i=0; i<Library.numRooms; i++){
                if(rooms[i].getAvailability()==true){
                    roomsAvailable = true;
                    break;
                }
            }

        if(roomsAvailable){
            //prompting for item search method
            boolean exception = true;
            int  optionChosen=0;
            do {
                try {

                  optionChosen  = Integer.parseInt(JOptionPane.showInputDialog("Study Room Reservation -" + "\n\n" +
                                                                            "(1) Reserve by room number" + "\n" +
                                                                            "(2) Reserve by needed participant size" + "\n" +
                                                                            "(3) Search available rooms \n\n"));
                    if(optionChosen<1 || optionChosen>3){
                        throw new NumberFormatException();
                    }
                    exception = false;
                } catch (NumberFormatException e) {
                    exception = true;
                    JOptionPane.showMessageDialog(null, "Error: Choose option 1-3." + "\n\n");
                }
            } while (exception);

            
            int searchedRoomNum = 0;
            boolean roomFound = false;
            int foundRoomIndex = 0;
            if(optionChosen==1){ //search by room number
                try {
                    searchedRoomNum = Integer.parseInt(JOptionPane.showInputDialog("Study Room Reservation -" + "\n\n" +
                                                                                    "Enter room number" + "\n\n"));

                    for(int i=0; i<Library.numRooms; i++){
                        if(rooms[i].getRoomNum()==searchedRoomNum){
                            if(rooms[i].getAvailability()==true){
                                foundRoomIndex = i;
                                roomFound = true;
                                break;
                            }
                            else{
                                throw new IllegalArgumentException();
                            }
                        }
                    }
                    if(!roomFound){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    optionChosen=3; //changing option chosen to allow for display of available rooms
                    JOptionPane.showMessageDialog(null, "Error: No study rooms matching this room number were found." + "\n\n");
                } catch (IllegalArgumentException e){
                    optionChosen=3; //changing option chosen to allow for display of available rooms
                    JOptionPane.showMessageDialog(null, "Error: Room #" + searchedRoomNum + " is not currently available." + "\n\n");
                }
            }

            
            if(optionChosen==2){ //search by needed participant size
                int searchedRoomSize = 0;
                roomFound = false;
                try {
                    searchedRoomSize = Integer.parseInt(JOptionPane.showInputDialog("Study Room Reservation -" + "\n\n" +
                                                                            "Enter number of participants"  + "\n\n"));

                    String roomsMatchingSize = "Study Room Reservation -" + "\n\n" +
                                                "Available rooms matching size needed:" + "\n";

                    for(int i=0; i<Library.numRooms; i++){
                        if(rooms[i].getSize()==searchedRoomSize){
                            if(rooms[i].getAvailability()==true){
                                roomsMatchingSize += rooms[i].getRoomNum() + "\n";
                                roomFound = true;
                            }
                            else{
                                throw new IllegalArgumentException();
                            }
                        }
                    }

                    if(!roomFound){
                        throw new NumberFormatException();
                    }
                    else if(roomFound){
                        searchedRoomNum = Integer.parseInt(JOptionPane.showInputDialog(null, roomsMatchingSize + "\n" +
                                                                                        "Enter room number to reserve" + "\n\n"));
                        
                        for(int i=0; i<Library.numRooms; i++){
                            if(rooms[i].getRoomNum()==searchedRoomNum){
                                if(rooms[i].getAvailability()==true){
                                    foundRoomIndex = i;
                                    roomFound = true;
                                    break;
                                }
                            }
                        }
                        if(!roomFound){
                            throw new IllegalArgumentException();
                        }
                    }
                } catch (NumberFormatException e) {
                    optionChosen=3; //changing option chosen to allow for display of available rooms
                    JOptionPane.showMessageDialog(null, "Error: No study rooms matching this paticipant size were found." + "\n\n");
                } catch (IllegalArgumentException e){
                    optionChosen=3; //changing option chosen to allow for display of available rooms
                    JOptionPane.showMessageDialog(null, "Error: No rooms with a size of " + searchedRoomSize + " are currently available." + "\n\n");
                }
            }

            if(optionChosen==3){ //search by displaying available rooms
                
                    String availableRooms = "Study Room Reservation -" + "\n\n" +
                                        "Available Rooms: " + "\n";
                    for(int i=0; i<Library.numRooms; i++){
                        if(rooms[i].getAvailability()==true){
                            availableRooms += rooms[i].toString() + "\n";
                        }
                    }

                    exception = true;
                    roomFound = false;
                    do {
                        try {
                            searchedRoomNum = Integer.parseInt(JOptionPane.showInputDialog(availableRooms + "\n\n" +
                                                                                    "Enter room number" + "\n\n"));

                            for(int i=0; i<Library.numRooms; i++){
                                if(rooms[i].getRoomNum()==searchedRoomNum){
                                    if(rooms[i].getAvailability()==true){
                                        foundRoomIndex = i;
                                        roomFound = true;
                                        exception = false;
                                        break;
                                    }
                                    else{
                                        throw new IllegalArgumentException();
                                    }
                                }
                            }
                            if(!roomFound){
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException e) {
                            exception = true;
                            JOptionPane.showMessageDialog(null, "Error: No study rooms matching this room number were found." + "\n\n");
                        } catch (IllegalArgumentException e){
                            exception = true;
                            JOptionPane.showMessageDialog(null, "Error: Room #" + searchedRoomNum + " is not currently available." + "\n\n");
                        }
                    } while (exception);
            }

            if(roomFound){
                rooms[foundRoomIndex].setAvailability(false);
                JOptionPane.showMessageDialog(null, "Reservation confirmation -" + "\n\n" +
                                                                rooms[foundRoomIndex].toString() + "\n\n");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Error: No study rooms are currently available for reservation. Try again later." + "\n\n");
        }
    }

    public static void printLibraryInfo(Library aLibrary){
        JOptionPane.showMessageDialog(null, aLibrary.toString() + "\n\n");
    }
}