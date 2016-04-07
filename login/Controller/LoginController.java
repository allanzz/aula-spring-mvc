package br.com.caelum.contas.login.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.UsuarioDAO;
import br.com.caelum.contas.modelo.Usuario;

@Controller
public class LoginController {
	@RequestMapping("/loginForm")
	public String loginForm()
	{
		return "conta/formulario-login";
	}
	@RequestMapping("/efetuaLogin")
	public String efetuaLogin(Usuario usuario,HttpSession session)
	{
		if(new UsuarioDAO().existeUsuario(usuario))
		{
			session.setAttribute("usuarioLogado",usuario);
			return "conta/menu";
		}
		return "redirect:loginForm";
	}
	@RequestMapping("/logout")
	public String Sair(HttpSession session)
	{
		session.invalidate();
		return "redirect:loginForm";
	}

}
