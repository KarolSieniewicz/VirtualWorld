#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string>
#include "Roslina.h"

class Guarana : public Roslina {
    public:
        Guarana(Swiat *swiatOrganizmu, int posX, int posY, int age, bool czyCzeka);
        void rysowanie() override;
        string getNazwa() override;
        Organizm *klonowanie(int posX, int posY) override;
        ~Guarana() override;
};
