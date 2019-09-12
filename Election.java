package com.voting_app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

abstract class Election {
    abstract void CastVote(List<Voter> vList,List<Candidate> cList,Voter v);
    abstract boolean CheckTime();
    abstract int GetVoter(List<Voter> vList,Voter v,int ind);
    abstract int SelectCandidate(List<Candidate> cList);
}
class Booth extends Election{

    @Override
    void CastVote(List<Voter> vList,List<Candidate> cList,Voter v) {
        System.out.print("\n\tWELCOME TO ELECTION BOOTH\n");
        if(!CheckTime()){
            int ind=0;
            ind =GetVoter(vList,v,ind);
            vList.get(ind).Disp();
            if(ind!=-1){    //Authentication Layer
                vList.get(ind).CanCast=false;
                ind=SelectCandidate(cList);
                cList.get(ind).TotalVotes++;
                System.out.print("Vote Successfully Casted to\n");
                cList.get(ind).Disp();
            }
            else
                System.out.print("Person can not cast vote");
        }
        else{
            if(v.email!=null) {
                if (v.email.contains("@")) {  //Notify via Email iff valid email address is given
                    Notification mail = new Email();   //Association
                    mail.PushNotification();
                }
            }
            else{
                Notification SMS = new SMS();
                SMS.PushNotification();
            }
        }

    }

    @Override
    boolean CheckTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter TimeStyle = DateTimeFormatter.ofPattern("HH");
        String formatTimeS = now.format(TimeStyle);
        return formatTimeS.contains("18");  //Check if 6 pm
    }
    @Override
    int GetVoter(List<Voter> vList,Voter v,int index) { //Utility_Function
        if(v.CanCast){
            for(index=0;index<vList.size();index++) {
                if (v.name.equals(vList.get(index).name)) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    int SelectCandidate(List<Candidate> cList) {    //Print ballot paper to cast vote to a candidate
        Scanner input = new Scanner(System.in);
        for(int i=0;i<cList.size();i++){
            System.out.print("\nPress: "+i+" for\t\n");
            cList.get(i).Disp();
        }
        return input.nextInt();
    }
}
