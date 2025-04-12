package pl.pjatk.movieservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.movieservice.Film;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private List<Film> movies = Arrays.asList(
            new Film("Incepcja", "Sci-Fi", 8.2, "Czasy, gdy technologia pozwala na wchodzenie w świat snów. Złodziej Cobb ma za zadanie wszczepić myśl do śpiącego umysłu."),
            new Film("Ojciec chrzestny", "Dramat", 8.6, "Opowieść o nowojorskiej rodzinie mafijnej. Starzejący się Don Corleone pragnie przekazać władzę swojemu synowi."),
            new Film("Shrek", "Animacja", 7.8, "By odzyskać swój dom, brzydki ogr z gadatliwym osłem wyruszają uwolnić piękną księżniczkę.")
    );

    @GetMapping
    public ResponseEntity<List<Film>> getAllMovies() {
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getMovieById(@PathVariable UUID id) {
        Optional<Film> film = movies.stream().filter(f -> f.getId().equals(id)).findFirst();

        return film.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
