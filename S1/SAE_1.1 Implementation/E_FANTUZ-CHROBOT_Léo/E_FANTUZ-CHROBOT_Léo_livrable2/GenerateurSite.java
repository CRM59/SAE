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








    String contenu(String page){
        String contenu = "";

        if(equals(page, "index")){
            contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit1.html\">Produit 1</a></li>\n        <li><a href=\"produit2.html\">Produit 2</a></li>\n        <li><a href=\"produit3.html\">Produit 3</a></li>\n        <li><a href=\"produit4.html\">Produit 4</a></li>\n        <li><a href=\"produit5.html\">Produit 5</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>Tout ce que vous avez toujours voulu savoir sur les vieux ordis sans jamais avoir osé le demander !</h2>\n          <p>\nBienvenue dans le musée virtuel d'ordinateurs mythiques de l'histoire de l'informatique. Vous trouverez ici des éléments sur quelques machines qui ont marqué l'histoire de l'informatique que cela soit par leurs caractéristiques techniques ou l'impact commercial qu'elles ont eu et qui ont contribué au développement du secteur informatique.\n          </p>\n      </section>\n    </main>\n  </body>\n</html>\n";
        }
        else{

            String texte = fileAsString("data/" + page + ".txt");

            String[] tab = data(texte);


            contenu ="<!DOCTYPE html>\n<html lang=\"fr\">\n  <head>\n    <title>Ordinateurs mythiques</title>\n    <meta charset=\"utf-8\">\n    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n  </head>\n  <body>\n    <header>\n      <h1>Ordinateurs mythiques</h1>\n    </header>\n    <nav>\n      <ul>\n        <li><a href=\"index.html\">Accueil</a></li>\n        <li><a href=\"produit1.html\">Produit 1</a></li>\n        <li><a href=\"produit2.html\">Produit 2</a></li>\n        <li><a href=\"produit3.html\">Produit 3</a></li>\n        <li><a href=\"produit4.html\">Produit 4</a></li>\n        <li><a href=\"produit5.html\">Produit 5</a></li>\n      </ul>\n    </nav>\n    <main>\n      <section>\n        <h2>" + (String) tab[0] + " (" + (String) tab[2] + ")" + "</h2>\n        <h3>" + (String) tab[3] + " (Sortie en " + (String) tab[1] + ")" + "</h3>\n        <p>\n" + (String) tab[4] + "\n        </p>\n      </section>\n    </main>\n  </body>\n</html>";
        }

        return contenu;
    }

    




    void algorithm(){

        String nomPage = "index";

        for(int indice = 1; indice <= 6; indice = indice + 1){
            stringAsFile("output/" + nomPage + ".html", contenu(nomPage));
            nomPage = "produit" + indice;
        }
    }

}