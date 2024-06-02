--Ex1 Q2

--(a) Combien il y a de formations gérés par ParcourSup ?

SELECT count(DISTINCT n110) FROM import; --13869


--(b) Combien il y a d’établissements gérés par ParcourSup ?

SELECT DISTINCT count(*) FROM Etablissements ; --4105


--(c) Combien il y a de formations pour l'université de Lille ?

SELECT DISTINCT count(*) FROM import --124
WHERE n4 = 'Université de Lille';

--(d) Combien il y a de formations pour notre IUT ?

SELECT DISTINCT count(*) FROM import
WHERE n8 = 'Lille'
AND n4 LIKE 'IUT%'; --14


--(e) Quel est le code du BUT Informatique de l’unversité de Lille ?

--Pour le code UAI :

SELECT DISTINCT n3 FROM import
WHERE n8 = 'Lille'
AND n9 LIKE 'Villeneuve%'
AND n10 = 'BUT - Informatique'; --0597215X


--Pour le code de formation :

SELECT DISTINCT n110 FROM import
WHERE n8 = 'Lille'
AND n9 LIKE 'Villeneuve%'
AND n10 = 'BUT - Informatique'; --6888


--(f) Citez 5 colonnes contenant des valeurs nulles:

--n16, n23, n38, n39 et n55.


--Ex 3:

--Q1. Ecrire une requête qui, à partir de import affiche le contenu de la colonne n56 et le re-calcul de celle-ci à partir d’autres colonnes de import (2 cols).

SELECT n56, (n57 + n58 + n59) AS calcul FROM import LIMIT 10;


--Nous pouvons valider ce calcul à l'aide de la commande suivante:

SELECT n56 FROM import
EXCEPT
SELECT (n57 + n58 + n59) AS calcul FROM import
UNION ALL
SELECT n56 FROM import
EXCEPT
SELECT (n57 + n58 + n59) AS calcul FROM import;




--Q2. Quelle requête vous permet de savoir que ce re-calcul est parfaitement exact ?

SELECT n56 FROM import
EXCEPT
SELECT (n57 + n58 + n59) FROM import
UNION ALL
SELECT (n57 + n58 + n59) AS calcul FROM import
EXCEPT
SELECT n56 FROM import;

--Le nombre de lignes différentes est de 0.





--Q3. Ecrire une requête qui, à partir de import affiche le contenu de la colonne n74 et le re-calcul de celle-ci à partir d’autres colonnes de import (2 cols).

SELECT n74, round((n51/n47)*100) AS calcul FROM import
WHERE n47 <> 0 LIMIT 10;



--Nous pouvons valider ce calcul à l'aide de la commande suivante:

SELECT n74 FROM import
EXCEPT
SELECT round((n51/n47)*100) AS calcul FROM import
WHERE n47 <> 0
UNION ALL
SELECT n74 FROM import
EXCEPT
SELECT round((n51/n47)*100) AS calcul FROM import
WHERE n47 <> 0;


--Pour les lignes foireuses (celles où n47 = 0, donc division impossible), nous pouvons en déduire le nombre à l'aide de la requête suivante:

SELECT count(*) FROM import
WHERE n47 = 0;

--Son nombre est de 138 lignes.





--Q4. Quelle requête vous permet de savoir que ce re-calcul est parfaitement exact ?

SELECT n74 FROM import
EXCEPT
SELECT round((n51/n47)*100) FROM import
WHERE n47 <> 0
UNION ALL
SELECT round((n51/n47)*100) AS calcul FROM import
WHERE n47 <> 0
EXCEPT
SELECT n74 FROM import;

--Le nombre de lignes différentes est de 0.


--Pour les lignes foireuses (celles où n47 = 0, donc division impossible), nous pouvons en déduire le nombre à l'aide de la requête suivante:

SELECT count(*) FROM import
WHERE n47 = 0;

--Son nombre est de 138 lignes.




--Q5. Ecrire une requête qui, à partir de import affiche le contenu de la colonne n76 et le re-calcul de celle-ci à partir d’autres colonnes de import (2 cols). A partir de combien de décimales ces données sont exactes ?

SELECT n76, round((n53/n47)*100) AS calcul FROM import
WHERE n47 <> 0 LIMIT 10;


--Pour vérifier cela, voici la commande:

SELECT n76 FROM import
EXCEPT
SELECT round((n53/n47)*100) AS calcul FROM import
WHERE n47 <> 0
UNION ALL
SELECT round((n53/n47)*100) AS calcul FROM import
WHERE n47 <> 0
EXCEPT
SELECT n76 FROM import;

--Aucune ligne incorrecte.





--Q6. Fournir la même requête sur vos tables ventilées

SELECT n76, round((propAvFinProc/propAccep)*100) AS calcul FROM Admis AS a, import AS i
WHERE a.formation = i.n110
AND propAccep <> 0 LIMIT 10;

--Pour les lignes foireuses (celles où propAccep (donc n47) = 0, donc division impossible), nous pouvons en déduire le nombre à l'aide de la requête suivante:

SELECT count(*) FROM Admis
WHERE propAccep = 0;

--Son nombre est de 138 lignes.


--Pour vérifier la validité de ce calcul, nous pouvons exécuter la commande suivante:

SELECT n76 FROM import
EXCEPT
SELECT round((propAvFinProc/propAccep)*100) FROM Admis
WHERE propAccep <> 0
UNION ALL
SELECT round((propAvFinProc/propAccep)*100) FROM Admis
WHERE propAccep <> 0
EXCEPT
SELECT n76 FROM import;





--Q7. Ecrire une requête qui, à partir de import affiche la n81 et la manière de la recalculer. A partir de combien de décimales ces données sont exactes ?

SELECT n81, round((n55*100)/n56) AS calcul FROM import
WHERE n56 <> 0 LIMIT 10;


SELECT n81 FROM import
EXCEPT
SELECT round((n55*100)/n56) FROM import
WHERE n56 <> 0
UNION ALL
SELECT round((n55*100)/n56) AS calcul FROM import
WHERE n56 <> 0
EXCEPT
SELECT n81 FROM import;

--Le nombre de lignes différentes est de 1, cela est dû à un arrondi différent entre le calcul ici présent et le fichier officiel Parcoursup, mais les résultats reste cohérent à un chiffre près.


--Pour les lignes foireuses (celles où n56 = 0, donc division impossible), nous pouvons en déduire le nombre à l'aide de la requête suivante:

SELECT count(*) FROM import
WHERE n56 = 0;

--Son nombre est de 22 lignes.





--Q8. Fournir la même requête sur vos tables ventilées

SELECT n81, round((admisBoursiers*100)/totalAdmis) AS calcul FROM Admis AS a, Boursiers AS b, import AS i
WHERE b.formation = i.n110
AND a.formation = b.formation
AND totalAdmis <> 0 LIMIT 10;


SELECT n81 FROM import
EXCEPT
SELECT round((admisBoursiers*100)/totalAdmis) FROM Admis AS a, Boursiers AS b
WHERE a.formation = b.formation
AND totalAdmis <> 0
UNION ALL
SELECT round((admisBoursiers*100)/totalAdmis) FROM Admis AS a, Boursiers AS b
WHERE a.formation = b.formation
AND totalAdmis <> 0
EXCEPT
SELECT n81 FROM import;

--Le nombre de lignes différentes est de 1, cela est dû à un arrondi différent entre le calcul ici présent et le fichier officiel Parcoursup, mais les résultats reste cohérent à un chiffre près.

