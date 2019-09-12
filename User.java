package com.uber;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

abstract class User {
    String name;
    private String email;
    private String password;
    int contact;
    User(){
        contact= 1001;
    }
    User(String nam,String mail,String pass,int cont)
    {
        name=nam;
        email=mail;
        password=pass;
        contact=cont;
    }
    abstract void  Disp();
    public static void main(String[] args) {
        //DemoDataEntry
        List<Driver> DriversList=new ArrayList<Driver>();
        //List of Drivers instead of List<Users> so to give access to members of driver class (feedback,status etc)
        Driver d=new Driver("DemoDriver0","DemoDriver0@gmail.com","DemoDriver0666","ler2334","Sedan");
        DriversList.add(d);
        d=new Driver("DemoDriver1","DemoDriver1@gmail.com","DemoDriver1666", 440744311);
        DriversList.add(d);
        d=new Driver("DemoDriver2","DemoDriver2@gmail.com","DemoDriver2666", 440744311);
        DriversList.add(d);
        d=new Driver("DemoDriver3","DemoDriver3@gmail.com","DemoDriver3666","leb7123","Sedan");
        DriversList.add(d);
        d=new Driver("DemoDriver4","DemoDriver4@gmail.com","DemoDriver4666");
        DriversList.add(d);
        d=new Driver("DemoDriver5","DemoDriver5@gmail.com","DemoDriver5666");
        DriversList.add(d);
        //CurrentUser is Customer here after sign in / sign up module
        Customer CurrentUser=new Customer("Ali","Ali@gmail.com","Henny666", 3021125);
        CurrentUser.BookRide(DriversList);

    }

}
class Driver extends User{
    boolean Status; //By Default all drivers will be set to ONLINE (true). To switch status invoke ChangeStatus();
    List<String> Feedback;
    List<Integer> StarRatings;
    private Car c; //In Uber, A Driver "Has-a" Car (not necessarily the opposite)
    static class Car {
        String NumberPlate;
        String Type;
        Car()
        {
            NumberPlate="AAA 1234";
            Type="Sedan";
        }
        Car(String no,String typ){
            NumberPlate=no;
            Type=typ;
        }
    }
    Driver(){
        Status=true;
        Feedback=new ArrayList<String>();
        StarRatings=new ArrayList<Integer>();
        c=new Car();

    }
    Driver(String nam, String mail,String pass){
        super(nam,mail,pass,1234);
        Feedback=new ArrayList<String>();
        StarRatings=new ArrayList<Integer>();
        Status=true;
        c=new Car();
    }
    Driver(String nam, String mail,String pass,int cont){
        super(nam,mail,pass,cont);
        Feedback=new ArrayList<String>();
        StarRatings=new ArrayList<Integer>();
        Status=true;
        c=new Car();
    }
    Driver(String nam, String mail,String pass,String no,String typ){
        super(nam,mail,pass,1234);
        Feedback=new ArrayList<String>();
        StarRatings=new ArrayList<Integer>();
        Status=true;
        c=new Car(no,typ);
    }

    @Override
    void Disp() {
        System.out.print("\t\nDRIVER INFORMATION\n");
        System.out.print("\nDriver Name:\t"+name);
        int avg=0,size=StarRatings.size();
        if(size>0){
            for (Integer starRating : StarRatings) {
                avg = avg + starRating;
            }
            avg=avg/size;
        }
        System.out.print("\nCar Number:\t"+c.NumberPlate);
        System.out.print("\nAverage Rating:\t"+avg);
        System.out.print("\n\t-----------------------\n");
    }

    void ChangeStatus(boolean st){//true for online. false for offline
        Status=st;
    }

}
class Customer extends User{
    int RideStatus; //0 for NO_RIDE_ORDERD .. 1 for Waiting_for_Ride .. 2 for InRide .. 3 for InvoiceStage
    Customer(){
    RideStatus=0;
    }
    Customer(String nam,String mail,String pass,int cont){
        super(nam, mail, pass, cont);
        RideStatus=0;
    }

    @Override
    void Disp() {
        System.out.print("\tCustomer INFORMATION\n");
        System.out.print("\nCustomer Name:\t"+name);
        System.out.print("\nCustomer Contact:\t"+contact);
        System.out.print("\n\t-----------------------\n");
    }

    void BookRide(List<Driver> Drivers){
        RideStatus=1;
        System.out.print("Enter Pickup location\n");
        System.out.print("Pickup point set to current location for demo\n");
        System.out.print("Enter Destination\n");
        System.out.print("Destination Set to Center of City for demo\n");
        Cab c=new Cab();
        if(RideStatus!=3)   //i.e User has completed previous ride (if-any)
            c.BookRide(Drivers,this);

    }

}
