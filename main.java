import java.util.Scanner;
class main {
  public static Masu myMap;
  public static Masu theirMap;
  public static Battleship[] myShips;
  public static Battleship[] theirShips;

  public static final int[][] startPos = {{0,0},
                                          {0,4},
                                          {4,0},
                                          {4,4}};

  public static void initialize() {
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

  public static void main (String arg[]) {
    initialize();
  }

  public static void setMapAll (Battleship[] team, Masu map) {
    for (int i=0; i<4; i++) {
      team[i].setMap(map);
    }
  }
}
