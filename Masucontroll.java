
public class MasuControll implements Initializable {
    public MasuData masuData;
    public ShipData ships;
    private int posX;
    private int posY;
    shipChara(int startX, int startY, MapData mapData){
        this.mapData = mapData;
        posX = startX;
        posY = startY;
    public void initialize(URL url, ResourceBundle rb) {
        masuData = new NasuData(5, 5);
        ships = new ShipData(1,1);
        drawMap(ship, mapData);
    }
    // Draw the map have ship
    public void drawMap(Shipchara c, MapData m){
        int cx = c.getPosX();
        int cy = c.getPosY();
    }
    public int drawShip(int x, int y){
        setMap(x, y, MapData.TYPE_SHIP);
    }
}
