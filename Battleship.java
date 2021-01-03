public class Battleship {
    public static final int HP_0 = 0;
    public static final int HP_1 = 1;
    public static final int HP_2 = 2;
    public static final int HP_3 = 3;

    private int HP;
    private int posX;
    private int posY;

    private Masu map;

    Battleship(int startX, int startY, Masu map) {
        this.HP = HP_3;
        this.posX = startX;
        this.posY = startY;
        this.map = map;
    }

    public void setPos (int[] pos) {
      this.posX = pos[0];
      this.posY = pos[1];
    }

    public void setMap (Masu map) {
      this.map = map;
    }

    public void setHP (int HP) {
      this.HP = HP;
    }

    public void attacked () {

    }

    public void attack () {

    }

    public void fleet () {

    }

}
