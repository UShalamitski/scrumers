package com.scrumers.controller;

import javax.servlet.http.HttpSession;

import com.scrumers.model.User;
import com.scrumers.api.service.UserService;

public class ControllerUtils {

    public HttpSession checkSessionAttr(HttpSession session, User u,
            UserService userSrv) {
        try {
            if (session.getAttribute("org") == null)
                if (u.getActualOrganization() > 0
                        && u.getActualOrganization() != null) {
                    session.setAttribute("org",
                            userSrv.getActualOrganizationName(u.getId()));
                    session.setAttribute("org_id", u.getActualOrganization());
                }

            if (session.getAttribute("prod") == null)
                if (u.getActualProduct() > 0 && u.getActualProduct() != null) {
                    session.setAttribute("prod",
                            userSrv.getActualProductName(u.getId()));
                    session.setAttribute("prod_id", u.getActualProduct());
                }

            if (session.getAttribute("iter") == null)
                if (u.getActualIteration() > 0
                        && u.getActualIteration() != null) {
                    session.setAttribute("iter",
                            userSrv.getActualIterationName(u.getId()));
                    session.setAttribute("iter_id", u.getActualIteration());
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return session;
    }

    public boolean IsSessionAttrEqNull(HttpSession session) {
        if (session.getAttribute("org") == null)
            return true;
        else
            return false;
    }

    public boolean IsAllSessionAttrsExists(HttpSession session) {
        if (session.getAttribute("org") != null
                && session.getAttribute("prod") != null
                && session.getAttribute("iter") != null)
            return true;
        else
            return false;
    }

    public HttpSession delAttributes(HttpSession session) {
        session.setAttribute("org", null);
        session.setAttribute("org_id", null);
        session.setAttribute("prod", null);
        session.setAttribute("prod_id", null);
        session.setAttribute("iter", null);
        session.setAttribute("iter_id", null);
        return session;
    }

}
