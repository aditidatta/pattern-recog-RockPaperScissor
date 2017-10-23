package game;


public enum Weapon{
    Rock, Paper, Scissors;

    public int compareWeapons(Weapon otherWeapon) {

        if (this == otherWeapon)
            return 0;

        switch (this) {
            case Rock:
                return (otherWeapon == Scissors ? 1 : -1);
            case Paper:
                return (otherWeapon == Rock ? 1 : -1);
            case Scissors:
                return (otherWeapon == Paper ? 1 : -1);
        }

        return 0;
    }
}
