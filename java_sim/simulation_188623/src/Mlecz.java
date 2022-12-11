import javax.swing.ImageIcon;

public class Mlecz extends Roslina {

    Mlecz(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 0, x_pos, y_pos, wiek, czyCzeka);
        this.ikonka = new ImageIcon("zdjecia/mlecz.png");      
        this.hexIkonka = new ImageIcon("zdjecia/mleczHex.png");  
    }
    
    String getNazwa() {
        return ("Mlecz");
    }

    @Override
    char getZnaczek() {
        return '6'; 
    }

    void akcja() {
        wiek++;
        //trzy proby rozmnozenia sie
        super.akcja();
        super.akcja();
        super.akcja();
    }

    @Override
    Organizm klonowanie(int posX, int posY) {
        return new Mlecz(swiatOrganizmu, posX, posY, 1, true);
    }
}
