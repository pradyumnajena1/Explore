package LLD.snakegame;

public interface Snake {

    public boolean isWithinBody(Position nextPosition);
    public void changeSize();

    void makeMove(Position nextPosition);

    Position getHead();
}
