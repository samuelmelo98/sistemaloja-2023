package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import teste.model.UsuarioSistemaLogin;

//@WebFilter(urlPatterns = {"/login/login.xhtml"})
//@WebFilter(urlPatterns = {"/*"})
public class Login implements Filter {

    public Login() {

    }


	public void destroy() {

	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	System.out.println("Interceptando com filtro url login");
	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession sessao = req.getSession();
	UsuarioSistemaLogin user = (UsuarioSistemaLogin) sessao.getAttribute("usuario");
	
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
