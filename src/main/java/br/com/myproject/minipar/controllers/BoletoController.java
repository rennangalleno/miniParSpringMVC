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

import br.com.myproject.minipar.dao.BoletoDAO;
import br.com.myproject.minipar.dao.ClienteDAO;
import br.com.myproject.minipar.dao.PagadorDAO;
import br.com.myproject.minipar.dao.SituacaoRecebivelDAO;
import br.com.myproject.minipar.dao.TipoRecebivelDAO;
import br.com.myproject.minipar.models.Boleto;
import br.com.myproject.minipar.models.Cliente;
import br.com.myproject.minipar.models.Pagador;
import br.com.myproject.minipar.validation.BoletoValidation;

@Controller
@RequestMapping("/boleto")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class BoletoController {

	@Autowired
	private BoletoDAO boletoDao;
	
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
		binder.addValidators(new BoletoValidation());
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.GET)
	public ModelAndView novo(Boleto boleto) {
		ModelAndView modelAndView = new ModelAndView("boleto/novo");
		List<Pagador> pagadores = pagadorDao.lista();
		List<Cliente> clientes = clienteDao.listar();
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("pagadores", pagadores);
	
	
		return modelAndView;		
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView novoGravar(@Valid Boleto boleto, BindingResult result, RedirectAttributes redirectAttributes){
		System.out.println("Pagador "+boleto.getPagador().getId());
		System.out.println("Cliente "+boleto.getCliente().getId());
		System.out.println("Data de Vencimento "+boleto.getDataVencimento());
		System.out.println("Valor "+boleto.getValor());
		if (result.hasErrors()) {
			return novo(boleto);
		}
		boleto.setDataCriacao(new Date());
		boleto.setSituacaoRecebivel(situacaoDao.find(1));
		boleto.setTipoRecebivel(tipoDao.find(3));
		boletoDao.gravar(boleto);
		redirectAttributes.addFlashAttribute("sucesso", "Boleto cadastrado com sucesso!");
		return new ModelAndView("redirect:/boleto/lista");	
	}
	
	@RequestMapping(value="/lista", method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Boleto> boletos = boletoDao.listar();
		List<Pagador> pagadores = pagadorDao.lista();
		List<Cliente> clientes = clienteDao.listar();
		
		ModelAndView modelAndView = new ModelAndView("boleto/lista");
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("pagadores", pagadores);
		modelAndView.addObject("boletos", boletos);
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
		List<Boleto> boletos = boletoDao.consultar(clienteId, pagadorId);
		model.addAttribute("clientes", clientes);
		model.addAttribute("pagadores", pagadores);
		model.addAttribute("boletos", boletos);		
		return  new ModelAndView("/boleto/lista");
	}
	
	@RequestMapping(value="/edita", method = RequestMethod.GET)
	public ModelAndView editar(@RequestParam Integer id) {
		ModelAndView modelAndView = new ModelAndView("boleto/edita");
		Boleto boleto = boletoDao.find(id);
		List<Pagador> pagadores = pagadorDao.lista();
		List<Cliente> clientes = clienteDao.listar();
		modelAndView.addObject("boleto", boleto);
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("pagadores", pagadores);
	
		return modelAndView;		
	}
	
	@RequestMapping(value="/edita", method = RequestMethod.POST)
	public ModelAndView editaGravar(@Valid Boleto boleto, BindingResult result, RedirectAttributes redirectAttributes){
		System.out.println("Pagador "+boleto.getPagador().getId());
		System.out.println("Cliente "+boleto.getCliente().getId());
		System.out.println("Tipo do Recebivel "+boleto.getTipoRecebivel().getId());
		System.out.println("Situacao do Recebivel "+boleto.getSituacaoRecebivel().getId());
		System.out.println("Data de Criação "+boleto.getDataCriacao());
		System.out.println("Data de Vencimento "+boleto.getDataVencimento());
		System.out.println("Valor "+boleto.getValor());
		
		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("/boleto/edita");
			List<Pagador> pagadores = pagadorDao.lista();
			List<Cliente> clientes = clienteDao.listar();
			modelAndView.addObject("clientes", clientes);
			modelAndView.addObject("pagadores", pagadores);
			return modelAndView;
		}
		
		boletoDao.gravar(boleto);
		redirectAttributes.addFlashAttribute("sucesso", "Boleto alterado com sucesso!");
		return new ModelAndView("redirect:/boleto/lista");	
	}
	
	@RequestMapping(value="/deleta", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		
		Boleto boleto = boletoDao.find(id);
		boleto.setSituacaoRecebivel(situacaoDao.find(3));		
		boletoDao.gravar(boleto);
		redirectAttributes.addFlashAttribute("sucesso", "Boleto excluido com sucesso!");
		return new ModelAndView("redirect:/boleto/lista");
	}		
}
