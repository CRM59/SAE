package Appli;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class donneesCSV {
    public static ArrayList<String> recupererDonnees(String folder) throws FileNotFoundException, NoSuchElementException, IOException {
        ArrayList<String> data = new ArrayList<String>();

        try(BufferedReader br = new BufferedReader(new FileReader("CSV/" + folder))){
            
            String line = br.readLine();
            
            while(line != null){
                data.add(line);
                line = br.readLine();
            }
        }
        catch(FileNotFoundException fnfe){
            throw new FileNotFoundException();
        }
        catch(NoSuchElementException nsee){
            System.err.println(nsee);
        }
        catch(IOException ioe){
            System.out.println(ioe);
        }

        return data;

    }
}
