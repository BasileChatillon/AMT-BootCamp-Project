# AMT-BootCamp-Project

## Description
Ce projet vise à nous faire créer une application multi-tiers. Pour cela, nous allons devoir créer une base de donnée et pouvoir la gérer depuis une interface web. Nous allons églaement devoir intéragir avec cette base de donnée, afin d'afficher / modifier / supprimer / ajouter des éléments.
Nous allons devoir implémenter les échanges via le model MVC (Modèle, Vue, Controleur) grâce à des Servlets ainsi que des pages JSP.

Nous allons également devoir déployer notre application via WildFly et "dockeriser" notre application pour pouvoir tout déployer via un *docker-compose*, ainsi que mySQL et phpMyAdmin pour pouvoir manager notre base de donnée.

La connexion entre l'application et MySQL se fera grâce au plug-in JDBC (Java Data Base Connector).
L'application est construite à partir d'un projet web Maven (sur NetBeans).

Nous avons décidé de créer une base de donnée sur des chiens.

## URL

L'URL du site est : [http://<IP_DOCKER-MACHINE>:9090/AMTBootcamp-1.0-SNAPSHOT/](http://<IP_DOCKER-MACHINE>:9090/AMTBootcamp-1.0-SNAPSHOT/)
<IP_DOCKER-MACHINE> étant l'addresse IP de la machine docker tournant sur votre odrinateur.

## Fonctionnalités de base
* There is a clean repo containing the code and a README.md file that describes how to use the app (indicate the access URL) and what has
    * Le repo du projet se trouve [ici](https://github.com/BasileChatillon/AMT-BootCamp-Project)

* We can launch the app with **docker-compose up --build**.
    * On a même fait mieux! Il y a un script dans le dossier */topology-amt* qui permet de lancer automatiquement les différentes commandes.

* We can generate a (large) list of random things.
    * Il y a possibilité de créer jusqu'à 1'234'567 chiens en même temps.

* We can add a new Thing. There is a form for that.
    * Il y a la possibilité de créer un nouveau chien via le formulaire disponible à l'URL suivante :[http://<IP_DOCKER-MACHINE>:9090/AMTBootcamp-1.0-SNAPSHOT/dog/create](http://<IP_DOCKER-MACHINE>:9090/AMTBootcamp-1.0-SNAPSHOT/dog/create)

* We can delete a Thing.
    * Le lien de suppression se trouve à côté de chaque chien dans le tableau.

* We can update a Thing.
    * Le lien d'édition se trouve à côté de chaque chien dans le tableau. Ce lien redirige sur un formulaire ou les diverses informations peuvent être éditées.

* We have pagination controls to browse through the list.
    * Nous avons créer une pagination ainsi que les différents bouton pour y naviguer. Chaque bouton permet respectivement d'aller à la première page / la page précédente / la page suivante / la dernière page.

* The Things are stored in a database (running in a Docker container)
    * Nous avons un container docker MySQL pour stocker nos chiens. Nous utilisons également PhpMyAdmin pour pouvoir manager notre base de donnée.

* The MVC pattern is used to manage navigation between pages.
    * Nous avons séparé avec soins les différents modules et nous avons respecté le pattern MVC.

* We are not able to make the application crash.
    * On espère :-)

## Fonctionnalités optionnelles

### Un Site web de qualité

Nous avons créé un site web simple, facile à utiliser et à comprendre. Chaque page est belle et simple (à notre goût en tout cas). Nous avons également fait de jolis formulaires qui vérifient les entrées de l'utilisateur et un tableau d'une qualité hors-pair, sans parler du menu de navigation.

### Une API-REST des plus pure

Pour pouvoir faciliter la création, la génération, la suppression, etc., nous avons réalisé une API-REST simple et efficace. Étant donné que nous n'en avions jamais fait auparavant, nous sommes très satisfait du résultat et nous avons réussi à l'utiliser d'une manière que nous pensons correcte.

### Une pagination à toute épreuve

 Non-content d'avoir implémenté une simple pagination, nous avons en plus décidé de gérer le nombre d'entrées du tableau de chiens à afficher. Le nombre de pages s'adapten en fonction et changer de page conserve le bon nombre d'entrées.

 ### Une base de données sur des chiens
 Déjà, qui n'aime pas les chiens? 
 Chaque quote est également générée avec astuce et soin, dans le but d'avoir un bruit de chien le plus réaliste possible!

