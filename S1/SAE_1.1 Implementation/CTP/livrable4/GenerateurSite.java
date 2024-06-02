class GenerateurSite extends Program {
    
    final char   NEW_LINE = '\n';
    final String ENTETE   = "<!DOCTYPE html>"    + NEW_LINE +
                            "<html lang=\"fr\">" + NEW_LINE;
    
    // indices des différents champs (en colonne) de la structure de données des produits
    final int IDX_NOM         = 0;
    final int IDX_DATE        = 1;
    final int IDX_ENTREPRISE  = 2;
    final int IDX_PRIX        = 3;
    final int IDX_DESCRIPTION = 4;

    // Livrable 1
    String rechercherValeur(String chaine, String cle) {
        String valeur = "";
        int indice = 0;
        while (indice < length(chaine) && indice + length(cle) < length(chaine) && 
               !equals(cle, substring(chaine, indice, indice+length(cle)))) {
            indice = indice + 1;
        }
        if (indice < length(chaine) - length(cle)) {
            int indiceRetourLigne = indice;
            while (indiceRetourLigne < length(chaine) && charAt(chaine, indiceRetourLigne) != NEW_LINE) {
                indiceRetourLigne = indiceRetourLigne + 1;
            }
            valeur = substring(chaine, indice+length(cle), indiceRetourLigne);
        }
        return valeur;
    }

    // Livrable 2
    String genererHead(String titre) {
        return 
            "  <head>"    + NEW_LINE + 
            "    <title>" + titre + "</title>" + NEW_LINE + 
            "    <meta charset=\"utf-8\">"     + NEW_LINE +  
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">" + NEW_LINE +  
            "  </head>"    + NEW_LINE;
    }

    // Livrable 2
    String genererHeader(String titre) {
        return
            "    <header>"  + NEW_LINE +
            "      <h1>" + titre + "</h1>" + NEW_LINE +
            "    </header>" + NEW_LINE;
    }

    // Livrable 3
    /**
     * La fonction genererNav est paramétrée par la table des produits et le numéro du produit 
     * de la page courante. Ainsi, la navigation propose en premier bouton vers l'accueil, puis
     * le bouton vers le produit courant (connu grâce au paramètre numeroProduit) et ensuite 4
     * boutons vers les produits suivants. Si l'on arrive à la fin du catalogue, il faut être 
     * attentif à ne générer que le nombre de boutons nécessaires. Chaque bouton contient 
     * maintenant le nom du produit au lieu du nommage générique "ProduitX" du livrable prédécent. 
     */
    String genererNav(String[][] produits, int numeroProduit) {
        String boutonsProduits = "";
        if (numeroProduit >= 0) {
            int nbBoutons = 1;
            while (nbBoutons <= 5 && numeroProduit + nbBoutons <= length(produits, 1)) {
                boutonsProduits = boutonsProduits + 
                    "        <li><a href=\"produit" + (numeroProduit + nbBoutons) + ".html\">" +
                    getChamps(produits, numeroProduit + nbBoutons - 1, IDX_NOM) + "</a></li>" + NEW_LINE;
                nbBoutons = nbBoutons + 1;
            }
            boutonsProduits = boutonsProduits + NEW_LINE;
        }
        return 
            "    <nav>"     + NEW_LINE +
            "      <ul>"    + NEW_LINE +
            "        <li><a href=\"index.html\">Accueil</a></li>" + NEW_LINE +
            boutonsProduits + 
            "<li><a href=\"produits-nom.html\">Produits</a></li>" + NEW_LINE +
            "      </ul>"   + NEW_LINE +
            "    </nav>"    + NEW_LINE;
    }

    // Livrable 1
    String genererContenuProduit(String nom, String date, String entreprise, String prix, String description) {
        return             
            "    <main>"       + NEW_LINE +
            "      <section>"  + NEW_LINE +
            "        <h2>" + nom + " (" + entreprise + ")</h2>"      + NEW_LINE +
            "        <h3>" + prix + " (Sortie en " + date + ")</h3>" + NEW_LINE +
            "        <p>"      + NEW_LINE +
            description        + NEW_LINE + 
            "        </p>"     + NEW_LINE + 
            "      </section>" + NEW_LINE +
            "    </main>"      + NEW_LINE;
    }

    // Livrable 2
    String genererPageProduit(String head_titre, String[][] produits, int numeroProduit) {
        final String NOM         = getChamps(produits, numeroProduit, IDX_NOM);
        final String DATE        = getChamps(produits, numeroProduit, IDX_DATE);
        final String ENTREPRISE  = getChamps(produits, numeroProduit, IDX_ENTREPRISE);
        final String PRIX        = getChamps(produits, numeroProduit, IDX_PRIX);
        final String DESCRIPTION = getChamps(produits, numeroProduit, IDX_DESCRIPTION);
        return 
            ENTETE + 
            genererHead(head_titre) + 
            "  <body>"  + NEW_LINE + 
            genererHeader(head_titre) + 
            genererNav(produits, numeroProduit) + 
            genererContenuProduit(NOM, DATE, ENTREPRISE, PRIX, DESCRIPTION) + 
            "  </body>" + NEW_LINE + 
            "</html>";
    }

    // Livrable 2
    String genererAccueil(String head_titre, String[][] produits) {
        return ENTETE +
            genererHead(head_titre)   +
            "  <body>"                + NEW_LINE + 
            genererHeader(head_titre) + 
            genererNav(produits, 0)   +
            "    <main>"              + NEW_LINE +
            "      <section>"         + NEW_LINE +
            "        <h2>Tout ce que vous avez toujours voulu savoir sur les vieux ordis sans jamais avoir osé le demander !</h2>" + NEW_LINE +
            "          <p>"           + NEW_LINE +
            "Bienvenue dans le musée virtuel d'ordinateurs mythiques de l'histoire de l'informatique. "+ 
            "Vous trouverez ici des éléments sur quelques machines qui ont marqué l'histoire de l'informatique "+
            "que cela soit par leurs caractéristiques techniques ou l'impact commercial qu'elles ont eu et qui "+
            "ont contribué au développement du secteur informatique." + NEW_LINE +
            "          </p>"          + NEW_LINE +
            "      </section>"        + NEW_LINE +
            "    </main>"             + NEW_LINE +
            "  </body>"               + NEW_LINE + 
            "</html>"                 + NEW_LINE;
    }

    // Livrable 4
    void testPermuterLignes() {
        String[][] t = new String[][]{{"a", "b", "c"},
                                      {"D", "E", "F"}};
        permuterLignes(t, 0, 1);
        assertArrayEquals(new String[][]{{"D", "E", "F"},
                                        {"a", "b", "c"}}, t);
    }

    // Livrable 4
    void permuterLignes(String[][] produits, int ligneA, int ligneB) {
        String tmp;
        for (int idxCol = 0; idxCol < length(produits, 2); idxCol = idxCol + 1) {
            tmp = produits[ligneA][idxCol];
            produits[ligneA][idxCol] = produits[ligneB][idxCol];
            produits[ligneB][idxCol] = tmp;
        }
    }

    // Livrable 4
    void testTrierSurColonne() {
        String[][] t = new String[][]{{"a1", "b3", "c2"},
                                      {"a3", "b2", "c3"},
                                      {"a2", "b1", "c1"}};
        trierSurColonne(t, 0); // on trie sur la première colonne
        assertArrayEquals(new String[][]{{"a1", "b3", "c2"},
                                         {"a2", "b1", "c1"},
                                         {"a3", "b2", "c3"}
                                         }, t);
        trierSurColonne(t, 1); // on trie sur la deuxième colonne
        assertArrayEquals(new String[][]{{"a2", "b1", "c1"},
                                         {"a3", "b2", "c3"},
                                         {"a1", "b3", "c2"},
                                         }, t);
        trierSurColonne(t, 2); // on trie sur la troisième colonne
        assertArrayEquals(new String[][]{{"a2", "b1", "c1"},
                                         {"a1", "b3", "c2"},
                                         {"a3", "b2", "c3"}
                                         }, t);
    }

    // Livrable 4
    void trierSurColonne(String[][] produits, int idxCol) {
        boolean permutation = true;
        int end = 1;
        while (permutation) {
            permutation = false;
            for (int idxLig = 0; idxLig < length(produits, 1) - end; idxLig = idxLig +1) {
                if (compare(getChamps(produits, idxLig,   idxCol),
                            getChamps(produits, idxLig+1, idxCol)) > 0) {
                    permuterLignes(produits, idxLig, idxLig + 1);
                    permutation = true;
                }
            }
            end = end + 1;
        }
    }

    // Livrable 4
    String genererTable(String[][] produits) {
        String tableDeProduits = "            <table>" + NEW_LINE;
        //tableDeProduits        = tableDeProduits + "              <tr><td>NOM</td><td>DATE</td><td>PRIX</td><td>ENTREPRISE</td><td>DESCRIPTION</td></tr>";
        for (int numeroProduit = 0; numeroProduit < length(produits, 1); numeroProduit = numeroProduit + 1) {
            final String NOM         = getChamps(produits, numeroProduit, IDX_NOM);
            final String DATE        = getChamps(produits, numeroProduit, IDX_DATE);
            final String ENTREPRISE  = getChamps(produits, numeroProduit, IDX_ENTREPRISE);
            final String PRIX        = getChamps(produits, numeroProduit, IDX_PRIX);
            final String DESCRIPTION = getChamps(produits, numeroProduit, IDX_DESCRIPTION); 
            tableDeProduits    = tableDeProduits + "              <tr>" + NEW_LINE;
            tableDeProduits    = tableDeProduits + "                <td>" + NOM + "</td><td>" + DATE + "</td><td>"+
                                 PRIX + "</td><td>" + ENTREPRISE + "</td><td>" + DESCRIPTION+"</td>" + NEW_LINE;;
            tableDeProduits    = tableDeProduits + "              </tr>" + NEW_LINE;
        }
        return tableDeProduits + "            </table>";
    }

    // Livrable 4
    String genererListeProduit(String head_titre, String[][] produits) {
        return ENTETE +
            genererHead(head_titre)   +
            "  <body>"                + NEW_LINE + 
            genererHeader(head_titre) + 
            genererNav(produits, -1)   +
            "    <main>"              + NEW_LINE +
            "      <section>"         + NEW_LINE +
            "        <h2>Liste de l'ensemble des ordinateurs</h2>" + NEW_LINE +
            "          <p>"           + NEW_LINE +
            "Trier sur : <a href=\"produits-nom.html\">NOM</a>, <a href=\"produits-date.html\">DATE</a>, " + 
            "<a href=\"produits-prix.html\">PRIX</a>, <a href=\"produits-entreprise.html\">ENTREPRISE</a>, " + 
            "<a href=\"produits-description.html\">DESCRIPTION</a>." + NEW_LINE +
            genererTable(produits)    + NEW_LINE +
            "          </p>"          + NEW_LINE +
            "      </section>"        + NEW_LINE +
            "    </main>"             + NEW_LINE +
            "  </body>"               + NEW_LINE + 
            "</html>"                 + NEW_LINE;
    }

    // Livrable 3
    /**
     * La fonction chargerProduits parcourt les fichiers 'repertoire/prefixeX.txt' et retourne
     * un tableau à deux dimensions de chaîne de caractères contenant l'ensemble des produits.
     * Le tableau comporte 5 colonnes, la première pour le NOM du produit, la deuxième pour 
     * le DATE du produit, la troisième pour l'ENTREPRISE du produit, la quatrième pour 
     * le PRIX du produit et la cinquième pour la DESCRIPTION du produit. Le tableau comporte 
     * donc autant de lignes qu'il y a de fichiers produits présents dans le répertoire 'data'.
     */
    String[][] chargerProduits(String repertoire, String prefixe) {
        final int NB_FICHIERS =  length(getAllFilesFromDirectory(repertoire));
        String[][] produits   = new String[NB_FICHIERS][5];
        for (int idx = 0; idx < length(produits, 1); idx = idx + 1) {
            final String CONTENU       = fileAsString(repertoire + "/" + prefixe + (idx+1) + ".txt");
            produits[idx][IDX_NOM]         = rechercherValeur(CONTENU, "nom : ");
            produits[idx][IDX_DATE]        = rechercherValeur(CONTENU, "date : ");
            produits[idx][IDX_ENTREPRISE]  = rechercherValeur(CONTENU, "entreprise : ");
            produits[idx][IDX_PRIX]        = rechercherValeur(CONTENU, "prix : ");
            produits[idx][IDX_DESCRIPTION] = rechercherValeur(CONTENU, "description : ");
        }
        return produits;
    }

    // Livrable 3
    /**
     * La fonction getChamps prend un paramètre un tableau de produits, un indice de ligne 
     * (correspondant à un produit) et un indice de colonne correspondant à l'un des 
     * champs NOM, DATE, ENTREPRISE, PRIX, DESCRIPTION et retourne la valeur correspondant 
     * au champs souhaité. En cas d'indices invalides, la fonction se termine sur une 
     * exception de type ArrayIndexOutOfBoundsException.
    */
    String getChamps(String[][] produits, int idxLigne, int idxChamps) {
        return produits[idxLigne][idxChamps];
    }

    // Livrable 3
    /**
     * La fonction toString retourne une représentation sous forme de chaîne de caractères
     * du tableau de produits à des fins de déboggage. Ainsi, la fonction ne retourne qu'un
     * sous-ensemble des données : NOM (DATE) - PRIX - DESCRIPTION. Exemple de chaîne produite :
     * Apple II (Avril 1977) - 1 298 dollars (environ 5 600 dollars ajustés à l'inflation en 
     * 2023) - L'Apple II était l'un des premiers ordinateurs personnels largement adoptés par 
     * le grand public. Il était équipé d'un microprocesseur MOS Technology 6502, de 4 Ko de RAM 
     * (extensible à 48 Ko) et d'une couleur graphique. L'Apple II a été très populaire dans 
     * les écoles et les foyers.
     */
    String toString(String[][] produits) {
        String table = "";
        for (int idxLigne = 0; idxLigne < length(produits, 1); idxLigne = idxLigne + 1) {
            table = table + getChamps(produits, idxLigne, IDX_NOM)  + " ("   + 
                            getChamps(produits, idxLigne, IDX_DATE) + ") - " +
                            getChamps(produits, idxLigne, IDX_PRIX) + " - "  + 
                            getChamps(produits, idxLigne, IDX_DESCRIPTION)   + '\n';
        }
        return table;
    }

    void algorithm() {
        // Chargement des fichiers data/produitX.html dans la structure de données
        String[][] produits = chargerProduits("data", "produit");
        //println(toString(produits));

        println("Création de la page index.html");
        final String TITLE = "Ordinateurs mythiques";
        final String PAGE_ACCUEIL = genererAccueil(TITLE, produits);
        stringAsFile("output/index.html", PAGE_ACCUEIL);

        // Création de l'ensemble des pages HTML des produits
        print("Création de la page produitX.html : ");
        for (int numeroProduit = 0; numeroProduit < length(produits, 1); numeroProduit = numeroProduit + 1) {
            final String PAGE_PRODUIT = genererPageProduit(TITLE, produits, numeroProduit);
            final String NOM_FICHIER  = (numeroProduit + 1)+".html";
            stringAsFile("output/produit" + NOM_FICHIER, PAGE_PRODUIT);
            print((numeroProduit + 1)+" ");
            //println(PAGE_PRODUIT);
        }
        println();

        String[] champs = new String[]{"nom", "date", "entreprise", "prix", "description"};
        for (int idxCol = 0; idxCol < length(produits, 2); idxCol = idxCol +1) {
            println("Création des pages produits-"+champs[idxCol]+".html");
            trierSurColonne(produits, idxCol);
            final String PAGE_PRODUITS = genererListeProduit(TITLE, produits);
            stringAsFile("output/produits-"+champs[idxCol]+".html", PAGE_PRODUITS);
            if (idxCol== 4) {
                println(toString(produits));
            }
        }
    }
}