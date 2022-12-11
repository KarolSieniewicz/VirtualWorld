#pragma once

#include <iostream>
#include <fstream>
#include <time.h>
#include <typeinfo>

using std::string;

class Organizm;

struct podgladOrganizmu{
    Organizm *aktualnyOrganizm;
    podgladOrganizmu *next;
};

class Organizmy {
    private:
        int maksIlosc;
        int aktualnaIlosc = 0;
    public:
        Organizmy(int maksIlosc);
        podgladOrganizmu *podglad;
        int getMaksIlosc();
        int getAktualnaIlosc();
        void dodajOrganizm(Organizm *dodawanyOrganizm);
        void usunOrganizm(Organizm *dodawanyOrganizm);
        ~Organizmy();
};

class Swiat {
    private:
        int BoardX, BoardY;
        int aktualnaTura = 1;
        int resetUmiejetnosciCzlowieka;
        bool czyGraAktywna;
    public:
        Swiat(int BoardX, int BoardY);
        Swiat(int BoardX, int BoardY, std::ifstream &loadFile);

        int getBoardX();
        int getBoardY();
        int getResetUmiejetnosciCzlowieka();
        int getAktualnaTura();
        bool getCzyGraAktywna();
        void zmienStanGry();
        void ustawResetUmiejetnosciCzlowieka(int i);
        void ustawAktualnaTure(int turn);

        void rysujSwiat();
        void wykonajTure();
        void ustawLosowo(Organizm *organizm);
        void ustawNaKoordynatach(Organizm *organizm, int x, int y);

        void zapiszSwiat();
        
        Organizm ***plansza;
        Organizmy *podgladOrganizmow;

        ~Swiat();
};