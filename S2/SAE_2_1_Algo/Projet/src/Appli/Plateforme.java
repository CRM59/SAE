package Appli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.ulille.but.sae_s2_2024.*;


public class Plateforme {

    private TypeCout tri;
    // private ModaliteTransport transport;
    private int nbChemins;

    
    public Plateforme(TypeCout tri, int nbChemins) {
        this.tri = tri;
        this.nbChemins = nbChemins;
    }




    public ArrayList<String> afficherChemins(List<Chemin> chemins1, List<Chemin> chemins2, List<Chemin> chemins3, int[] bornesChemins1, int[] bornesChemins2, int[] bornesChemins3) throws IndexOutOfBoundsException, NullValueException {


        if(chemins1 == null || chemins2 == null || chemins3 == null || bornesChemins1 == null || bornesChemins2 == null || bornesChemins3 == null){
            throw new NullValueException();
        }


        if(this.getNbChemins() > chemins1.size()){
            System.out.println("Le nombre de chemins que vous voulez est plus grand que le nombre de chemins total disponible. Par conséquent, nous vons afficherons tout les plus courts chemins disponibles. \n");
            this.nbChemins = chemins1.size();
        }

        //Initialisation des variables marquant l'origine et le puit du graphe

        Lieu origine = chemins1.get(0).aretes().get(0).getDepart();
        Lieu puit = chemins1.get(0).aretes().get(chemins1.get(0).aretes().size() - 1).getArrivee();

        //Initialisation type d'affichage

        String[] typesAffichage = typeAffichage(tri);

        //Récupération de tout les sommets d'un chemin + affichage du chemin et ce pour k nombre de chemins les plus courts

        int idxChemin = 0;

        int idx = 0;

        ArrayList<String> cheminsFinaux = new ArrayList<String>();

        
        while(idx < chemins1.size() && idxChemin < this.getNbChemins()){

            boolean poidsChemins2Donnee = false;
            boolean poidsChemins3Donnee = false;

            boolean cheminValide = true;

            //String sommets = listeSommets(chemins1, idx, origine);
            String sommets = listeSommetsChangModa(chemins1, idx, origine);
            
            //Affichage final du chemin en fonction de son type


            String listeModalites = chercherModalites(chemins1, idx);

            
            String res = (idxChemin + 1) + ") " + listeModalites + " de " + origine + " à " + puit + " en passant par " + sommets + ". " + typesAffichage[0] + chemins1.get(idx).poids();

            //Mise en commun du graphe principal avec le deuxième graphe (exemple: récupération du temps de trajet pour les graphes triés par prix pour les mettres en communs)
            

            for(int j = 0; j < chemins1.size(); j++){
                if(chemins1.get(idx).aretes().toString().equals(chemins2.get(j).aretes().toString())){
                    if(chemins2.get(j).poids() < bornesChemins2[0] || chemins2.get(j).poids() > bornesChemins2[1]){
                        cheminValide = false;
                    }
                    else if(!poidsChemins2Donnee){
                        res +=  ", " + typesAffichage[1] + chemins2.get(j).poids();
                        poidsChemins2Donnee = true;
                    }
                }
            }

            //Mise en commun du graphe principal avec le troisème graphe (exemple: récupération du taux de CO2 du trajet pour les graphes triés par prix pour les mettres en communs)

            for(int j = 0; j < chemins1.size(); j++){
                if(chemins1.get(idx).aretes().toString().equals(chemins3.get(j).aretes().toString())){
                    if(chemins3.get(j).poids() < bornesChemins3[0] || chemins3.get(j).poids() > bornesChemins3[1]){
                        cheminValide = false;
                    }
                    else if(!poidsChemins3Donnee){
                        res +=  ", " + typesAffichage[2] + chemins3.get(j).poids();
                        poidsChemins3Donnee = true;
                    }
                }
            }

            if(chemins1.get(idx).poids() < bornesChemins1[0] || chemins1.get(idx).poids() > bornesChemins1[1]){
                cheminValide = false;
            }
            
            if(cheminValide){
                cheminsFinaux.add(res);
                idxChemin++;
            }

            idx++;
        }

        return cheminsFinaux;
    }



    public String chercherModalites(List<Chemin> chemins1, int idx) throws IndexOutOfBoundsException, NullValueException, NullPointerException {
        String modalites = "";

        ArrayList<String> differentesModalites = new ArrayList<String>();

        if(chemins1 == null){
            throw new NullValueException();
        }
        

        for(int i = 0; i < chemins1.get(idx).aretes().size(); i++){
            if(!differentesModalites.contains(chemins1.get(idx).aretes().get(i).getModalite().toString())){
                modalites += chemins1.get(idx).aretes().get(i).getModalite() + " ";
                differentesModalites.add(chemins1.get(idx).aretes().get(i).getModalite().toString());
            }
        }

        return modalites;
    }




    public String[] typeAffichage(TypeCout tri) throws NullValueException {

        if(tri == null){
            throw new NullValueException();
        }

        String[] tab;

        if(tri.equals(TypeCout.PRIX)){
            tab = new String[]{"Prix (en euros): ", "Temps (en minutes): ", "CO2 (en kg): "};
        }
        else if(tri.equals(TypeCout.TEMPS)){
            tab = new String[]{"Temps (en minutes): ", "Prix (en euros): ", "CO2 (en kg): "};
        }
        else{
            tab = new String[]{"CO2 (en kg): ", "Temps (en minutes): ", "Prix (en euros): "};
        }

        return tab;
    }




    public String listeSommets(List<Chemin> chemins1, int idx, Lieu origine) throws IndexOutOfBoundsException, NullValueException, NullPointerException {
        String sommets = "";

        if(chemins1 == null || origine == null){
            throw new NullValueException();
        }
            
        for(int j = 0; j < chemins1.get(idx).aretes().size(); j++){
            if(!sommets.contains(chemins1.get(idx).aretes().get(j).getDepart().toString()) && chemins1.get(idx).aretes().get(j).getDepart() != origine){
                sommets = sommets + chemins1.get(idx).aretes().get(j).getDepart().toString() + " ";
            }
        }  

        return sommets.substring(0, sommets.length());
    }




    public String listeSommetsChangModa(List<Chemin> chemins1, int idx, Lieu origine) throws IndexOutOfBoundsException, NullValueException, NullPointerException {

        if(chemins1 == null || origine == null){
            throw new NullValueException();
        }

        String sommets = "";
        ModaliteTransport modalite = chemins1.get(0).aretes().get(0).getModalite();

            
        for(int j = 0; j < chemins1.get(idx).aretes().size(); j++){
            if(!sommets.contains(chemins1.get(idx).aretes().get(j).getDepart().toString()) && chemins1.get(idx).aretes().get(j).getDepart() != origine && chemins1.get(idx).aretes().get(j).getModalite() != modalite){
                sommets = sommets + chemins1.get(idx).aretes().get(j).getDepart().toString() + " ";
                modalite = chemins1.get(idx).aretes().get(j).getModalite();
            }
        }  

        return sommets.substring(0, sommets.length());
    }




    public static ModaliteTransport choixModalite() throws KeyboardException, IOException {
        boolean modaliteCorrect = false;

        String choix = "";

        while (!modaliteCorrect) {
            choix = Keyboard.readString("Entrée le type de transport que vous souhaitez emprunter (train ou avion) : ").toUpperCase();

            if(choix.equals("TRAIN") || choix.equals("AVION")){
                modaliteCorrect = true;
            }
            else{
                throw new KeyboardException("Valeur incorrecte, veuillez choisir une valeur correcte !");
            }
        }

        ModaliteTransport transport = ModaliteTransport.valueOf(choix);

        return transport;
    }




    public static TypeCout choixTriType() throws KeyboardException, IOException {
        boolean triCorrect = false;

        String choix = "";

        while (!triCorrect) {
            choix = Keyboard.readString("Entrée le critère de tri souhaité (prix/temps/co2) : ").toUpperCase();

            if(choix.equals("PRIX") || choix.equals("TEMPS") || choix.equals("CO2")){
                triCorrect = true;
            }
            else{
                throw new KeyboardException("Valeur incorrecte, veuillez choisir une valeur correcte !");
            }
        }

        TypeCout tri = TypeCout.valueOf(choix);

        return tri;
    }




    public static int choixNombrePlusCourtsChemins() throws KeyboardException, IOException {
        boolean nombreCorrect = false;

        int tri = -1;

        while (!nombreCorrect) {
            tri = Integer.parseInt(Keyboard.readString("Combien de plus court trajet souhaitez vous? "));

            if(tri >= 0){
                nombreCorrect = true;
            }
            else{
                throw new KeyboardException("Valeur incorrecte, veuillez choisir une valeur correcte !");
            }
        }

        return tri;
    }





    public static int[] choixBornesPrix() throws KeyboardException, NumberFormatException, IOException {
        boolean bornesCorrect = false;

        String min = "";
        String max = "";

        while (!bornesCorrect) {
            min = Keyboard.readString("Entrée le prix de trajet minimum souhaité : ");
            max = Keyboard.readString("Entrée le prix de trajet maximum souhaité : ");

            if(Integer.parseInt(min) >= 0 && Integer.parseInt(max) >= Integer.parseInt(min)){
                bornesCorrect = true;
            }
            else{
                throw new KeyboardException("Valeur incorrecte, veuillez choisir une valeur correcte !");
            }
        }

        return new int[]{Integer.parseInt(min), Integer.parseInt(max)};
    }





    public static int[] choixBornesTemps() throws KeyboardException, NumberFormatException, IOException {
        boolean bornesCorrect = false;

        String min = "";
        String max = "";

        while (!bornesCorrect) {
            min = Keyboard.readString("Entrée le temps de trajet minimum souhaité : ");
            max = Keyboard.readString("Entrée le temps de trajet maximum souhaité : ");

            if(Integer.parseInt(min) >= 0 && Integer.parseInt(max) >= Integer.parseInt(min)){
                bornesCorrect = true;
            }
            else{
                throw new KeyboardException("Valeur incorrecte, veuillez choisir une valeur correcte !");
            }
        }

        return new int[]{Integer.parseInt(min), Integer.parseInt(max)};
    }




    public static int[] choixBornesCO2() throws KeyboardException, NumberFormatException, IOException {
        boolean bornesCorrect = false;

        String min = "";
        String max = "";

        while (!bornesCorrect) {
            min = Keyboard.readString("Entrée le taux de CO2 du trajet minimum souhaité : ");
            max = Keyboard.readString("Entrée le taux de CO2 du trajet maximum souhaité : ");

            if(Integer.parseInt(min) >= 0 && Integer.parseInt(max) >= Integer.parseInt(min)){
                bornesCorrect = true;
            }
            else{
                throw new KeyboardException("Valeur incorrecte, veuillez choisir une valeur correcte !");
            }
        }

        return new int[]{Integer.parseInt(min), Integer.parseInt(max)};
    }




    public static Sommet choixOrigine(ArrayList<String> data) throws IncorrectSommetException, IOException {
        boolean valueCorrect = false;

        String origineString = "";

        while (!valueCorrect) {
            origineString = Keyboard.readString("Quel est l'origine du graphe? ");
            
            for(int i = 0; i < data.size(); i++){
                String[] caracteristiquesData = data.get(i).split(";");

                if(origineString.equals(caracteristiquesData[0]) || origineString.equals(caracteristiquesData[1])){
                    valueCorrect = true;
                }
            }

            if(!valueCorrect){
                throw new IncorrectSommetException("Sommet incorrect, veuillez choisir un sommet correct !");
            }
        }

        Sommet origine = new Sommet(origineString);

        return origine;
    }



    public static Sommet choixPuit(ArrayList<String> data, Sommet origine) throws IncorrectSommetException, IOException {
        boolean valueCorrect = false;

        String puitString = "";

        while (!valueCorrect) {
            puitString = Keyboard.readString("Quel est le puit du graphe? ");
            
            for(int i = 0; i < data.size(); i++){
                String[] caracteristiquesData = data.get(i).split(";");

                if((puitString.equals(caracteristiquesData[0]) || puitString.equals(caracteristiquesData[1])) && !puitString.equals(origine.toString())){
                    valueCorrect = true;
                }
            }

            if(!valueCorrect){
                throw new IncorrectSommetException("Sommet incorrect, veuillez choisir un sommet correct !");
            }
        }

        Sommet puit = new Sommet(puitString);

        return puit;
    }




    public static void dataValide(ArrayList<String> data) throws IncorrectDataException, IndexOutOfBoundsException, NullPointerException {
        boolean valide = true;

        for(int i = 0; i < data.size() && valide; i++){

            String[] caracteristiquesData = data.get(i).split(";");
            
            if(caracteristiquesData.length == 6){

                String prix = caracteristiquesData[3];
                String co2 = caracteristiquesData[4];
                String temps = caracteristiquesData[5];

                for(int j = 0; j < prix.length(); j++){
                    if(((prix.charAt(j) >= '0' && prix.charAt(j) <= '9') || prix.charAt(j) == '.') && valide){
                        valide = true;
                    }
                    else{
                        valide = false;
                    }
                }
                
                for(int j = 0; j < co2.length(); j++){
                    if(((co2.charAt(j) >= '0' && co2.charAt(j) <= '9') || co2.charAt(j) == '.') && valide){
                        valide = true;
                    }
                    else{
                        valide = false;
                    }
                    
                }

                for(int j = 0; j < temps.length(); j++){
                    if(((temps.charAt(j) >= '0' && temps.charAt(j) <= '9') || temps.charAt(j) == '.') && valide){
                        valide = true;
                    }
                    else{
                        valide = false;
                    }
                }
            }
            else{
                valide = false;
            }
        }

        if(valide == false){
            throw new IncorrectDataException("Les données ne sont pas valides !");
        }
    }



    public TypeCout getTri() {
        return this.tri;
    }




    public int getNbChemins() {
        return this.nbChemins;
    }



    
}
