#include "Antylopa.h"

Antylopa::Antylopa(Swiat *swiatOrganizmu, int posX, int posY, int age, bool czyCzeka)
: Zwierze(swiatOrganizmu, 4, 4, posX, posY, age, czyCzeka){

};

int *Antylopa::znajdzNowePole(){
    int *returnPosition = new int[2];

    returnPosition[0] = koordynaty[0];
    returnPosition[1] = koordynaty[1];

    int field;
    bool invalidMove;
    do {
        field = rand() % 8;

        invalidMove = false;
        switch (field) {
            case GORA:
                if(returnPosition[1] == 0)
                    invalidMove = true;
                else returnPosition[1]--;
                break;
            case PRAWO:
                if(returnPosition[0] == swiatOrganizmu->getBoardX() - 1)
                    invalidMove = true;
                else returnPosition[0]++;
                break;
            case DOL:
                if(returnPosition[1] == swiatOrganizmu->getBoardY() - 1)
                    invalidMove = true;
                else returnPosition[1]++;
                break;
            case LEWO:
                if(returnPosition[0] == 0)
                    invalidMove = true;
                else returnPosition[0]--;
                break;
            case GORA+4:
                if(returnPosition[1] <= 1)
                    invalidMove = true;
                else returnPosition[1] -= 2;
                break;
            case PRAWO+4:
                if(returnPosition[0] >= swiatOrganizmu->getBoardX() - 2)
                    invalidMove = true;
                else returnPosition[0] += 2;
                break;
            case DOL+4:
                if(returnPosition[1] >= swiatOrganizmu->getBoardY() - 2)
                    invalidMove = true;
                else returnPosition[1] += 2;
                break;
            case LEWO+4:
                if(returnPosition[0] <= 1)
                    invalidMove = true;
                else returnPosition[0] -= 2;
                break;
        }
    }while(invalidMove);

    return returnPosition;
};

void Antylopa::rysowanie(){
    printf("A");
};

string Antylopa::getNazwa(){
    return ("Antylopa");
};

void Antylopa::kolizja(Organizm *kolidujacyOrganizm){
    
    // Antylopa probuje uciekac
    int randomTick = rand() % 2;

    if((randomTick > 0) && !(czyTenSamGatunek(*kolidujacyOrganizm)) && (kolidujacyOrganizm->getSila() > sila)){

        this->komunikat("[Ucieczka]", kolidujacyOrganizm);
        
        int *newPosition = znajdzKolejneWolnePole();
        if((newPosition[0] != -1) && (newPosition[1] != -1)){
            swiatOrganizmu->plansza[newPosition[0]][newPosition[1]] = this;
            swiatOrganizmu->plansza[koordynaty[0]][koordynaty[1]] = NULL;
            koordynaty[0] = newPosition[0];
            koordynaty[1] = newPosition[1];
        }
    }
    else{
        Zwierze::kolizja(kolidujacyOrganizm);
    }
};

Organizm *Antylopa::klonowanie(int posX, int posY){
    return new Antylopa(swiatOrganizmu, posX, posY, 1, true);
};

Antylopa::~Antylopa(){

};