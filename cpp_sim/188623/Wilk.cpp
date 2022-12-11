#include "Wilk.h"

Wilk::Wilk(Swiat *swiatOrganizmu, int posX, int posY, int age, bool czyCzeka)
: Zwierze(swiatOrganizmu, 9, 5, posX, posY, age, czyCzeka){
    
};

void Wilk::rysowanie(){
    printf("W");
};

string Wilk::getNazwa(){
    return ("Wilk");
};

Organizm *Wilk::klonowanie(int posX, int posY){
    return new Wilk(swiatOrganizmu, posX, posY, 1, true);
};

Wilk::~Wilk(){
    
};
