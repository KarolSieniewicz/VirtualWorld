import javax.swing.ImageIcon;

public class Wilk extends Zwierze{

    Wilk(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 9,5, x_pos, y_pos, wiek, czyCzeka);
        this.ikonka = new ImageIcon("zdjecia/wilk.png");
        this.hexIkonka = new ImageIcon("zdjecia/wilkHex.png");
    }

    String getNazwa() {
        return ("Wilk");
    }

    @Override
    char getZnaczek() {
        return '0'; 
    }

    Organizm klonowanie(int posX, int posY) {
        return new Wilk(swiatOrganizmu, posX, posY, 1, true);
    }
    
}
