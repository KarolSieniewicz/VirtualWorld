#include "Lis.h"

Lis::Lis(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka)
: Zwierze(swiatOrganizmu, 3, 7, posX, posY, age, czyCzeka){
    
};

void Lis::rysowanie(){
    printf("L");
};

string Lis::getNazwa(){
    return ("Lis");
};

void Lis::akcja(){
    wiek++;
    
    int *newPosition = znajdzNowePole();
    
    if(swiatOrganizmu->plansza[newPosition[0]][newPosition[1]] == NULL){
        swiatOrganizmu->plansza[newPosition[0]][newPosition[1]] = this;
        swiatOrganizmu->plansza[koordynaty[0]][koordynaty[1]] = NULL;
        koordynaty[0] = newPosition[0];
        koordynaty[1] = newPosition[1];
    }
    else{
        
        if(swiatOrganizmu->plansza[newPosition[0]][newPosition[1]]->getSila() <= getSila()){
            kolizja(swiatOrganizmu->plansza[newPosition[0]][newPosition[1]]);
        }else{
            
            this->komunikat("[Odmowa]", this);
       }
    }
    delete newPosition;
};

Organizm* Lis::klonowanie(int posX, int posY){
    return new Lis(swiatOrganizmu, posX, posY, 1, true);
};

Lis::~Lis(){
    
};
