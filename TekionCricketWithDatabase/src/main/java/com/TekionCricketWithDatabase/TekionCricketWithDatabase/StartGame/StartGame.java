package com.TekionCricketWithDatabase.TekionCricketWithDatabase.StartGame;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;
class Pair {
    public int first;
    public int second;
    public Pair(int first, int second)
    {
        this.first = first;
        this.second = second;
    }
}
public class StartGame {
    private int overs;
    private int wicketsPlayer1;
    private int runsPlayer1;
    private int wicketsPlayer2;
    private int runsPlayer2;
    public int getOvers() {
        return overs;
    }

    public int getWicketsPlayer1() {
        return wicketsPlayer1;
    }

    public int getRunsPlayer1() {
        return runsPlayer1;
    }

    public int getWicketsPlayer2() {
        return wicketsPlayer2;
    }

    public int getRunsPlayer2() {
        return runsPlayer2;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public void setWicketsPlayer1(int wicketsPlayer1) {
        this.wicketsPlayer1 = wicketsPlayer1;
    }

    public void setRunsPlayer1(int runsPlayer1) {
        this.runsPlayer1 = runsPlayer1;
    }

    public void setWicketsPlayer2(int wicketsPlayer2) {
        this.wicketsPlayer2 = wicketsPlayer2;
    }

    public void setRunsPlayer2(int runsPlayer2) {
        this.runsPlayer2 = runsPlayer2;
    }

    LinkedHashMap<String, Pair> ScoreCard1 = new LinkedHashMap<>();
    LinkedHashMap<String, Pair> ScoreCard2 = new LinkedHashMap<>();

    public void gameReset(int overs, int wicketsPlayer1, int runsPlayer1,int wicketsPlayer2,int runsPlayer2)
    {
        this.overs = overs;
        this.wicketsPlayer1 = wicketsPlayer1;
        this.runsPlayer1 = runsPlayer1;
        this.wicketsPlayer2 = wicketsPlayer2;
        this.runsPlayer2= runsPlayer2;
    }
    public void gamePlay(int TotalBalls,int PlayersInATeam)
    {
        int balls1 = overs*6, balls2 = overs*6,  userInput, runs;
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        do
        {
            System.out.println("\nTeam 1 Batting, Team 2 Bowling\n"
                    +"Get Ready, Press '5' to HIT STROKE\n" +"Press '6' for Autoplay Inning (Team 1)\n"
                    +"Press '9' to check SCORE\n");
            switch(input.nextInt())
            {
                case 5:
                {
                    if(balls1==TotalBalls)
                    {
                        System.out.print("\n==========="+1+" OVER=============\n");
                    }
                    int curr_ball=(TotalBalls-balls1)%6 +1;
                    System.out.print("ball "+curr_ball+":");
                    balls1--;
                    runs = rand.nextInt(8);
                    if(runs==7)
                    {
                        System.out.print("Ooops, OUT\n");
                        wicketsPlayer1--;
                    }
                    else if (runs==4)
                    {
                        System.out.print(runs+" runs, Are Bhaiya Ye To Kamaal Ka Shot Mara Hai, 4 Runs!!!\n");
                        runsPlayer1=runsPlayer1+runs;
                    }
                    else if(runs == 6)
                    {
                        System.out.print(runs+" runs, Darshak Ban Gaye Filder, Filder Ban Gaye Darsak, Gend Gayi Seema Rekha Ke Bahar , Six Runs !\n");
                        runsPlayer1=runsPlayer1+runs;
                    }
                    else
                    {
                        System.out.print(runs+" runs\n");
                        runsPlayer1=runsPlayer1+runs;
                    }
                    if(balls1%6==0)
                    {
                        int next_over=(TotalBalls-balls1)/6 +1;
                        System.out.println("\n"+(next_over-1)+" Over Ki Samapti Team 1 ka Score hai\n");
                        scoreBoard(runsPlayer1,PlayersInATeam-wicketsPlayer1,TotalBalls-balls1,TotalBalls,PlayersInATeam);

                        if(balls1!=0)
                            System.out.println("\n==========="+next_over+" OVER=============\n");
                    }
                    saveScoreWithOver(TotalBalls-balls1,runsPlayer1,PlayersInATeam-wicketsPlayer1,ScoreCard1);
                    break;
                }
                case 6:{
                    while(balls1>0)
                    {
                        if(balls1==TotalBalls)
                        {
                            System.out.print("\n==========="+1+" OVER=============\n");
                        }
                        int curr_ball=(TotalBalls-balls1)%6 +1;
                        System.out.print("ball "+curr_ball+":");
                        balls1--;
                        runs = rand.nextInt(8);
                        if(runs==7)
                        {
                            System.out.print("Ooops, OUT\n");
                            wicketsPlayer1--;
                        }
                        else if (runs==4)
                        {
                            System.out.print(runs+" runs, Are Bhaiya Ye To Kamaal Ka Shot Mara Hai, 4 Runs!!!\n");
                            runsPlayer1=runsPlayer1+runs;
                        }
                        else if(runs == 6)
                        {
                            System.out.print(runs+" runs, Darshak Ban Gaye Filder, Filder Ban Gaye Darsak, Gend Gayi Seema Rekha Ke Bahar , Six Runs !\n");
                            runsPlayer1=runsPlayer1+runs;
                        }
                        else
                        { System.out.print(runs+" runs\n");
                            runsPlayer1=runsPlayer1+runs;
                        }
                        if(balls1%6==0)
                        {
                            int next_over=(TotalBalls-balls1)/6 +1;
                            System.out.println("\n"+(next_over-1)+" Over Ki Samapti Team 1 ka Score hai\n");
                            scoreBoard(runsPlayer1,PlayersInATeam-wicketsPlayer1,TotalBalls-balls1,TotalBalls,PlayersInATeam);

                            if(balls1!=0)
                                System.out.println("\n==========="+next_over+" OVER=============\n");
                        }
                    }
                    saveScoreWithOver(TotalBalls-balls1,runsPlayer1,PlayersInATeam-wicketsPlayer1,ScoreCard1);
                    break;
                }
                case 9 :
                {
                    System.out.println("\nTeam 1: SCORE \n");
                    scoreBoard(runsPlayer1,PlayersInATeam-wicketsPlayer1,TotalBalls-balls1,TotalBalls,PlayersInATeam);  // scoreBoard(runs,wickets,overs)
                    break;
                }
                default:  System.out.println("HIT the VALID Key!!!");
            }

        }
        while((wicketsPlayer1>0 && balls1>0));
        System.out.println("\nTeam 1: SCORE \n");
        scoreBoard(runsPlayer1,PlayersInATeam-wicketsPlayer1,TotalBalls-balls1,TotalBalls,PlayersInATeam);
        System.out.println("\n========Inning Over, Break Time========\n");
        System.out.println("\nTeam 2 Batting, get Ready!!!\n");

        do
        {
            System.out.println("\nTeam 2 Batting, Team 1 Bowling\n"
                    +"Get Ready, Press '5' to HIT STROKE\n"+"Press '6' for Autoplay Inning (Team 2)\n"
                    +"Press '9' to check SCORE\n"
                    +"Press '7' to check TARGET to chase\n");
            switch(input.nextInt())
            {
                case 5:
                {
                    if(balls2==TotalBalls)
                    {
                        System.out.println("\n==========="+1+" OVER=============\n");
                    }
                    int curr_ball=(TotalBalls-balls2)%6 +1;
                    System.out.print("ball "+curr_ball+":");
                    balls2--;
                    runs = rand.nextInt(8);
                    if(runs==7)
                    {
                        System.out.print("Ooops, OUT\n");
                        wicketsPlayer2--;
                    }
                    else if (runs==4)
                    {
                        System.out.print(runs+" runs, Are Bhaiya Ye To Kamaal Ka Shot Mara Hai, 4 Runs!!!\n");
                        runsPlayer2=runsPlayer2+runs;
                    }

                    else if(runs == 6)
                    {
                        System.out.print(runs+" runs, Darshak Ban Gaye Filder, Filder Ban Gaye Darsak, Gend Gayi Seema Rekha Ke Bahar , Six Runs \n");
                        runsPlayer2=runsPlayer2+runs;
                    }
                    else
                    {
                        System.out.print(runs+" runs\n");
                        runsPlayer2=runsPlayer2+runs;
                    }
                    if(balls2%6==0)
                    {
                        int next_over=(TotalBalls-balls2)/6 +1;
                        System.out.println("\n"+(next_over-1)+" Over Ki Samapti Team 2 ka Score hai\n");
                        scoreBoard(runsPlayer2,PlayersInATeam-wicketsPlayer2,TotalBalls-balls2,TotalBalls,PlayersInATeam);
                        if(balls2!=0)
                            System.out.println("\n==========="+next_over+" OVER=============\n");
                    }
                    saveScoreWithOver(TotalBalls-balls2,runsPlayer2,PlayersInATeam-wicketsPlayer2,ScoreCard2);
                    break;
                }
                case 6:{
                    while(balls2>0)
                    {
                        if(balls2==TotalBalls)
                        {
                            System.out.println("\n==========="+1+" OVER=============\n");
                        }
                        int curr_ball=(TotalBalls-balls2)%6 +1;
                        System.out.print("ball "+curr_ball+":");
                        balls2--;
                        runs = rand.nextInt(8);
                        if(runs==7)
                        {
                            System.out.print("Ooops, OUT\n");
                            wicketsPlayer2--;
                        }
                        else if (runs==4)
                        {
                            System.out.print(runs+" runs, Are Bhaiya Ye To Kamaal Ka Shot Mara Hai, 4 Runs!!!\n");
                            runsPlayer2=runsPlayer2+runs;
                        }

                        else if(runs == 6)
                        {
                            System.out.print(runs+" runs, Darshak Ban Gaye Filder, Filder Ban Gaye Darsak, Gend Gayi Seema Rekha Ke Bahar , Six Runs \n");
                            runsPlayer2=runsPlayer2+runs;
                        }
                        else
                        {
                            System.out.print(runs+" runs\n");
                            runsPlayer2=runsPlayer2+runs;
                        }
                        if(balls2%6==0)
                        {
                            int next_over=(TotalBalls-balls2)/6 +1;
                            System.out.println("\n"+(next_over-1)+" Over Ki Samapti Team 2 ka Score hai\n");
                            scoreBoard(runsPlayer2,PlayersInATeam-wicketsPlayer2,TotalBalls-balls2,TotalBalls,PlayersInATeam);
                            if(balls2!=0)
                                System.out.println("\n==========="+next_over+" OVER=============\n");
                        }
                    }
                    saveScoreWithOver(TotalBalls-balls2,runsPlayer2,PlayersInATeam-wicketsPlayer2,ScoreCard2);
                    break;
                }
                case 9 :
                {
                    System.out.println("\nTeam 2: SCORE \n");
                    scoreBoard(runsPlayer2,PlayersInATeam-wicketsPlayer2,TotalBalls-balls2,TotalBalls,PlayersInATeam);  // scoreBoard(runs,wickets,overs)
                    break;
                }
                case 7: {
                    System.out.println("\nScore: "+runsPlayer2+"-"+(PlayersInATeam-wicketsPlayer2)+"\nNeed "+(1+(runsPlayer1-runsPlayer2))+" runs in "+(balls2)+ " balls to WIN\n");
                    break;}
                default:  System.out.println("HIT the VALID Key!!!");
            }
        }
        while((wicketsPlayer2>0 && balls2>0 && runsPlayer1>runsPlayer2));
        System.out.println("\nPlayer 2: SCORE \n");
        scoreBoard(runsPlayer2,PlayersInATeam-wicketsPlayer2,TotalBalls-balls2,TotalBalls,PlayersInATeam);
        winStatus();                                         //check the FINAL status of the game

    }
    public void scoreBoard(int runs, int wickets, int overs,int TotalBalls,int PlayersInATeam)
    {
        System.out.println("Score is:\n"
                +"Runs: "+runs+"\n"
                +"Wickets: "+wickets+"\n"
                +"Overs: "+(overs/6)+"."+(overs%6)+"\n");
    }
    public void winStatus()
    {
        if (runsPlayer1 > runsPlayer2 )
        {
            System.out.println("\nTeam 1 wins by "+ (runsPlayer1 - runsPlayer2)+" runs\n" );
        }
        else if (runsPlayer2 > runsPlayer1 )
        {
            System.out.println("\nTeam 2 wins by "+ (runsPlayer2 - runsPlayer1)+" runs\n" );
        }
        else if (runsPlayer1 == runsPlayer2 )
        {
            System.out.println("\n It's a DRAW\n" );

        }
        System.out.println("\n======Match-Over======\n");
        System.out.println("Press 9 for Scorecard\n");
        Scanner input = new Scanner(System.in);
        switch(input.nextInt()){
            case 9:{
                DisplayScoreCard();
                break;
            }
            default:  {
                System.out.println("HIT the VALID Key!!!");
                winStatus();
            }
        }
    }
    public void gameRules()
    {
        System.out.println("Team 1 is going to BAT first\n"
                +"Match will be of "+overs+" overs\n"
                +"Each team will have "+ wicketsPlayer1+" wickets to play\n"
                +"Follow the instructions further along while playing game\n"
                + "CHEERS!!!\n");
    }
    public void saveScoreWithOver(int currBall,int Score,int Wicket,LinkedHashMap<String, Pair> ScoreCard)
    {
        String OverPoint= (currBall/6)+"."+(currBall%6);
        Pair Score_Wicket=new Pair(Score, Wicket);
        ScoreCard.put(OverPoint,Score_Wicket);
    }
    public void DisplayScoreCard()
    {
        System.out.println("\nPress 1 for Team-1 Scorecard and 2 for Team-2 Scorecard and 3 for Quit Score Menu\n");
        Scanner input = new Scanner(System.in);
        switch(input.nextInt()){
            case 1:{
                printHasMap(ScoreCard1);
                System.out.println("\nPress 2 for Team-2 Scorecard and Press any key to Quit Score Menu\n");
                int input1=input.nextInt();
                if(input1==2){
                    printHasMap(ScoreCard2);
                }
                else
                    break;
                break;
            }
            case 2:{
                printHasMap(ScoreCard2);
                System.out.println("\nPress 1 for Team-1 Scorecard and Press any key to Quit Score Menu");
                int input2=input.nextInt();
                if(input2==1){
                    printHasMap(ScoreCard1);
                }
                else
                    break;
            }
            case 3:{
                break;
            }
            default:  {
                System.out.println("HIT the VALID Key!!!");
                DisplayScoreCard();
            }
        }
    }
    public void printHasMap(LinkedHashMap<String, Pair> ScoreCard){
        ScoreCard.forEach((OvePoint,Pair)->System.out.println(OvePoint + " :- " +"{Runs: "+Pair.first+", Wickets: "+Pair.second+"}"));
    }
}
