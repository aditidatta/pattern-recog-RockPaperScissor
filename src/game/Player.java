package game;

import java.util.Scanner;

public class Player {
    private Scanner sc;

    public Player() {
        sc = new Scanner(System.in);
    }

    public Weapon getWeapon() {

        System.out.print("[R]ock, [P]aper, or [S]cissors? ");

        char weapon = sc.nextLine().toUpperCase().charAt(0);

        if (weapon == 'R' || weapon == 'P' || weapon == 'S') {

            switch (weapon) {
                case 'R':
                    return Weapon.Rock;
                case 'P':
                    return Weapon.Paper;
                case 'S':
                    return Weapon.Scissors;
            }
        }

        // Player has not entered a valid input. Prompt again.
        return getWeapon();
    }

    public boolean playAgain() {
        System.out.print("Do you want to play again [y/n]? ");
        String input = sc.nextLine().toUpperCase();
        return (input.charAt(0) == 'Y');
    }
}
