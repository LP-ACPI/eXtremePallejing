DROP TABLE Produits CASCADE CONSTRAINT;
DROP TABLE Catalogues CASCADE CONSTRAINT;

CREATE TABLE Catalogues
(codeCatalogue NUMBER DEFAULT 0 NOT NULL, 
nomCatalogue VARCHAR(10) NOT NULL, 
CONSTRAINT pk_catalogue PRIMARY KEY(codeCatalogue),
CONSTRAINT uk_catalogue UNIQUE (nomCatalogue));

CREATE TABLE Produits
(codeProduit NUMBER DEFAULT 0 NOT NULL, 
nomProduit VARCHAR(10) NOT NULL, 
prixProduit FLOAT NOT NULL, 
quantiteProduit NUMBER NOT NULL, 
catalogue VARCHAR(10),
CONSTRAINT pk_produits PRIMARY KEY(codeProduit),
CONSTRAINT fk_produits_Catalogue FOREIGN KEY(catalogue) REFERENCES Catalogues(nomCatalogue) ON DELETE CASCADE,
CONSTRAINT uk_produits_catalogue UNIQUE (nomProduit,catalogue));

CREATE OR REPLACE TRIGGER auto_incr_produits_trig
  AFTER INSERT ON Produits
  FOR EACH ROW
BEGIN
  :new.codeProduit := auto_incr_seq.nextval;
END;

CREATE OR REPLACE TRIGGER auto_incr_catalogues_trig
  AFTER INSERT ON Catalogues
  FOR EACH ROW
BEGIN
  :new.codeCatalogue := auto_incr_seq.nextval;
END;