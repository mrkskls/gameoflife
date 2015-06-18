package de.outfittery.game.of.life.controller;

import de.outfittery.game.of.life.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FieldController {

	@Autowired
	private FieldService fieldService;


	@RequestMapping(method = RequestMethod.GET, value = "/start-new-game")
	public String startNewGame(@RequestParam int rows, @RequestParam int columns) {

		return fieldService.createNewField(rows, columns).toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/start-new-randomized-game")
	public String startNewGameRandomInit(@RequestParam int rows, @RequestParam int columns) {

		return fieldService.createNewRandomField(rows, columns).toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/add-living-cell")
	public String addLivingCell(@RequestParam String id, @RequestParam int rows, @RequestParam int columns) {

		return fieldService.addLivingCell(id, rows, columns).toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/next-generation")
	public String nextGeneration(@RequestParam String id) {

		return fieldService.computeNextGeneration(id).toString();
	}
}