#pragma once

#include <iostream>
#include <string>
#include "Swiat.h"

using std::string;

enum kierunki {
    GORA,
    PRAWO,
    DOL,
    LEWO
};


class Organizm {
    protected:
        Swiat *swiatOrganizmu;                   
        int sila;                           
        int inicjatywa;                         
		int koordynaty[2];                        
		int wiek;                                
    public:
        bool czyCzeka;
        Organizm(Swiat *swiatOrganizmu, int sila, int inicjatywa, int x_pos, int y_pos, int wiek, bool czyCzeka);
        virtual int *znajdzNowePole();
        int *znajdzKolejneWolnePole();
        
        void komunikat(string wydarzenie, Organizm* drugiOrganizm);
        int getSila();                      
        int getInicjatywa();
        void setX(int x);
        void setY(int y);
        int getX();
        int getY();
        int getWiek();
        bool getCzyCzeka();
    
        virtual bool odbicie(Organizm *organizm);
        virtual Organizm *klonowanie(int posX, int posY) = 0;       

        virtual string getNazwa() = 0;
        virtual void rysowanie() = 0;
        virtual void akcja() = 0;
        virtual ~Organizm();
};
