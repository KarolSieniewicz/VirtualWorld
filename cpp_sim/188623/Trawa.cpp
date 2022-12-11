#include "Trawa.h"

Trawa::Trawa(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka)
: Roslina(swiatOrganizmu, 0, posX, posY, age, czyCzeka){
};

void Trawa::rysowanie(){
    printf("#");
};

string Trawa::getNazwa(){
    return ("Trawa");
};

Organizm *Trawa::klonowanie(int posX, int posY){
    return new Trawa(swiatOrganizmu, posX, posY, 1, true);
};

Trawa::~Trawa(){
    
};