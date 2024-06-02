--wc -l data.csv (savoir nb de lignes)

--head -1 data.csv (si l'on veut voir la première colonne)

--head -11 data.csv | tail -1 (pour voir la 11eme colonne)

--tail -1 data.csv (pour voir la dernière colonne)

--head -1 fr-esr-parcoursup.csv | tr ";" "\n" | wc -l (si on veut le nombre de colonne)

--sed -i.old -e "1d" fr-esr-parcoursup.csv (pour enlever la ligne "1" du fichier)


--\! curl "https://www.exemple.com" > data.csv

--(créer la table import avant importation des données)

--\copy import FROM data.csv DELIMITER ';';

--ALTER TABLE Etablissements ADD PRIMARY KEY(UAI, codeDep, commune);

--ALTER TABLE Admis ADD FOREIGN KEY(formation) REFERENCES Formations(codeFormation);





--\copy Etablissements TO Etablissements.csv DELIMITER ';';
