

import java.io.File;
import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Example program to list links from a URL.
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {


        File input = new File("/Users/arthur/Documents/development/wamba.html");

        Document doc = Jsoup.parse(input, null);

        Elements firstElement = doc.select("div[id*=gs_result_list]");

        for(Element element : firstElement.get(0).children()){
            String profileId = element.select("a[href*=/profile/]").get(0).attr("href");

            System.out.println(profileId.substring(9));

            String city =  element.select("div[class*=gs_result_i_t_addr]").html();
            System.out.println(city);
            System.out.println();
        }

//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");
//
//        print("\nMedia: (%d)", media.size());
//        for (Element src : media) {
//            if (src.tagName().equals("img"))
//                print(" * %s: <%s> %sx%s (%s)",
//                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
//                        trim(src.attr("alt"), 20));
//            else
//                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
//        }
//
//        print("\nImports: (%d)", imports.size());
//        for (Element link : imports) {
//            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
//        }

//        print("\nLinks: (%d)", links.size());
//        for (Element link : links) {
////            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
//            System.out.println(link.attr("href"));
//        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + "";
        else
            return s;
    }
}