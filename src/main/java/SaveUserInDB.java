import dao.JdbcUserDao;
import dao.odnoklasniki.UserRessourceOdnoklasniki;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class SaveUserInDB
{
    public static void main( String[] args )
    {
        ApplicationContext context
            = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserRessourceOdnoklasniki ressourceOdnoklasniki = new UserRessourceOdnoklasniki();


        JdbcUserDao userDao = (JdbcUserDao) context.getBean("jdbcUserDao");

        //List of parsed User from html dom
        List <User> users  = ressourceOdnoklasniki.getListofUsers();

        for(User user : users){
            if(userDao.getUserById(user.getId())==null){
                userDao.commitUser(user);
            }
        }


    }


}