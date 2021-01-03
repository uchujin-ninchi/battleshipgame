public class Masu implements Initializable {
    public static final int TYPE_VOID = 0;
    public static final int TYPE_SHIP = 1;

    private int[][] map;
    private int width;
    private int height;
    private int availablility;  //count if ship is there
    private int ships;

    Masu (int width, int height) {
        this.width = width;
        this.height = height;
    }
}
