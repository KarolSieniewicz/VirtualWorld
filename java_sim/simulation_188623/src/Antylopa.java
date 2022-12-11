import java.util.Random;

import javax.swing.ImageIcon;

public class Antylopa extends Zwierze {

    Antylopa(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 4, 4, x_pos, y_pos, wiek, czyCzeka);
        this.ikonka = new ImageIcon("zdjecia/antylopa.png");
        this.hexIkonka = new ImageIcon("zdjecia/antylopaHex.png");
    }

    int[] znajdzNowePole() {
        int returnPosition[] = {koordynaty[0],koordynaty[1]};
        int field;
        int counter = 0;
        boolean validMove = false;
        do {            
            Random rand = new Random();
            field = rand.nextInt(12);
            switch(field) {
            case 0:
                if(returnPosition[1] <= 0)
                    validMove = true;
                else returnPosition[1]--;
                break;
            case 1:
                if(returnPosition[0] >= swiatOrganizmu.getBoardX() - 1)
                    validMove = true;
                else returnPosition[0]++;
                break;
            case 2:
                if(returnPosition[1] >= swiatOrganizmu.getBoardY() - 1)
                    validMove = true;
                else returnPosition[1]++;
                break;
            case 3:
                if(returnPosition[0] <= 0)
                    validMove = true;
                else returnPosition[0]--;
                break;
            case 4:
                if(returnPosition[1] <= 1)
                    validMove = true;
                else returnPosition[1] -= 2;
                break;
            case 5:
                if(returnPosition[0] >= swiatOrganizmu.getBoardX() - 2)
                    validMove = true;
                else returnPosition[0] += 2;
                break;
            case 6:
                if(returnPosition[1] >= swiatOrganizmu.getBoardY() - 2)
                    validMove = true;
                else returnPosition[1] += 2;
                break;
            case 7:
                if(returnPosition[0] <= 1)
                    validMove = true;
                else returnPosition[0] -= 2;
                break;
            case 8:
                if(!inBounds(returnPosition[0]-1, returnPosition[1]-1))
                    validMove = true;
                else if(this.swiatOrganizmu instanceof Hex) {
                    returnPosition[0]--;
                    returnPosition[1]--;
                }
                break;
            case 9:
                if(!inBounds(returnPosition[0]+1, returnPosition[1]+1))
                validMove = true;
                else {
                    if(this.swiatOrganizmu instanceof Hex) {
                        returnPosition[0]++;
                        returnPosition[1]++;
                    }
                }
                break;
            case 10:
                if(!inBounds(returnPosition[0]-2, returnPosition[1]-2))
                validMove = true;
                else {
                    if(this.swiatOrganizmu instanceof Hex) {
                        returnPosition[0]-=2;
                        returnPosition[1]-=2;
                    }
                }
                break;
            case 11:
                if(!inBounds(returnPosition[0]+2, returnPosition[1]+2))
                validMove = true;
                else {
                    if(this.swiatOrganizmu instanceof Hex) {
                        returnPosition[0]+=2;
                        returnPosition[1]+=2;
                    }
                }
                break;
            }
            counter++;
        } while(validMove && counter<15);
        return returnPosition;
    }
    
    String getNazwa() {
        return ("Antylopa");
    }

    @Override
    char getZnaczek() {
        return '1'; 
    }
    
    void kolizja(Organizm kolidujacyOrganizm) {
        //Antylopa probuje uciekac
        Random rand = new Random();
        int randomTick = rand.nextInt(2);
        if(randomTick == 1 && !czyTenSamGatunek(kolidujacyOrganizm) && kolidujacyOrganizm.getSila() > sila) {
            this.komunikat("[Ucieczka]", kolidujacyOrganizm);

            int newPosition[] = znajdzKolejneWolnePole();
            if(newPosition[0] != -1 && newPosition[1] != -1) {
                swiatOrganizmu.plansza[newPosition[0]][newPosition[1]] = this;
                swiatOrganizmu.plansza[koordynaty[0]][koordynaty[1]] = null;
                koordynaty[0] = newPosition[0];
                koordynaty[1] = newPosition[1]; 
            }
        }
        else {
            super.kolizja(kolidujacyOrganizm);
        }
    }

    @Override
    Organizm klonowanie(int posX, int posY) {
        return new Antylopa(swiatOrganizmu,posX,posY,1,true);
    }
}
