#pragma once

#include <stdio.h>
#include "Zwierze.h"

class Lis : public Zwierze {
    public:
        Lis(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka);
        void rysowanie() override;
        string getNazwa() override;
        void akcja() override;
        Organizm* klonowanie(int posX, int posY) override;
        ~Lis() override;
};
