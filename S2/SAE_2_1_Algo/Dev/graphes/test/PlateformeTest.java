import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.ulille.but.sae_s2_2024.Chemin;
import fr.ulille.but.sae_s2_2024.ModaliteTransport;
import java.util.List;
import Appli.Plateforme;
import Appli.PlusCourtsChemins;
import Appli.Sommet;
import Appli.TypeCout;

public class PlateformeTest {


    String[] data;
    String[] data2;
    String[] data3;
    String[] data4;
    String[] data5;
    Plateforme plateforme;
    Plateforme plateforme2;
    Plateforme plateforme3;
    PlusCourtsChemins courtsChemins;

    @BeforeEach
    public void testInitialization(){
        plateforme = new Plateforme(TypeCout.PRIX, ModaliteTransport.TRAIN, 3);
        plateforme2 = new Plateforme(TypeCout.TEMPS, ModaliteTransport.AVION,1);
        plateforme3 = new Plateforme(TypeCout.CO2, ModaliteTransport.BUS, 2);

        courtsChemins = new PlusCourtsChemins();

        //data valide

        data = new String[]{
            "A;B;Train;60;1.7;80",
            "B;C;Avion;50;2.0;105",
            "B;D;Train;30;2.5;98"
        };

        //data valide

        data2 = new String[]{
            "Douai;Amesterdam;Train;59;1.8;10",
            "Amesterdam;Paris;Train;61;2.5;97",
            "Amesterdam;Chypre;Train;17;3.7;55"
        };

        //data non valide

        data3 = new String[]{
            "A;B;Train;60",
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

        //data non valide

        data4 = new String[]{
            "A;B;Train;60;2.4;58",
            "B;A;Train;60;1.7;80",
            "A;C;Train;42;1.4;Test",
            "C;A;Train;42;1.4;50",
            "B;C;Train;14;1.4;60",
            "C;B;Train;14;1.4;60",
            "B;D;Train;30;1.4;60",
            "D;B;Train;30;1.4;60",
            "C;D;Train;90;1.4;60",
            "D;C;Train;90;1.4;60"
        };

        //data valide

        data5 = new String[]{
            "A;B;Train;60;2.0;45",
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
    void testTypeAffichage(){
        String[] type1 = new String[]{"Prix (en euros): ", "Temps (en minutes): ", "CO2 (en kg): "};
        String[] type2 = new String[]{"Temps (en minutes): ", "Prix (en euros): ", "CO2 (en kg): "};
        String[] type3 = new String[]{"CO2 (en kg): ", "Temps (en minutes): ", "Prix (en euros): "};

        assertEquals(type1[0], plateforme.typeAffichage(TypeCout.PRIX)[0]);
        assertEquals(type1[1], plateforme.typeAffichage(TypeCout.PRIX)[1]);
        assertEquals(type1[2], plateforme.typeAffichage(TypeCout.PRIX)[2]);

        assertEquals(type2[0], plateforme.typeAffichage(TypeCout.TEMPS)[0]);
        assertEquals(type2[1], plateforme.typeAffichage(TypeCout.TEMPS)[1]);
        assertEquals(type2[2], plateforme.typeAffichage(TypeCout.TEMPS)[2]);

        assertEquals(type3[0], plateforme.typeAffichage(TypeCout.CO2)[0]);
        assertEquals(type3[1], plateforme.typeAffichage(TypeCout.CO2)[1]);
        assertEquals(type3[2], plateforme.typeAffichage(TypeCout.CO2)[2]);
    }



    @Test
    void testListeSommets(){
        List<Chemin> chemins = courtsChemins.creationCheminsPlusCourts(data, TypeCout.PRIX, ModaliteTransport.TRAIN);
        List<Chemin> chemins2 = courtsChemins.creationCheminsPlusCourts(data2, TypeCout.PRIX, ModaliteTransport.TRAIN);
        List<Chemin> chemins3 = courtsChemins.creationCheminsPlusCourts(data5, TypeCout.PRIX, ModaliteTransport.TRAIN);
        
        String listeSommetsData1 = "A B ";
        String listeSommetsData2 = "Douai Amesterdam ";
        String listeSommetsData5 = "A C B ";

        assertEquals(listeSommetsData1, plateforme.listeSommets(chemins, 0, new Sommet("A")));
        assertEquals(listeSommetsData2, plateforme.listeSommets(chemins2, 0, new Sommet("Douai")));
        assertEquals(listeSommetsData5, plateforme.listeSommets(chemins3, 0, new Sommet("A")));
    }


    @Test
    void testGetTri(){
        assertEquals(TypeCout.PRIX, plateforme.getTri());
        assertEquals(TypeCout.TEMPS, plateforme2.getTri());
        assertEquals(TypeCout.CO2, plateforme3.getTri());
    }


    @Test
    void testGetTransport(){
        assertEquals(ModaliteTransport.TRAIN, plateforme.getTransport());
        assertEquals(ModaliteTransport.AVION, plateforme2.getTransport());
        assertEquals(ModaliteTransport.BUS, plateforme3.getTransport());
    }


    @Test
    void testGetNbChemins(){
        assertEquals(3, plateforme.getNbChemins());
        assertEquals(1, plateforme2.getNbChemins());
        assertEquals(2, plateforme3.getNbChemins());
    }

}