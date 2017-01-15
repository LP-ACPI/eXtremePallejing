# Projet java JDBC - UML/BDD - gestion de stock

## Parties I - II

### Gestion de produits d'un seul catalogue seulement. 
> Pour changer de technologie de persistance (Relationnel - XML - *autres à venir (NoSQL)*):
>> à chaque instaciation de ProduitDAO (dans classes métier Produit **et** Catalogue)
>> changer `FabriqueDAOXXX` où `XXX` correspond à la technologie souhaitée

*Design patterns et architectures utilisés:*

- *fabrique concrète de DAO (Relationnel - XML - autre)*
- *Data-gateway pour liaison avec base de données*
- *MVC/Façade pour interaction entre les interfaces graphiques et les classes Métier*
- *principe du Polymorphisme pour, par exempele, action de déconnexion par défaut de ConnexionDAO (XML n'en nécéssite pas)*
- *Singletons pour contrôleurs et connexionDAO*
- *Adaptateur pour ProduitDAO_XML : signatures de cette classe ne permettent pas d'implémenter l'interface I_ProduitDAO*

## Parties III - IV

### Gestion de produits de plusieurs catalogues