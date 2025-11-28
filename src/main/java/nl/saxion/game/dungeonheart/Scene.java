package nl.saxion.game.dungeonheart;

public class Scene {

    public static String Story(int number) {

        String[] dialogue = {"Peter Piper picked a \n" +
                "peck of pickled peppers. \n" +
                "A peck of pickled peppers \n" +
                "Peter Piper picked. \n" +
                "If Peter Piper picked a peck of\n" +
                " pickled peppers, \n" +
                "Where’s the peck of pickled \n" +
                "peppers Peter Piper picked? ",

                "How much wood would a woodchuck chuck " +
                        "if a woodchuck could chuck wood?\n" +
                        "He would chuck, he would, as much as he could," +
                        " and chuck as much wood\n" +
                        "As a woodchuck would if a woodchuck " +
                        "could chuck wood"};

        return dialogue[number];
    }
}

