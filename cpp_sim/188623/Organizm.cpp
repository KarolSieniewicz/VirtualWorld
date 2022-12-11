#include "Organizm.h"

Organizm::Organizm(Swiat* swiatOrganizmu, int sila, int inicjatywa, int posX, int posY, int wiek, bool czyCzeka) 
: swiatOrganizmu(swiatOrganizmu), sila(sila), inicjatywa(inicjatywa), wiek(wiek), czyCzeka(czyCzeka){

    this->koordynaty[0] = posX;
    this->koordynaty[1] = posY;           

    if(posX == -1 && posY == -1) return;    
    swiatOrganizmu->plansza[posX][posY] = this;   
    swiatOrganizmu->podgladOrganizmow->dodajOrganizm(this);
};

void Organizm::komunikat(string wydarzenie, Organizm* kolidujacyOrganizm) {
    std::cout << wydarzenie << " - " << this->getNazwa() << " (" << koordynaty[0] << "," << koordynaty[1] << "), ";
    std::cout << kolidujacyOrganizm->getNazwa() << " (" << kolidujacyOrganizm->getX() << "," << kolidujacyOrganizm->getY() << ").\n";
}

int *Organizm::znajdzNowePole(){

    int *returnPosition = new int[2];
    returnPosition[0] = koordynaty[0];
    returnPosition[1] = koordynaty[1];

    int field;
    bool invalidMove;
    do{

        field = rand() % 4;
        invalidMove = false;
        switch(field){
            case GORA:
                if(returnPosition[1] == 0){
                    invalidMove = true;
                }
                else returnPosition[1]--;
                break;
            case PRAWO:
                if(returnPosition[0] == swiatOrganizmu->getBoardX()-1){
                    invalidMove = true;
                }
                else returnPosition[0]++;
                break;
            case DOL:
                if(returnPosition[1] == swiatOrganizmu->getBoardY()-1){
                    invalidMove = true;
                }
                else returnPosition[1]++;
                break;
            case LEWO:
                if(returnPosition[0] == 0){
                    invalidMove = true;
                }
                else returnPosition[0]--;
        }
    }while(invalidMove);
    return returnPosition;
};


int *Organizm::znajdzKolejneWolnePole(){
    
    int *returnPosition = new int[2];
    returnPosition[0] = koordynaty[0];
    returnPosition[1] = koordynaty[1];

    // GORA
    if((returnPosition[1] != 0) && (swiatOrganizmu->plansza[returnPosition[0]][returnPosition[1]-1] == NULL)){
        returnPosition[1]--;
        return returnPosition;
    }
    // PRAWO
    else if((returnPosition[0] != swiatOrganizmu->getBoardX() - 1) && (swiatOrganizmu->plansza[returnPosition[0]+1][returnPosition[1]] == NULL)){
        returnPosition[0]++;
        return returnPosition;
    }
    // DOL
    else if((returnPosition[1] != swiatOrganizmu->getBoardY() - 1) && (swiatOrganizmu->plansza[returnPosition[0]][returnPosition[1]+1] == NULL)){
        returnPosition[1]++;
        return returnPosition;
    }
    // LEWO
    else if((returnPosition[0] != 0) && (swiatOrganizmu->plansza[returnPosition[0]-1][returnPosition[1]] == NULL)){
        returnPosition[0]--;
        return returnPosition;
    }
    
    returnPosition[0] = -1;
    returnPosition[1] = -1;
    return returnPosition;
};

int Organizm::getSila(){
    return sila;
};

int Organizm::getInicjatywa(){
    return inicjatywa;
};

void Organizm::setX(int x){
    koordynaty[0] = x;
};

int Organizm::getX(){
    return koordynaty[0];
};

void Organizm::setY(int y){
    koordynaty[1] = y;
};

int Organizm::getY(){
    return koordynaty[1];
};

int Organizm::getWiek(){
    return wiek;
};

bool Organizm::getCzyCzeka(){
    return czyCzeka;
};

bool Organizm::odbicie(Organizm *organizm){
    return false;
};

Organizm::~Organizm(){

};