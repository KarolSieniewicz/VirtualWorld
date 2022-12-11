import javax.swing.ImageIcon;

public class WilczaJagoda extends Roslina{

    WilczaJagoda(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 99, x_pos, y_pos, wiek, czyCzeka);
        this.ikonka = new ImageIcon("zdjecia/wilczajagoda.png");       
        this.hexIkonka = new ImageIcon("zdjecia/wilczajagodaHex.png");    
    }
    
    String getNazwa() {
        return ("WilczaJagoda");
    }

    @Override
    char getZnaczek() {
        return '9'; 
    }

    @Override
    Organizm klonowanie(int posX, int posY) {
        return new WilczaJagoda(swiatOrganizmu, posX, posY, 1, true);
    }

}
