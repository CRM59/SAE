import java.io.File;
import java.io.IOException;
import java.io.PrintStream;





class GenerateurSite extends Program {



    public String fileAsString(String filename) {
        extensions.File f = new extensions.File(filename);
        String content = "";
        while (ready(f)) {
            content = content + readLine(f) + '\n';
        }
        return content;
    }






    String[] data(String texte){
        String content = texte;
        String[] resultat = new String[5];

        int indexTab = 0;

        int index = 0;

        int retourLigneIndex = 0;

        while(index < length(content)){                     
            if(charAt(content, index) == ':'){
                boolean retourLigneTrouvé = false;
                for(int indice = index; retourLigneTrouvé == false; indice = indice + 1){ 
                    if(charAt(content, indice) == '\n'){
                        retourLigneIndex = indice;
                        retourLigneTrouvé = true;
                    }
                }
                resultat[indexTab] = substring(content, index + 2, retourLigneIndex);
                index = retourLigneIndex + 1;
                indexTab = indexTab + 1;
            }
            index = index + 1;
        }
        return resultat;
    }








    String[][] chargerProduits(String repertoire, String prefixe){
        String[][] resultat = new String[length(getAllFilesFromDirectory("data"))][5];

        for(int indice = 0; indice < length(resultat, 1); indice = indice + 1){

            String texte = fileAsString(repertoire + "/" + prefixe + (indice + 1) + ".txt");

            resultat[indice] = data(texte);
        }




        return resultat;
    }












    String contenu(String page, int indice){
        String contenu = "";
        String[][] Produits = chargerProduits("data", "produit");


        if(equals(page, "index")){
            contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[0][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[3][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 5) + ".html\">" + (String) Produits[4][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>Tout ce que vous avez toujours voulu savoir sur les vieux ordis sans jamais avoir osé le demander !</h2>\n          <p>\nBienvenue dans le musée virtuel d'ordinateurs mythiques de l'histoire de l'informatique. Vous trouverez ici des éléments sur quelques machines qui ont marqué l'histoire de l'informatique que cela soit par leurs caractéristiques techniques ou l'impact commercial qu'elles ont eu et qui ont contribué au développement du secteur informatique.\n          </p>\n      </section>\n    </main>\n  </body>\n</html>\n\n";
        }
        else{
            

            if((indice) == 1){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][1] + " (" + (String) Produits[indice - 1][0] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][2] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if((indice) == 2){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if((indice) == 3){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][1] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][2] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if((indice) == 4){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][1] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][0] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }





            else if((indice) == 7){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][3] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][2] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }





            else if(indice == (int)(length(Produits) - 3)){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if(indice == (int)(length(Produits) - 2)){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if(indice == (int)(length(Produits) - 1)){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if(indice == (int)(length(Produits))){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else{
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n\n<li><a href=\"produits-nom.html\">Produits</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
        }

        return contenu;
    }






    void permuterLignes(String[][] contenu, int indice1, int indice2){
        String ligne1_col1 = contenu[indice1][0];
        String ligne1_col2 = contenu[indice1][1];
        String ligne1_col3 = contenu[indice1][2];
        String ligne1_col4 = contenu[indice1][3];
        String ligne1_col5 = contenu[indice1][4];

        String ligne2_col1 = contenu[indice2][0];
        String ligne2_col2 = contenu[indice2][1];
        String ligne2_col3 = contenu[indice2][2];
        String ligne2_col4 = contenu[indice2][3];
        String ligne2_col5 = contenu[indice2][4];



        contenu[indice1][0] = ligne2_col1;
        contenu[indice1][1] = ligne2_col2;
        contenu[indice1][2] = ligne2_col3;
        contenu[indice1][3] = ligne2_col4;
        contenu[indice1][4] = ligne2_col5;

        contenu[indice2][0] = ligne1_col1;
        contenu[indice2][1] = ligne1_col2;
        contenu[indice2][2] = ligne1_col3;
        contenu[indice2][3] = ligne1_col4;
        contenu[indice2][4] = ligne1_col5;

    }





    public int compare(String s1,  String s2){

        int resultat = 0;


        int tailleMot1 = length(s1);
        int tailleMot2 = length(s2);

        int tailleBoucle = 0;

        if(tailleMot1 > tailleMot2){
            tailleBoucle = tailleMot2;
        }
        else{
            tailleBoucle = tailleMot1;
        }

        boolean stop = false;



         if(!equals(s1, s2)){
             for(int i = 0; i < tailleBoucle && stop == false; i++){
                 if(charAt(s1, i) > charAt(s2, i)){
                     resultat = 1;
                     stop = true;
                 }
                 else if(charAt(s1, i) < charAt(s2, i)){
                     resultat = -1;
                     stop = true;
                 }
            }
        }



        if(resultat == 0){
            if(length(s1) > length(s2)){
                resultat = 1;
            }
            else if(length(s1) < length(s2)){
                resultat = -1;
            }
        }

        return resultat;
    }




    void trierSurColonne(String[][] contenu, int colonne){



        for(int i = length(contenu)-1; i >= 0; i--){
            for(int j = 0; j<i; j++){
                if(compare(contenu[j][colonne], contenu[j+1][colonne]) == 1){
                    permuterLignes(contenu, j, j+1);
                }
            }
            
        }
    }









    void contenuProduitNom(String[][] contenu, String nomPage){
        String resultat = "";



        resultat = "<!DOCTYPE html>\n"
        + "<html lang=\"fr\">\n"
        + "  <head>\n"
        + "    <title>Ordinateurs mythiques</title>\n"
        + "    <meta charset=\"utf-8\">\n"
        + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n"
        + "  </head>\n"
        + "  <body>\n"
        + "    <header>\n"
        + "      <h1>Ordinateurs mythiques</h1>\n"
        + "    </header>\n"
        + "    <nav>\n"
        + "      <ul>\n"
        + "        <li><a href=\"index.html\">Accueil</a></li>\n"
        + "<li><a href=\"produits-nom.html\">Produits</a></li>\n"
        + "      </ul>\n"
        + "    </nav>\n"
        + "    <main>\n"
        + "      <section>\n"
        + "        <h2>Liste de l'ensemble des ordinateurs</h2>\n"
        + "          <p>\n"
        + "Trier sur : <a href=\"produits-nom.html\">NOM</a>, <a href=\"produits-date.html\">DATE</a>, <a href=\"produits-prix.html\">PRIX</a>, <a href=\"produits-entreprise.html\">ENTREPRISE</a>, <a href=\"produits-description.html\">DESCRIPTION</a>.\n"
        + "            <table>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[0][0] + "</td><td>" + contenu[0][1] + "</td><td>" + contenu[0][2] + "</td><td>" + contenu[0][3] + "</td><td>" + contenu[0][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[1][0] + "</td><td>" + contenu[1][1] + "</td><td>" + contenu[1][2] + "</td><td>" + contenu[1][3] + "</td><td>" + contenu[1][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[2][0] + "</td><td>" + contenu[2][1] + "</td><td>" + contenu[2][2] + "</td><td>" + contenu[2][3] + "</td><td>" + contenu[2][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[3][0] + "</td><td>" + contenu[3][1] + "</td><td>" + contenu[3][2] + "</td><td>" + contenu[3][3] + "</td><td>" + contenu[3][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[4][0] + "</td><td>" + contenu[4][1] + "</td><td>" + contenu[4][2] + "</td><td>" + contenu[4][3] + "</td><td>" + contenu[4][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[5][0] + "</td><td>" + contenu[5][1] + "</td><td>" + contenu[5][2] + "</td><td>" + contenu[5][3] + "</td><td>" + contenu[5][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[6][0] + "</td><td>" + contenu[6][1] + "</td><td>" + contenu[6][2] + "</td><td>" + contenu[6][3] + "</td><td>" + contenu[6][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[7][0] + "</td><td>" + contenu[7][1] + "</td><td>" + contenu[7][2] + "</td><td>" + contenu[7][3] + "</td><td>" + contenu[7][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[8][0] + "</td><td>" + contenu[8][1] + "</td><td>" + contenu[8][2] + "</td><td>" + contenu[8][3] + "</td><td>" + contenu[8][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[9][0] + "</td><td>" + contenu[9][1] + "</td><td>" + contenu[9][2] + "</td><td>" + contenu[9][3] + "</td><td>" + contenu[9][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[10][0] + "</td><td>" + contenu[10][1] + "</td><td>" + contenu[10][2] + "</td><td>" + contenu[10][3] + "</td><td>" + contenu[10][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[11][0] + "</td><td>" + contenu[11][1] + "</td><td>" + contenu[11][2] + "</td><td>" + contenu[11][3] + "</td><td>" + contenu[11][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[12][0] + "</td><td>" + contenu[12][1] + "</td><td>" + contenu[12][2] + "</td><td>" + contenu[12][3] + "</td><td>" + contenu[12][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[13][0] + "</td><td>" + contenu[13][1] + "</td><td>" + contenu[13][2] + "</td><td>" + contenu[13][3] + "</td><td>" + contenu[13][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[14][0] + "</td><td>" + contenu[14][1] + "</td><td>" + contenu[14][2] + "</td><td>" + contenu[14][3] + "</td><td>" + contenu[14][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[15][0] + "</td><td>" + contenu[15][1] + "</td><td>" + contenu[15][2] + "</td><td>" + contenu[15][3] + "</td><td>" + contenu[15][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[16][0] + "</td><td>" + contenu[16][1] + "</td><td>" + contenu[16][2] + "</td><td>" + contenu[16][3] + "</td><td>" + contenu[16][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[17][0] + "</td><td>" + contenu[17][1] + "</td><td>" + contenu[17][2] + "</td><td>" + contenu[17][3] + "</td><td>" + contenu[17][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[18][0] + "</td><td>" + contenu[18][1] + "</td><td>" + contenu[18][2] + "</td><td>" + contenu[18][3] + "</td><td>" + contenu[18][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[19][0] + "</td><td>" + contenu[19][1] + "</td><td>" + contenu[19][2] + "</td><td>" + contenu[19][3] + "</td><td>" + contenu[19][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[20][0] + "</td><td>" + contenu[20][1] + "</td><td>" + contenu[20][2] + "</td><td>" + contenu[20][3] + "</td><td>" + contenu[20][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[21][0] + "</td><td>" + contenu[21][1] + "</td><td>" + contenu[21][2] + "</td><td>" + contenu[21][3] + "</td><td>" + contenu[21][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[22][0] + "</td><td>" + contenu[22][1] + "</td><td>" + contenu[22][2] + "</td><td>" + contenu[22][3] + "</td><td>" + contenu[22][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[23][0] + "</td><td>" + contenu[23][1] + "</td><td>" + contenu[23][2] + "</td><td>" + contenu[23][3] + "</td><td>" + contenu[23][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[24][0] + "</td><td>" + contenu[24][1] + "</td><td>" + contenu[24][2] + "</td><td>" + contenu[24][3] + "</td><td>" + contenu[24][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[25][0] + "</td><td>" + contenu[25][1] + "</td><td>" + contenu[25][2] + "</td><td>" + contenu[25][3] + "</td><td>" + contenu[25][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[26][0] + "</td><td>" + contenu[26][1] + "</td><td>" + contenu[26][2] + "</td><td>" + contenu[26][3] + "</td><td>" + contenu[26][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[27][0] + "</td><td>" + contenu[27][1] + "</td><td>" + contenu[27][2] + "</td><td>" + contenu[27][3] + "</td><td>" + contenu[27][4] + "</td>\n"
        + "              </tr>\n"
        + "              <tr>\n"
        + "                <td>" + contenu[28][0] + "</td><td>" + contenu[28][1] + "</td><td>" + contenu[28][2] + "</td><td>" + contenu[28][3] + "</td><td>" + contenu[28][4] + "</td>\n"
        + "              </tr>\n"
        + "            </table>\n"
        + "          </p>\n"
        + "      </section>\n"
        + "    </main>\n"
        + "  </body>\n"
        + "</html>\n\n";
        
        
        stringAsFile("output/" + nomPage + ".html", resultat);
        

    }







    void organiserProduits(String[][] contenu){
        /////////1//////////
        String Entreprise = contenu[0][0];
        String Nom = contenu[0][1];
        String Date = contenu[0][2];
        String Prix = contenu[0][3];

        contenu[0][0] = Nom;
        contenu[0][1] = Date;
        contenu[0][2] = Prix;
        contenu[0][3] = Entreprise;
        //////////2/////////
        Entreprise = contenu[1][2];
        Prix = contenu[1][3];

        contenu[1][2] = Prix;
        contenu[1][3] = Entreprise;
        //////////3/////////
        Entreprise = contenu[2][1];
        Date = contenu[2][2];
        Prix = contenu[2][3];

        contenu[2][1] = Date;
        contenu[2][2] = Prix;
        contenu[2][3] = Entreprise;
        //////////4/////////
        Nom = contenu[3][1];
        Date = contenu[3][0];
        Prix = contenu[3][3];
        Entreprise = contenu[3][2];

        contenu[3][0] = Nom;
        contenu[3][1] = Date;
        contenu[3][2] = Prix;
        contenu[3][3] = Entreprise;
        //////////5/////////
        Entreprise = contenu[4][2];
        Prix = contenu[4][3];

        contenu[4][2] = Prix;
        contenu[4][3] = Entreprise;
        ///////////////////
        Entreprise = contenu[5][2];
        Prix = contenu[5][3];

        contenu[5][2] = Prix;
        contenu[5][3] = Entreprise;
        ///////////////////

        for(int i = 7; i < length(contenu); i++){
        Entreprise = contenu[i][2];
        Prix = contenu[i][3];

        contenu[i][2] = Prix;
        contenu[i][3] = Entreprise;
        ///////////////////
        }


    }











    void algorithm(){

        String[][] Produits = chargerProduits("data", "produit");
        
        String nomPage = "index";


        for(int indice = 0; indice <= length(Produits); indice = indice + 1){
            stringAsFile("output/" + nomPage + ".html", contenu(nomPage, (indice)));
            nomPage = "produit" + (indice + 1);
        }



        organiserProduits(Produits);

        //Produits-nom
        trierSurColonne(Produits, 0);

        contenuProduitNom(Produits, "produits-nom");

        //Produits-date
        trierSurColonne(Produits, 1);

        contenuProduitNom(Produits, "produits-date");

        //Produits-prix
        trierSurColonne(Produits, 2);

        contenuProduitNom(Produits, "produits-prix");

        //Produits-entreprise
        trierSurColonne(Produits, 3);

        contenuProduitNom(Produits, "produits-entreprise");

        //Produits-description
        trierSurColonne(Produits, 4);

        contenuProduitNom(Produits, "produits-description");

    }

}