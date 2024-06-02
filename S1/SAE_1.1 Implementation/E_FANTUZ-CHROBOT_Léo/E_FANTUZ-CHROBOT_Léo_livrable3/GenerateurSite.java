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
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][1] + " (" + (String) Produits[indice - 1][0] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][2] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if((indice) == 2){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if((indice) == 3){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][1] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][2] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if((indice) == 4){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][1] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][1] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][0] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }





            else if((indice) == 7){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][3] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][2] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }





            else if(indice == (int)(length(Produits) - 3)){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if(indice == (int)(length(Produits) - 2)){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if(indice == (int)(length(Produits) - 1)){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else if(indice == (int)(length(Produits))){
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
            else{
                contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit" + (indice) + ".html\">" + (String) Produits[indice - 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 1) + ".html\">" + (String) Produits[indice][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 2) + ".html\">" + (String) Produits[indice + 1][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 3) + ".html\">" + (String) Produits[indice + 2][0] + "</a></li>\n        <li><a href=\"produit" + (indice + 4) + ".html\">" + (String) Produits[indice + 3][0] + "</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) Produits[indice - 1][0] + " (" + (String) Produits[indice - 1][2] + ")" + "</h2>\n        <h3>" + (String) Produits[indice - 1][3] + " (Sortie en " + (String) Produits[indice - 1][1] + ")" + "</h3>\n        <p>\n" + (String) Produits[indice - 1][4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
            }
        }

        return contenu;
    }








    void algorithm(){

        String[][] Produits = chargerProduits("data", "produit");
        
        String nomPage = "index";


        for(int indice = 0; indice <= length(Produits); indice = indice + 1){
            stringAsFile("output/" + nomPage + ".html", contenu(nomPage, (indice)));
            nomPage = "produit" + (indice + 1);
        }
    }

}