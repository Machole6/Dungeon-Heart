package nl.saxion.game.dungeonheart;

public class Scene {

    static int[] Screen = {1,2,3,4,5,6};

    public static String Story(int number) {

        String[] dialogue = {"Chapter 1 \n" +
                "The heroes step into the dungeon as the Heart stirs from a long sleep,\n" +
                " barely aware of the intruders. The halls feel watchful,\n " +
                "as if the walls themselves are learning their presence.\n"
                , "Chapter 2\n" +
                "The Dungeon Heart begins to understand how the heroes fight, \n" +
                "reshaping its defenders to counter them. \n" +
                "Each room feels less random and more intentional.\n"
                , "Chapter 3 \n" +
                "Deeper within, the dungeon reveals memories of past adventurers, \n" +
                "twisted into hostile echoes. The Heart now remembers,\n " +
                "and it does not forget.\n"
                , "Chapter 4 \n" +
                "Stone and flesh merge as the dungeon’s body grows aggressive and cruel.\n" +
                " The heroes realize the dungeon is no longer just reacting it is attacking.\n"
                ,  "Chapter 5\n" +
                "The Dungeon Heart asserts its will,\n" +
                " guiding enemies with purpose and coordination. \n" +
                "Escape paths vanish, and every step forward feels contested.\n"
                ," Chapter 6 \n" +
                "The pulse of the Heart shakes the dungeon as its full consciousness awakens.\n " +
                "The heroes feel its focus tighten, drawing them inevitably toward the core.\n"
                ,  "Chapter 7 \n" +
                "At the center, the Dungeon Heart confronts the heroes directly,\n" +
                " reshaping reality around them. Only one will leave with their will intact.\n"};

        return dialogue[number];
    }


    static String[] ItemDescription = {"Gladiator","Elf", "DarkElf", "Brawler", "Samurai", "Vampire", "Shogun", "Healing", "Harm"};
}

