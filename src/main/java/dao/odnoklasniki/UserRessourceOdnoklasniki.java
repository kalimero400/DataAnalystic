package dao.odnoklasniki;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import model.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.lang.StringUtils;


/**
 * Created by arthur on 05.09.18.
 */
public class UserRessourceOdnoklasniki {


    public List getListofUsers ()  {

        List users = new ArrayList();
        File input = new File("ok.txt");

        Document doc = null;
        try {
            doc = Jsoup.parse(input, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements firstElement = doc.select("div[id*=gs_result_list]");

        for(Element element : firstElement.get(0).children()){
            User user = new User();
            String profileId = "11";
            String image = null;
            String city =  null;
            System.out.println((element.children().get(0).children().get(0).select("a[href*=/]").attr("href")));

            try{
                profileId = element.select("a[href*=/]").get(0).attr("href").concat("/");
                if(profileId.contains("profile")){
                    profileId = StringUtils.substringBetween(profileId, "/profile/", "/");
                }
                else{
                    profileId = StringUtils.substringBetween(profileId, "/", "/");
                }

                 image = element.select("img").get(0).attr("src");
                 city =  element.select("div[class*=gs_result_i_t_addr]").html();
            }
            catch (Exception e){
                e.printStackTrace();
            }


            user.setDate(new Date(new java.util.Date().getTime()));
            user.setId(profileId);
            user.setImageId(image);
            user.setInfo(city);
            user.setPlatform(1);
            users.add(user);

            System.out.println(profileId);
            System.out.println(city);
            System.out.println(image);
            System.out.println();
        }

        return users;
    }
}
