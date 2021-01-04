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
    public int[] getPos () {
      int[] pos = {this.posX, this.posY};
      return pos;
    }

    public void setMap (Masu map) {
      this.map = map;
    }
    public Masu getMap(){
        return this.map;
    }

    public void setHP (int HP) {
      this.HP = HP;
    }
    public int getHP () {
      return this.HP;
    }

    public static void flee (int desX, int desY) {
        if (isMovable(desX, desY)) {
            String dir = "";
            int d = 0;
            if (desX > posX) {
                dir = "東";
                d = desX - posX;
            } else if (desX < posX) {
                dir = "西";
                d = posX - desX;
            } else if (desY > posY) {
                dir = "南";
                d = desY - posY;
            } else if (desY < posY) {
                dir = "北";
                d = posY - desY;
            }
            this.getMap.setMap(this.posX,this.posY) == MasuData.TYPE_VOID;
            this.setPos({desX, desY});
            this.getMap.setMap(desX,desY) == MasuData.TYPE_SHIP;
            System.out.println("Moved " + dir + d);
        }
    }
    
    public boolean isMovable(int newX, int newY){
        if (newX >= MAP_SIZE || newY >= MAP_SIZE || newX < 0 || newY < 0 ){
            return false;
        } else if (this.getMap.getMap(newX, newY) == MasuData.TYPE_SHIP){
            return false;
        } else {
            return true;
        }
        return false;
    }

}
