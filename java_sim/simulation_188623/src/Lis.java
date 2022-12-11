import javax.swing.ImageIcon;

public class Lis extends Zwierze{

    Lis(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 3, 7, x_pos, y_pos, wiek, czyCzeka);
        this.ikonka = new ImageIcon("zdjecia/lis.png");     
        this.hexIkonka = new ImageIcon("zdjecia/lisHex.png"); 
    }

    String getNazwa() {
        return ("Lis");
    }

    @Override
    char getZnaczek() {
        return '5'; 
    }
    
    void akcja() {
        wiek++;

        int newPosition[] = znajdzNowePole();
        
        if(swiatOrganizmu.plansza[newPosition[0]][newPosition[1]] == null) {
            swiatOrganizmu.plansza[newPosition[0]][newPosition[1]] = this;
            swiatOrganizmu.plansza[koordynaty[0]][koordynaty[1]] = null;
            koordynaty[0] = newPosition[0];
            koordynaty[1] = newPosition[1];
        }
        else {
            if(swiatOrganizmu.plansza[newPosition[0]][newPosition[1]].getSila() <= this.getSila()) {
                kolizja(swiatOrganizmu.plansza[newPosition[0]][newPosition[1]]);
            }
            else {
                this.komunikat("[Odmowa]", this);
            }
        }
    }
    
    Organizm klonowanie(int posX, int posY) {
        return new Lis(swiatOrganizmu, posX, posY, 1, true);
    }

}   


