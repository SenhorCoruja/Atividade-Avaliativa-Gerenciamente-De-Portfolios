package br.com.gerenciamentoprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.gerenciamentoprojetos.model.User;
import br.com.gerenciamentoprojetos.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/user/list")
	public String listUser(Model model) {
		model.addAttribute("users", userRepository.findAll(Sort.by("id")));
		return "user/list";
	}
	
	@GetMapping("/user/add")
	public String addUser(Model model) {
		try {
			  
			model.addAttribute("user", new User());
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return "user/add";
	}
	
		@PostMapping("/user/save")
	    public String saveUser(User user) {
		try {
			userRepository.save(user);
		}catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return "redirect:/user/view/" + user.getId();
	}
	
	@GetMapping("/user/view/{id}")
	public String viewUser(@PathVariable long id, Model model) {
		model.addAttribute("user", userRepository.findById(id));
		return "user/view";
	}
	
	@GetMapping("/user/edit/{id}")
	public String editUser(@PathVariable long id, Model model) {
		
		model.addAttribute("user", userRepository.findById(id));
		return "user/edit";
		
	}
	
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable long id, Model model) {
		try {
			userRepository.deleteById(id); 
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return "redirect:/user/list";
	}
	
	
	
	}

