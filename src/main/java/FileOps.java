import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class FileOps {


        public static void main(String[] argv) throws IOException {

            File folder = new File("/Volumes/Untitled/");
            File[] listOfFiles = folder.listFiles();
            PrintWriter writer = null;
            writer = new PrintWriter("links.html", "UTF-8");

            for (int i = 0; i < listOfFiles.length; i++) {

                if (listOfFiles[i].isFile()) {

                    String str = listOfFiles[i].getName();
                    str = str.replace(".jpg", "");

                    writer.println("https://ok.ru/profile/"+str);
                    writer.println("<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://ok.ru/profile/" + str+  "\">Link</a>\n");

                    System.out.println(str);

                }
            }
            writer.close();


            System.out.println("conversion is done");
        }
    }

