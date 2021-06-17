package com.ncho.controller;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dominic E. Ncho
 */
@ManagedBean
@SessionScoped
public class languageController implements Serializable{
    private Locale locale ;

    public languageController() {
    }
    
    public String changeToEnglish(){
        FacesContext context = FacesContext.getCurrentInstance();
       context.getViewRoot().setLocale(Locale.ENGLISH);
       locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return null;
    }
    
    public String changeToFrench(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.FRENCH);
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return null;
    }

    public Locale getLocale() {
        return locale;
    }
    
}
