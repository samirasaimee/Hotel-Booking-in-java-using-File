package hotelbooking;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class HotelProgram
{
    private static boolean MainMenu = true;
    private static boolean SubMenu = true;

    public static void main(String[] args) throws IOException //(Error Handling)
    {
        Scanner input = new Scanner(System.in);
        Room[] myHotel = new Room[10];
        myHotel[0] = new Room();
        myHotel[1] = new Room();
        myHotel[2] = new Room();
        myHotel[3] = new Room();
        myHotel[4] = new Room();
        myHotel[5] = new Room();
        myHotel[6] = new Room();
        myHotel[7] = new Room();
        myHotel[8] = new Room();
        myHotel[9] = new Room();

        int roomNum = 0;
        initialise(myHotel);

        while (MainMenu)
        {
            while (SubMenu)
            {
                System.out.println("*************************************************************************************");
                System.out.println("Hello and Welcome to our Diamond Hotel");
                System.out.println("Please select one of the options.");
                System.out.println("*************************************************************************************");
                System.out.println("A: Book A New Room.");
                System.out.println("E: Display Empty Rooms.");
                System.out.println("V: View all Rooms.");
                System.out.println("D: Delete customer from room.");
                System.out.println("F: Find room from customer name.");
                System.out.println("S: Store program data in to file.");
                System.out.println("L: Load program data from file.");
                System.out.println("******************************************************************************************");


                String Selection = input.next();
                Selection = Selection.toUpperCase();

                switch (Selection)
                {
                    case "A":
                        BookARoom(myHotel, roomNum);
                        break;
                    case "E":
                        CheckIfEmpty(myHotel);
                        break;
                    case "V":
                        ViewAllRooms(myHotel);
                        break;
                    case "D":
                        DeleteCustomerFromRoom(myHotel, roomNum);
                        break;
                    case "F":
                        FindRoomFromCustomerName(myHotel);
                        break;
                    case "S":
                        StoreProgramDataInToFile(myHotel);
                        break;
                    case "L":
                        LoadProgramDataFromFile(myHotel);
                        break;
                    default:
                        System.out.println("Invalid Selection");
                        break;
                }
                System.out.println("********************************************************");
                System.out.println("Would you like to Select another Option\n1 ) Yes\n2 ) No");
                System.out.println("********************************************************");

                if (input.nextInt() == 1)
                {
                    SubMenu = true;
                }
                else
                {
                    SubMenu = false;
                }

            }
            SubMenu = true;
            System.out.println("********************************************************");
            System.out.println("Would You Like To Continue With The Program\n1 ) Yes\n2 ) No");
            System.out.println("***********************************************************");

            if (input.nextInt() == 1)
            {
                MainMenu = true;
            }
            else
            {
                System.out.println("");
                System.exit(0);

            }
        }

    }
    private static void initialise(Room[] myHotel)
    {
        for (int x = 0; x < myHotel.length; x++)
        {
            myHotel[x].setName("nobody");
        }
    }

    //empty room
    private static void CheckIfEmpty(Room[] myHotel)
    {
        for (int x = 0; x < myHotel.length; x++)
        {
            if (myHotel[x].getName().equals("nobody"))
            {
                System.out.println("room " + (x + 1) + " is empty");
            }
            else
            {
                continue;
            }
        }
    }

    //BookARoom
    private static void BookARoom(Room[] myHotel, int roomNum)
    {
        String roomName;
        int x;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter room number (1-10):");
        roomNum = input.nextInt() - 1;

            if (myHotel[roomNum].getName()!=("nobody") )
            {
                System.out.println("Sorry!This room already booked.PLease enter the another room number" );
                System.out.println("Enter room number (1-10):");
                roomNum = input.nextInt() - 1;
                System.out.println("Enter name for room " + (roomNum + 1) + " :");
                roomName = input.next();
                myHotel[roomNum].setName(roomName);
            }
            else
            {

                System.out.println("Enter name for room " + (roomNum + 1) + " :");
                roomName = input.next();
                myHotel[roomNum].setName(roomName);// name set korse
            }



    }

    //ViewAllRooms
    private static void ViewAllRooms(Room[] myHotel)
    {
        for (int x = 0; x < myHotel.length; x++)
        {
            System.out.println("room " + (x + 1) + " occupied by " + myHotel[x].getName());
        }
    }

    //delete customer
    private static void DeleteCustomerFromRoom(Room[] myHotel, int roomNum)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter room number to delete(1-10):");
        roomNum = input.nextInt() - 1;
        myHotel[roomNum].setName("nobody");
        System.out.println("Entery Deleted :)");
    }

    private static void FindRoomFromCustomerName(Room[] myHotel)
    {
        Scanner input = new Scanner(System.in);
        String roomName;
        System.out.println("Enter name to Search for:");
        roomName = input.next();
        int x;
        boolean Checker = false;
        for (x = 0; x < myHotel.length; x++)
        {
            if (roomName.equals(myHotel[x].getName()))
            {
                System.out.println("The Account That Matches That name is room number " + (x+1));
                Checker = true;
            }
        }
        if (Checker == false)
        {
            System.out.println("There are no Rooms Booked with that name\n(make sure you've used the correct CAP's)");
        }
    }

    private static void StoreProgramDataInToFile(Room[] myHotel) throws IOException
    {
        try (PrintWriter out = new PrintWriter(new FileWriter("E:\\Hotel Booking in java  using File\\hotelbooking\\src\\hotelbooking\\StoreProgramData.txt")))
        {
            int x;
            for (x = 0; x < myHotel.length; x++)
            {
                out.println("Name and Room number is: " + myHotel[x].getName() + " at: " + (x+1));
            }

        }
        System.out.println("All Room Names have been Saved.");
    }

    private static void LoadProgramDataFromFile(Room[] myHotel) throws IOException
    {
        FileInputStream fs = new FileInputStream("E:\\Hotel Booking in java  using File\\hotelbooking\\src\\hotelbooking\\LoadProgramData.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < myHotel.length; i++)
        {
            if(myHotel[i].getName()!=("nobody"))
            {
                continue;
            }
            else
            {
                myHotel[i].setName(br.readLine());
            }

        }
    }

    public static class Room
    {

        private String mainName;
        int guestsInRoom;

        public Room()
        {
            mainName = "k";

        }

        public void setName(String aName)
        {

            mainName = aName;
        }

        public String getName()
        {
            return mainName;
        }
    }

}
