package com.senai.maria.prjGame.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.maria.prjGame.entities.Jogo;
import com.senai.maria.prjGame.services.JogoServices;

@RestController
@RequestMapping("/jogos")
public class JogoController {

	private final JogoServices JogoServices;

	@Autowired
	public JogoController(JogoServices JogoServices) {
		this.JogoServices = JogoServices;
	}

	@PostMapping
	public Jogo creatJogo(@RequestBody Jogo Jogo) {
		return JogoServices.saveJogo(Jogo);
	}
	
	@GetMapping
	public ResponseEntity<List<Jogo>> getAllJogo(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Jogo> jogos = JogoServices.getAllJogo();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(jogos);
	}
	
	@PutMapping("/{id}")
	public Jogo updateJogo(@PathVariable Long id, @RequestBody Jogo jogo) {
	    return JogoServices.updateJogo(id, jogo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Jogo> getJogo(@PathVariable Long id) {
		Jogo Jogo = JogoServices.getJogoById(id);
		if (Jogo != null) {
			return ResponseEntity.ok(Jogo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	@DeleteMapping("/{id}")
	public void deleteJogo(@PathVariable Long id) {
		JogoServices.deleteJogo(id);
	}

}
