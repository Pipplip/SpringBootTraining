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

***
### Exkurs: Domain Driven Development und Hexagonal Architecture
Domain-Driven Development ist eine Methode der Softwareentwicklung, die sich auf die Modellierung der Domäne konzentriert, in der die Anwendung arbeitet. Es geht darum, das fachliche Problem zu verstehen und den Code um die Domäne herum zu organisieren, sodass die Software die Geschäftsanwendungslogik genau abbildet.

Beispiel: Domäne im E-Commerce: Der Bereich „Online-Shop“ ist die Domäne. In dieser Domäne gibt es Fachbegriffe wie „Produkt“, „Warenkorb“, „Bestellung“, „Zahlung“, „Kunde“ etc. Die Geschäftsprozesse beinhalten die Schritte vom Produktkauf bis hin zur Bestellabwicklung und Lieferung.

Bausteine um die Domäne aufzubauen:
1) Entität: sind Objekte, die eine eigene Identität über ihren gesamten Lebenszyklus hinweg besitzen
2) Aggregat: Gruppe von Entitäten, die als Einheit betrachtet werden
3) Wertobjekt (value object): haben eine Identität, eignen sich zur Darstellung von Werten
4) Service: realisieren einen use-case und kapseln Geschäftslogik
5) Repository: übernehmen die Verantwortung für die Persistenz
6) Domain events: repräsentieren wichtige Ereignisse innerhalb der Domäne und können andere Teile des Systems informieren


Hexagonal Architecture (Zwiebelarchitekur):
![Hexagon](hexagon.png)
Die Hexagonal Architecture, auch als Ports and Adapters-Architektur bezeichnet, ist ein Entwurfsmuster, das darauf abzielt, Software-Systeme so zu strukturieren, dass die Anwendungslogik (die Domäne) von externen Systemen (wie Datenbanken, Web-Services, UI-Frameworks usw.) isoliert wird.

Interface:
Bearbeitung von externen Anfragen über unterschiedliche Schnittstellen (REST, CLi, Event controller)

Core:
Herzstück der Anwendung, beinhaltet Domänenlogik (Entitiäten, value objects etc)

Infrastructure:
Ausgehende Adapter über Ports. Sie verbinden die Anwendung mit externen Systemen, z.B. Datenbanken

Daraus ergibt sich folgende Projektstruktur:
![structure](structure.png)

***
### Annotations
| Name                   | Auswirkung                                                                                                                                                                    |
|------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| @Component             | Macht eine Klasse zu einer Komponente/Spring-managed Bean. Sie wird von Spring automatisch erkannt, instanziiert. Sie ist dann aber "irgendeine" Komponente spezielle Aufgabe |
| @Service               | Eine spzeielle Component. Führt Busingesslogik aus                                                                                                                            |
| @Repository            | Eine spzeielle Component. Regelt den Datenspeicher                                                                                                                            |
| @Controller            | Eine spzeielle Component. Nehmen vom Frontend Aufgaben entgegen                                                                                                               |
| @SpringBootApplication | Ist eine Zusammenfassung von weiteren Annotations: @SpringBootConfiguration, @EnableAutoConfiguration, @ComponentScan                                                         |
| @ShellComponent        | Spezielle Komponente für interaktive Shell Programme zu schreiben                                                                                                             |

Kontrollfluss:
Es gibt eine Anfrage vom Client, diese
nimmt ein Controller entgegen. Der Controller delegiert die Aufgaben an den Service. Der Service benötigt Daten, er geht an das Repository; das Repository liefert die Daten in der Regel aus einer Datenbank. Die Daten gehen zurück zum Service, und dieser liefert die Daten zurück zum Controller. Ein Controller wäre eine Komponente wie ein REST-Controller für RESTful Webservices. Es könnte ebenso ein Controller für ein Command-Line-Interface oder für ein Chat-Interface sein.

*** 
### ClassPath scanning
Die Annotation SpringBootApplication ist eine Zusammenfassung von weiteren Annotations: @SpringBootConfiguration, @EnableAutoConfiguration, @ComponentScan
D.h. man kann eine Configurations-Klasse schreiben, die @SpringBootApplication als Annotation erhält.
Die wird in main als Parameter übergeben, die main darf dann aber kein @SpringBootApplication enthalten

Beispiel:
`@SpringBootApplication
class Date4uConfiguration { }
...
public static void main( String[] args ) {
SpringApplication.run( Date4uConfiguration.class, args );
}`

In der neuen Configuration lassen sich z.B. mit @ComponentScan( basePackages= { "com.tutego.date4u.core" }) beschreiben, wo SpringBoot nach Klassen für den Container suchen soll.
Es gibt Filter und viel mehr.

***
### Spring shell Anwendungen
Es gibt die Möglichkeit eine Spring Shell component in einer Spring Anwendung einzubetten. D.h. eine interaktive Shell wird dadurch gestartet.