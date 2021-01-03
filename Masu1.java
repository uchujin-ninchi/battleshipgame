public class Masu implements Initializable {
    public static final int TYPE_VOID = 0;
    public static final int TYPE_SHIP = 1;

    private int[][] map;
    private int width;
    private int height;
    private int availablility;  //count if ship is there
    private int ships;

    MasuData (int width, int height) {
        this.width =  width;
        this.height = height;
        map = new Int[width][height];
        fillMap(MapData.TYPE_VOID);
        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                map[y][x] = MasuData.TYPE_SHIP;
            }

    }
    ShipData(int x, int y){
        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                map[y][x] = MasuData.TYPE_SHIP;
            }
        }
    }
    public int getMap(int x, int y) {
        if (x < 0 || width <= x || y < 0 || height <= y) {
            return -1;
        }
        return maps[y][x];
    }
    public void setMap(int x, int y, int type){
        if (x < 1 || width <= x-1 || y < 1 || height <= y-1) {
            return;
        }
        maps[y][x] = type;
    }
    public void fillMap(int type){
        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                maps[y][x] = type;
            }
        }
    }

    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

}
