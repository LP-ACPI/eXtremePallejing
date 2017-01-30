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

## Partie III

### Gestion de produits de plusieurs catalogues

suite des parties précédentes, avec ajout des fonctionnalités daffichage de détails, d'ajout, de suppression et de sélection de Catalogues.

> Pour changer de technologie de persistance (Relationnel - XML - MongoDB (préconfiguré pour un serveur MongoDB local)):
>> Dans main de `ControleurAccueil` mettre l'instance la fabrique souhaitée dans `FabriqueAbstraiteDAO.setInstance(new FabriqueDAOXXX()`, où `XXX` correspond au choix de la technologie 

Ajouts et changements par rapport à la partie précédente:

- nouvelle fenêtre et guise d'accueil
- design pattern d'observateur sur la gestion des catalogues lié cette fenêtre
- Fabrique abstraite à la place des fabriques concrètes
- Data-gateway en fenêtre principale, Data-Mapper à l'accueil
- CataloguesDAO (xml, relationnel, MongoDB)
- ProduitsDAO adaptés pour réagir au Catalogue où le produit se situe

