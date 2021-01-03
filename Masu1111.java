public class MasuData implements Initializable {
    public static final int TYPE_VOID = 0;
    public static final int TYPE_SHIP = 1;

    private int[][] map;
    private int width;
    private int height;
    private int ships;

    MasuData (int width, int height) {
        this.width =  width;
        this.height = height;
        map = new Int[width][height];
        fillMap(MasuData.TYPE_VOID);
    }
    public void setShipData(int x, int y){
        map[y][x] = MasuData.TYPE_SHIP;
    }
    public int getShipdata(int y, int x){
        return  map[y][x];
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
public class AvData implements Initializable {
    public static final int TYPE_VOID = 0;
    public static final int TYPE_SHIP = 1;

    private int[][] map;
    private int width;
    private int height;
    private int av;  //count if ship is there

    AvData (int width, int height) {
        this.width =  width;
        this.height = height;
        map = new Int[width][height];
        fillMap(AvData.TYPE_VOID);
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
