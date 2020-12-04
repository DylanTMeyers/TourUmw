package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Baseball {
    private ArrayList<String> baseballTeam;
    private ArrayList<String> compSciTeam;
    private ArrayList<Integer> baseballTeamAverages;
    private ArrayList<Integer> compSciTeamAverages;
    private ArrayList<String> bases;
    private Boolean nerdsCheer = false;
    private Boolean jocksCheer = false;
    private Boolean leave = false;
public Baseball() {

}
public void letsPlay() throws InterruptedException {
    ArrayList<String> baseballTeam = new ArrayList<>();
    ArrayList<String> compSciTeam = new ArrayList<>();
    ArrayList<Integer> baseballTeamAverages = new ArrayList<>();
    ArrayList<Integer> compSciTeamAverages = new ArrayList<>();
    ArrayList<String> bases = new ArrayList<>(3);
	 bases.add("");
     bases.add("");
     bases.add("");
     baseballTeamAverages.add(70);
     baseballTeamAverages.add(100);
     baseballTeamAverages.add(70);
     baseballTeamAverages.add(10);
     baseballTeamAverages.add(70);

     compSciTeamAverages.add(70);
     compSciTeamAverages.add(100);
     compSciTeamAverages.add(70);
     compSciTeamAverages.add(10);
     compSciTeamAverages.add(70);
     baseballTeam.add("Chris Rubano*Right Field");
     baseballTeam.add("Jonathan Sedmak*Third Baseman");
     baseballTeam.add("Garrett Lyons*Center Field");
     baseballTeam.add("Christian Hague*First Baseman");
     baseballTeam.add("Norman Holcomb*Left Field");
     baseballTeam.add("Parker Wilburn*Designated Hitter");
     baseballTeam.add("Xavier Herring*Second Baseman");
     baseballTeam.add("Andrew Gerhart*Catcher");
     baseballTeam.add("Noah Angstadt*Shortstop");
     baseballTeam.add("Cole Gabriele*Pitcher");
     

     compSciTeam.add("Karen Anewalt*Third Baseman");
     compSciTeam.add("Jessica Zeitz*Designated Hitter");
     compSciTeam.add("Gusty Cooper*Second Baseman");
     compSciTeam.add("Stephen Davies*First Baseman");
     compSciTeam.add("Ian finlayson*Shortstop");
     compSciTeam.add("Veena Ravishankar*Catcher");
     compSciTeam.add("Dylan Meyers*Center Field");
     compSciTeam.add("Tessa Lanzafame*Right Field");
     compSciTeam.add("Justin Daniels*Left Field");
     compSciTeam.add("Arsalan Ahmad*Pitcher");
	 System.out.println("The UMW BASEBALL TEAM LINEUP:");
     for(int i = 0; i<10; i++){
         String[] baseballPlayer;
         baseballPlayer = baseballTeam.get(i).split("\\*");
         System.out.println(baseballPlayer[0] + " is the " + baseballPlayer[1]);
         System.out.println();
         Thread.sleep(2000);
     }
     System.out.println("The COMPUTER NERDS LINEUP:");
     for(int i = 0; i<10; i++){
         String[] baseballPlayer;
         baseballPlayer = compSciTeam.get(i).split("\\*");
         System.out.println(baseballPlayer[0] + " is the " + baseballPlayer[1]);
         System.out.println();
         Thread.sleep(2000);
     }
     for(int extraSpace = 0; extraSpace< 30; extraSpace++){
         System.out.println();
     }
     int s1 = 0;
     int s2 = 0;
     int q = 0;
     int z  = 0;
     for(int l = 0; l<3; l++) {
    	 if(leave) {
    		 leave = false;
    		 break;
    	 }
         for(int n = 0; n<2; n++) {
        	 System.out.println(compSciTeamAverages);
        	 if(leave) {
        		break;
        	 }
             boolean threeOuts = false;
             int k = 0 ;
             if(n ==0) {
                 System.out.println("Top of the inning (The Baseball team hits now)");
                 System.out.println();
                 while(!threeOuts) {
                     String[] baseballPlayer;
                     String[] pitcher;
                     Random rand = new Random();
                     int outcome = rand.nextInt(1000);
                     baseballPlayer = baseballTeam.get(q).split("\\*");
                     pitcher = compSciTeam.get(9).split("\\*");
                     System.out.println(baseballPlayer[0] + " walks up to the batter box....");
                     System.out.println();
                     System.out.println(pitcher[0] + " throws the pitch and......");
                     System.out.println();
                     System.out.println();
                     Thread.sleep(2000);
                     if(baseballTeamAverages.get(0)>outcome) {
                         System.out.println(baseballPlayer[0] + " walks");
                         if(!bases.get(0).equals("")) {
                             String player1;
                             String player2;
                             String player3;
                             player1 = bases.get(0);
                             player2 = bases.get(1);
                             player3 = bases.get(2);
                             bases.set(0, baseballPlayer[0]);
                             bases.set(1, player1);
                             if(!player2.equals("")){
                                 if(!player3.equals("")) {
                                     s1++;
                                 }
                                 else {
                                     bases.set(2, player2);
                                 }
                             }
                         }
                         else {
                             bases.set(0, baseballPlayer[0]);
                         }
                     }else if(baseballTeamAverages.get(0)+baseballTeamAverages.get(1)>outcome) {
                         System.out.println(baseballPlayer[0] + " hits a single");
                         if(!bases.get(0).equals("")) {
                             String player1;
                             String player2;
                             String player3;
                             player1 = bases.get(0);
                             player2 = bases.get(1);
                             player3 = bases.get(2);
                             bases.set(0, baseballPlayer[0]);
                             bases.set(1, player1);
                             if(!player2.equals("")){
                                 if(!player3.equals("")) {
                                     s1++;
                                     System.out.println(player3 + " SCORES!");
                                 }
                                 else {
                                     bases.set(2, player2);
                                 }
                             }
                         }
                         else {
                             bases.set(0, baseballPlayer[0]);
                         }
                     }else if(baseballTeamAverages.get(0)+baseballTeamAverages.get(1) +baseballTeamAverages.get(2)> outcome) {
                         System.out.println(baseballPlayer[0] + " hits a double!");
                         if(!bases.get(0).equals("")) {
                             String player1;
                             String player2;
                             String player3;
                             player1 = bases.get(0);
                             player2 = bases.get(1);
                             player3 = bases.get(2);
                             bases.set(0,"");
                             bases.set(1, baseballPlayer[0]);
                             bases.set(2, player1);
                             if(!player2.equals("")){
                                 s1++;
                                 System.out.println(player2 + " SCORES!");
                                 if(!player3.equals("")) {
                                     s1++;
                                     System.out.println(player3 + " SCORES!");
                                 }
                             }
                         }
                         else {
                             bases.set(1, baseballPlayer[0]);
                         }
                     }else if(baseballTeamAverages.get(0)+baseballTeamAverages.get(1) + baseballTeamAverages.get(2)+ baseballTeamAverages.get(3)> outcome) {
                         System.out.println(baseballPlayer[0]+ " hits a TRIPLE!");
                         for(int y = 0; y <3;y++) {
                             if(bases.get(y).length()>1) {

                                 s1++;
                                 System.out.println(bases.get(y) + " SCORES!");
                                 bases.set(y, "");

                             }

                         }
                         bases.set(2, baseballPlayer[0]);
                     }else if(baseballTeamAverages.get(0)+baseballTeamAverages.get(1) + baseballTeamAverages.get(2)+ baseballTeamAverages.get(3) + baseballTeamAverages.get(4)> outcome) {
                         System.out.println("Holy peanuts and Crackerjacks " + baseballPlayer[0] + " hits a HOMER!");
                         for(int y = 0; y<3; y++) {
                             if(bases.get(y).length()>1) {
                                 s1++;
                                 System.out.println(bases.get(y) + " SCORES!");
                                 bases.set(y, "");
                             }
                         }
                         System.out.println(baseballPlayer[0] + " SCORES!");
                         s1++;
                     }
                     else {
                         Random player = new Random();
                         int randomPlayer = player.nextInt(9);
                         String[] randomOut = compSciTeam.get(randomPlayer).split("\\*");
                         System.out.println(baseballPlayer[0] + " hits the ball");
                         System.out.println();
                         System.out.println();
                         System.out.println(randomOut[0] + " catches the out!");
                         k++;
                         System.out.println("That makes the " + k + " outs ");
                     }
                     q++;
                     if(q ==9){
                         q = 0;
                     }
                     System.out.println();
                     System.out.println("THE SCORE");
                     System.out.println();
                     System.out.println("UMW Baseball team: " + s1);
                     System.out.println("The Computer Nerds: " + s2);
                     System.out.println();
                     System.out.println();
                     for(int g = 0; g<3; g++) {
                         int p = g + 1;
                         if (bases.get(g).equals("") && p == 1  ) {
                             System.out.println("Nobody is at " + p + "st base");
                         }else if (bases.get(g).equals("") && p == 2  ) {
                             System.out.println("Nobody is at " + p + "nd base");
                         }else if (bases.get(g).equals("") && p == 3  ) {
                             System.out.println("Nobody is at " + p + "rd base");
                         } else if(p == 1) {
                             System.out.println(bases.get(g) + " is at " + p + "st base");
                         }else if(p == 2) {
                             System.out.println(bases.get(g) + " is at " + p + "nd base");
                         }else if(p == 3) {
                             System.out.println(bases.get(g) + " is at " + p + "rd base");
                         }

                     }
                     if(k == 3) {
                         threeOuts = true;
                     }Thread.sleep(6000);
                     for(int extraSpace = 0; extraSpace< 30; extraSpace++){
                         System.out.println();
                     }
                 }
             }

             if (n == 1) {
                 System.out.println("Bottom of the inning (The Computer Science team hits now)");
                 System.out.println();
                 while(!threeOuts) {
                     String[] baseballPlayer;
                     String[] pitcher;
                     Random rand = new Random();
                     int outcome = rand.nextInt(1000);
                     baseballPlayer = compSciTeam.get(z).split("\\*");
                     pitcher = baseballTeam.get(9).split("\\*");
                     System.out.println(baseballPlayer[0] + " walks up to the batter box....");
                     System.out.println();
                     System.out.println(pitcher[0] + " throws the pitch and......");
                     System.out.println();
                     System.out.println();
                     Thread.sleep(2000);
                     if(compSciTeamAverages.get(0)>outcome) {
                         System.out.println(baseballPlayer[0] + " walks");
                         if(!bases.get(0).equals("")) {
                             String player1;
                             String player2;
                             String player3;
                             player1 = bases.get(0);
                             player2 = bases.get(1);
                             player3 = bases.get(2);
                             bases.set(0, baseballPlayer[0]);
                             bases.set(1, player1);
                             if(!player2.equals("")){
                                 if(!player3.equals("")) {
                                     s2++;
                                     System.out.println(player3 + " SCORES!");
                                 }
                                 else {
                                     bases.set(2, player2);
                                 }
                             }
                         }
                         else {
                             bases.set(0, baseballPlayer[0]);
                         }
                     }else if(compSciTeamAverages.get(0)+compSciTeamAverages.get(1)>outcome) {
                         System.out.println(baseballPlayer[0] + " hits a single");
                         if(!bases.get(0).equals("")) {
                             String player1;
                             String player2;
                             String player3;
                             player1 = bases.get(0);
                             player2 = bases.get(1);
                             player3 = bases.get(2);
                             bases.set(0, baseballPlayer[0]);
                             bases.set(1, player1);
                             if(!player2.equals("")){
                                 if(!player3.equals("")) {
                                     s2++;
                                     System.out.println(player3 + " SCORES!");
                                 }
                                 else {
                                     bases.set(2, player2);
                                 }
                             }
                         }
                         else {
                             bases.set(0, baseballPlayer[0]);
                         }
                     }else if(compSciTeamAverages.get(0)+compSciTeamAverages.get(1) + compSciTeamAverages.get(2)> outcome) {
                         System.out.println(baseballPlayer[0] + " hits a double!");
                         if(!bases.get(0).equals("")) {
                             String player1;
                             String player2;
                             String player3;
                             player1 = bases.get(0);
                             player2 = bases.get(1);
                             player3 = bases.get(2);
                             bases.set(0,"");
                             bases.set(1, baseballPlayer[0]);
                             bases.set(2, player1);
                             if(!player2.equals("")){
                                 s2++;
                                 System.out.println(player2 + " SCORES!");
                                 if(!player3.equals("")) {
                                     s2++;
                                     System.out.println(player3 + " SCORES!");
                                 }
                             }
                         }
                         else {
                             bases.set(1, baseballPlayer[0]);
                         }
                     }else if(compSciTeamAverages.get(0)+compSciTeamAverages.get(1) + compSciTeamAverages.get(2)+ compSciTeamAverages.get(3)> outcome) {
                         System.out.println(baseballPlayer[0] + " hits a TRIPLE!");
                         for(int y = 0; y <3;y++) {
                             if(bases.get(y).length()>1) {

                                 s2++;
                                 System.out.println(bases.get(y) + " SCORES!");
                                 bases.set(y, "");

                             }

                         }
                         bases.set(2, baseballPlayer[0]);
                     }else if(compSciTeamAverages.get(0)+compSciTeamAverages.get(1) + compSciTeamAverages.get(2)+ compSciTeamAverages.get(3) + compSciTeamAverages.get(4)> outcome) {
                         System.out.println("Holy peanuts and Crackerjacks " + baseballPlayer[0] + " hits a HOMER!");
                         for(int y = 0; y <3;y++) {
                             if(bases.get(y).length()>1) {

                                 s2++;
                                 System.out.println(bases.get(y) + " SCORES!");
                                 bases.set(y, "");

                             }

                         }
                         System.out.println(baseballPlayer[0] + " SCORES!");
                         s2++;
                     }
                     else {
                         Random player = new Random();
                         int randomPlayer = player.nextInt(9);
                         String[] randomOut = baseballTeam.get(randomPlayer).split("\\*");
                         System.out.println(baseballPlayer[0] + " hits the ball");
                         System.out.println();
                         System.out.println();
                         System.out.println(randomOut[0] + " catches the out!");
                         k++;
                         System.out.println("That makes the " + k + " outs ");
                     }
                     System.out.println();
                     System.out.println("THE SCORE");
                     System.out.println();
                     System.out.println("UMW Baseball team: " + s1);
                     System.out.println("The Computer Nerds: " + s2);
                     System.out.println();
                     System.out.println();
                     z++;
                     if(z ==9){
                         z = 0;
                     }
                     for(int g = 0; g<3; g++) {
                         int p = g + 1;
                         if (bases.get(g).equals("") && p == 1  ) {
                             System.out.println("Nobody is at " + p + "st base");
                         }else if (bases.get(g).equals("") && p == 2  ) {
                             System.out.println("Nobody is at " + p + "nd base");
                         }else if (bases.get(g).equals("") && p == 3  ) {
                             System.out.println("Nobody is at " + p + "rd base");
                         } else if(p == 1) {
                             System.out.println(bases.get(g) + " is at " + p + "st base");
                         }else if(p == 2) {
                             System.out.println(bases.get(g) + " is at " + p + "nd base");
                         }else if(p == 3) {
                             System.out.println(bases.get(g) + " is at " + p + "rd base");
                         }

                     }
                     if(k == 3) {
                         threeOuts = true;
                     }
                     Thread.sleep(6000);
                     for(int extraSpace = 0; extraSpace< 30; extraSpace++){
                         System.out.println();
                     }
                 }
             }

         bases.set(0, "");
             bases.set(1, "");
             bases.set(2, "");

         }
         if(l!=2) {
         CheerLevels(); 
         if(jocksCheer) {
        	 for( int average = 0; average<  baseballTeamAverages.size(); average++) {
        		 baseballTeamAverages.set(average, baseballTeamAverages.get(average) + 30);
        	 }
         }
         else if(nerdsCheer) {
        	 for( int average = 0; average<  compSciTeamAverages.size(); average++) {
        		 compSciTeamAverages.set(average, compSciTeamAverages.get(average) + 30);
        	 }
         }else if(leave) {
        	 break;
         }
         }
     }
     if(!leave) {
     if(s1>s2){
         System.out.println("UMW baseball team wins.. cool");
     }
     else if(s2>s1){
         System.out.println("COMPUTER NERDS WIN!!!");
     }
     else{
         System.out.println("The games tie.. what a ball game");
     }
     }
	// write your code here
}

public void CheerLevels() {
	Scanner stdn = new Scanner(System.in);
	System.out.println("Would you like to cheer on the nerds or the jocks?");
	System.out.println("You can input whatever cheer you would like it but it has to contain 'go [nerds] or [jocks]' ");
	System.out.println("If you would like to leave just type [Leave]");
	String cheer = stdn.nextLine();
	if(cheer.toLowerCase().contains("go nerds")) {
		nerdsCheer = true;
		jocksCheer = false;
	}
	else if(cheer.toLowerCase().contains("go jocks")) {
		jocksCheer = true;
		nerdsCheer = false;
	}else if(cheer.toLowerCase().contains("leave")) {
		leave = true;
	}

}

}
