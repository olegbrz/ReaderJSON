package solution;

import java.io.IOException;

public class GsonDatabaseClient {

    public static void main(String[] args) {
        try{
            DatabaseJSonReader dbjp = new DatabaseJSonReader();
            System.out.println(dbjp.parse("./resources/datos.json"));

        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
