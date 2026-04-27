package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();
    private int nextId = 1;
    private static final LocalDate MIN_RELEASE = LocalDate.of(1895, 12, 28);

    @GetMapping
    public List<Film> getAll() {
        return new ArrayList<>(films.values());
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        if (film.getReleaseDate() != null && film.getReleaseDate().isBefore(MIN_RELEASE)) {
            throw new RuntimeException("Дата релиза не может быть раньше 28 декабря 1895 года");
        }
        film.setId(nextId++);
        films.put(film.getId(), film);
        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        if (film.getId() == null) {
            throw new RuntimeException("Id должен быть указан");
        }
        if (!films.containsKey(film.getId())) {
            throw new RuntimeException("Фильм не найден");
        }
        if (film.getReleaseDate() != null && film.getReleaseDate().isBefore(MIN_RELEASE)) {
            throw new RuntimeException("Дата релиза не может быть раньше 28 декабря 1895 года");
        }
        films.put(film.getId(), film);
        return film;
    }
}