# Tárgyak felvétele - mini Neptun

### 1.0 Célkitűzés

Cél a Neptun tárgyfelvétel funkciójának megvalósítása kisebb keretek között. Az alkalmazással lehetősége lesz egy hallgatónak tárgyai felvételére, leadására, illetve egy tanárnak tárgyak hirdetésére és megszüntetésére.

### 1.1 Funkcionális követelmények

- Regisztráció
- Bejelentkezés
- Bejelentkezett hallgatóknak
  - Tárgyak listázása
  - Tárgy felvétele
  - Tárgy leadása
  - Felvett órák listázása
- Bejelentkezett tanár
  - Tárgyak listázása
  - Tárgy meghirdetése
  - Tárgy törlése
  - Tárgy adatainak módosítása


### 1.2 Nem funkcionális követelmények

- Letisztult, könnyen áttekinthető felhasználói felület. Egyszerűen navigálható oldal.
- Biztonságos, jelszóval védett oldalak. A jelszavak biztonságosan tárolva.
- Stabil, gyors működés és adatelérés.

### 1.3 Szakterületi fogalomjegyzék

- órarend: Egy hallgató felvett tárgyainak adatai és az órák időpontjai egy listában

### 1.4 Szerepkörök

- Vendég: Nem regisztrált látogató.
- Tanár: Regisztrált, belépés után tárgyakat tud hirdetni és törölni
- Hallgató: Regisztrált, belépés után tárgyakat vehet fel, adhat le, listázhatja a tárgyait