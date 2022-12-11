import javax.swing.ImageIcon;

public class Owca extends Zwierze {

    Owca(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 4, 4, x_pos, y_pos, wiek, czyCzeka);
        this.ikonka = new ImageIcon("zdjecia/owca.png");
        this.hexIkonka = new ImageIcon("zdjecia/owcaHex.png");
    }
    
    @Override
    String getNazwa() {        
        return ("Owca");
    }

    @Override
    char getZnaczek() {
        return '7'; 
    }

    @Override
    Organizm klonowanie(int posX, int posY) {        
        return new Owca(swiatOrganizmu, posX, posY, 1, true);
    }
}
