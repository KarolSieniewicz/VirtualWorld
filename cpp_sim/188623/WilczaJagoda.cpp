#include "WilczaJagoda.h"

WilczaJagoda::WilczaJagoda(Swiat *swiatOrganizmu, int posX, int posY, int age, bool czyCzeka)
: Roslina(swiatOrganizmu, 99, posX, posY, age, czyCzeka){
};

void WilczaJagoda::rysowanie(){
    printf("J");
};

string WilczaJagoda::getNazwa(){
    return ("WilczaJagoda");
};

Organizm *WilczaJagoda::klonowanie(int posX, int posY){
    return new WilczaJagoda(swiatOrganizmu, posX, posY, 1, true);
};

WilczaJagoda::~WilczaJagoda(){
};