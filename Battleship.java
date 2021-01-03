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
}
