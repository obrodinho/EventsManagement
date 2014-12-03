import org.consultjr.mvc.core.config.ApplicationConfig;
import org.consultjr.mvc.dao.SystemProfileDAO;
import org.consultjr.mvc.dao.UserDAO;
import org.consultjr.mvc.dao.UserSystemProfileDAO;
import org.consultjr.mvc.model.SystemProfile;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.model.UserSystemProfile;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

/**
 * Classe de inserção de informações iniciais, no sistema
 *
 * @author Rafael
 */
public class AppInstall {
    
    public static void main(String[] args) {
        //Não funciona. Objetivo: criar objetos no banco.
        ApplicationConfig appConfig = new ApplicationConfig();       
        HibernateTransactionManager htm = appConfig.transactionManager();
        SessionFactory sess = htm.getSessionFactory();
        
        UserDAO userDAO = new UserDAO();
        userDAO.setSessionFactory(sess);
        SystemProfileDAO spDAO = new SystemProfileDAO();
        spDAO.setSessionFactory(sess);
        UserSystemProfileDAO uspDAO = new UserSystemProfileDAO();
        uspDAO.setSessionFactory(sess);
        
        SystemProfile adminProfile = new SystemProfile("admin", "Administrador do Sistema");
        spDAO.addSystemProfile(adminProfile);
        User defaultUser = new User("Administrador", "do Sistema", "admin", "admin@LPS");
        userDAO.addUser(defaultUser);
        UserSystemProfile usp = new UserSystemProfile(userDAO.getUserByUsername("admin"), spDAO.getSystemProfileByShortname("admin"));
        uspDAO.addUserSystemProfile(usp);
    }
}
