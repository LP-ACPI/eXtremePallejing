DROP TYPE produit_type;
CREATE TYPE produit_type AS OBJECT (
	code NUMBER, 
	codeProduit VARCHAR2(10), 
	prixProduit FLOAT, 
	quantiteProduit NUMBER
);

DROP TYPE produit_nt_type;
CREATE TYPE produit_nt_type AS TABLE OF produit_type;

DROP TYPE catalogue_type;
CREATE TYPE catalogue_type AS OBJECT (
	code NUMBER,
	nomCatalogue VARCHAR2(10),
	ProduitsObjet produit_nt_type
);

DROP TABLE CataloguesObjet CASCADE CONSTRAINT;
CREATE TABLE CataloguesObjet of catalogue_type
(	CONSTRAINT nn_code CHECK (code is NOT NULL),
	CONSTRAINT un_nom UNIQUE (nomCatalogue),
	CONSTRAINT pk_catalogue PRIMARY KEY(codeCatalogue),
	CONSTRAINT uk_catalogue UNIQUE (nomCatalogue)
)
NESTED TABLE ProduitsObjet(
	CONSTRAINT nn_code CHECK (code is NOT NULL),
	CONSTRAINT un_nom UNIQUE (nomCatalogue),
) 
STORE AS tabliv_nt;

CREATE OR REPLACE TRIGGER auto_incr_produits_objets_trig
  BEFORE INSERT ON ProduitsObjet
  FOR EACH ROW
BEGIN
  codeProduit := auto_incr_seq.nextval;
END;

CREATE OR REPLACE TRIGGER auto_incr_catalogues_objets_trig
  BEFORE INSERT ON CataloguesObjet
  FOR EACH ROW
BEGIN
	codeCatalogue := auto_incr_seq.nextval;
END;