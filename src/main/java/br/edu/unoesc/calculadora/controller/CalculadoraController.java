package br.edu.unoesc.calculadora.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.unoesc.calculadora.model.Calculadora;

@Controller
public class CalculadoraController {
	@GetMapping("/soma-query")
	public String somaQuery(@RequestParam(value = "n1", defaultValue = "0") String n1,
			@RequestParam(value = "n2", defaultValue = "0") String n2, RedirectAttributes atributos) {
		atributos.addAttribute("v1", "40");
		atributos.addFlashAttribute("v2", "42");

		return "redirect:/soma-path/" + n1 + "/" + n2;
	}

	@GetMapping("/soma-path/{numero1}/{numero2}")
	public String somaPath(@PathVariable String numero1, @PathVariable String numero2, @ModelAttribute("v1") Double v1,
			@ModelAttribute("v2") Double v2, ModelMap model) {
		System.out.println(v1 + " " + v2);
		System.out.println(model.getAttribute("v1") + " " + model.getAttribute("v2"));

		model.addAttribute("resultado", Calculadora.somar(numero1, numero2));

		return "resultado";
	}

	// ----------------
	@GetMapping("/soma-forward-query")
	public String somaForwardQuery(@RequestParam(value = "n1", defaultValue = "0") String n1,
			@RequestParam(value = "n2", defaultValue = "0") String n2, ModelMap model, HttpServletRequest requisicao) {
		model.addAttribute("v1", "40");
		requisicao.setAttribute("v2", "2");

		return "forward:/soma-forward-path/" + n1 + "/" + n2;
	}

	@GetMapping("/soma-forward-path/{numero1}/{numero2}")
	public String somaForwardPath(@PathVariable String numero1, @PathVariable String numero2, Model model,
			HttpServletRequest requisicao) {
		System.out.println(requisicao.getAttribute("v1"));
		System.out.println(requisicao.getAttribute("v2"));

		model.addAttribute("resultado", Calculadora.somar(numero1, numero2));

		return "resultado";
	}

	@GetMapping("/subtrair-query")
	public String subtrairQuery(@RequestParam(value = "n1", defaultValue = "0") String n1,
			@RequestParam(value = "n2", defaultValue = "0") String n2, RedirectAttributes atributos) {
		atributos.addAttribute("v1", "50");
		atributos.addFlashAttribute("v2", "30");

		return "redirect:/subtrair-path/" + n1 + "/" + n2;
	}

	@GetMapping("/subtrair-path/{n1}/{n2}")
	public String subtrairPath(@PathVariable String n1, @PathVariable String n2, @ModelAttribute("v1") String v1,
			@ModelAttribute("v2") String v2) {
		Double resultado = Calculadora.subtrair(n1, n2);
		int valor1 = Integer.parseInt(v1);
		int valor2 = Integer.parseInt(v2);

		System.out.println("v1 = " + valor1);
		System.out.println("v2 = " + v2);

		return "O resultado da subtração de " + n1 + " - " + n2 + " = " + resultado;
	}

	@GetMapping("/multiplica-forward-query")
	public String multiplicaForwardQuery(@RequestParam(value = "n1", defaultValue = "0") String n1,
			@RequestParam(value = "n2", defaultValue = "0") String n2, ModelMap model, HttpServletRequest requisicao) {
		model.addAttribute("v1", "10");
		requisicao.setAttribute("v2", "5");

		return "forward:/multiplica-forward-path/" + n1 + "/" + n2;
	}

	@GetMapping("/multiplica-forward-path/{n1}/{n2}")
	public String multiplicaForwardPath(@PathVariable String n1, @PathVariable String n2, Model model,
			HttpServletRequest requisicao) {
		String v1 = (String) model.getAttribute("v1");
		String v2 = (String) requisicao.getAttribute("v2");

		Double resultado = Calculadora.multiplicar(n1, n2);

		return "Resultado: " + resultado + ", v1: " + v1 + ", v2: " + v2;
	}
}