package Appli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ulille.but.sae_s2_2024.*;



public class PlusCourtsChemins{

    
    public List<Chemin> creationCheminsPlusCourts(ArrayList<String> data, ArrayList<String> changementsModalites, TypeCout tri, Sommet origine, Sommet puit) throws IndexOutOfBoundsException, NullValueException, NullPointerException {
        MultiGrapheOrienteValue graphe = new MultiGrapheOrienteValue();
        ArrayList<Sommet> listeSommets = this.getListeSommets(graphe, data, origine, puit);

        
        ArrayList<String> listeSommetsOrigine = getSommetsOrigine(listeSommets, origine, puit);
        ArrayList<String> listeSommetsPuit = getSommetsPuit(listeSommets, origine, puit);
        
        
        ArrayList<String> listeTracons = this.getListeTrancons(data);
        
        
        Map<String, Map<String, Double>> listeArretes = this.getListeArretes(listeTracons, data, listeSommetsOrigine, listeSommetsPuit, origine, puit);
        
        
        remplirListeArretes(listeArretes, data, tri);
        remplirGraphe(listeSommets, listeArretes, graphe, origine, puit);
        remplirGrapheChangementModalite(changementsModalites, listeSommets, graphe);

        return AlgorithmeKPCC.kpcc(graphe, (Lieu)(listeSommets.get(listeSommets.size() - 1)),(Lieu)(listeSommets.get(listeSommets.size() - 2)),100);

    }


    
    public double getPoidsDonnees(int idx, ArrayList<String> data, TypeCout tri) throws IndexOutOfBoundsException, NullValueException, NullPointerException {

        if(data == null || tri == null){
            throw new NullValueException();
        }

        String[] caracteristiquesData = data.get(idx).split(";");

        if (caracteristiquesData.length > 6) {
            throw new IndexOutOfBoundsException();
        }

        return Double.parseDouble(caracteristiquesData[tri.ordinal()+3]);
    }









    public ArrayList<String> sommetsToString(ArrayList<String> data,  Sommet origine, Sommet puit) throws IndexOutOfBoundsException, NullValueException, NullPointerException {
        
        if(data == null || puit == null || origine == null){
            throw new NullValueException();
        }

        ArrayList<String> sommets = new ArrayList<String>();

        for(int i = 0; i < data.size(); i++){

            String[] caracteristiquesData = data.get(i).split(";");

            boolean dejaPresente = false;
            
            for(int j = 0; j < sommets.size(); j++){

                if(sommets.get(j).equals(caracteristiquesData[0] + "_" + caracteristiquesData[2].toUpperCase())){
                    dejaPresente = true;
                }
            }

            if(!dejaPresente){
                sommets.add(caracteristiquesData[0] + "_" + caracteristiquesData[2].toUpperCase());
            }
        }


        for(int i = 0; i < data.size(); i++){

            String[] caracteristiquesData = data.get(i).split(";");

            boolean dejaPresente = false;
            
            for(int j = 0; j < sommets.size(); j++){
                if(sommets.get(j).equals(caracteristiquesData[1] + "_" + caracteristiquesData[2].toUpperCase())){
                    dejaPresente = true;
                }
            }

            if(!dejaPresente){
                sommets.add(caracteristiquesData[1] + "_" + caracteristiquesData[2].toUpperCase());
            }
        }

        sommets.add(puit.toString());
        sommets.add(origine.toString());        


        return sommets;
    }







    public ArrayList<Sommet> getListeSommets(MultiGrapheOrienteValue graphe, ArrayList<String> data, Sommet origine, Sommet puit) throws IndexOutOfBoundsException, NullValueException, NullPointerException {
        
        if(data == null || origine == null || puit == null){
            throw new NullValueException();
        }
        
        ArrayList<Sommet> listeSommets = new ArrayList<Sommet>();

        ArrayList<String> sommetsString = this.sommetsToString(data, origine, puit);

        for(int i = 0; i < sommetsString.size(); i++){
            Sommet newSommet = new Sommet(sommetsString.get(i));
            listeSommets.add(newSommet);
            graphe.ajouterSommet(newSommet);
        }
        return listeSommets;
    }







    public ArrayList<String> getListeTrancons(ArrayList<String> data) throws IndexOutOfBoundsException, NullValueException, NullPointerException {

        if(data == null){
            throw new NullValueException();
        }

        ArrayList<String> listeTracons = new ArrayList<String>();

        for(int i = 0; i < data.size(); i++){
            String[] caracteristiquesData = data.get(i).split(";");
            
            boolean estPresent = false;
            
            for(int j = 0; j < listeTracons.size(); j++){
                if(listeTracons.get(j).equals(caracteristiquesData[0] + "_" + caracteristiquesData[2].toUpperCase() + ";" + caracteristiquesData[1] + "_" + caracteristiquesData[2].toUpperCase())){
                    estPresent = true;
                }
            }

            if(!estPresent){
                listeTracons.add(caracteristiquesData[0] + "_" + caracteristiquesData[2].toUpperCase() + ";" + caracteristiquesData[1] + "_" + caracteristiquesData[2].toUpperCase());
            }
        }
        return listeTracons;
    }




    public Map<String, Map<String, Double>> getListeArretes(ArrayList<String> listeTracons, ArrayList<String> data, ArrayList<String> listeSommetsOrigine, ArrayList<String> listeSommetsPuit, Sommet origine, Sommet puit) throws IndexOutOfBoundsException, NullValueException, NullPointerException {
 
        if(listeTracons == null || listeSommetsOrigine == null || data == null || listeSommetsPuit == null || origine == null || puit == null){
            throw new NullValueException();
        }
 
        Map<String, Map<String, Double>> listeArretes = new HashMap<String, Map<String, Double>>();

        
        for(int i = 0; i < listeTracons.size(); i++){
            String[] caracteristiquesData = data.get(i).split(";");
            
            
            for(int j = 0; j < listeTracons.size(); j++){
                if(listeTracons.get(j).equals(caracteristiquesData[0] + "_" + caracteristiquesData[2].toUpperCase() + ";" + caracteristiquesData[1] + "_" + caracteristiquesData[2].toUpperCase())){
                    Map<String, Double> newMap = new HashMap<String, Double>();
                    listeArretes.put(listeTracons.get(j), newMap);
                }

            }
        }

        Map<String, Double> newMap = new HashMap<String, Double>();


        for(int i = 0; i < listeSommetsOrigine.size(); i++){
            listeArretes.put(origine.toString() + ";" + listeSommetsOrigine, newMap);
        }

        for(int i = 0; i < listeSommetsPuit.size(); i++){
            listeArretes.put(listeSommetsPuit + ";" + puit.toString(), newMap);
        }

        return listeArretes;
    }



    public ArrayList<String> getSommetsOrigine(ArrayList<Sommet> listeSommets, Sommet origine, Sommet puit) throws IndexOutOfBoundsException, NullPointerException {
        ArrayList<String> listeSommetsOrigine = new ArrayList<String>();

        

        for(int i = 0; i < listeSommets.size(); i++){
            if(listeSommets.get(i).toString().length() > origine.toString().length()){
                if(!listeSommets.get(i).toString().equals(origine.toString()) && !listeSommets.get(i).toString().equals(puit.toString()) && listeSommets.get(i).toString().substring(0, listeSommets.get(i).toString().indexOf("_")).equals(origine.toString())){
                    listeSommetsOrigine.add(listeSommets.get(i).toString());
                }
            }
        }

        return listeSommetsOrigine;
    }




    public ArrayList<String> getSommetsPuit(ArrayList<Sommet> listeSommets, Sommet origine, Sommet puit) throws IndexOutOfBoundsException, NullValueException, NullPointerException {
        ArrayList<String> listeSommetsPuit = new ArrayList<String>();

        if(origine == null){
            throw new NullValueException();
        }

        if(puit == null){
            throw new NullValueException();
        }

        for(int i = 0; i < listeSommets.size(); i++){
            if(listeSommets.get(i).toString().length() > origine.toString().length()){
                if(!listeSommets.get(i).toString().equals(origine.toString()) && !listeSommets.get(i).toString().equals(puit.toString()) && listeSommets.get(i).toString().substring(0, listeSommets.get(i).toString().indexOf("_")).equals(puit.toString())){
                    listeSommetsPuit.add(listeSommets.get(i).toString());
                }
            }
        }

        return listeSommetsPuit;
    }




    public void remplirListeArretes(Map<String, Map<String, Double>> listeArretes, ArrayList<String> data, TypeCout tri)  throws IndexOutOfBoundsException, NullValueException, NullPointerException {
        for(int i = 0; i < data.size(); i++){
            String[] caracteristiquesData = data.get(i).split(";");

            listeArretes.get(caracteristiquesData[0] + "_" + caracteristiquesData[2].toUpperCase() + ";" + caracteristiquesData[1] + "_" + caracteristiquesData[2].toUpperCase()).put((String)caracteristiquesData[2].toUpperCase(), (Double)getPoidsDonnees(i, data, tri));
        }
    }




    public void remplirGraphe(ArrayList<Sommet> listeSommets, Map<String, Map<String, Double>> listeArretes, MultiGrapheOrienteValue graphe, Sommet origine, Sommet puit) throws IndexOutOfBoundsException, NullValueException, NullPointerException {

        if(origine == null){
            throw new NullValueException();
        }

        if(puit == null){
            throw new NullValueException();
        }

        if(listeArretes == null){
            throw new NullValueException();
        }

        if(listeSommets == null){
            throw new NullValueException();
        }

        for(Map.Entry<String, Map<String, Double>> arrete : listeArretes.entrySet()){
            for(Map.Entry<String, Double> cout : arrete.getValue().entrySet()){
                Sommet sommet1 = new Sommet("sommet1");
                Sommet sommet2 = new Sommet("sommet2");

                for(int j = 0; j < listeSommets.size(); j++){
                    if(!listeSommets.get(j).toString().equals(origine.toString()) && !listeSommets.get(j).toString().equals(puit.toString()) && arrete.getKey().substring(0, arrete.getKey().indexOf(';')).equals(listeSommets.get(j).toString())){
                        sommet1 = listeSommets.get(j);
                    }

                    if(!listeSommets.get(j).toString().equals(origine.toString()) && !listeSommets.get(j).toString().equals(puit.toString()) && arrete.getKey().substring(arrete.getKey().indexOf(';') + 1, arrete.getKey().length()).equals(listeSommets.get(j).toString())){
                        sommet2 = listeSommets.get(j);
                    }
                }

                graphe.ajouterArete(new Arrete(sommet1, sommet2, ModaliteTransport.valueOf(cout.getKey().toUpperCase())), cout.getValue());
            }
        }
        
        
        
        for(int i = 0; i < listeSommets.size(); i++){
            if(!listeSommets.get(i).toString().equals(origine.toString()) && !listeSommets.get(i).toString().equals(puit.toString()) && listeSommets.get(i).toString().length() > 1 && listeSommets.get(i).toString().substring(0,listeSommets.get(i).toString().indexOf("_")).equals(origine.toString())){
                graphe.ajouterArete(new Arrete(listeSommets.get(listeSommets.size() - 1), listeSommets.get(i), ModaliteTransport.TRAIN), 0.0);
            }
        }
        
        
        for(int i = 0; i < listeSommets.size(); i++){
            if(!listeSommets.get(i).toString().equals(origine.toString()) && !listeSommets.get(i).toString().equals(puit.toString()) && listeSommets.get(i).toString().length() > 1 && listeSommets.get(i).toString().substring(0,listeSommets.get(i).toString().indexOf("_")).equals(puit.toString())){
                graphe.ajouterArete(new Arrete(listeSommets.get(i), listeSommets.get(listeSommets.size() - 2), ModaliteTransport.TRAIN), 0.0);
            }
        }
    }



    public void remplirGrapheChangementModalite(ArrayList<String> changementsModalites, ArrayList<Sommet> listeSommets, MultiGrapheOrienteValue graphe) throws IndexOutOfBoundsException, NullValueException, NullPointerException {

        
        if(changementsModalites == null){
            throw new NullValueException();
        }

        if(listeSommets == null){
            throw new NullValueException();
        }

        for(int i = 0; i < changementsModalites.size(); i++){
            String[] caracteristiquesData = changementsModalites.get(i).split(";");

            Sommet sommet1 = new Sommet("sommet1");
            Sommet sommet2 = new Sommet("sommet2");

            for(int j = 0; j < listeSommets.size(); j++){
                if(listeSommets.get(j).toString().equals(caracteristiquesData[0] + "_" + caracteristiquesData[1].toUpperCase())){
                    sommet1 = listeSommets.get(j);
                }

                if(listeSommets.get(j).toString().equals(caracteristiquesData[0] + "_" + caracteristiquesData[2].toUpperCase())){
                    sommet2 = listeSommets.get(j);
                }
            }
            
            graphe.ajouterArete(new Arrete(sommet1, sommet2, ModaliteTransport.AVION), Double.parseDouble(caracteristiquesData[3]));
        }


    }
}