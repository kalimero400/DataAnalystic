import dao.JdbcUserDao;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context
            = new ClassPathXmlApplicationContext("applicationContext.xml");


        JdbcUserDao userDao = (JdbcUserDao) context.getBean("jdbcUserDao");
        User user = new User(1, "mkyong",28);
        userDao.setUser(user);

        User user1 = userDao.getUserById(1);
        System.out.println(user1);



    }


}