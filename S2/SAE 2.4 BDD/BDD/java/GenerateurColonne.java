public class GenerateurColonne{
    public static void main(String[] args){
        String debut = "CREATE TABLE import(" + "\n";
        String fin = ");";

        String content = "";

        for(int i = 0; i < 118; i++){
            if(i == 117){
                content += "n" + (i + 1) + " TEXT";
            }
            else{
                content += "n" + (i + 1) + " TEXT,";
            }
            if(i%5 == 0){
                content += "\n";
            }

        }

        System.out.println(debut + content + fin);
    }
}
