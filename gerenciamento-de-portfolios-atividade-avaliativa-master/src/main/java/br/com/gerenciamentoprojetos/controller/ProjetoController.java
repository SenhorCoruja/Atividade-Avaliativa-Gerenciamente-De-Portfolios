package br.com.gerenciamentoprojetos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.gerenciamentoprojetos.model.Projeto;
import br.com.gerenciamentoprojetos.repository.FuncionarioRepository;
import br.com.gerenciamentoprojetos.repository.ProjetoRepository;

@Controller
public class ProjetoController {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/projeto/list")
	public String listProjeto(Model model) {
		model.addAttribute("projetos", projetoRepository.findAll(Sort.by("dataInicio")));
		return "projeto/list";
	}
	
	@GetMapping("/projeto/add")
	public String addProjeto(Model model) {
		try {
			  
			model.addAttribute("projeto", new Projeto());
			model.addAttribute("gerentes", funcionarioRepository.findByCargo("Gerente"));
			model.addAttribute("funcionarios", funcionarioRepository.findByCargoNot("Gerente"));
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return "projeto/add";
	}
	
	@PostMapping("/projeto/save")
	public String saveProjeto(Projeto projeto) {
		try {
			if (projeto != null)
				projetoRepository.save(projeto);
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return "redirect:/projeto/view/" + projeto.getId();
		
	}
	
	@GetMapping("/projeto/view/{id}")
	public String viewProjeto(@PathVariable long id, Model model) {
		model.addAttribute("projeto", projetoRepository.findById(id));
		return "projeto/view";
	}
	
	@GetMapping("/projeto/edit/{id}")
	public String editProjeto(@PathVariable long id, Model model) {
		
		model.addAttribute("projeto", projetoRepository.findById(id));
		model.addAttribute("gerentes", funcionarioRepository.findByCargo("Gerente"));
		model.addAttribute("funcionarios", funcionarioRepository.findByCargoNot("Gerente"));
		return "projeto/edit";
		
	}
	
	@GetMapping("/projeto/delete/{id}")
	public String deleteProjeto(@PathVariable long id) {
		try {
			projetoRepository.deleteById(id); 
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return "redirect:/projeto/list";
	}
	
}
