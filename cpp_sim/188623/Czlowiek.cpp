#include "Czlowiek.h"

Czlowiek::Czlowiek(Swiat *swiatOrganizmu, int posX, int posY, int age) 
: Zwierze(swiatOrganizmu, 5, 4, posX, posY, age, false){
    
};

void Czlowiek::rysowanie(){
    printf("$");
};

string Czlowiek::getNazwa(){
    return ("Czlowiek");
};

void Czlowiek::akcja(){    
    wiek++;
    int playerMove;
    this->dezaktywujTarcze();
    int newPosition[2];
    newPosition[0] = koordynaty[0];
    newPosition[1] = koordynaty[1];

    printf("Wcisnij (w/s/a/d) by wykonac ruch: ");
    if(swiatOrganizmu->getAktualnaTura() == 1){
        getchar();
    }
    
    playerMove = getchar();

    if(playerMove == 97 ){
        // a - lewo
            newPosition[0]--;
    }
    else if(playerMove == 100){
        // d - prawo
            newPosition[0]++;
    }
    else if(playerMove == 119){
        // w - góra
            newPosition[1]--;
    }
    else if(playerMove == 115){
        // s - dol
            newPosition[1]++;
    }
    else if(playerMove == 114){
        // r - Tarcza Alzura
        specjalnaUmiejetnosc();
    }
    else if (playerMove == 113)
    {
        exit(0);
    }
    printf("\n");
        
    if((newPosition[0] < 0 || newPosition[1] < 0 ) || (newPosition[0] >= swiatOrganizmu->getBoardX()) || (newPosition[1] >= swiatOrganizmu->getBoardY())){
        printf("\n [Gracz] - niewlasciwy ruch/pozostaje w miejscu\n");
        return;
    }
        
    if(swiatOrganizmu->plansza[newPosition[0]][newPosition[1]] == NULL){
        swiatOrganizmu->plansza[newPosition[0]][newPosition[1]] = this;
        swiatOrganizmu->plansza[koordynaty[0]][koordynaty[1]] = NULL;
        koordynaty[0] = newPosition[0];
        koordynaty[1] = newPosition[1];
    }
    else{        
        kolizja(swiatOrganizmu->plansza[newPosition[0]][newPosition[1]]);
    }
};

void Czlowiek::dezaktywujTarcze()
{
    if (tarczaAlzura) tarczaAlzura = false;
}

bool Czlowiek::odbicie(Organizm* atakujacy) {
    if (tarczaAlzura)
    {
        atakujacy->znajdzKolejneWolnePole();        
        return true;
    }
    else return false;
}

void Czlowiek::specjalnaUmiejetnosc(){
    if(swiatOrganizmu->getResetUmiejetnosciCzlowieka() == 0){

        printf("\n[Gracz] - Uruchomiono Tarcze Alzura, kolejne ataki zostana odbite\n");
        this->tarczaAlzura = true;
        swiatOrganizmu->ustawResetUmiejetnosciCzlowieka(6); //cooldown Tarczy Alzura
    }
    else
    {
        printf("\n[Gracz] - Nie udalo sie uruchomic Tarczy Alzura, poczekaj %i tur", swiatOrganizmu->getResetUmiejetnosciCzlowieka());
    }
};

Organizm *Czlowiek::klonowanie(int posX, int posY){
    return new Czlowiek(swiatOrganizmu, posX, posY, 1);
};

Czlowiek::~Czlowiek(){
    swiatOrganizmu->zmienStanGry();
};