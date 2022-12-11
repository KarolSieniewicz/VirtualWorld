import javax.swing.ImageIcon;

public class Czlowiek extends Zwierze {

    private boolean tarczaAlzura = false;

    Czlowiek(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek){
        super(swiatOrganizmu, 5, 4, x_pos, y_pos, wiek, false);
        this.ikonka = new ImageIcon("zdjecia/czlowiek.png");
        this.hexIkonka = new ImageIcon("zdjecia/czlowiekHex.png");
        this.swiatOrganizmu.setPlayerX(x_pos);
        this.swiatOrganizmu.setPlayerY(y_pos);
    }

    @Override
    String getNazwa() {        
        return ("Czlowiek");
    }


    void dezaktywujTarcze() {
        if(tarczaAlzura) tarczaAlzura = false;
    }
    
    @Override
    boolean odbicie(Organizm atakujacy) {
        if(tarczaAlzura) {
            atakujacy.znajdzKolejneWolnePole();
            return true;
        }
        else return false;
    }

    void specjalnaUmiejetnosc(){
        if(swiatOrganizmu.getResetUmiejetnosciCzlowieka() == 0) {
            System.out.println("[Gracz] - Uruchomiono Tarcze Alzura, kolejne ataki zostana odbite");
            Komentator.DodajKomentarz("[Gracz] - Uruchomiono Tarcze Alzura, kolejne ataki zostana odbite");
            this.tarczaAlzura = true;
            swiatOrganizmu.ustawResetUmiejetnosciCzlowieka(6);
        }
        else {
            System.out.println("[Gracz] - Nie udalo sie uruchomic Tarczy Alzura, ability on cooldown");
            Komentator.DodajKomentarz("[Gracz] - Nie udalo sie uruchomic Tarczy Alzura, ability on cooldown ("+swiatOrganizmu.getResetUmiejetnosciCzlowieka()+")");
        }
    }

    @Override
    char getZnaczek() {
        return '3'; 
    }

    @Override
    void akcja() {
        wiek++;

        int newPosition[] = {koordynaty[0],koordynaty[1]};

        switch(swiatOrganizmu.playerMove) {
            //0 - gora lub gora lewo
            case 0:
            newPosition[1]--;
            break;
            //1 - dol lub dol lewo
            case 1:
            newPosition[1]++;
            break;
            //2 - prawo
            case 2:
            newPosition[0]++;
            break;
            //3 - lewo
            case 3:
            newPosition[0]--;
            break;
            //4 - gora prawo
            case 4:
            newPosition[0]++;
            newPosition[1]--;
            break;
            //5 - dol prawo
            case 5:
            newPosition[0]++;
            newPosition[1]++;
            break;
            //6 - gora lewo
            case 6:
            newPosition[0]--;
            newPosition[1]--;
            break;
            //7 - dol lewo
            case 7:
            newPosition[0]--;
            newPosition[1]++;
            break;
            //8 - tarcza alzura
            case 8:
            specjalnaUmiejetnosc();
            break;
            //nic
            default:
            break;
        }

        if(!inBounds(newPosition[0], newPosition[1])) {
            System.out.println("Niewlasciwy ruch/czlowiek pozostaje w miejscu");
            Komentator.DodajKomentarz("Niewlasciwy ruch/czlowiek pozostaje w miejscu");
            return;
        }

        //TODO sprawdz czy nie mozesz zapakowac w funkcje sprawdzPole(x,y)
        if(swiatOrganizmu.plansza[newPosition[0]][newPosition[1]] == null){
            swiatOrganizmu.plansza[newPosition[0]][newPosition[1]] = this;
            swiatOrganizmu.plansza[koordynaty[0]][koordynaty[1]] = null;
            koordynaty[0] = newPosition[0];
            koordynaty[1] = newPosition[1];
            this.swiatOrganizmu.setPlayerX(koordynaty[0]);
            this.swiatOrganizmu.setPlayerY(koordynaty[1]);
        }
        else{        
            kolizja(swiatOrganizmu.plansza[newPosition[0]][newPosition[1]]);
        }
    }

    @Override
    Organizm klonowanie(int posX, int posY) {
        return null;
    }
    
}
