# Tárgyak felvétele - mini Neptun

## Tudnivalók

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

## Végpontok

  - ### GET
    - /student/subjects - tanuló tárgyai
    
    - /subject - összes tárgy
    - /subject/{id} - tárgy ID szerint
    
    - /teacher/subjects - tanár tárgyai
    
    - /user/{username} - felhasználó

  - ### POST
    - /student/register - új tanuló hozzáadása
    - /teacher/register - új tanár hozzáadása
    
    - /subject - új tárgy hozzáadása egy tanárhoz

  - ### PATCH
    - /student/subjects - tanuló tárgyainak módosítása

  - ### PUT
    - /subject/{id} - tárgy szerkesztése
    
  - ### DELETE
    - /subject/{id} - tárgy törlése
    	
## Egy végpont működése 

A diagram egy hallgató bejelentkezését, majd egy tárgy felvételét mutatja be. Tárgyfelvétel után az adott hallgató felvett tárgyainak listázása következik.

![Tárgy felvétele majd egy hallgató tárgyainak listázása](/docs/targyfelvetel_vegpont_diagram.PNG)

## Adatbázisterv

![Adatbázisterv UML diagramja](/docs/erd.png)

## Drótvázterv

[Az oldal drótvázterve](/docs/wireframe.pdf)

## Fejlesztői környezet

### Felhasznált eszközök

- Git verziókezelő
- SpringBoot Java keretrendszer
- H2 adatbázis kezelő
- NetBeans és IntelliJ IDE
- Github a projekt egyszerű megosztásához
- Maven a dependenciák kezelésére
- Angular 6

### Projekt indítása

#### Backend

- Git verziókezelő telepítése
- Projekt klónozása: `https://github.com/attilavegh/mini-neptun.git`
- A használt fejlesztői környezetbe való importálás után a Spring alkalmazás indítása lehetséges:
  - parancssorból: `mvn spring-boot:run`
  - ha elérhető Maven plug-in, akkor a `run` goal futtatásával

### Git

A fő branch a `master`, melyre közvetlen `push` nem lehetséges, csak külön branchből létrehozott pull request jóváhagyása után lehetséges a `merge`.

## Alkalmazott könyvtárstruktúra
- Frontend
  - app
    - components
    - models
    - modules
    - services

- Backend
  - entities
    - User.java
	- Teacher.java
	- Student.java
	- Subject.java
	- Role.java
	
  - repositories
    - UserBaseRepository.java
    - UserRepository.java
	- TeacherRepository.java
	- StudentRepository.java
	- SubjectRepository.java
	
  - controllers
    - TeacherController.java
	- StudentController.java
	- UserController.java
	- SubjectController.java
	
  - security
    - MyUserDetailsService.java
    
  - MiniNeptunApplication.java
  - JpaConfig.java
  - WebSecurityConfig.java

## Use-case diagram

![Use-case diagram](/docs/use-case.PNG)

## Kliensoldali szolgáltatások

- Regisztráció
  - Új felhasználó regisztációját teszi lehetőve. Lehetőség van tanárként, illetve diákként regisztrálni. Sikeres regisztráció után az alkalmazás a belépés oldalra navigál.
- Belépés
  - Sikeres regisztráció után lehetőség van belépni az alkalmazásba.
- Tantárgyak listázása
  - Az alkalmazás listázza az elérhető tantárgyakat.
- Tantárgy felvétele
  - Belépett hallgató vehet fel meghirdetett tantárgyakat.
- Tantárgy leadása
  - Belépett hallgató leadhat tárgyakat.
- Órarend megtekintése
  - Belépett hallgató megtekintheti a felvett tárgyait, illetve azok adatait, mint például az óra helyszínét és időpontját.
- Tantárgy meghirdetése
  - Belépett tanár új tantárgyat tehet elérhetővé a hallgatók számára.
- Tantárgy szerkesztése
  - Belépett tanárnak lehetősége van egy tantárgy adatait megváltoztatni.
- Tantárgy törlése
  - Belépett tanár meglévő tantárgyakat törölhet, ekkor a hallgatók felvett tárgyai közül is törlődik a tantárgy.
- Felhasználói adatok megtekintése
  - Belépett felhasználó megtekintheti a saját adatait.
- Kilépés
  - Belépett felhasználó ki tud lépni az oldalról.
  
## Kapcsolat a szerverrel

A szerverrel való kapcsolatot az Angularban található ```HttpClientModule``` biztosítja.

## Egy folyamat bemutatása

Egy felhasználó regisztrációja, belépése, majd a főoldalra (Tárgyak) kerülése.  
![Szekvencia diagram](/docs/szekvencia-diagram.PNG)

## Felhasználói dokumentáció

### Telepítés és futtatás

- A futtatáshoz szükséges programok: Node.js, Angular 6, Java
- A https://github.com/attilavegh/mini-neptun GitHub oldalon kattintsunk a Clone or download gombra, majd `Download ZIP`. A letöltött zip állományt csomagoljuk ki tetszőleges helyen.
- Javahoz használt fejlesztői környezetben, például NetBeans-ben a pom.xml fájlra kattintunk jobb egérgombbal, majd Run Maven -> Goals. Itt a következő parancsot adjuk meg Goal-ként: `spring-boot:run`. A szerverünk így már fut is.
- Node.js használatával `npm install` paranccsal tudjuk telepíteni a kliens oldali alkalmazás futtatásához szükséges függőségeket, majd az `npm start` paranccsal elindíthatjuk az alkalmazást.
- Ha minden rendben lefutott, akkor látogassunk el egy böngészőben a http://localhost:4200 címre (alapbeállítás, a port eltérhet).

- Az oldalra ellátogatva a bejelentkezés oldal fogad minket, ahol be tudunk lépni meglévő felhasználóval és jelszóval. Ha ezekkel nem rendelkezünk, akkor a regisztráció menüpontra kattintva regisztrálhatunk.
- Regisztrálhatunk tanárként vagy hallgatóként.
- Sikeres regisztráció után a ismét a belépés oldalra navigál minket az alkalmazás, ahol immár beléphetünk, ha eddig nem voltunk regisztrálva.

- Belépve már elérhetőek számunkra a `Kliensoldali szolgáltatások` pontban leírt lehetőségek.
