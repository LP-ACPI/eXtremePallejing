DROP TABLE Produits;

CREATE TABLE Produits
(codeProduit NUMBER DEFAULT 0 NOT NULL, 
nomProduit VARCHAR(10) UNIQUE NOT NULL, 
prixProduit FLOAT NOT NULL, 
quantiteProduit NUMBER NOT NULL, 
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