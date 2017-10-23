package game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Computer implements Serializable{

    private Random randomGenerator;
    private HashMap<Pattern,Integer> map;

    public Computer(){
        randomGenerator = new Random();
        map = new HashMap<Pattern,Integer>();
    }

    public Weapon getRandomWeapon() {
        Weapon[] weapons = Weapon.values();
        int index = randomGenerator.nextInt(weapons.length);
        return weapons[index];
    }

    public void storePattern(Pattern p) {

        int length = p.getPattern().length();
        while(length>1) {
            Integer time = map.get(p);

            if (time == null) {
                map.put(p, 1);
            } else {
                map.put(p, time + 1);
            }

            String pattern = p.getPattern().substring(0,length-1);
            p = new Pattern(pattern);
            length = pattern.length();
        }
    }

    public int getRPS(String patt, String rps){
        Pattern pattern = new Pattern(rps + patt);
        Integer value = map.get(pattern);
        if(value == null){
            return 0;
        }
        return value;
    }

    public Weapon getWeapon(String userPattern) {

        String patt = new String(userPattern);
        while(patt.length() > 0) {
            //System.out.println(patt);
            int r = getRPS(patt, "R");

            int p = getRPS(patt, "P");

            int s = getRPS(patt, "S");
            //System.out.println(r+" "+p+" "+s);
            if ((s - r) > 0 && (s - p) > 0)
                return Weapon.Rock;
            if ((p - s) > 0 && (p - r) > 0)
                return Weapon.Scissors;
            if ((r - s) > 0 && (r - p) > 0)
                return Weapon.Paper;
            if (s == r && r != 0)
                return Weapon.Paper;
            if (s == p && s != 0)
                return Weapon.Rock;
            if (r == p && p != 0)
                return Weapon.Scissors;
            patt = patt.substring(0,patt.length()-1);

        }
        return getRandomWeapon();
    }


    public void getMap() {
        System.out.println("Map size: " + map.size());
        for (Map.Entry<Pattern, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey().getPattern()+ "\t\t" +entry.getValue());
        }
    }
}
