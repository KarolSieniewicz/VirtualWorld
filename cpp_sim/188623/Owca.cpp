#include "Owca.h"

Owca::Owca(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka)
: Zwierze(swiatOrganizmu, 4, 4, posX, posY, age, czyCzeka){
    
};

void Owca::rysowanie(){
    printf("O");
};

string Owca::getNazwa(){
    return ("Owca");
};

Organizm *Owca::klonowanie(int posX, int posY){
    return new Owca(swiatOrganizmu, posX, posY, 1, true);
};

Owca::~Owca(){
    
};
