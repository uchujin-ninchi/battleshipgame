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
    myMap = new Masu(5, 5);
    theirMap = new Masu(5, 5);

    myShips = new Battleship[4];
    theirShips = new Battleship[4];

    for (int i = 0; i<4; i++) {
      myShips[i].setPos(startPos[i]);
    }

    setMapAll(myShips, myMap);
    setMapAll(theirShips, theirMap);
  }

  static public void main (String arg[]) {

  }

  public void setMapAll (Battleship[4] team, Masu map) {
    for (int i=0; i<4; i++) {
      team[i].setMap(map);
    }
  }
}
