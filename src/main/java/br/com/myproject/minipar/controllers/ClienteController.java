package br.com.myproject.minipar.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.myproject.minipar.dao.ClienteDAO;
import br.com.myproject.minipar.dao.ContaDAO;
import br.com.myproject.minipar.models.Cliente;
import br.com.myproject.minipar.models.Conta;
import br.com.myproject.minipar.validation.ClienteValidation;

@Controller
@RequestMapping("/cliente")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ClienteController {
	
	@Autowired
	private ClienteDAO clienteDao;
	
	@Autowired
	private ContaDAO contaDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.addValidators(new ClienteValidation());
	}
			
	@RequestMapping(value="/novo", method=RequestMethod.GET)
	public ModelAndView form(Cliente cliente) {
		return new ModelAndView("cliente/novo");
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.POST)
	public ModelAndView gravar(@Valid Cliente cliente, BindingResult result, RedirectAttributes redirectAttributes) {
				
		if (result.hasErrors()) {
			return form(cliente);
		}		
		Conta conta = new Conta();
		conta.setNumero(1000 + contaDao.findNumero());
		contaDao.gravar(conta);				
		clienteDao.gravar(cliente, conta.getId());	
		redirectAttributes.addFlashAttribute("sucesso", "Cliente cadastrado com sucesso!");
		return new ModelAndView("redirect:/cliente/lista");
	}	
	
	@RequestMapping(value="/lista", method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Cliente> clientes = clienteDao.listar();
		ModelAndView model = new ModelAndView("/cliente/lista");
		model.addObject("clientes", clientes);
		return model;
	}
	
	@RequestMapping(value="/consulta", method=RequestMethod.GET)
	public ModelAndView consultar(Model model,
			@RequestParam String nome, 
			@RequestParam String cpf) {
		
		List<Cliente> clientes = clienteDao.consultar(nome, cpf);
		model.addAttribute("clientes", clientes);		
		return  new ModelAndView("/cliente/lista");
	}
		
	@RequestMapping(value="/edita", method=RequestMethod.GET)
	public ModelAndView editar(@RequestParam Integer id) {
		ModelAndView model = new ModelAndView("/cliente/edita");
		Cliente cliente = clienteDao.find(id);
		model.addObject("cliente", cliente);
		return model;
	}
		
	@RequestMapping(value="/edita", method=RequestMethod.POST)
	public ModelAndView atualizar(@Valid Cliente cliente, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("/cliente/edita");
		}			
		clienteDao.atualizar(cliente);
		redirectAttributes.addFlashAttribute("sucesso", "Cliente atualizado com sucesso!");
		return new ModelAndView("redirect:/cliente/lista");
	}	
	
	@RequestMapping(value="/deleta", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		
		Cliente cliente = clienteDao.find(id);
		cliente.setBolAtivo(0);
		
		Conta conta = cliente.getConta();
		conta.setBolAtivo(0);
		
		System.out.println("Conta"+conta);
		
		contaDao.gravar(conta);
		clienteDao.atualizar(cliente);
		redirectAttributes.addFlashAttribute("sucesso", "Cliente inativado com sucesso!");
		return new ModelAndView("redirect:/cliente/lista");
	}	 
	
}
