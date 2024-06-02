--Destructions des tables

DROP TABLE Lieu;
DROP TABLE Boursiers;
DROP TABLE Admis;
DROP TABLE Etablissements;
DROP TABLE Formations;
DROP TABLE import;


--Téléchargement du fichier parcoursup.csv comportant les données et redirection dans le fichier "fr-esr-parcoursup.csv"

--\! curl "https://data.enseignementsup-recherche.gouv.fr/api/explore/v2.1/catalog/datasets/fr-esr-parcoursup/exports/csv?lang=fr&timezone=Europe%2FBerlin&use_labels=true&delimiter=%3B" > fr-esr-parcoursup.csv

--Création de la table "import":

CREATE TABLE import(
n1 INT,
n2 TEXT,n3 TEXT,n4 TEXT,n5 TEXT,n6 TEXT,
n7 TEXT,n8 TEXT,n9 TEXT,n10 TEXT,n11 TEXT,
n12 TEXT,n13 TEXT,n14 TEXT,n15 TEXT,n16 TEXT,
n17 TEXT,n18 FLOAT,n19 INT,n20 INT,n21 INT,
n22 TEXT,n23 INT,n24 INT,n25 INT,n26 INT,
n27 INT,n28 INT,n29 INT,n30 INT,n31 INT,
n32 INT,n33 INT,n34 INT,n35 INT,n36 INT,
n37 TEXT,n38 TEXT,n39 INT,n40 INT,n41 INT,
n42 INT,n43 INT,n44 INT,n45 INT,n46 INT,
n47 INT,n48 INT,n49 INT,n50 INT,n51 FLOAT,
n52 FLOAT,n53 FLOAT,n54 TEXT,n55 INT,n56 INT,
n57 INT,n58 INT,n59 INT,n60 INT,n61 INT,
n62 FLOAT,n63 TEXT,n64 TEXT,n65 TEXT,n66 FLOAT,
n67 FLOAT,n68 INT,n69 INT,n70 TEXT,n71 TEXT,
n72 INT,n73 INT,n74 FLOAT,n75 TEXT,n76 FLOAT,
n77 FLOAT,n78 FLOAT,n79 FLOAT,n80 FLOAT,n81 FLOAT,
n82 FLOAT,n83 FLOAT,n84 FLOAT,n85 FLOAT,n86 FLOAT,
n87 FLOAT,n88 FLOAT,n89 FLOAT,n90 FLOAT,n91 FLOAT,
n92 FLOAT,n93 FLOAT,n94 FLOAT,n95 FLOAT,n96 FLOAT,
n97 FLOAT,n98 FLOAT,n99 FLOAT,n100 FLOAT,n101 FLOAT,
n102 TEXT,n103 TEXT,n104 TEXT,n105 TEXT,n106 TEXT,
n107 TEXT,n108 TEXT,n109 TEXT,n110 TEXT,n111 TEXT,
n112 TEXT,n113 TEXT,n114 FLOAT,n115 TEXT,n116 FLOAT,
n117 TEXT,n118 TEXT);



--Importation des données dans la table "import":

\copy import FROM fr-esr-parcoursup.csv DELIMITER ';';
    
   
--Création de la table "Etablissements":    

CREATE TABLE Etablissements AS
SELECT DISTINCT n3 AS UAI, n4 AS nom, n5 AS codeDep, n9 AS commune, n2 AS status
FROM import;


--Création de la table "Lieu":

CREATE TABLE Lieu AS
SELECT DISTINCT n6 AS departement, n7 AS region, n8 AS academie, n3 AS UAI, n5 AS codeDep, n9 AS commune
FROM import;


--Création de la table "Formations":

CREATE TABLE Formations AS
SELECT DISTINCT n110 AS codeFormation, n11 AS selectivite, n13 AS filiereDetaillee, n14 AS filiere, n15 AS filiereDetailleeBis, n16 AS filiereTresDetaillee, n19 AS effectifTotal, n20 AS candidates, n39 AS generaux, n41 AS techno, n43 AS professionnels, n17 AS coordonnees, n18 AS capaEtablissement, n112 AS lienSite, N21 AS effectifPhasePrinc, n39 AS generauxClasses, n41 AS technoClasses, n43 AS proClasses, n45 AS autresClasses, n3 AS UAI, n5 AS codeDep, n9 AS commune
FROM import;


--Création de la table "Admis":

CREATE TABLE Admis AS
SELECT DISTINCT n110 AS formation, n56 AS totalAdmis, n57 AS generaux, n58 AS techno, n59 AS professionnels, n47 AS propAccep, n51 AS propDebProc, n53 AS propAvFinProc, n49 AS admisPhasePrinc, n62 AS sansMention, n63 AS mentionAB, n64 AS mentionB, n65 AS mentionTB, n66 AS mentionF, n48 AS candidatesAdmises
FROM import;



--Création de la table "Boursiers":

CREATE TABLE Boursiers AS
SELECT DISTINCT n110 AS formation, n24 AS generaux, n26 AS techno, n28 AS professionnels, n40 AS generauxClasses, n42 AS technoClasses, n44 AS proClasses, n55 AS admisBoursiers
FROM import;



--Initialisation des clés primaires:

ALTER TABLE Etablissements ADD PRIMARY KEY(UAI, codeDep, commune);
ALTER TABLE Formations ADD PRIMARY KEY(codeFormation);
ALTER TABLE Admis ADD PRIMARY KEY(formation);
ALTER TABLE Boursiers ADD PRIMARY KEY(formation);
ALTER TABLE Lieu ADD PRIMARY KEY(UAI, codeDep, commune);


--Initialisation des clés étrangères :

ALTER TABLE Formations ADD FOREIGN KEY(UAI, codeDep, commune) REFERENCES Etablissements(UAI, codeDep, commune);
ALTER TABLE Admis ADD FOREIGN KEY(formation) REFERENCES Formations(codeFormation);
ALTER TABLE Boursiers ADD FOREIGN KEY(formation) REFERENCES Formations(codeFormation);
ALTER TABLE Lieu ADD FOREIGN KEY(UAI, codeDep, commune) REFERENCES Etablissements(UAI, codeDep, commune);
ALTER TABLE Lieu ADD FOREIGN KEY(UAI, codeDep, commune) REFERENCES Etablissements(UAI, codeDep, commune);





--Exportations des tables :

--\copy Etablissements TO Etablissements.csv DELIMITER ';';
--\copy Formations TO Formations.csv DELIMITER ';';
--\copy Admis TO Admis.csv DELIMITER ';';
--\copy Boursiers TO Boursiers.csv DELIMITER ';';
--\copy Lieu TO Lieu.csv DELIMITER ';';




