#pragma once

#include <stdio.h>
#include "Zwierze.h"

class Antylopa : public Zwierze {
    public:
        Antylopa(Swiat *swiatOrganizmu, int posX, int posY, int age, bool czyCzeka);
        int *znajdzNowePole() override;
        void rysowanie() override;
        string getNazwa() override;
        void kolizja(Organizm *kolidujacyOrganizm) override;
        Organizm *klonowanie(int posX, int posY) override;
        ~Antylopa() override;
};