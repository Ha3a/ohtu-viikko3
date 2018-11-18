package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String kurssiurl = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String ohturl = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String railsurl = "https://studies.cs.helsinki.fi/courses/rails2018/stats";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String kUbodyText = Request.Get(kurssiurl).execute().returnContent().asString();
        String ohtuBody = Request.Get(ohturl).execute().returnContent().asString();
        String railBody = Request.Get(railsurl).execute().returnContent().asString();

        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(ohtuBody).getAsJsonObject();
        int ohtuStud = parsittuData.get("1").getAsJsonObject().get("students").getAsInt();
        int ohtuteht = parsittuData.get("1").getAsJsonObject().get("exercise_total").getAsInt();
        double ohtutunt = parsittuData.get("1").getAsJsonObject().get("hour_total").getAsDouble();
//        
//        
        JsonParser parser2 = new JsonParser();
        JsonObject parsittuData2 = parser2.parse(railBody).getAsJsonObject();
        int railsStud = parsittuData2.get("1").getAsJsonObject().get("students").getAsInt();
        int railsTeht = parsittuData2.get("1").getAsJsonObject().get("exercise_total").getAsInt();
        double railsTunt = parsittuData2.get("1").getAsJsonObject().get("hour_total").getAsDouble();

        System.out.println("json-muotoinen data:");
        System.out.println(ohtuBody);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Kurssi[] kurssi = mapper.fromJson(kUbodyText, Kurssi[].class);

        System.out.println("kurssi test");
        for (Kurssi k : kurssi) {
            for (Submission sub : subs) {
                if (sub.getCourse().equals(k.getName())) {
                    k.addSub(sub);
                }
            }

            if (k.yesSubs()) {
                System.out.println(k.getFullName());

                k.viikottain();
                System.out.println("");

                if (k.getName().equals("ohtu2018")) {
                    System.out.println("Kurssilla yhteensä " + ohtuStud + "opiskelijaa, tehtäviä " + ohtuteht + " kpl, aikaa käytetty yhteensä " + ohtutunt + " tuntua");
                }
                if (k.getName().equals("rails2018")) {
                    System.out.println("Kurssilla yhteensä " + railsStud + "opiskelijaa, tehtäviä " + railsTeht + " kpl, aikaa käytetty yhteensä " + railsTunt + " tuntua");
                }
                
                System.out.println("");

            }

        }

//        System.out.println("Oliot:");
//        for (Submission submission : subs) {
//            System.out.println(submission);
//        }
    }
}
