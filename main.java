import java.util.Scanner;
class main implements Initializable {
  public Masu map;
  public Battleship[] ships;

  public static final int[][] startPos = {{0,0},
                                          {0,4},
                                          {4,0},
                                          {4,4}};

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    mapThis = new Masu(5, 5);
    mapOppo = new Masu(5, 5);
    myShips = new Battleship[4];
    theirShips = new Battleship[4];
    for (int i = 0; i<4; i++) {
      myShips[i].setPos(startPos[i]);
    }
  }

  static public void main (String arg[]) {

  }
}
