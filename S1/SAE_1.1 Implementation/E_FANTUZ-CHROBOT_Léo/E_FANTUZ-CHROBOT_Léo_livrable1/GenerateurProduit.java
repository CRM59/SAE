import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


class GenerateurProduit extends Program {



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





    void algorithm(){

        String nomFichier = argument(0);


        if(fileExist(nomFichier)){
            String texte = fileAsString(nomFichier);

            String[] tab = data(texte);

            println("<!DOCTYPE html>");
            println("<html lang=\"fr\">");
            println("  <head>");
            println("    <title>" + tab[0] + "</title>");
            println("    <meta charset=\"utf-8\">");
            println("  </head>");
            println("  <body>");
            println("    <h1>" + tab[0] + " (" + tab[2] + ")" + "</h1>");
            println("    <h2>" + tab[3] + " (Sortie en " + tab[1] + ")" + "</h2>");
            println("    <p>");
            println(tab[4] + "    </p>");
            println("  </body>");
            println("</html>");
        }
        else {
            error("le fichier que vous essayez de generer n'existe pas");
        }

    }

}