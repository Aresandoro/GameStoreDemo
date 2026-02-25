package com.example.service;

import com.example.Game;
import com.example.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    // Iniezione tramite costruttore
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Restituisce tutti i giochi
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // Restituisce un gioco per id
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    // Crea un nuovo gioco
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    // Aggiorna un gioco esistente
    public Game updateGame(Long id, Game updatedGame) {
        return gameRepository.findById(id)
                .map(game -> {
                    game.setTitle(updatedGame.getTitle());
                    game.setDescription(updatedGame.getDescription());
                    game.setPrice(updatedGame.getPrice());
                    game.setGenre(updatedGame.getGenre());
                    return gameRepository.save(game);
                })
                .orElseThrow(() -> new RuntimeException("Game non trovato con id: " + id));
    }

    // Cancella un gioco
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}