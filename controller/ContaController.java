package br.com.caelum.contas.controller;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {
private ContaDAO dao;
@Autowired
public ContaController(ContaDAO dao)
{
	this.dao = dao;
}
@RequestMapping("adicionaConta")	
public String adiciona(@Valid Conta conta, BindingResult result)
{
	if(result.hasErrors())
	{
		return "conta/formulario";
	}
	//ContaDAO dao = new ContaDAO();
	this.dao.adiciona(conta);
	return "conta/conta-adicionada";
}
@RequestMapping(value="/form")
public String form()
{
	return "conta/formulario";
}
@RequestMapping("/listaContas")
public ModelAndView lista()
{
	//ContaDAO DaoConta = new ContaDAO();
	List<Conta> contas = this.dao.lista();
	ModelAndView mv = new ModelAndView("conta/lista");
	mv.addObject("contas",contas);
	return mv;
}
@RequestMapping("/removeConta")
public String remover(Conta conta)
{
	//ContaDAO dao = new ContaDAO();
	this.dao.remove(conta);
	return "redirect:listaContas";
}
@RequestMapping("/pagaConta")
public void paga(Long id,HttpServletResponse response)
{
	//ContaDAO daoConta = new ContaDAO();
	this.dao.paga(id);
	response.setStatus(200);
}
@RequestMapping("/mostraConta")
public String mostra(Long id,Model model)
{
	model.addAttribute("conta",dao.buscaPorId(id));
	return "conta/mostra";
}
@RequestMapping("/alteraConta")
public String altera(Conta conta)
{
	dao.altera(conta);
	return "redirect:listaContas";
}
}
