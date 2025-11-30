package org.example.TechZone.Seguridad;

import com.openxava.naviox.Modules;
import com.openxava.naviox.impl.SignInHelperProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.jpa.XPersistence;
import org.openxava.util.Messages;
import org.openxava.util.Users;
import org.openxava.view.View;

import javax.persistence.Query;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Properties;


public class CustomSignInHelperProvider extends SignInHelperProvider {

    private final static String PROPERTIES_FILE = "naviox-users.properties";
    private static Log log = LogFactory.getLog(SignInHelperProvider.class);
    private Properties users;

    public String [] init(HttpServletRequest request, View view) {
        return null;
    }

    public void initRequest(HttpServletRequest request, View view) {
    }

    public String refineForwardURI(HttpServletRequest request, String forwardURI) {
        return forwardURI;
    }

    public void signIn(HttpServletRequest request, String userName) {
        HttpSession session = request.getSession();
        session.setAttribute("naviox.user", userName);
        Modules modules = (Modules) session.getAttribute("modules");
        Users.setCurrent(userName); // In order startInLastVisitedModule=false works
        modules.reset();
    }

    private boolean isAuthorized(ServletRequest request, String user, String password) {
        String storedPassword = getUsers().getProperty("password." + user, null);
        return password.equals(storedPassword);
    }

    public boolean isAuthorized(ServletRequest request, String userName, String password, Messages errors, String unauthorizedMessage) {
        boolean authorized = isAuthorized(request, userName, password);
        if (!authorized) errors.add(unauthorizedMessage);
        return authorized;
    }


    private Properties getUsers() {
        users = new Properties(); //Inicializar ya que se le pasan objetos de tipo Object
        users.setProperty("usuario.admin", "admin");
        users.setProperty("password.admin", "admin");
        Query query = XPersistence.getManager().createQuery("SELECT e.usuario, e.contrasena, e.rol.id FROM Empleado e");
        List<Object[]> resultados = query.getResultList();
        if(!resultados.isEmpty()){
            for (Object[] fila : resultados) {
                Object usuarioObj = fila[0];
                Object passObj = fila[1];
                Object rolObj = fila[2];

                if (usuarioObj != null && passObj != null) {

                    String usuario = usuarioObj.toString();
                    String password = passObj.toString();

                    String rol = (rolObj != null) ? String.valueOf(rolObj) : "";

                    users.setProperty("usuario." + usuario, usuario);
                    users.setProperty("password." + usuario, password);
                    users.setProperty("rol." + usuario, rol);
                }

            }
        }
        return users;
    }

    public String getSignInURL() {
        return null;
    }
}
