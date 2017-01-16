# mini-Projet java - UML/BDD - gestion de stock

*exercice d'architecture logicielle/UML/Bases de données: application de design patterns et de technologies de persistence de données*

## Parties I - II

### Gestion de produits d'un seul catalogue seulement. 
> Pour changer de technologie de persistance (Relationnel - XML):
>> à chaque instaciation de ProduitDAO (dans classes métier Produit **et** Catalogue)
>> changer `FabriqueDAOXXX` où `XXX` correspond à la technologie souhaitée

Design patterns et architectures utilisés:

- fabrique concrète de DAO (Relationnel - XML - autre)
- Data-gateway pour liaison avec base de données
- MVC/Façade pour interaction entre les interfaces graphiques et les classes Métier
- principe du Polymorphisme pour, par exempele, action de déconnexion par défaut de ConnexionDAO (XML n'en nécéssite pas)
- Singletons pour contrôleurs, connexionDAO et fabriques
- Adaptateur pour ProduitDAO_XML : signatures des méthodes de cette classe ne permettent pas d'implémenter l'interface I_ProduitDAO

---

## Parties III - IV

### Gestion de produits de plusieurs catalogues

suite des parties précédentes, avec ajout des fonctionnalités daffichage de détails, d'ajout, de suppression et de sélection de Catalogues.

> Pour changer de technologie de persistance (Relationnel - XML - *autres à venir (Objet relationnel, NoSQL)*):
>> Dans le renvoi de l'instance de `FabriqueAbstraiteDAO`, choisir la technologie `XXX` dans l'instanciation de `FabriqueDAOXXX`

Ajouts et changements par rapport à la partie précédente:

- nouvelle fenêtre et guise d'accueil
- designe pattern d'observateur sur la gestion de catalogues lié cette fenêtre
- Fabrique abstraite à la place des fabriques concrètes
- Data-gateway changé en Data-Mapper
- CataloguesDAO (xml et relationnel) ajoutés
- ProduitsDAO adaptés pour réagir au Catalogue où le produit se situe

---

#TO DO

- refactoring
- tests adaptés
- DAOs pour NoSQL (MongoDB) et objet Relationnel oracle
- (optionnel) Partie IV : Gestion des catégories de produits
