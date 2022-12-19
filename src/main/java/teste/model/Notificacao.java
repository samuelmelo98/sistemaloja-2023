package teste.model;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



public class Notificacao {
	
	
	
	public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public static void msgInformacao(String infomacaoMsg, String corpoMsg) {
        addMessage(FacesMessage.SEVERITY_INFO, infomacaoMsg, corpoMsg);
    }

    public static void msgAviso(String infomacaoMsg, String corpoMsg) {
        addMessage(FacesMessage.SEVERITY_WARN, infomacaoMsg, corpoMsg);
    }

    public static void msgErro(String infomacaoMsg, String corpoMsg) {
        addMessage(FacesMessage.SEVERITY_ERROR,infomacaoMsg, corpoMsg);
    }

    public static void showSticky() {
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sticky Message", "Message Content"));
    }

    public static void showMultiple() {
        addMessage(FacesMessage.SEVERITY_INFO, "Message 1", "Message Content");
        addMessage(FacesMessage.SEVERITY_INFO, "Message 2", "Message Content");
        addMessage(FacesMessage.SEVERITY_INFO, "Message 3", "Message Content");
    }

}
