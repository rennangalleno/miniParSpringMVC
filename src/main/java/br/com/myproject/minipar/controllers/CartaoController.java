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

import br.com.myproject.minipar.dao.CartaoDAO;
import br.com.myproject.minipar.dao.ClienteDAO;
import br.com.myproject.minipar.dao.SituacaoRecebivelDAO;
import br.com.myproject.minipar.dao.TipoRecebivelDAO;
import br.com.myproject.minipar.models.Bandeira;
import br.com.myproject.minipar.models.Cartao;
import br.com.myproject.minipar.models.Cliente;
import br.com.myproject.minipar.validation.CartaoValidation;

@Controller
@RequestMapping("/cartao")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CartaoController {

	@Autowired
	private CartaoDAO cartaoDao;
	
	@Autowired
	private ClienteDAO clienteDao;
	
	@Autowired
	private SituacaoRecebivelDAO situacaoDao;
	
	@Autowired
	private TipoRecebivelDAO tipoDao;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new CartaoValidation());
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.GET)
	public ModelAndView novo(Cartao cartao) {
		ModelAndView modelAndView = new ModelAndView("cartao/novo");
		List<Bandeira> bandeiras = cartaoDao.listaBandeira();
		List<Cliente> clientes = clienteDao.listar();
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("bandeiras", bandeiras);
	
	
		return modelAndView;		
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView novoGravar(@Valid Cartao cartao, BindingResult result, RedirectAttributes redirectAttributes){
		System.out.println("Bandeira "+cartao.getBandeira().getId());
		System.out.println("Cliente "+cartao.getCliente().getId());
		System.out.println("Data de Vencimento "+cartao.getDataVencimento());
		System.out.println("Valor "+cartao.getValor());
		if (result.hasErrors()) {
			return novo(cartao);
		}
		cartao.setDataCriacao(new Date());
		cartao.setSituacaoRecebivel(situacaoDao.find(1));
		cartao.setTipoRecebivel(tipoDao.find(1));
		cartaoDao.gravar(cartao);
		redirectAttributes.addFlashAttribute("sucesso", "Cartão cadastrado com sucesso!");
		return new ModelAndView("redirect:/cartao/lista");	
	}
	
	@RequestMapping(value="/lista", method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Cartao> cartoes = cartaoDao.listar();
		List<Bandeira> bandeiras = cartaoDao.listaBandeira();
		List<Cliente> clientes = clienteDao.listar();
		
		ModelAndView modelAndView = new ModelAndView("cartao/lista");
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("bandeiras", bandeiras);
		modelAndView.addObject("cartoes", cartoes);
		return modelAndView;
	}
	
	@RequestMapping(value="/consulta", method=RequestMethod.GET)
	public ModelAndView consultar(Model model,
			@RequestParam Integer clienteId, 
			@RequestParam Integer bandeiraId) {
		System.out.println("Estou no Post");
		System.out.println("Cliente id "+clienteId);
		System.out.println("Bandeira id "+bandeiraId);
		
		List<Cliente> clientes = clienteDao.listar();		
		List<Bandeira> bandeiras = cartaoDao.listaBandeira();
		List<Cartao> cartoes = cartaoDao.consultar(clienteId, bandeiraId);
		model.addAttribute("clientes", clientes);
		model.addAttribute("bandeiras", bandeiras);
		model.addAttribute("cartoes", cartoes);		
		return  new ModelAndView("/cartao/lista");
	}
	
	@RequestMapping(value="/edita", method = RequestMethod.GET)
	public ModelAndView editar(@RequestParam Integer id) {
		ModelAndView modelAndView = new ModelAndView("cartao/edita");
		Cartao cartao = cartaoDao.find(id);
		List<Bandeira> bandeiras = cartaoDao.listaBandeira();
		List<Cliente> clientes = clienteDao.listar();
		modelAndView.addObject("cartao", cartao);
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("bandeiras", bandeiras);
	
		return modelAndView;		
	}
	
	@RequestMapping(value="/edita", method = RequestMethod.POST)
	public ModelAndView editaGravar(@Valid Cartao cartao, BindingResult result, RedirectAttributes redirectAttributes){
		System.out.println("Bandeira "+cartao.getBandeira().getId());
		System.out.println("Cliente "+cartao.getCliente().getId());
		System.out.println("Tipo do Recebivel "+cartao.getTipoRecebivel().getId());
		System.out.println("Situacao do Recebivel "+cartao.getSituacaoRecebivel().getId());
		System.out.println("Data de Criação "+cartao.getDataCriacao());
		System.out.println("Data de Vencimento "+cartao.getDataVencimento());
		System.out.println("Valor "+cartao.getValor());
		
		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("/cartao/edita");
			List<Bandeira> bandeiras = cartaoDao.listaBandeira();
			List<Cliente> clientes = clienteDao.listar();
			modelAndView.addObject("clientes", clientes);
			modelAndView.addObject("bandeiras", bandeiras);
			return modelAndView;
		}
		
		cartaoDao.gravar(cartao);
		redirectAttributes.addFlashAttribute("sucesso", "Cartão alterado com sucesso!");
		return new ModelAndView("redirect:/cartao/lista");	
	}
	
	@RequestMapping(value="/deleta", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		
		Cartao cartao = cartaoDao.find(id);
		cartao.setSituacaoRecebivel(situacaoDao.find(3));		
		cartaoDao.gravar(cartao);
		redirectAttributes.addFlashAttribute("sucesso", "Cartão excluido com sucesso!");
		return new ModelAndView("redirect:/cartao/lista");
	}	
	
	
}
