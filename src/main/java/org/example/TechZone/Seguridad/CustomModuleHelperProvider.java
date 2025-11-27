package org.example.TechZone.Seguridad;

import com.openxava.naviox.Modules;
import com.openxava.naviox.impl.MetaModuleFactory;
import com.openxava.naviox.impl.ModulesHelperProvider;
import org.example.TechZone.model.Vistas;
import org.openxava.application.meta.MetaModule;
import org.openxava.jpa.XPersistence;
import org.openxava.util.Users;

import javax.persistence.Query;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomModuleHelperProvider extends ModulesHelperProvider {
    public void init(String applicationName) {
    }

    public String getCurrent(HttpServletRequest request) {
        return null;
    }

    /** @since 5.6 */
    public String getUserAccessModule(ServletRequest request) {
        return "SignIn";
    }

    public List<MetaModule> getAll(HttpServletRequest request) {
        if (Users.getCurrent() == null) return Collections.EMPTY_LIST;
        return createAll();
    }

    /** @since 6.5 */
    public List<MetaModule> getNotInMenu() {
        return Collections.EMPTY_LIST;
    }

    private List<MetaModule> createAll() {
        String user = Users.getCurrent();
        System.out.println(user);
        Query query = XPersistence.getManager().createQuery(
                "SELECT v.nombreVista " +
                "FROM Empleado e " +
                "JOIN e.rol.vistas v " +
                "WHERE e.usuario = :user");
        query.setParameter("user", user);
        List<String> vistasPermitidas = query.getResultList();
        List<MetaModule> result = new ArrayList<MetaModule>();
        if(user.equals("admin")){
            for (MetaModule module: MetaModuleFactory.createAll()) {
                System.out.println(module.getName());
                if (module.getName().equals("SignIn")) continue;
                if (module.getName().equals("DiscussionComment")) continue;
                result.add(module);
            }
        }else{
            for (MetaModule module: MetaModuleFactory.createAll()) {
                System.out.println(module.getName());
                if (module.getName().equals("SignIn")) continue;
                if (module.getName().equals("DiscussionComment")) continue;
                if (!vistasPermitidas.contains(module.getName())) continue;
                result.add(module);
            }
        }

        return result;
    }

    public boolean isPublic(HttpServletRequest request, String moduleName) {
        return Modules.FIRST_STEPS.equals(moduleName);
    }

    public boolean showsIndexLink() {
        return false;
    }

    public boolean showsSearchModules(HttpServletRequest request) {
        Modules modules = (Modules) request.getSession().getAttribute("modules");
        return modules.getAll(request).size() > 30;
    }

    public Collection<MetaModule> getInMenu(HttpServletRequest request, Modules modules) {
        if ("true".equals(request.getParameter("fixedModules"))) {
            return modules.getFixedModules(request);
        }
        if ("true".equals(request.getParameter("bookmarkModules"))) {
            return modules.getBookmarkModules(request);
        }
        return modules.getRegularModules(request);
    }
}
