package com.uber;

import java.util.List;
import java.util.Scanner;

class Cab {
    private Driver d=new Driver();     //A cab can not exist without Driver
    private Customer c=new Customer(); //A cab "has-a" Customer

    void BookRide(List<Driver> Drivers, Customer cr){
        int size=Drivers.size();
        Driver Rider=new Driver();
        for (Driver driver : Drivers) {
            if (driver.Status)
            {
                Rider = driver;
                break;
            }

        }
        d=Rider;
        c=cr;
        System.out.print("\n\t-----------------------\n");
        System.out.print("Driver is Coming");
        System.out.print("\n\t-----------------------\n");
        System.out.print("Ride Started");
        c.RideStatus=3;
        System.out.print("\n\t-----------------------\n");
        System.out.print("Ride Completed");
        GenerateInvoice();
        GiveFeedback(Drivers,d);
    }
   private int CalculateFare() { //Fare=distance(km)*(time(mins)/constant)
        int Distance = 10; //hard coded from pickup location to destination
        int Time=45;
        int constant=4; //random number depends on Uber Formula
       return Distance*(Time/constant);
    }

   private  void GenerateInvoice() {
        int fare = CalculateFare();
        System.out.print("\n\t-----------------------\n");
        System.out.print("\n\n\t****INVOICE****\n\nTotal Amount To be Paid:\t");
        System.out.print(fare);
       d.Disp();
       c.Disp();
        System.out.print("\nDistance Travelled: 10 km"); //Distance HardCoded for Demo
         System.out.print("\n\t-----------------------\n");
        c.RideStatus=0;

    }
   private void GiveFeedback(List<Driver> Drivers, Driver d){
        int Rating=0,FeedbackPtr=0;
        String s="Nil";
        Scanner input = new Scanner(System.in);
        System.out.println("\n\nEnter Rating 0 to 5");
        Rating=input.nextInt();
        System.out.println("\n\nTell more about Driver!\nEnter:\n1:\tFriendly\n2:\tNot Friendly");
        System.out.println("\n3:tProfessional\n4:\tNot Professional\n0:\tSkip");
        FeedbackPtr=input.nextInt();
        if(FeedbackPtr==1)
            s="Friendly";
        else if(FeedbackPtr==2)
            s="NotFriendly";
        else if(FeedbackPtr==3)
            s="Professional";
        else if(FeedbackPtr==4)
            s="NotProfessional";
        else
            FeedbackPtr=-1;
        int size=Drivers.size();
       for (Driver driver : Drivers) {
           if (driver.name == d.name) {
               if (FeedbackPtr != -1)
                   driver.Feedback.add(s);
               driver.StarRatings.add(Rating);
           }
       }
       System.out.print("\nTHANK YOU FOR USING UBER <3\n");
    }
}
