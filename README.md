# DI-3_Mankomania

Als Projekt für die LV Software Engineering II wird das Brettspiel Mankomania als Android-App programmiert.

Link zur Sonarcloud:
https://sonarcloud.io/organizations/di-3mankomania/projects

Link zu Travis:
https://travis-ci.com/criedl1/DI-3_Mankomania

# Spiel

Jeder Spieler soll mit seinem eigenen Handy spielen können (Kommunikation über das Netzwerk).

Es existiert eine Möglichkeit zu schummeln:

* Jeder Spieler kann einmal im Spiel versuchen zu schummeln
* Er kann die doppelte Summe von dem Zahlen, was er eigentlich müsste ( bei einem Zahlfeld)
* Die Mitspieler können intervenieren
  * Wenn richtig beschuldigt, dann bekommt die Schummelnde Person das doppelte von dem, was man eigentlich hätte zahlen müssen
  * Wenn man falsch beschuldigt wird, bekommt die Petze das Geld was gezahlt wurde.
* Man kann nur einmal falsch beschuldigen (dann darf man keinen mehr beschuldigen).


Aus dem existierenden Spiel wird eine Klagenfurter-Version gemacht.

Die Felder sind im Original wie folgt aufgeteilt: 

![Speilbrett als Tabelle](https://i.imgur.com/j14gtTk.png)

TLDR: 

| Typ           | Anzahl |
|---------------|--------|
| Spiele        | 4      |
| Verlustfelder | 18     |
| Hotels        | 6      |
| Schick-Felder | 23     |
| Aktien        | 3      |
| Spezialfelder | 8      |
| Gewinnfelder  | 4      |


# Organisation 
Die Zuständigkeiten wurden wie folgt ausgesucht:

Jennifer - Testerin (Stellvertetende Architektin )    
Christoph - CI-Manager (Stellvertretender GUI-Mensch)    
Jonas - Projektmanager   
Nicole - GUI-Mensch (Stellvertretende Projektmanagerin)    
Haris - Stellvertretender Tester &  CI-Manager    
Timon - Architekt    

