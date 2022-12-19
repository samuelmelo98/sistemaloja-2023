package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import teste.model.UsuarioSistemaLogin;

@WebFilter(urlPatterns = {"/*"})
public class Index implements Filter {

    public Index() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	     System.out.println("interceptando index filter tudo");
	     //Requisição request http
	     HttpServletRequest req = (HttpServletRequest) request;
	     //Cria sessão e pega sessão enviada por request
	 	 HttpSession sessao = req.getSession();
	 	 //Instancia novo obj e pega via sessão, atraves de atributo usuario enviado para sessão
	 	 UsuarioSistemaLogin user = (UsuarioSistemaLogin) sessao.getAttribute("usuario");
	 	//Verifica se o usuario que foi passado para sessão estar diferente de nulo
	 	if(user == null) {
	 		RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.xhtml");
	 		dispatcher.forward(request, response);
	 		return;
	 	}
	 		chain.doFilter(request, response);
	 	
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
