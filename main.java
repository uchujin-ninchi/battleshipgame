import java.util.Scanner;
class main implements Initializable {
  public Masu map;
  public Battleship[4] ships;


  @Override
  public void initialize(URL url, ResourceBundle rb) {
    map = new Masu(5, 5);
    ships = new Battelship();
  }

  static public void main (String arg[]) {

  }
}
