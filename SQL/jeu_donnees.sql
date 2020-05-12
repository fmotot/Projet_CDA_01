INSERT INTO utilisateurs (administrateur,code_postal,credit,email,mot_de_passe,nom,prenom,pseudo,rue,telephone,ville)
VALUES (0,35000,130,'bob.leponge@sea.shell','azerty','Leponge','Bob', 'boby','124 rue des Conques','06788888','Bikini Bottom');

INSERT INTO utilisateurs (administrateur,code_postal,credit,email,mot_de_passe,nom,prenom,pseudo,rue,telephone,ville)
VALUES (0,35000,100,'patrick.letoile@sea.shell','azerty','Letoile','patrick', 'patStar','120 rue des Conques','06788899','Bikini Bottom');

INSERT INTO utilisateurs (administrateur,code_postal,credit,email,mot_de_passe,nom,prenom,pseudo,rue,telephone,ville)
VALUES (0,35000,50,'carlo.tentacule@sea.shell','azerty','Tentacule','Carlo', 'Carl','121 rue des Conques','06788866','Bikini Bottom');

INSERT INTO utilisateurs (administrateur,code_postal,credit,email,mot_de_passe,nom,prenom,pseudo,rue,telephone,ville)
VALUES (0,35000,30,'sandy.ecureuil@sea.shell','azerty','Ecureuil','Sandy', 'Dynuts','13 rue de la Noix','06788800','Bikini Bottom');
;

INSERT INTO categories (libelle) VALUES ('BIJOUX');
INSERT INTO categories (libelle) VALUES ('VELO');
INSERT INTO categories (libelle) VALUES ('MEUBLES');
INSERT INTO categories (libelle) VALUES ('CHAUSSETTES');

INSERT INTO ventes (date_fin_encheres,description,nom_article,no_categorie,no_utilisateur,prix_initial,retrait_article)
VALUES('2020-05-11','une description de bijoux enchère terminée','Des bijoux de famiille',1,1,10,1);

INSERT INTO ventes (date_fin_encheres,description,nom_article,no_categorie,no_utilisateur,prix_initial)
VALUES('2020-02-03','une description d\' un beau velo enchère terminée','Un veau Bélo',2,2,15);

INSERT INTO ventes (date_fin_encheres,description,nom_article,no_categorie,no_utilisateur,prix_initial)
VALUES('2020-10-25','une description d\'une commode enchères en cours','Une commode.',3,3,5);

-- Enchère Article 1 vendu par 1 

INSERT INTO encheres (date_enchere,mise,no_utilisateur,no_vente)
VALUES ('2020-04-28 11:50:34',20,2,1);

INSERT INTO encheres (date_enchere,mise,no_utilisateur,no_vente)
VALUES ('2020-05-02 10:34:00',30,3,1);


-- Enchère Article 2 vendu par 2 

INSERT INTO encheres (date_enchere,mise,no_utilisateur,no_vente)
VALUES ('2020-01-01 11:50:34',20,3,2);

INSERT INTO encheres (date_enchere,mise,no_utilisateur,no_vente)
VALUES ('2020-02-02 10:34:00',35,1,2);

-- Enchère Article 3 vendu par 3

INSERT INTO encheres (date_enchere,mise,no_utilisateur,no_vente)
VALUES ('2020-01-01 11:50:34',20,1,3);

INSERT INTO encheres (date_enchere,mise,no_utilisateur,no_vente)
VALUES ('2020-04-13 10:34:00',70,4,3);

-- retrait article 1 numéro de vente 1

INSERT INTO retraits (no_vente,rue,code_postal,ville)
VALUES(1,'3 bvd des huitres','35000','Bikini Bottom');

-- retrait article 2 numéro de vente 2

INSERT INTO retraits (no_vente,rue,code_postal,ville)
VALUES(2,'6 bvd des moules','35000','Bikini Bottom');















