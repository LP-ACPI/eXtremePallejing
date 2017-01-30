DROP TABLE Produits CASCADE CONSTRAINT;
DROP TABLE Catalogues CASCADE CONSTRAINT;

CREATE TABLE Catalogues
(codeCatalogue NUMBER DEFAULT 0, 
nomCatalogue VARCHAR(10),
CONSTRAINT nn_code_catalogue CHECK (codeCatalogue IS NOT NULL),
CONSTRAINT nn_nom_catalogue CHECK (nomCatalogue IS NOT NULL),
CONSTRAINT pk_catalogue PRIMARY KEY(codeCatalogue),
CONSTRAINT uk_catalogue UNIQUE (nomCatalogue));

CREATE TABLE Produits
(codeProduit NUMBER DEFAULT 0, 
nomProduit VARCHAR(10), 
prixProduit FLOAT, 
quantiteProduit NUMBER, 
catalogue VARCHAR(10),
CONSTRAINT nn_code_produit CHECK (codeProduit IS NOT NULL),
CONSTRAINT nn_nom_produit CHECK (nomProduit IS NOT NULL),
CONSTRAINT nn_prix_produit CHECK (prixProduit IS NOT NULL),
CONSTRAINT nn_qunatite_produit CHECK (quantiteProduit IS NOT NULL),
CONSTRAINT pk_produits PRIMARY KEY(codeProduit),
CONSTRAINT fk_produits_Catalogue FOREIGN KEY(catalogue) REFERENCES Catalogues(nomCatalogue) ON DELETE CASCADE,
CONSTRAINT uk_produits_catalogue UNIQUE (nomProduit,catalogue));

CREATE OR REPLACE TRIGGER auto_incr_produits_trig
  BEFORE INSERT ON Produits
  FOR EACH ROW
BEGIN
  :new.codeProduit := auto_incr_seq.nextval;
END;

CREATE OR REPLACE TRIGGER auto_incr_catalogues_trig
  BEFORE INSERT ON Catalogues
  FOR EACH ROW
BEGIN
  :new.codeCatalogue := auto_incr_seq.nextval;
END;