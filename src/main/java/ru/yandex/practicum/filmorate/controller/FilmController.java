package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import java.time.LocalDate;
import java.util.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;

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
    public Film create(@RequestBody Film film) {
        validateFilm(film);
        film.setId(nextId++);
        films.put(film.getId(), film);
        return film;
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        if (film.getId() == null) {
            throw new RuntimeException("Id должен быть указан");
        }
        if (!films.containsKey(film.getId())) {
            throw new RuntimeException("Фильм не найден");
        }
        validateFilm(film);
        films.put(film.getId(), film);
        return film;
    }

    private void validateFilm(Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription() != null && film.getDescription().length() > 200) {
            throw new ValidationException("Описание не должно превышать 200 символов");
        }
        if (film.getReleaseDate() != null && film.getReleaseDate().isBefore(MIN_RELEASE)) {
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
        }
        if (film.getDuration() == null || film.getDuration() <= 0) {
            throw new ValidationException("Продолжительность должна быть положительной");
        }
    }
}