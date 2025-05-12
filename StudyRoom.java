/***
Name: Myranda Miller
Date: 11/22/22
Course/Section: IT 206-002
Assignment: PA5
Program Description: This class will create the StudyRoom object as well as instantiate all of its variables. It will also aid the implementation class in accessing, mutating, and printing any info generated within the object.
Required input: roomNum, size, available
Expected output: a StudyRoom object
***/


public class StudyRoom {
    //Instance Variables
    private int roomNum;
    private int size;
    private boolean available;
    public static int rooms_w_size_2 = 0;
    public static int rooms_w_size_4 = 0;
    public static int rooms_w_size_6 = 0;
    public static int rooms_w_size_8 = 0;
    public final static int MAX_ROOMS_W_SIZE_2 = 22;
    public final static int MAX_ROOMS_W_SIZE_4 = 15;
    public final static int MAX_ROOMS_W_SIZE_6 = 6;
    public final static int MAX_ROOMS_W_SIZE_8 = 3;


    //Constructors
    StudyRoom(){
        Library.numRooms++;
    }
    StudyRoom(int roomNum, int size, boolean available){
        this();
        setRoomNum(roomNum);
        setSize(size);
        setAvailability(available);
    }
    //Mutators
    public void setRoomNum(int roomNum){
        if(roomNum<=0){
            throw new IllegalArgumentException("Error: Room number cannot be negative." + "\n\n");
        }
        if(roomNum==9999){
            throw new IllegalArgumentException("Error: Room number is already assigned.");
        }
        this.roomNum = roomNum;
    }

    public void setSize(int size){
        if(size==0){
            throw new IllegalArgumentException("Error: Room size cannot be null." + "\n\n");
        }
        else if(size<2){
            throw new IllegalArgumentException("Error: Room size cannot be less than 2 participants." + "\n\n");
        }
        else if(size>8){
            throw new IllegalArgumentException("Error: Room size cannot be more than 8 participants." + "\n\n");
        }
        else if(size!=2 && size !=4 && size!=6 && size!= 8){
            throw new IllegalArgumentException("Error: Room size can only be 2, 4, 6, or 8." + "\n\n");
        }
        
        if(size==2){
            if(rooms_w_size_2>=MAX_ROOMS_W_SIZE_2){
                throw new IllegalArgumentException("Error: Maximum number of rooms with size 2 have already been created." + "\n\n");
            }
            else{
                rooms_w_size_2++;
            }
        }
        else if(size==4){
            if(rooms_w_size_4>=MAX_ROOMS_W_SIZE_4){
                throw new IllegalArgumentException("Error: Maximum number of rooms with size 4 have already been created." + "\n\n");
            }
            else{
                rooms_w_size_4++;
            }
        }
        else if(size==6){
            if(rooms_w_size_6>=MAX_ROOMS_W_SIZE_6){
                throw new IllegalArgumentException("Error: Maximum number of rooms with size 6 have already been created." + "\n\n");
            }
            else{
                rooms_w_size_6++;
            }
        }
        else if(size==8){
            if(rooms_w_size_8>=MAX_ROOMS_W_SIZE_8){
                throw new IllegalArgumentException("Error: Maximum number of rooms with size 8 have already been created." + "\n\n");
            }
            else{
                rooms_w_size_8++;
            }
        }
        this.size = size;
    }

    public void setAvailability(boolean available){
        this.available = available;
    }

    //Accessors
    public int getRoomNum(){
        return this.roomNum;
    }

    public int getSize(){
        return this.size;
    }

    public boolean getAvailability(){
        return this.available;
    }

    //Special Method Purposes
    public String toString(){
        String availbility = null;
        if(getAvailability()==true){
            availbility = "Available";
        }
        else if(getAvailability()==false){
            availbility = "Reserved";
        }

        String output = "    Room -" + "\n" +
                        "        Room Number: " + getRoomNum() + "\n" +
                        "        Maximum Participant Size: " + getSize() + "\n" +
                        "        Availability: " + availbility + "\n";
        return output;
    }
}
