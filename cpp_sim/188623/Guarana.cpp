#include "Guarana.h"

Guarana::Guarana(Swiat *currentWorld, int posX, int posY, int age, bool czyCzeka)
: Roslina(currentWorld, 0, posX, posY, age, czyCzeka){
};

void Guarana::rysowanie(){
    printf("G");
};

string Guarana::getNazwa(){
    return ("Guarana");
};

Organizm *Guarana::klonowanie(int posX, int posY){
    return new Guarana(swiatOrganizmu, posX, posY, 1, true);
};

Guarana::~Guarana(){

};