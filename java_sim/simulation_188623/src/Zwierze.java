public class Zwierze extends Organizm {

    Zwierze(Swiat swiatOrganizmu, int sila, int inicjatywa, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, sila, inicjatywa, x_pos, y_pos, wiek, czyCzeka);
    }

    boolean czyTenSamGatunek(Organizm kolidujacyOrganizm){
        return (this.getNazwa() == kolidujacyOrganizm.getNazwa());
    }

    void rozmnazanie(){
        int newPosition[] = znajdzKolejneWolnePole();

        if(newPosition[0] == -1 && newPosition[1] == -1){
            System.out.println("Rozmnazanie "+this.getNazwa()+" nieudane, brak dostepnych pol");
            return;
        }

        klonowanie(newPosition[0],newPosition[1]);
    }

    void walka(Organizm oponent){
        this.komunikat("[Walka]", oponent);

        if (sila >= oponent.getSila() && !(oponent instanceof BarszczSosnowskiego)) {

            System.out.println("Zwyciestwo: "+ this.getNazwa()); 
            Komentator.DodajKomentarz("Zwyciestwo: "+ this.getNazwa());   
    
            if (oponent instanceof Guarana) {

                System.out.println(this.getNazwa()+" ("+koordynaty[0]+","+koordynaty[1]+") sila zwiekszona");
                Komentator.DodajKomentarz(this.getNazwa()+" ("+koordynaty[0]+","+koordynaty[1]+") sila zwiekszona");             
                this.sila += 3;
            }
    
            swiatOrganizmu.plansza[koordynaty[0]][koordynaty[1]] = null;
            koordynaty[0] = oponent.getX();
            koordynaty[1] = oponent.getY();
            if(this instanceof Czlowiek) {
                this.swiatOrganizmu.setPlayerX(koordynaty[0]);
                this.swiatOrganizmu.setPlayerY(koordynaty[1]);
            }
            swiatOrganizmu.plansza[koordynaty[0]][koordynaty[1]] = this;
            swiatOrganizmu.usunOrganizm(oponent);
        }
        else {
            System.out.println("Przegrana: "+this.getNazwa());         
            swiatOrganizmu.plansza[koordynaty[0]][koordynaty[1]] = null;
            swiatOrganizmu.usunOrganizm(this);
        }
    }

    void kolizja(Organizm kolidujacyOrganizm){
        if(czyTenSamGatunek(kolidujacyOrganizm)){
            if(kolidujacyOrganizm instanceof Czlowiek){
                return;
            }

            this.komunikat("[Rozmnazanie]", kolidujacyOrganizm);
            rozmnazanie();
            return;
        }
        else{
            if(kolidujacyOrganizm.odbicie(this)){
                this.komunikat("[Odbicie]", kolidujacyOrganizm);
                return;
            }
    
            this.walka(kolidujacyOrganizm);  
        }
    }

    @Override
    Organizm klonowanie(int posX, int posY){
        return null;
    }

    @Override
    String getNazwa()
    {
        return null;
    }

    @Override
    char getZnaczek() {
        return '-'; 
    }

    @Override
    void akcja() {
        //szansa zolwia na ominiecie ruchu
        if(this.getNazwa().equals("Zolw")){
            wiek++;
        }
        int newPosition[] = znajdzNowePole();

        if(swiatOrganizmu.plansza[newPosition[0]][newPosition[1]] == null){
            swiatOrganizmu.plansza[newPosition[0]][newPosition[1]] = this;
            swiatOrganizmu.plansza[koordynaty[0]][koordynaty[1]] = null;
            koordynaty[0] = newPosition[0];
            koordynaty[1] = newPosition[1];    
        }
        else{
            kolizja(swiatOrganizmu.plansza[newPosition[0]][newPosition[1]]);
        }
    }
    
}
