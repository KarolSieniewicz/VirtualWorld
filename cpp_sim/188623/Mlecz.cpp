#include "Mlecz.h"

Mlecz::Mlecz(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka)
: Roslina(swiatOrganizmu, 0, posX, posY, age, czyCzeka){
    
};

void Mlecz::rysowanie(){
    printf("M");
};

string Mlecz::getNazwa(){
    return ("Mlecz");
};

void Mlecz::akcja(){
    // Zwiększenie wieku organizmu
    wiek++;
    // Wykonuje trzy próby rozmnożenia się
    Roslina::akcja();
    Roslina::akcja();
    Roslina::akcja();
};

Organizm *Mlecz::klonowanie(int posX, int posY){
    return new Mlecz(swiatOrganizmu, posX, posY, 1, true);
};

Mlecz::~Mlecz(){
    
};