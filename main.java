import java.util.Scanner;
import java.util.ArrayList;

class main {
  public static final int TYPE_ATTACK = 1;
  public static final int TYPE_FLEE = 0;
  public static final int POS_A = 0;
  public static final int POS_B = 1;
  public static final int POS_C = 2;
  public static final int POS_D = 3;
  public static final int POS_E = 4;

  public static Masu myMap;
  public static Masu theirMap;
  public static Battleship[] myShips;
  public static Battleship[] theirShips;
  public static ArrayList<int[]> theyAttacked;
  public static ArrayList<int[]> weAttacked;
  public static int winner = 0;

  public static final int[][] startPos = {{POS_A,0},
                                          {POS_A,4},
                                          {POS_E,0},
                                          {POS_E,4}};

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
      String atkPosStr = sc.nextLine();
      int[] atkPos = interpretPos(atkPosStr);
      theyAttacked.add(atkPos);
      checkAttacked(atkPos);

    } else if (action == TYPE_FLEE) {
      System.err.print("They moved (N/S/E/W + num) ");
      String direction = sc.nextLine();
      int[] movedDirection = interpretDirection(direction);
    }
  }

  public static int[] interpretPos(String str) {
    char[] charArray = {str.charAt(0),str.charAt(1)};
    int[] pos = new int[2];
    if (charArray[0] == 'A') {
      pos[0] = POS_A;
    } else if (charArray[0] == 'B') {
      pos[0] = POS_B;
    } else if (charArray[0] == 'C') {
      pos[0] = POS_C;
    } else if (charArray[0] == 'D') {
      pos[0] = POS_D;
    } else if (charArray[0] == 'E') {
      pos[0] = POS_E;
    }
    pos[1] = Character.getNumericValue(charArray[1]);
    return pos;
  }

  public static int [] interpretDirection(String str) {
    char[] charArray = {str.charAt(0),str.charAt(1)};
    int[] dir = new int[2];
    if (charArray[0] == 'N') {
      dir[1] = 0 - Character.getNumericValue(charArray[1]);
    } else if (charArray[0] == 'S') {
      dir[1] = Character.getNumericValue(charArray[1]);
    } else if (charArray[0] == 'E') {
      dir[0] = 0 - Character.getNumericValue(charArray[1]);
    } else if (charArray[0] == 'W') {
      dir[0] = Character.getNumericValue(charArray[1]);
    }
    return dir;
  }

  public static checkAttacked (int[] pos) {
    if (hasMyShip(pos)) {
      hitAttacked(pos);
    } else if (nearMyShip(pos)) {
      nearAttacked(pos);
    } else if (missMyShip(pos)) {
      missAttacked(pos);
    }
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
