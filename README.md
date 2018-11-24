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
    - /students - tanulók listázása
    - /students/{username} - tanuló keresése felhasználónév szerint
    - /students/name/{name} - tanuló keresése név szerint
    
    - /subjects - tárgyak listázása
    - /subjects/{id} - tárgy keresése ID szerint
    - /subjects/name/{name} - tárgy keresése név szerint
    - /subjects/{id}/teacher - tárgy tanárának keresése
    - /subjects/{id}/students - tárgyat felvett tanulók listázása
    
    - /teacher - tanárok listázása
    - /teacher/{username} - tanár keresése felhasználónév szerint
    - /teacher/name/{name} - tanár keresése név szerint
   
    - /users - felhasználók listázása
    - /users/{username} - felhasználó keresése felhasználónév szerint

  - ### POST
    - /students/register - új tanuló hozzáadása
    - /teachers/register - új tanár hozzáadása
    - /subjects/{teacherId} - új tárgy hozzáadása

  - ### PATCH
    - /students/subjects - tárgyak módosítása

  - ### PUT
    - /students/{id} - tanuló szerkesztése
    - /subjects/{id} - tárgy szerkesztése
    - /teachers/{id} - tanár szerkesztése
    
  - ### DELETE
    - /students/{id} - tanuló törlése
    - /subjects/{id} - tárgy törlése
    - /teachers/{id} - tanár törlése
    	
## Egy végpont működése 

A diagram egy hallgató bejelentkezését, majd egy tárgy felvételét mutatja be. Tárgyfelvétel után az adott hallgató felvett tárgyainak listázása következik.

![Tárgy felvétele majd egy hallgató tárgyainak listázása](/docs/targyfelvetel_vegpont_diagram.PNG)

## Adatbázisterv

![Adatbázisterv UML diagramja](/docs/erd.png)

## Drótvázterv

![Az oldal drótvázterve](/docs/wireframe.png)

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

A fő branch a `master`, melyre közvetlen `push` nem lehetséges, csak külön branchből létrehozott pull request jóváhagyása után lehetséges a `merge`.

## Alkalmazott könyvtárstruktúra 

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
    - UserDetailsService.java
    
  - MiniNeptunApplication.java
  - JpaConfig.java
  - WebSecurityConfig.java

