package br.edu.infnet.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.model.Monstro;
import br.edu.infnet.service.MonstroService;

@RestController
@RequestMapping("/api/monstro")
public class MonstroController {
	
	Logger logger = Logger.getLogger(MonstroController.class.getName());
	
	@Autowired
	MonstroService monstroService;
	
	
	@PostMapping
	public void create(@RequestBody Monstro m) {
		logger.info("Criando o monstro: " + m.getNome());
		this.monstroService.create(m);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		try {
			Monstro byId = this.monstroService.getById(id);
			logger.info("Deletando o monstro: id:" + byId.getId() + " nome:" + byId.getNome());
			this.monstroService.delete(byId);
		} catch (NoSuchElementException e) {
			logger.info("Monstro não encontrado " + "(id:"+id+") ");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Monstro> getById(@PathVariable Long id) {
		logger.info("Buscando monstro pelo id: " + id);
		
		Monstro m = null;
		
		try {
			m = this.monstroService.getById(id);
			logger.info("Monstro encontrado: id:" + m.getId() + " nome:" + m.getNome());
			return new ResponseEntity<Monstro>(m,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			logger.info("Monstro não encontrado " + "(id:"+id+") ");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Monstro m){
		
		
		try {
			logger.info("Monstro encontrado: id:" + m.getId() + " nome:" + m.getNome());
			m = this.monstroService.getById(id);
			logger.info("Alterando monstro: " + id);
			this.monstroService.update(id, m);
		} catch (NoSuchElementException e) {
			logger.info("Monstro não encontrado " + "(id:"+id+") ");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping
	public List<Monstro> getAll() {
		logger.info("Listando todos os monstros");
		return monstroService.getAll();
	}
	
	@GetMapping("/random")
	public Monstro getRandom() {
		Monstro m = monstroService.getRandom();		
		logger.info("Recuperando monstro aleatório: " + m.getNome());
		return monstroService.getRandom();
	}
	

	
}
