package driver;

import game.Computer;
import game.Pattern;
import game.Player;
import game.Weapon;

import java.io.*;
import java.util.Scanner;


public class Driver {

    public static void main(String[] args) {
        int playerScore = 0;
        int computerScore = 0;
        System.out.println("Welcome to Rock Paper Scissors game!");
        Player player = new Player();
        Computer computer = new Computer();
        Weapon computerWeapon = null;
        Weapon playerWeapon = null;
        String p = "";
        Pattern pattern = new Pattern(p);
        int round = 1;

        File f = new File("computer-data.dat");
        if(f.exists() && !f.isDirectory()) {

            if(playVeteran()) {
                Computer c = loadObject(f);
                if (c != null) {
                    computer = c;
                }
            }
        }

        while(true) {


            if(round < 6){
                computerWeapon = computer.getRandomWeapon();
                playerWeapon = player.getWeapon();
                p = playerWeapon.toString().charAt(0)+p;
                if(round >= 2){
                    computer.storePattern(new Pattern(p));
                }
            }

            else {
                p = p.substring(0,4);
                computerWeapon = computer.getWeapon(p);
                playerWeapon = player.getWeapon();
                p = playerWeapon.toString().charAt(0)+p;
                computer.storePattern(new Pattern(p));

            }
            System.out.println("Computer plays: " + computerWeapon);

            switch (playerWeapon.compareWeapons(computerWeapon)) {

                case 0:
                    System.out.println("It's a Tie!");
                    break;
                case 1:
                    System.out.println("Player wins!");
                    playerScore++;
                    break;
                case -1:
                    System.out.println("Computer wins!");
                    computerScore++;
                    break;
            }

            if(player.playAgain()==false){
                break;
            }
            round++;

        }

        saveObject(computer);
        System.out.println();
        System.out.println("Player:   "+playerScore);
        System.out.println("Computer: "+computerScore);

    }

    public static boolean playVeteran(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Which level do you want to play? "
                +"\n 1. Novice"
                +"\n 2. Veteran");
        int option = sc.nextInt();
        //sc.close();
        if(option == 1)
            return false;
        else if(option == 2)
            return true;
        return playVeteran();
    }

    public static Computer loadObject(File f) {
        Computer computer = null;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(f));
            computer = (Computer)in.readObject();
            System.out.println("Successfully loaded.");
            in.close();
        } catch (IOException e) {
            System.out.println("Error processing file.");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find class.");
        }
        finally {
            return computer;
        }

    }

    public static void saveObject(Computer computer) {
        File f = new File("computer-data.dat");
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(f));
            out.writeObject(computer);
            System.out.println("Computer object saved.");
            out.close();
        } catch (IOException e) {
            System.out.println("Error processing file.");
        }
    }

}
