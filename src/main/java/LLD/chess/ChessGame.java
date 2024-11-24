package LLD.chess;

public class ChessGame {
   private static final String STATUS_WHITE_MOVE = "white_move";
    private static final String STATUS_BLACK_MOVE = "black_move";
    private static final String STATUS_WHITE_VICTORY = "white_victory";
    private static final String STATUS_BLACK_VICTORY = "black_victory";
  private final StateRenderer renderer;
    private Board board;
    private boolean finished;
    private String status;

    public ChessGame(StateRenderer renderer) {
        this.board = new Board();
        this.finished = false;
        this.renderer = renderer;
        this.status = STATUS_WHITE_MOVE;
    }

    public void run() {
     GameState gameState =    getGameState();
     renderer.renderState(gameState);
     while (!finished){
         Command command = parseCommand();
         if(tryMove(command)){
             board.executeMove(command);
             if  (status .equals(STATUS_WHITE_MOVE)){

                 status = ChessGame.STATUS_BLACK_MOVE;
             }
             else if( status.equals(  ChessGame.STATUS_BLACK_MOVE)){

                 status = ChessGame.STATUS_WHITE_MOVE;
             }
             renderer.renderState( this.getGameState());
         }

     }


    }

    private boolean tryMove(Command command) {
        return false;
    }

    private Command parseCommand() {
            return null;
    }

    private GameState getGameState() {
            return null;
    }
}
