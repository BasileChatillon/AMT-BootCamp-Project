# AMT-BootCamp-Project

## Description
Ce projet vise à nous faire créer une Multi-Tier Application. Pour cela, nous allons devoir créer une base de donnée et pouvoir la gérer depuis un site internet.
Nous allons devoir implémenter les échanges via le model MVC (Modele, Vue, Controleur) grâce à des Servlets ainsi que des pages JSP.
Nous allons également devoir déployer notre application via WildFly et dockeriser le toute pour pouvoir tout déployer via un "docker-compose", ainsi que mySQL et phpMyAdmin pour pouvoir manager notre base de donnée.
La connexion entre l'application et MySQL se fera grâce au plug-in JDBC (Java Data Base Connector).
L'application est construite à partir d'un projet web Maven (sur NetBeans).

Nous avons décidé de créer une base de donnée sur des chiens.

## URL

L'URL du site est : <a href=http://<IP_DOCKER-MACHINE>:9090/AMTBootcamp-1.0-SNAPSHOT/></a>
<IP_DOCKER-MACHINE> étant l'addresse IP de la machine docker tournant sur votre odrinateur

## Fonctionnalités de base
* There is a clean repo containing the code and a README.md file that describes how to use the app (indicate the access URL) and what has
.. The URL of the repo is <\a href="https://github.com/BasileChatillon/AMT-BootCamp-Project">.

* We can launch the app with **docker-compose up --build**.
.. On a même mieux fait. Il y a un script dans le dossier */topology-amt* qui pemret de lancer les différentes commandes.

* We can generate a (large) list of random things.
.. Il y a possibilité de créer jusqu'à 1'234'567 chiens en même temps.

* We can add a new Thing. There is a form for that.
.. Il y a la possibilité de créer un nouveau chien via le formulaire <a href=http://<IP_DOCKER-MACHINE>:9090/AMTBootcamp-1.0-SNAPSHOT/dog/create></a>

* We can delete a Thing.
.. Le lien de suppresion se trouve à côté de chaque chien dans le tableau

* We can update a Thing.
.. Le lien d'édition se trouve à côté de chaque chien dans le tableau. Ce lien redirige sur un formulaire ou les diverses informations peuvent être éditées

* We have pagination controls to browse through the list.
.. Nous avons créer une pagination ainsi que les différents bouton pour y naviguer. chaque bouton permettent respectivement d'aller à la première page / la page précédente / la page suivante / la dernière page.

* The Things are stored in a database (running in a Docker container)
..Nous avons un MySQL docker container pour stocker nos chiens. Nous utilisons également PhpMyAdmin pour pouvoir manager notre base de donnée.

* The MVC pattern is used to manage navigation between pages.
.. Nous avons séparé avec soins les différents modules et nous avons respecté le pattern MVC

* We are not able to make the application crash.
.. On espère.

## Fonctionnalités optionnelles.

* Un Site web de qualité
.. Nous avons créé un site web simple, facile à utiliser et à comprendre. Chaque page est belle et simple (à notre goût en tout cas). Nous avons également fait de jolis formulaires et un tableau d'une qualité hors-pair.

* Une API-REST des plus pure
.. Pour pouvoir faciliter la création, la génération, la suppression et autre, nous avons réalisé une API-REST simple et efficace. Compte tenu qu'on en avait jamais fait préalablement, nous sommes très satisfait du résultat et nous avons réussi à l'utiliser d'une manière que nous pensons correcte.

* Une pagination à toute épreuve
.. Non content d'avoir implémenté une simple pagination, nous avons en plus décidé de gérer le nombre d'êntrée du tableau de chiens à afficher. Le nombre de pages s'adapten en fonction et changer de page conserve le bon nombre d'entrées.

