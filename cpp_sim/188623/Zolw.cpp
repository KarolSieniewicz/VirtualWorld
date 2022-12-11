#include "Zolw.h"

Zolw::Zolw(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka)
: Zwierze(swiatOrganizmu, 2, 1, posX, posY, age, czyCzeka){
    
};

void Zolw::rysowanie(){
    printf("Z");
};

string Zolw::getNazwa(){
    return ("Zolw");
};

void Zolw::akcja(){
    wiek++;
    // Szansa na poruszenie sie 25%
    int randomTick = rand() % 100 + 1;
    if(randomTick > 75){
        Zwierze::akcja();
    }
};

bool Zolw::odbicie(Organizm *entity){
    return (entity->getSila() < 5);
};

Organizm *Zolw::klonowanie(int posX, int posY){
    return new Zolw(swiatOrganizmu, posX, posY, 1, true);
};

Zolw::~Zolw(){
    
};
