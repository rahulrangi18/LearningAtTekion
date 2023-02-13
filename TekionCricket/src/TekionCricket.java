import java.util.Scanner;
import StartGame.StartGame;


public class TekionCricket {
     public static void main(String[] args)
     {
        int overs=1,PlayersInATeam,userInput;
        Scanner input = new Scanner(System.in);
        System.out.println("Let's play CRICKET, Let's set some basic rule\n");
        System.out.printf("Enter No. of Overs in game: \n");
        overs=input.nextInt();
        int TotalBalls=6*overs;
        System.out.printf("Enter No. of Players in ̱̱each Team (suggested: 11): \n");
        PlayersInATeam=input.nextInt();

        StartGame game = new StartGame();
        System.out.println("Namashkar Main Aakash Chopra, Swagat krta hu hamare 'Tekion Premier League (TPL) me' \n");
        do
        {
            System.out.println("Ready to play CRICKET, '1' to PLAY, press '2' for Instructions and '0' to QUIT \n");
            userInput = input.nextInt();
            switch(userInput)
            {
                case 0: break;
                case 1:
                {
                    game.gameReset(overs,PlayersInATeam,0,PlayersInATeam,0);     //overs, wicketsPlayer1, runsPlayer1, wicketsPlayer2, runsPlayer2
                    game.gamePlay(TotalBalls,PlayersInATeam);
                    break;
                }
                case 2:
                {
                    game.gameReset(overs,PlayersInATeam,0,PlayersInATeam,0);
                    game.gameRules();
                    break;
                }
                default:  System.out.println("Invalid Input!!!");
            }
        }
        while(userInput != 0);
        System.out.println("Tata Bye-Bye Good Night");
    }
}