package LLD.chess;

public class Player extends Account{
    private Person person;
    private Color color;

    private int totalGamesPlayed;

    public Player(Person person, Color color) {
        this.person = person;
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
}
