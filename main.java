import java.util.Scanner;
import java.util.ArrayList;

class main {
  public static final int TYPE_ATTACK = 1;
  public static final int TYPE_FLEE = 0;

  public static Masu myMap;
  public static Masu theirMap;
  public static Battleship[] myShips;
  public static Battleship[] theirShips;
  public static ArrayList<int[]> theyAttacked;  
  public static ArrayList<int[]> weAttacked;
  public static int winner = 0;

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
      myMap.setMap(startPos[i], Masu.TYPE_SHIP);
    }

    setMapAll(myShips, myMap);
    setMapAll(theirShips, theirMap);
  }

  public static void main (String arg[]) {
    initialize();
    Scanner sc = new Scanner(System.in);
    System.err.print("Input which side to start first (this = 1, that = 0): ");
    int stSide = sc.nextInt();
    int turn = 1;

    while (true) {
      if (turn=1 && stSide=1) {
        myActions();
      }

      theirActions();
      myActions();

      System.err.println("End of turn " + turn + ".");

      turn++;

      if (end()) {
        stateWinner();
        break;
      }
    }
  }

  public static void setMapAll (Battleship[] team, Masu map) {
    for (int i=0; i<4; i++) {
      team[i].setMap(map);
    }
  }

  public static void theirActions() {
    System.err.print("Input opponent's action (Attack = 1, Flee = 0): ");
    int action = sc.nextInt;
    if (action == TYPE_ATTACK) {
      System.err.print("They attacked ");
      String atkPos = sc.next();
      interpretPos(atkPos);

    } else if (action == TYPE_FLEE) {

    }
  }

  public static void myActions() {

  }

  public static int totalHP(Battleship[] team) {
    int teamHP = 0;
    for (int i=0; i<4; i++) {
      teamHP += team[i].getHP;
    }
  }

  public static void stateWinner() {

  }

  public static boolean end() {

    if (totalHP(myShips) == 0) {
      winner = 0;
      return true;
    } else if (totalHP(theirShips) == 0) {
      winner = 1;
      return true;
    } else {
      return false;
    }
  }
}
