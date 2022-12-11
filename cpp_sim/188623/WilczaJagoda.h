#pragma once

#include <stdio.h>
#include "Roslina.h"
#include <string>

class WilczaJagoda : public Roslina {
    public:
        WilczaJagoda(Swiat *swiatOrganizmu, int posX, int posY, int age, bool czyCzeka);
        void rysowanie() override;
        string getNazwa() override;
        Organizm *klonowanie(int posX, int posY) override;
        ~WilczaJagoda() override;
};