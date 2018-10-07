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
    - /subject/all - tárgyak listázás
    - /subject/studentid - tanuló felvett tárgyainak listázása
    - /student/all - tanulók listázása
    - /student/subjectid - tanulók listázás tárgyak szerint
    - /teacher/all - tanárok listázása
    - /teacher/subjectid - tanárok listázás tárgy szerint (≧ 1)

  - ### POST
    - /login
    - /register
    - /student/addsubject/id - tárgy felvétele
    - /subject/new - új tárgy meghírdetése

  - ### PUT
    - /subject/modify/id - tárgy szerkesztés

  - ### DELETE
    - /subject/delete/id - tárgy törlése
    - /student/removesubject/id - tárgy leadása
	
## Egy végpont működése 

A diagram egy hallgató bejelentkezését, majd egy tárgy felvételét mutatja be. Tárgyfelvétel után az adott hallgató felvett tárgyainak listázása következik.

![Tárgy felvétele majd egy hallgató tárgyainak listázása](/img/targyfelvetel_vegpont_diagram.png)

## Adatbázisterv

![Adatbázisterv UML diagramja](/img/adatbazis_uml.png)

## Fejlesztői környezet

### Felhasznált eszközök

- Git verziókezelő
- SpringBoot Java keretrendszer
- H2 adatbázis kezelő
- NetBeans és IntelliJ IDE
- Github a projekt egyszerű megosztásához
- Maven a dependenciák kezelésére

### Projekt indítása

#### Backend

- Git verziókezelő telepítése
- Projekt klónozása: `https://github.com/attilavegh/mini-neptun.git`
- A használt fejlesztői környezetbe való importálás után a Spring alkalmazás indítása lehetséges:
  - parancssorból: `mvn spring-boot:run`
  - ha elérhető Maven plug-in, akkor a `run` goal futtatásával

### Git

A fő branch a `master`, melyre közvetlen `merge` nem lehetséges, csak külön branchből létrehozott pull request jóváhagyása után.

## Alkalmazott könyvtárstruktúra 

  - entities
    - UserEntity.java
	- TeacherEntity.java
	- StudentEntity.java
	- TimetableEntity.java
	- SubjectEntity.java
  - repositoris
    - UserRepository.java
	- TeacherRepository.java
	- StudentRepository.java
	- TimetableRepository.java
	- SubjectRepository.java
  - controllers
    - TeacherController.java
	- StudentController.java
	- UserController.java
	- TimetableController.java
	- SubjectController.java
  - services
    - MyUserDetailsService.java
  - MiniNeptunApplication.java
  - WebSecurityCongif.java

