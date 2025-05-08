package three_;

public class Pokemon {
    public String name;
    public int level;
    public static String trainer = "Ash";
    public static int partySize = 0;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
        this.partySize += 1;
    }

    public static void main(String[] args) {
        Pokemon p = new Pokemon("Pikachu", 17);
        Pokemon j = new Pokemon("Jolteon", 99);
        System.out.println("Party size: " + Pokemon.partySize);
        p.printStats();
        int level = 18;
        Pokemon.change(p, level);
        p.printStats();
        Pokemon.trainer = "Ash";
        j.trainer = "Cynthia";
        p.printStats();
        /* TODO
                Party size: 2
                Pikachu 17 Ash
                Luxray 1 Team Rocket
                Luxary 1 Cynthia
            */

        /* TODO Error
                Answer:
                Party Size: 2
                Pikachu 17 Ash
                Pikachu 18 Team Rocket
                Pikachu 18 Cynthia
                错误在于Java传递的是值，是地址，而非是p
                在change方法中，改变的是局部变量poke的地址，是他自己局部环境中，而非全局环境
        */
    }
    public static void change(Pokemon poke, int level) {
        poke.level = level;
        level = 50;  //todo 静态方法只能修改静态变量
        poke = new Pokemon("Luxray", 1);
        poke.trainer = "Team Rocket";
    }

    public void printStats() {
        System.out.println(name + " " + level + " " + trainer);
    }
}

