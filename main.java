import java.util.Scanner;
import java.util.ArrayList;

class main {
  public static final int TYPE_ATTACK = 1;
  public static final int TYPE_FLEE = 0;

  public static final int TYPE_CORNER = 0;
  public static final int TYPE_SIDE = 1;

  public static final int POS_A = 0;
  public static final int POS_B = 1;
  public static final int POS_C = 2;
  public static final int POS_D = 3;
  public static final int POS_E = 4;

  public static final int TYPE_MISSED = 0;
  public static final int TYPE_NEAR = 1;
  public static final int TYPE_HIT = 2;

  public static Masu myMap;
  public static Masu theirMap;
  public static Battleship[] myShips;
  public static Battleship[] theirShips;
  public static ArrayList<int[]> theyAttacked = new ArrayList<int[]>();
  public static ArrayList<int[]> weAttacked = new ArrayList<int[]>();
  public static int theirLastAttack = 0;
  public static int theirEarlierAttack = 0;

  public static int theirNearAttack = 0;
  public static int ourNearAttack = 0;
  public static int turn = 1;
  public static int[] lastCounterNearPattern = new int[3];
  public static int attacked = 0;
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
      myMap.setMap(startPos[i][0], startPos[i][1], Masu.TYPE_SHIP);
    }

    setMapAll(myShips, myMap);
    setMapAll(theirShips, theirMap);
  }

  public static void main (String arg[]) {
    initialize();
    Scanner sc = new Scanner(System.in);
    System.err.print("Input which side to start first (this = 1, that = 0): ");
    int stSide = sc.nextInt();

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

      theirMap.setMap(atkPos[0], atkPos[1], MasuData.TYPE_VOID);
      theirMap.raiseSurroundingsAv(atkPos[0], atkPos[1], 1);
      theirEarlierAttack = theirLastAttack;
      checkAttackedPos(atkPos);

    } else if (action == TYPE_FLEE) {
      System.err.print("They moved (N/S/E/W + num) ");
      String direction = sc.nextLine();
      int[] movedDirection = interpretDirection(direction);


    }
  }

  public static void myActions() {

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

  public static void checkAttackedPos (int[] pos) {
    if (hasMyShip(pos)) {
      hitAttacked(pos);
      theirLastAttack = TYPE_HIT;
    } else if (nearMyShip(pos)) {
      nearAttacked(pos);
      theirLastAttack = TYPE_NEAR;
    } else {
      missedAttacked(pos);
      theirLastAttack = TYPE_MISSED;
    }
  }

  public static boolean nearMyShip (int[] pos) {
    boolean ans = false;
    for (int i=0; i<8; i++) {
      int surX = pos[0] + MasuData.surrounding[i][0];
      int surY = pos[1] + MasuData.surrounding[i][1];
      if (myMap.getMap(surX,surY) == MasuData.TYPE_SHIP) {
        ans = true;
        break;
      }
    }
    return ans;
  }

  public static void nearAttacked (int[] pos) {
    System.out.println("Say 波高し");
    printTheirAvMap();
    theirNearAttack++;

    if (turn%2 == 1) {counterNearPattern1(pos);}
    else {counterNearPattern0(pos);}
  }

  public static void counterNearPattern1 (int[] pos) {
    if (theirNearAttack<=2) {counterNearAttack(pos);}
    else {fleeFromNearAttack(pos);}
    lastCounterNearPattern[0]=TYPE_ATTACK;
  }
  public static void counterNearPattern0 (int[] pos) {
    if (theirNearAttack<=2) {fleeFromNearAttack(pos);}
    else {counterNearAttack(pos);}
    lastCounterNearPattern[0]=TYPE_FLEE;
  }

  public static void counterNearAttack (int[] pos) {
    int pattern = int(2*Math.random());
    lastCounterNearPattern[1]=pattern;
    int[][] setdSIDE = {MasuData.surrounding[2], MasuData.surrounding[4], MasuData.surrounding[5], MasuData.surrounding[7]};
    int[][] setPosSIDE = setSurroundingPos(pos, setdSIDE, 4);

    int[] setdCORNER = {MasuData.surrounding[1], MasuData.surrounding[3], MasuData.surrounding[6], MasuData.surrounding[8]};
    int[][] setPosCORNER = setSurroundingPos(pos, setdCORNER, 4);

    attacked = 0;
    if (pattern = TYPE_SIDE) {
      attackFrom(setPosSIDE);
      if (attacked == 0) {
        attackFrom(setPosCORNER);
        lastCounterNearPattern[1]=TYPE_CORNER;
      }
    } else {
      attackFrom(setPosCORNER);
      if (attacked = 0) {
        attackFrom(setPosSIDE);
        lastCounterNearPattern[1]=TYPE_SIDE;
      }
    }
  }

  public static boolean attackable (int[] pos) {
    boolean ans = false;
    for (int i=0; i<8; i++) {
      int[] checkPos = new int[2];
      checkPos[0] = pos[0]+MasuData.surrounding[i][0];
      checkPos[1] = pos[1]+MasuData.surrounding[i][1];
      if (hasMyShip(checkPos)) {
        ans = true;
        break;
      }
    }
    if (hasMyShip(pos)) {
      ans = false;
    }
    return ans;
  }

  public static void attackFrom (int[][] setPos) {
    int[] chosen = new int[4];
    int n=0;
    do {
      n = int(4*Math.random());
      if (chosen[n]==0) {
        chosen[n] = 1;
        if (attackable(setPos[n])) {
          attack(setPos[n]);
          attacked = 1;
          break;
        }
      }
      if ((chosen[0]+chosen[1]+chosen[2]+chosen[3])==4) {
        break;
      }
    } while (chosen[n] == 1)
  }

  public static void attack (int[] pos) {
    System.out.println("Attack " + pos);
    System.out.print("Result? ");
    int result = sc.nextLine();
    if (result == TYPE_HIT) {
      hitAttack(pos);
    } else if (result == TYPE_NEAR) {
      nearAttack(pos);
    } else if (result == TYPE_MISSED) {
      missedAttack(pos);
    }
  }

  public static void fleeFromNearAttack (pos) {
    lastCounterNearPattern[0] = TYPE_FLEE;

    int[][] surroundingPos = setSurroundingPos(pos, MasuData.surrounding, 8);
    ArrayList<int> nearShip = new ArrayList<int>();
    for (int i=0; i<8; i++) {
      if (myMap.getMap(surroundingPos[i][0],surroundingPos[i][1]) == 1) {
        for (int j=0; j<4; j++) {
          if (myShips[j].getPos[0] == surroundingPos[i][0] && myShips[j].getPos[1] == surroundingPos[i][1]) {
            nearShip.add(j);
            break;
          }
        }
      }
    }

    int n = int(nearShip.size()*Math.random());
    fleeShipNo = nearShip.get(n);

    flee(fleeShipNo,pos);
  }

  public static void flee (int shipNo, int[] destination) {
    myShips[shipNo].setPos(destination);
  }

  public static void missedAttacked (int[] pos) {
    if (theirEarlierAttack = TYPE_NEAR) {
      fleeAfterMissedAttacked(int[] pos);
    } else {
      myInitialAttack(countIni);
    }
  }

  public static void fleeAfterMissedAttacked () {
    int[][] surroundingPos = setSurroundingPos(pos, MasuData.surrounding, 8);
    ArrayList<int> nearShip = new ArrayList<int>();
    for (int i=0; i<8; i++) {
      if (myMap.getMap(surroundingPos[i][0],surroundingPos[i][1]) == 1) {
        for (int j=0; j<4; j++) {
          if (myShips[j].getPos[0] == surroundingPos[i][0] && myShips[j].getPos[1] == surroundingPos[i][1]) {
            nearShip.add(j);
            break;
          }
        }
      }
    }
  }

  public static hitAttack () {

  }

  public static nearAttack () {

  }

  public static missedAttack () {

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

  public static void printTheirAvMap(){
    String str = "Their availability map:   ";
    for (int y=0; y<theirMap.getHeight; y++) {
      for (int x=0; x<theirMap.getWidth; x++) {
        str += theirMap.getAv(x,y) + " ";
      }
      str += "\n";
    }
    System.out.println(str);
  }

  public static int[][] setSurroundingPos (int[] pos, int[][] dPos, int count) {
    int[][] newPos = new int[count][2];
    for (int i=0; i<count; i++) {
      newPos[i][0]=pos[0]+dPos[i][0];
      newPos[i][1]=pos[1]+dPos[i][1];
    }
    return newPos;
  }
}
