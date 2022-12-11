#pragma once

#include <stdio.h>
#include "Roslina.h"

class Trawa : public Roslina {
    public:
        Trawa(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka);
        void rysowanie() override;
        string getNazwa() override;
        Organizm* klonowanie(int posX, int posY) override;
        ~Trawa() override;
};
