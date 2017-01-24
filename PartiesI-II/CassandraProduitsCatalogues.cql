CREATE KEYSPACE ks_catalogue WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

drop table if exists ks_catalogue.produits_du_catalogue;
CREATE TABLE ks_catalogue.produits_du_catalogue (
	nom_catalogue varchar static,
	nom_produit varchar,
	prix_produit double,
	quantite_produit int,
	PRIMARY KEY (nom_catalogue,nom_produit)
) WITH CLUSTERING ORDER BY (nom_produit DESC);

//drop table if exists ks_catalogue.produits_du_catalogue;
//CREATE TABLE ks_catalogue.produits_du_catalogue (
//	nom_catalogue varchar,
//	id_produit uuid,
//	nom_produit varchar,
//	prix_produit double,
//	quantite_produit int,
//	PRIMARY KEY (nom_catalogue)
//);


INSERT INTO ks_catalogue.catalogue_produits(nom_catalogue) 
VALUES ('test');

INSERT INTO ks_catalogue.produits_du_catalogue(id_produit,nom_produit,nom_catalogue,prix_produit,quantite_produit) 
VALUES (1,'test','test_produit',56.2,5);


INSERT INTO ks_catalogue.catalogue_produits(nom_catalogue,nom_produit,prix_produit,quantite_produit) 
VALUES ('test','test_produit2',56.2,5);

INSERT INTO ks_catalogue.catalogue_produits(nom_catalogue,nom_produit,prix_produit,quantite_produit) 
VALUES ('machin','test_produit',55,5);

SELECT count(*) from ks_catalogue.catalogue_produits WHERE nom_catalogue = 'test' ALLOW FILTERING;

UPDATE ks_catalogue.catalogue_produits SET prix_produit=5.3 WHERE nom_produit='test_produit' AND nom_catalogue='machin';

DELETE FROM ks_catalogue.catalogue_produits WHERE nom_produit='test_produit' AND nom_catalogue='test';