-- Script de création de la base de données ENCHERES
--   type :      MySQL
--

-- /!\Infomartions
-- Faire d'abord la modification du "Default Storage Engine" dans phpMyAdmin : 
-- Allez sur ServerMysql > Variable > Default Storage engine > Edit > taper : InnoDB (cf image dans Teams /Fichiers/BDD_trocEnchere/InnoDB.png
-- Ensuite  : Créer une base de donnée troc_encheres (conserver ce nom)
-- Insérer le script suivant d'un seul coup. 


CREATE TABLE CATEGORIES (
    no_categorie   INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    libelle        VARCHAR(30) NOT NULL
);



CREATE TABLE ENCHERES (
    date_enchere                  datetime NOT NULL,
    no_utilisateur   INTEGER NOT NULL,
    no_vente             INTEGER NOT NULL,
    mise   INTEGER NOT NULL
);

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY (no_utilisateur, no_vente);

CREATE TABLE RETRAITS (
    no_vente         INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
     
);

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_vente);

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(64) NOT NULL,
    credit           INTEGER NOT NULL,
    isActif			 bit NOT NULL DEFAULT 1,
    administrateur   bit NOT NULL 
  
);

CREATE UNIQUE INDEX 'UX_pseudo' ON UTILISATEURS('pseudo');
CREATE UNIQUE INDEX 'UX_email' ON UTILISATEURS('email');
CREATE INDEX 'IX_telephone' ON UTILISATEURS('telephone');


CREATE TABLE VENTES (
    no_vente                      INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nom_article                    VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER, 
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL,
    retrait_article bit 	      NOT NULL DEFAULT 0
);


ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_ventes_fk FOREIGN KEY ( no_vente )
        REFERENCES ventes ( no_vente )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_ventes_fk FOREIGN KEY ( no_vente )
        REFERENCES ventes ( no_vente )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE VENTES
    ADD CONSTRAINT ventes_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE VENTES
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action ;
