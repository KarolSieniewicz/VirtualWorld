#pragma once

#include <stdio.h>
#include "Zwierze.h"

class Wilk : public Zwierze {
    public:
        Wilk(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka);
        void rysowanie() override;
        string getNazwa() override;
        Organizm* klonowanie(int posX, int posY) override;
        ~Wilk() override;
};
