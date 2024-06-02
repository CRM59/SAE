public class Dico{
    public static void main(String[] args){

        String content = "";

        for(int i = 0; i < 118; i++){
            content += "n" + (i + 1) + ";";
        }

        System.out.println(content);
    }
}
