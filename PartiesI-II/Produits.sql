DROP TABLE Produits;

CREATE TABLE Produits
(codeProduit NUMBER DEFAULT 0, 
nomProduit VARCHAR(10), 
prixProduit FLOAT, 
quantiteProduit NUMBER,
CONSTRAINT nn_code_produit CHECK(codeProduit NOT NULL),
CONSTRAINT nn_nom_produit CHECK(nomProduit NOT NULL),
CONSTRAINT un_nom_produit UNIQUE(nomProduit),
CONSTRAINT un_prix_produit UNIQUE(prixProduit),
CONSTRAINT un_quantite_produit UNIQUE(quantiteProduit),
CONSTRAINT pk_Produits PRIMARY KEY(codeProduit)) ;

CREATE SEQUENCE auto_incr_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 100;

CREATE OR REPLACE TRIGGER auto_incr_produits_trig
  BEFORE INSERT ON Produits
  FOR EACH ROW
BEGIN
  :new.codeProduit := auto_incr_seq.nextval;
END;