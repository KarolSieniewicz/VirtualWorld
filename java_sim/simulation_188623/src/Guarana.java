import javax.swing.ImageIcon;

public class Guarana extends Roslina {

    Guarana(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 0, x_pos, y_pos, wiek, czyCzeka);
        this.ikonka = new ImageIcon("zdjecia/guarana.png");
        this.hexIkonka = new ImageIcon("zdjecia/guaranaHex.png");
    }

    @Override
    char getZnaczek() {
        return '4'; 
    }

    @Override
    String getNazwa() {        
        return "Guarana";
    }

    @Override
    Organizm klonowanie(int posX, int posY) {        
        return new Guarana(swiatOrganizmu, posX, posY, 1, true);
    }
    
}
