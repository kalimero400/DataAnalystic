import dao.JdbcUserDao;
import dao.odnoklasniki.UserRessourceOdnoklasniki;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by arthur on 11.09.18.
 */
public class SavePictureToTheFile {

    public static void main( String[] args ){

        ApplicationContext context
                = new ClassPathXmlApplicationContext("applicationContext.xml");

        JdbcUserDao userDao = (JdbcUserDao) context.getBean("jdbcUserDao");

        List<User> imageUrls = userDao.getListOfImageUrls("22/10/2018");

        for(User user : imageUrls){

            try{
                String imageUrl  = user.getImageId();

                imageUrl = imageUrl.substring(0, imageUrl.lastIndexOf("&")).concat("&fn=sqr_288_2x");

                imageUrl = "https:" + imageUrl;

                saveImage(imageUrl, user.getId());

            }
            catch (Exception e){

            }


        }





    }


    public static void saveImage(String imageUrl, String destinationFile) {
        try {



            if(new File("/Users/arthur/Documents/intel/invik/top/"+destinationFile).exists()){
                System.out.println("Top Online");
                destinationFile = new File("/Users/arthur/Documents/intel/invik/topOnline/"+destinationFile).getAbsolutePath()+".jpg";
            }
            else if(new File("/Users/arthur/Documents/intel/invik/alt/"+destinationFile).exists()){

                destinationFile = new File("/Users/arthur/Documents/intel/invik/alt/"+destinationFile).getAbsolutePath();
            }
            else {
                destinationFile = new File("/Volumes/Untitled/ok/"+destinationFile).getAbsolutePath()+".jpg";
            }


            URL url = new URL(imageUrl);
            InputStream is = url.openStream();



            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];

            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

