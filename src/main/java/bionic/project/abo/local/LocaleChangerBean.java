package bionic.project.abo.local;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class LocaleChangerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//String localeCode = context.getViewRoot().setLocale(Locale.ENGLISH);
	
	private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }
	
    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }
    
    
    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    
    
	public String germanAction() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.GERMAN);
		return null;
	}

	public String englishAction() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(Locale.ENGLISH);
		return null;
	}
}
