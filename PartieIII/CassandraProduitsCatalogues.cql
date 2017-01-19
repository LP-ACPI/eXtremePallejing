CREATE KEYSPACE ks_catalogue WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

drop table ks_catalogue.catalogue_produits;
CREATE TABLE ks_catalogue.catalogue_produits (
	nom_catalogue varchar,
	nom_produit varchar,
	prix_produit double,
	quantite_produit int,
	PRIMARY KEY (nom_produit,nom_catalogue)
) WITH CLUSTERING ORDER BY (nom_catalogue DESC);


INSERT INTO ks_catalogue.catalogue_produits(nom_catalogue,nom_produit,prix_produit,quantite_produit) 
VALUES ('test','test_produit',56.2,5);


INSERT INTO ks_catalogue.catalogue_produits(nom_catalogue,nom_produit,prix_produit,quantite_produit) 
VALUES ('test','test_produit2',56.2,5);

INSERT INTO ks_catalogue.catalogue_produits(nom_catalogue,nom_produit,prix_produit,quantite_produit) 
VALUES ('machin','test_produit',55,5);

SELECT count(*) from ks_catalogue.catalogue_produits WHERE nom_catalogue = 'test' ALLOW FILTERING;

UPDATE ks_catalogue.catalogue_produits SET prix_produit=5.3 WHERE nom_produit='test_produit' AND nom_catalogue='machin';

DELETE FROM ks_catalogue.catalogue_produits WHERE nom_produit='test_produit' AND nom_catalogue='test';