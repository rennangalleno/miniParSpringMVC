package br.com.myproject.minipar.controllers;

import java.util.Date;
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

import br.com.myproject.minipar.dao.ChequeDAO;
import br.com.myproject.minipar.dao.ClienteDAO;
import br.com.myproject.minipar.dao.PagadorDAO;
import br.com.myproject.minipar.dao.SituacaoRecebivelDAO;
import br.com.myproject.minipar.dao.TipoRecebivelDAO;
import br.com.myproject.minipar.models.Cheque;
import br.com.myproject.minipar.models.Cliente;
import br.com.myproject.minipar.models.Pagador;
import br.com.myproject.minipar.validation.ChequeValidation;

@Controller
@RequestMapping("/cheque")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ChequeController {

	@Autowired
	private ChequeDAO chequeDao;
	
	@Autowired
	private ClienteDAO clienteDao;
	
	@Autowired
	private PagadorDAO pagadorDao;
	
	@Autowired
	private SituacaoRecebivelDAO situacaoDao;
	
	@Autowired
	private TipoRecebivelDAO tipoDao;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ChequeValidation());
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.GET)
	public ModelAndView novo(Cheque cheque) {
		ModelAndView modelAndView = new ModelAndView("cheque/novo");
		List<Pagador> pagadores = pagadorDao.lista();
		List<Cliente> clientes = clienteDao.listar();
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("pagadores", pagadores);
	
	
		return modelAndView;		
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView novoGravar(@Valid Cheque cheque, BindingResult result, RedirectAttributes redirectAttributes){
		System.out.println("Pagador "+cheque.getPagador().getId());
		System.out.println("Cliente "+cheque.getCliente().getId());
		System.out.println("Data de Vencimento "+cheque.getDataVencimento());
		System.out.println("Valor "+cheque.getValor());
		if (result.hasErrors()) {
			return novo(cheque);
		}
		cheque.setDataCriacao(new Date());
		cheque.setSituacaoRecebivel(situacaoDao.find(1));
		cheque.setTipoRecebivel(tipoDao.find(2));
		chequeDao.gravar(cheque);
		redirectAttributes.addFlashAttribute("sucesso", "Cheque cadastrado com sucesso!");
		return new ModelAndView("redirect:/cheque/lista");	
	}
	
	@RequestMapping(value="/lista", method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Cheque> cheques = chequeDao.listar();
		List<Pagador> pagadores = pagadorDao.lista();
		List<Cliente> clientes = clienteDao.listar();
		
		ModelAndView modelAndView = new ModelAndView("cheque/lista");
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("pagadores", pagadores);
		modelAndView.addObject("cheques", cheques);
		return modelAndView;
	}
	
	@RequestMapping(value="/consulta", method=RequestMethod.GET)
	public ModelAndView consultar(Model model,
			@RequestParam Integer clienteId, 
			@RequestParam Integer pagadorId) {
		System.out.println("Estou no Post");
		System.out.println("Cliente id "+clienteId);
		System.out.println("Pagador id "+pagadorId);
		
		List<Cliente> clientes = clienteDao.listar();		
		List<Pagador> pagadores = pagadorDao.lista();
		List<Cheque> cheques = chequeDao.consultar(clienteId, pagadorId);
		model.addAttribute("clientes", clientes);
		model.addAttribute("pagadores", pagadores);
		model.addAttribute("cheques", cheques);		
		return  new ModelAndView("/cheque/lista");
	}
	
	@RequestMapping(value="/edita", method = RequestMethod.GET)
	public ModelAndView editar(@RequestParam Integer id) {
		ModelAndView modelAndView = new ModelAndView("cheque/edita");
		Cheque cheque = chequeDao.find(id);
		List<Pagador> pagadores = pagadorDao.lista();
		List<Cliente> clientes = clienteDao.listar();
		modelAndView.addObject("cheque", cheque);
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("pagadores", pagadores);
	
		return modelAndView;		
	}
	
	@RequestMapping(value="/edita", method = RequestMethod.POST)
	public ModelAndView editaGravar(@Valid Cheque cheque, BindingResult result, RedirectAttributes redirectAttributes){
		System.out.println("Pagador "+cheque.getPagador().getId());
		System.out.println("Cliente "+cheque.getCliente().getId());
		System.out.println("Tipo do Recebivel "+cheque.getTipoRecebivel().getId());
		System.out.println("Situacao do Recebivel "+cheque.getSituacaoRecebivel().getId());
		System.out.println("Data de Criação "+cheque.getDataCriacao());
		System.out.println("Data de Vencimento "+cheque.getDataVencimento());
		System.out.println("Valor "+cheque.getValor());
		
		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("/cheque/edita");
			List<Pagador> pagadores = pagadorDao.lista();
			List<Cliente> clientes = clienteDao.listar();
			modelAndView.addObject("clientes", clientes);
			modelAndView.addObject("pagadores", pagadores);
			return modelAndView;
		}
		
		chequeDao.gravar(cheque);
		redirectAttributes.addFlashAttribute("sucesso", "Cheque alterado com sucesso!");
		return new ModelAndView("redirect:/cheque/lista");	
	}
	
	@RequestMapping(value="/deleta", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		
		Cheque cheque = chequeDao.find(id);
		cheque.setSituacaoRecebivel(situacaoDao.find(3));		
		chequeDao.gravar(cheque);
		redirectAttributes.addFlashAttribute("sucesso", "Cheque excluido com sucesso!");
		return new ModelAndView("redirect:/cheque/lista");
	}	
	
	
}
