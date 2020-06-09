package br.com.myproject.minipar.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.myproject.minipar.dao.AgendaDAO;
import br.com.myproject.minipar.dao.BoletoDAO;
import br.com.myproject.minipar.dao.CartaoDAO;
import br.com.myproject.minipar.dao.ChequeDAO;
import br.com.myproject.minipar.dao.ClienteDAO;
import br.com.myproject.minipar.dao.PagadorDAO;
import br.com.myproject.minipar.dao.SituacaoRecebivelDAO;
import br.com.myproject.minipar.dao.TipoRecebivelDAO;
import br.com.myproject.minipar.models.Bandeira;
import br.com.myproject.minipar.models.Boleto;
import br.com.myproject.minipar.models.Cartao;
import br.com.myproject.minipar.models.Cheque;
import br.com.myproject.minipar.models.Cliente;
import br.com.myproject.minipar.models.Lote;
import br.com.myproject.minipar.models.Pagador;
import br.com.myproject.minipar.models.Remessa;
import br.com.myproject.minipar.models.TipoRecebivel;

@Controller
@RequestMapping("/agenda")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class AgendaController {
	
	@Autowired
	private ClienteDAO clienteDao;
	
	@Autowired
	private TipoRecebivelDAO tipoDao;
	
	@Autowired
	private CartaoDAO cartaoDao;
	
	@Autowired
	private PagadorDAO pagadorDao;
	
	@Autowired
	private AgendaDAO agendaDao;
	
	@Autowired
	private ChequeDAO chequeDao;
	
	@Autowired
	private BoletoDAO boletoDao;
	
	@Autowired
	private SituacaoRecebivelDAO situacaoDao;
		
		
	@RequestMapping(value="/cliente", method = RequestMethod.GET)
	public ModelAndView escolheCliente() {
		ModelAndView modelAndView = new ModelAndView("agendaFinanceira/cliente");
		List<Cliente> clientes = clienteDao.listar(); 
		modelAndView.addObject("clientes", clientes);
		return modelAndView ;
	}
	
	@RequestMapping(value="/lista", method = RequestMethod.GET)
	public ModelAndView listar(@RequestParam Integer clienteId) {
		List<TipoRecebivel> tipos = tipoDao.listaTipoRecebivel();
		List<Bandeira> bandeiras = cartaoDao.listaBandeira();
		List<Pagador> pagadores = pagadorDao.lista();
		List<Cheque> cheques = agendaDao.listaCheque(clienteId);
		List<Boleto> boletos = agendaDao.listaBoleto(clienteId);
		List<Cartao> cartoes = agendaDao.listaCartao(clienteId);
		Cliente cliente = clienteDao.find(clienteId);
		ModelAndView modelAndView = new ModelAndView("agendaFinanceira/lista");
		modelAndView.addObject("cliente", cliente);
		modelAndView.addObject("tipos", tipos);
		modelAndView.addObject("bandeiras", bandeiras);
		modelAndView.addObject("pagadores", pagadores);
		modelAndView.addObject("cheques", cheques);
		modelAndView.addObject("boletos", boletos);
		modelAndView.addObject("cartoes", cartoes);
		return modelAndView;
	}
	
	@RequestMapping(value="/consulta", method = RequestMethod.GET)
	public ModelAndView consultar(
			Cliente clienteAgenda, 
			@RequestParam Integer tipoId,
			@RequestParam Integer bandeiraId,
			@RequestParam Integer pagadorId
			/*@RequestParam Date dataInicial,
			@RequestParam Date dataFinal*/) {
				
		List<TipoRecebivel> tipos = tipoDao.listaTipoRecebivel();
		List<Bandeira> bandeiras = cartaoDao.listaBandeira();
		List<Pagador> pagadores = pagadorDao.lista();
		List<Cheque> cheques = agendaDao.consultaCheque(clienteAgenda.getId(), pagadorId, tipoId /*,dataInicial, dataFinal*/);
		List<Boleto> boletos = agendaDao.consultaBoleto(clienteAgenda.getId(), pagadorId, tipoId /*,dataInicial, dataFinal*/);
		List<Cartao> cartoes = agendaDao.consultaCartao(clienteAgenda.getId(), bandeiraId, tipoId /*,dataInicial, dataFinal*/);
		Cliente cliente = clienteDao.find(clienteAgenda.getId());
		ModelAndView modelAndView = new ModelAndView("agendaFinanceira/lista");
		modelAndView.addObject("cliente", cliente);
		modelAndView.addObject("tipos", tipos);
		modelAndView.addObject("bandeiras", bandeiras);
		modelAndView.addObject("pagadores", pagadores);
		modelAndView.addObject("cheques", cheques);
		modelAndView.addObject("boletos", boletos);
		modelAndView.addObject("cartoes", cartoes);
		return modelAndView;
	}
	
	@RequestMapping(value="/antecipaRemessa", method = RequestMethod.GET)
	public ModelAndView criarRemessa (@RequestParam Integer clienteId ) {
		
		List<Cheque> cheques = agendaDao.listaCheque(clienteId);
		List<Boleto> boletos = agendaDao.listaBoleto(clienteId);
		List<Cartao> cartoes = agendaDao.listaCartao(clienteId);
		List<Lote<?>> lotes = new ArrayList<Lote<?>>();
		
		if(!cheques.isEmpty() ) {
			Lote<Cheque> loteCheque = new Lote<Cheque>();
			loteCheque.setDataCriacao(new Date());
			loteCheque.addRecebiveis(cheques);
			agendaDao.gravaLote(loteCheque);
			
			for (Cheque cheque : cheques) {
				cheque.setLote(loteCheque);
				cheque.setSituacaoRecebivel(situacaoDao.find(2));
				chequeDao.gravar(cheque);
			}
			
			lotes.add(loteCheque);
		}
		
		if(!boletos.isEmpty() ) {
			Lote<Boleto> loteBoleto = new Lote<Boleto>();
			loteBoleto.setDataCriacao(new Date());
			loteBoleto.addRecebiveis(boletos);
			
			agendaDao.gravaLote(loteBoleto);
			
			for (Boleto boleto : boletos) {
				boleto.setLote(loteBoleto);
				boleto.setSituacaoRecebivel(situacaoDao.find(2));
				boletoDao.gravar(boleto);
			}	
			
			lotes.add(loteBoleto);
		}
		
		if(!cartoes.isEmpty() ) {
			Lote<Cartao> loteCartoes = new Lote<Cartao>();
			loteCartoes.setDataCriacao(new Date());
			loteCartoes.addRecebiveis(cartoes);
			agendaDao.gravaLote(loteCartoes);
			
			for (Cartao cartao : cartoes) {
				cartao.setLote(loteCartoes);
				cartao.setSituacaoRecebivel(situacaoDao.find(2));
				cartaoDao.gravar(cartao);
			}	
			
			lotes.add(loteCartoes);				
		}
		
			Remessa remessa = new Remessa();
			remessa.setDataCriacao(new Date());
			remessa.addLotes(lotes);
			agendaDao.gravaRemessa(remessa);
			
			for (Lote<?> lote : lotes) {
				lote.setRemessa(remessa);
				agendaDao.gravaLote(lote);
			}
												
			return new ModelAndView("redirect:/agenda/lista");
	}
}

