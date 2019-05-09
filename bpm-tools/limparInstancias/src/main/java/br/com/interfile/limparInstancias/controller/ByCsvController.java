package br.com.interfile.limparInstancias.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.interfile.limparInstacias.model.ByCsv;
import br.com.interfile.limparInstancias.service.CSVService;
import br.com.interfile.limparInstancias.service.ProcessInstanceService;

@Controller
public class ByCsvController {

	@Autowired
	private CSVService csvService; 

	@Autowired
	private ProcessInstanceService processInstanceService;

	@GetMapping("/byCsv")
	public String telaByCSV() {
		return "byCsv";
	}
	
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("url") String url) {
		try {
			List<ByCsv> dadosCsv = csvService.processInstances(file.getInputStream());
			System.out.println(dadosCsv.toString());
			System.out.println(url);
			processInstanceService.excluirInstanciasCSV(dadosCsv, url);
		} catch (IOException e) {
			System.out.println("Problema na leitura do Arquivo");
		} 
		return "redirect:/";
	}
}
