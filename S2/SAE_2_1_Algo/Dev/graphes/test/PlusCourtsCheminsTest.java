import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ulille.but.sae_s2_2024.AlgorithmeKPCC;
import fr.ulille.but.sae_s2_2024.Chemin;
import fr.ulille.but.sae_s2_2024.ModaliteTransport;
import fr.ulille.but.sae_s2_2024.MultiGrapheOrienteValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Appli.Arrete;
import Appli.PlusCourtsChemins;
import Appli.Sommet;
import Appli.TypeCout;

public class PlusCourtsCheminsTest {

    
    String[] data;
    String[] data2;
    String[] data3;
    PlusCourtsChemins courtsChemins;
    ArrayList<Sommet> listeSommets;
    ArrayList<String> listeTrancons;
    ArrayList<String> listeTrancons2;
    Map<String, Map<String, Double>> listeArretes;
    Map<String, Map<String, Double>> listeArretes2;
    Map<String, Double> sousMap1;
    Map<String, Double> sousMap2;
    MultiGrapheOrienteValue graphe;
    MultiGrapheOrienteValue graphe2;




    @BeforeEach
    public void testInitialization(){
        courtsChemins = new PlusCourtsChemins();
        listeSommets = new ArrayList<Sommet>();
        listeTrancons = new ArrayList<String>();
        listeTrancons2 = new ArrayList<String>();
        listeArretes = new HashMap<String, Map<String, Double>>();
        listeArretes2 = new HashMap<String, Map<String, Double>>();
        sousMap1 = new HashMap<String, Double>();
        sousMap2 = new HashMap<String, Double>();
        graphe = new MultiGrapheOrienteValue();
        graphe2 = new MultiGrapheOrienteValue();

        data = new String[]{
            "A;B;Train;60;1.7;80",
            "B;C;Avion;50;2.0;105"
        };

        data2 = new String[]{
            "Douai;Amsterdam;Train;10;0.2;3",
            "Douai;Chypre;Train;90.0;10.55;05"
        };


        data3 = new String[]{
            "A;B;Train;60;1.7;80",
            "B;A;Train;60;1.7;80",
            "A;C;Train;42;1.4;50",
            "C;A;Train;42;1.4;50",
            "B;C;Train;14;1.4;60",
            "C;B;Train;14;1.4;60",
            "B;D;Train;30;1.4;60",
            "D;B;Train;30;1.4;60",
            "C;D;Train;90;1.4;60",
            "D;C;Train;90;1.4;60"
        };

    }

    @Test
    public void testGetPoidsDonnees(){
        assertEquals(60.0, courtsChemins.getPoidsDonnees(0, data, TypeCout.PRIX));
        assertEquals(1.7, courtsChemins.getPoidsDonnees(0, data, TypeCout.CO2));
        assertEquals(80.0, courtsChemins.getPoidsDonnees(0, data, TypeCout.TEMPS));
        assertEquals(50.0, courtsChemins.getPoidsDonnees(1, data, TypeCout.PRIX));
        assertEquals(2.0, courtsChemins.getPoidsDonnees(1, data, TypeCout.CO2));
        assertEquals(105.0, courtsChemins.getPoidsDonnees(1, data, TypeCout.TEMPS));

        assertEquals(10.0, courtsChemins.getPoidsDonnees(0, data2, TypeCout.PRIX));
        assertEquals(0.2, courtsChemins.getPoidsDonnees(0, data2, TypeCout.CO2));
        assertEquals(3.0, courtsChemins.getPoidsDonnees(0, data2, TypeCout.TEMPS));
        assertEquals(90.0, courtsChemins.getPoidsDonnees(1, data2, TypeCout.PRIX));
        assertEquals(10.55, courtsChemins.getPoidsDonnees(1, data2, TypeCout.CO2));
        assertEquals(5.0, courtsChemins.getPoidsDonnees(1, data2, TypeCout.TEMPS));

    }

    @Test
    void testSommetsToString(){
        ArrayList<String> sommets = courtsChemins.sommetsToString(data);
        ArrayList<String> test = new ArrayList<String>();
        test.add("A");
        test.add("B");
        test.add("C");
        test.equals(sommets);
        sommets = courtsChemins.sommetsToString(data2);
        test.clear();
        test.add("Douai");
        test.add("Amsterdam");
        test.add("Chypre");
        test.equals(sommets);

    }



    @Test 
    void testGetListeTranconsEtArretes(){
        listeTrancons.add("A;B");
        listeTrancons.add("B;C");
        assertEquals(listeTrancons, courtsChemins.getListeTrancons(data));

        listeTrancons2.add("Douai;Amsterdam");
        listeTrancons2.add("Douai;Chypre");
        assertEquals(listeTrancons2, courtsChemins.getListeTrancons(data2));
        
        listeArretes.put("A;B", new HashMap<String, Double>());
        listeArretes.put("B;C", new HashMap<String, Double>());

        assertEquals(listeArretes, courtsChemins.getListeArretes(listeTrancons, data));        


        listeArretes2.put("Douai;Amsterdam", new HashMap<String, Double>());
        listeArretes2.put("Douai;Chypre", new HashMap<String, Double>());

        assertEquals(listeArretes2, courtsChemins.getListeArretes(listeTrancons2, data2));        
    

    }




    @Test
    void testListeSommets(){
        //pour data

        listeSommets.add(new Sommet("A"));
        listeSommets.add(new Sommet("B"));
        listeSommets.add(new Sommet("C"));

        assertEquals(listeSommets.toString(), courtsChemins.getListeSommets(new MultiGrapheOrienteValue(), data).toString());

        //pour data2

        listeSommets = new ArrayList<Sommet>();
        listeSommets.add(new Sommet("Douai"));
        listeSommets.add(new Sommet("Amsterdam"));
        listeSommets.add(new Sommet("Chypre"));

        assertEquals(listeSommets.toString(), courtsChemins.getListeSommets(new MultiGrapheOrienteValue(), data2).toString());
    }




    @Test
    void testRemplirListeArretes(){
        ////////////////////////////////////
        ///Situation de data pour le PRIX///
        ////////////////////////////////////

        sousMap1.put("TRAIN", 60.0);

        sousMap2 = new HashMap<String, Double>();
        

        listeArretes.put("A;B", sousMap1);
        listeArretes.put("B;C", sousMap2);

        listeArretes2.put("A;B", new HashMap<String, Double>());
        listeArretes2.put("B;C", new HashMap<String, Double>());
        courtsChemins.remplirListeArretes(listeArretes2, data, ModaliteTransport.TRAIN, TypeCout.PRIX);

        assertEquals(listeArretes, listeArretes2);

        ////////////////////////////////////
        //Situation de data2 pour le TEMPS//
        ////////////////////////////////////

        listeArretes = new HashMap<String, Map<String, Double>>();
        sousMap1 = new HashMap<String, Double>();
        sousMap1.put("TRAIN", 3.0);

        sousMap2 = new HashMap<String, Double>();
        sousMap2.put("TRAIN", 5.0);

        listeArretes.put("Douai;Amsterdam", sousMap1);
        listeArretes.put("Douai;Chypre", sousMap2);

        listeArretes2 = new HashMap<String, Map<String, Double>>();
        listeArretes2.put("Douai;Amsterdam", new HashMap<String, Double>());
        listeArretes2.put("Douai;Chypre", new HashMap<String, Double>());
        courtsChemins.remplirListeArretes(listeArretes2, data2, ModaliteTransport.TRAIN, TypeCout.TEMPS);

        assertEquals(listeArretes, listeArretes2);
    }





    @Test
    void testCreationCheminsPlusCourts(){
        Sommet A = new Sommet("A");
        Sommet B = new Sommet("B");
        Sommet C = new Sommet("C");
        Sommet D = new Sommet("D");

        graphe.ajouterSommet(A);
        graphe.ajouterSommet(B);
        graphe.ajouterSommet(C);
        graphe.ajouterSommet(D);

        Arrete AB = new Arrete(A, B, ModaliteTransport.TRAIN);
        Arrete BA = new Arrete(B, A, ModaliteTransport.TRAIN);
        Arrete BC = new Arrete(B, C, ModaliteTransport.TRAIN);
        Arrete CB = new Arrete(C, B, ModaliteTransport.TRAIN);
        Arrete AC = new Arrete(A, C, ModaliteTransport.TRAIN);
        Arrete CA = new Arrete(C, A, ModaliteTransport.TRAIN);

        Arrete BD = new Arrete(B, D, ModaliteTransport.TRAIN);
        Arrete DB = new Arrete(D, B, ModaliteTransport.TRAIN);
        Arrete CD = new Arrete(C, D, ModaliteTransport.TRAIN);
        Arrete DC = new Arrete(D, C, ModaliteTransport.TRAIN);

        graphe.ajouterArete(AB,60.0);
        graphe.ajouterArete(BA,60.0);
        graphe.ajouterArete(BC,14.0);
        graphe.ajouterArete(CB,14.0);
        graphe.ajouterArete(AC,42.0);
        graphe.ajouterArete(CA,42.0);

        graphe.ajouterArete(BD,30.0);
        graphe.ajouterArete(DB,30.0);
        graphe.ajouterArete(CD,90.0);
        graphe.ajouterArete(DC,90.0);

        List<Chemin> chemins = AlgorithmeKPCC.kpcc(graphe,A,D,100);

        assertEquals(chemins.toString(), courtsChemins.creationCheminsPlusCourts(data3, TypeCout.PRIX, ModaliteTransport.TRAIN).toString());
    }




    @Test
    void testRemplirGraphe(){
        graphe = new MultiGrapheOrienteValue();
        Sommet B = new Sommet("B");
        Sommet C = new Sommet("D");
        Sommet D = new Sommet("C");
        Sommet A = new Sommet("A");

       

        graphe.ajouterSommet(D);
        graphe.ajouterSommet(B);
        graphe.ajouterSommet(A);
        graphe.ajouterSommet(C);

        Arrete AB = new Arrete(A, B, ModaliteTransport.TRAIN);
        Arrete BA = new Arrete(B, A, ModaliteTransport.TRAIN);
        Arrete AC = new Arrete(A, C, ModaliteTransport.TRAIN);
        Arrete CA = new Arrete(C, A, ModaliteTransport.TRAIN);
        Arrete BC = new Arrete(B, C, ModaliteTransport.TRAIN);
        Arrete CB = new Arrete(C, B, ModaliteTransport.TRAIN);

        Arrete BD = new Arrete(B, D, ModaliteTransport.TRAIN);
        Arrete DB = new Arrete(D, B, ModaliteTransport.TRAIN);
        Arrete CD = new Arrete(C, D, ModaliteTransport.TRAIN);
        Arrete DC = new Arrete(D, C, ModaliteTransport.TRAIN);

        graphe.ajouterArete(AB,60.0);
        graphe.ajouterArete(BA,60.0);
        graphe.ajouterArete(BC,14.0);
        graphe.ajouterArete(CB,14.0);
        graphe.ajouterArete(AC,42.0);
        graphe.ajouterArete(CA,42.0);
        graphe.ajouterArete(BD,30.0);
        
        graphe.ajouterArete(DB,30.0);
        graphe.ajouterArete(CD,90.0);
        graphe.ajouterArete(DC,90.0);

        listeSommets = courtsChemins.getListeSommets(graphe2, data3);

        listeTrancons = courtsChemins.getListeTrancons(data3);

        listeArretes = courtsChemins.getListeArretes(listeTrancons, data3);
        
        courtsChemins.remplirListeArretes(listeArretes, data3, ModaliteTransport.TRAIN, TypeCout.PRIX);
        
        courtsChemins.remplirGraphe(listeSommets, listeArretes, graphe2);


        assertEquals(graphe.sommets().toString(), graphe2.sommets().toString());

        //IMPORTANT: difficile de faire plus de tests car le résultat renvoyé par graphe.toString() ainsi fourni dans la librairie de base ne fourni pas le bon affichage en raison d'un possible problème interne.
    }


}



