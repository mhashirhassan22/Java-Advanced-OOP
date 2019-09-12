package com.voting_app;

import java.util.ArrayList;
import java.util.List;

abstract class People {
     String name;
     String cnic;
     int age;
     String email;
     int contact;
     People(){};
    People(String nam, int A) {
        name=nam;
        age=A;
        cnic="Nadra";
    }
    People(String nam, String mail, int A) {
        name=nam;
        age=A;
        email=mail;
        cnic="Nadra";
    }
    People(String nam,int cont, int A) {
        name=nam;
        age=A;
        contact=cont;
        cnic="Nadra";
    }

    People(String nam,String NIC,String mail,int cont, int A) {
        name=nam;
        age=A;
        cnic=NIC;
        contact=cont;
        email=mail;
    }

    abstract void Disp();

}
class Candidate extends People{
    private String PoliticalParty;
    int TotalVotes;
    Candidate(String nam,int age,String PP){
        super(nam,age);
        PoliticalParty=PP;
    }
    private void ChangeInfo(String nam,int A,String PP){
        name=nam;
        age=A;
        PoliticalParty=PP;
    }

    @Override
    void Disp() {
        System.out.print("NAME:\t"+name);
        System.out.print("\nPolitical Party:\t"+PoliticalParty);
        System.out.print("\n--------------------------\n");

    }
}
class Voter extends People {
    boolean CanCast;
    Voter(){}
    Voter(String nam, int A) {
        super(nam, A);
        CanCast = A > 18; //only 18+ people can cast vote
    }

    @Override
    void Disp() {
        System.out.print("\n\tVoter Information\n");
        System.out.print("NAME:\t"+name);
        System.out.print("\nAGE:\t"+age);
        System.out.print("\n--------------------------\n");
    }

    public static void main(String[] args) {
        //Adding Candidates List
        List<Candidate> CandidateList = new ArrayList<Candidate>();
        Candidate c = new Candidate("Khadim Rizwi", 100, "TLP");
        CandidateList.add(c);
        c = new Candidate("Nawaz Shareef", 69, "PML-N");
        CandidateList.add(c);
        c = new Candidate("Sir Zardari", 28, "PPP");
        CandidateList.add(c);
        //Adding Voter List
        List<Voter> VoterList=new ArrayList<Voter>();
        Voter v=new Voter("Shoaib Zahoor",19);
        VoterList.add(v);
        v=new Voter("Qureshi",21);
        VoterList.add(v);
        v=new Voter("Sana Shukira",40);
        VoterList.add(v);
        v=new Voter("Shakeel",80);
        VoterList.add(v);
        Booth VotingBooth=new Booth();
        VotingBooth.CastVote(VoterList,CandidateList,v);
    }
}
