import java.util.Random;

import javax.swing.ImageIcon;

public class Zolw extends Zwierze {

    Zolw(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 2, 1, x_pos, y_pos, wiek, czyCzeka);
        this.ikonka = new ImageIcon("zdjecia/zolw.png");
        this.hexIkonka = new ImageIcon("zdjecia/zolwHex.png");
    }

    String getNazwa() {
        return ("Zolw");
    }

    @Override
    char getZnaczek() {
        return 'Z'; 
    }
    
    void akcja() {
        //szansa na poruszenie sie 25%
        Random rand = new Random();
        int randomTick = rand.nextInt(101);
        if(randomTick>75) {
            super.akcja();
        }
    }

    boolean odbicie(Organizm entity) {
        return (entity.getSila() < 5);
    }

    Organizm klonowanie(int posX, int posY) {
        return new Zolw(swiatOrganizmu, posX, posY, 1, true);
    }

}
