# SpringBootTraining

Projekt aufsetzen:
https://start.spring.io/

Spring Boot nutzt eine Parent-POM. (spring-boot-starter-parent)<br>
Eine Parent-POM ist eine Art »Oberklasse« für Maven-Projekte und verwaltet alle Abhängigkeiten
für ein Projekt (Konsistenz der Dependencies)

### Dependency Erklärungen

| Name                         | Wirkung                                                                                        |
|------------------------------|------------------------------------------------------------------------------------------------|
| spring-boot-starter          | Core-Starter und bringt alles mit, was man für Spring-Boot-Anwendungen braucht                 |
| spring-boot-starter-jdbc     | Databasezugriffe über JDBC und DataSources                                                     |
| spring-boot-starter-data-jpa | Jakarta Persistenz                                                                             |
| spring-boot-starter-json     | JSON Mapping                                                                                   |
| spring-boot-starter-web      | Für Webservices und dynamische Webseiten inklusive des Servlet-Containers Tomcat               |

Ausführliche Liste:
[Dependencies](https://docs.spring.io/spring-boot/reference/using/build-systems.html#using.build-systems.starters)

***
### Konfiguration in der application.properties

| Key                               | Auswirkung                    |
|-----------------------------------|-------------------------------|
| spring.main.banner-mode           | Banner Anzeige im Log steuern |
| logging.level.org.springframework | LogLevel festlegen            |

***
### Container für Spring-managed Beans (=Komponenten)

Der Spring Container ist wie ein Manager, der sich darum kümmert, dass die richtigen Objekte erstellt, miteinander verbunden und verwaltet werden. 
Er vereinfacht die Verwaltung der Objekte und Abhängigkeiten und hilft, eine saubere, wartbare Struktur in deiner Anwendung zu erhalten.

Die run Methode in der main() startet den Container.