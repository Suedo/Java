import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.net.*;

import com.google.gson.*;

/**
 * https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman
 */



public class Solution {
    /*
     * Complete the function below.
     * Base url: https://jsonmock.hackerrank.com/api/movies/search/?Title=
     */

    public class MRes {
        public String page;
        public Integer per_page;
        public Integer total;
        public Integer total_pages;
        List<Data> data;
    }

    public class Data {
        public String Title;
        public Integer Year;
        public String imdbID;
    }

    static String[] getMovieTitles(String substr) {

        try {
            URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");





        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String[] res;
        String _substr;
        try {
            _substr = in.nextLine();
        } catch (Exception e) {
            _substr = null;
        }

        res = getMovieTitles(_substr);
        for (int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}