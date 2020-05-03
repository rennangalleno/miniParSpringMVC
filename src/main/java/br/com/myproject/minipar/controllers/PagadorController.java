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

import br.com.myproject.minipar.dao.PagadorDAO;
import br.com.myproject.minipar.models.Pagador;
import br.com.myproject.minipar.validation.PagadorValidation;

@Controller
@RequestMapping("/pagador")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PagadorController {
	
	@Autowired
	private PagadorDAO pagadorDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new PagadorValidation());
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.GET)
	public ModelAndView novo(Pagador pagador) {
		return new ModelAndView("pagador/novo");
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Pagador pagador, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return novo(pagador);
		}
		pagadorDao.gravar(pagador);
		redirectAttributes.addFlashAttribute("sucesso", "Pagador cadastrado com sucesso!");
		return new ModelAndView("redirect:/pagador/lista");
	}
	
	@RequestMapping(value="/lista", method = RequestMethod.GET)
	public ModelAndView lista() {
		List<Pagador> pagadores = pagadorDao.lista();
		ModelAndView modelAndView = new ModelAndView("pagador/lista");
		modelAndView.addObject("pagadores", pagadores);
		return modelAndView;
	}
	
	@RequestMapping(value="/consulta", method = RequestMethod.GET)
	public ModelAndView pesquisa(Model model,
			@RequestParam String nome,
			@RequestParam String cpf) {
		
		List<Pagador> pagadores = pagadorDao.consulta(nome, cpf);
		model.addAttribute("pagadores", pagadores);
		return new ModelAndView("pagador/lista");
	}
	
	@RequestMapping(value="/edita", method = RequestMethod.GET)
	public ModelAndView edita(@RequestParam Integer id) {
		Pagador pagador = pagadorDao.find(id);
		ModelAndView modelAndView = new ModelAndView("pagador/edita");
		modelAndView.addObject("pagador", pagador);
		return modelAndView;
	}
	
	@RequestMapping(value="/edita", method = RequestMethod.POST)
	public ModelAndView atualiza(@Valid Pagador pagador, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("/pagador/edita");
		}
		System.out.println("Controller pagador:"+pagador.getId());
		pagadorDao.gravar(pagador);
		redirectAttributes.addFlashAttribute("sucesso", "Pagador atualizado com sucesso!");
		return new ModelAndView("redirect:/pagador/lista");
	}
	
	@RequestMapping(value="/deleta", method = RequestMethod.GET)
	public ModelAndView deleta(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		pagadorDao.deleta(id);
		redirectAttributes.addFlashAttribute("sucesso", "Pagador excluído com sucesso!");
		return new ModelAndView("redirect:/pagador/lista");	
	}
	
	
}
