public class MasuData implements Initializable {
    public static final int TYPE_VOID = 0;
    public static final int TYPE_SHIP = 1;
    public static final int[][] surrounding = { {-1,-1}, {0,-1}, {1,-1},
                                                {-1, 0},         {1, 0},
                                                {-1, 1}, {0, 1}, {1, 1} };

    private int[][] map;
    private int width;
    private int height;
    private int ships;
    private int[][] AvMap;

    MasuData (int width, int height) {
        this.width = width;
        this.height = height;
        map = new int[width][height];
        AvMap = new int[width][height];
        fillShipData(MasuData.TYPE_VOID);
        fillAvMap(MasuData.TYPE_VOID);
    }

    public int getMap(int x, int y) {
        return maps[y][x];
    }
    public void setMap(int x, int y, int type){
        map[y][x] = type;
    }

    public int getAv(int x, int y) {
        return AvMap[y][x];
    }
    public void raiseAv(int x, int y, int count){
        AvMap[y][x] += count;
    }
    public void resetAv(int x, int y) {
        AvMap[y][x] = 0;
    }

    public void fillShipData(int type){
        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                map[y][x] = type;
            }
        }
    }

    public void fillAvMap(int type){
        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                AvMap[y][x] = type;
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
