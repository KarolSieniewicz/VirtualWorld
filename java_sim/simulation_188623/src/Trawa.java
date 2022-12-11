import javax.swing.ImageIcon;

public class Trawa extends Roslina {

    Trawa(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 0, x_pos, y_pos, wiek, czyCzeka); 
        this.ikonka = new ImageIcon("zdjecia/trawa.png");       
        this.hexIkonka = new ImageIcon("zdjecia/trawaHex.png");
    }

    String getNazwa() {
        return ("Trawa");
    }
    
    @Override
    char getZnaczek() {
        return '8'; 
    }

    @Override
    Organizm klonowanie(int posX, int posY) {
        return new Trawa(swiatOrganizmu, posX, posY, 1, true);
    }
}
