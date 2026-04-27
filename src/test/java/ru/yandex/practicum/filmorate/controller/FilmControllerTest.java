package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    private FilmController controller;

    @BeforeEach
    void setUp() {
        controller = new FilmController();
    }

    @Test
    void createFilm_shouldAddFilm() {
        Film film = new Film();
        film.setName("Valid");
        film.setDescription("Description");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(100);
        Film created = controller.create(film);
        assertNotNull(created.getId());
        assertEquals(1, controller.getAll().size());
    }

    @Test
    void createFilm_shouldThrowWhenReleaseDateBefore1895() {
        Film film = new Film();
        film.setName("Old");
        film.setDescription("Description");
        film.setReleaseDate(LocalDate.of(1895, 12, 27));
        film.setDuration(100);
        assertThrows(RuntimeException.class, () -> controller.create(film));
    }

    @Test
    void updateFilm_shouldUpdateExisting() {
        Film film = new Film();
        film.setName("First");
        film.setDescription("Desc");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(100);
        Film created = controller.create(film);
        created.setName("Second");
        Film updated = controller.update(created);
        assertEquals("Second", updated.getName());
    }

    @Test
    void updateFilm_shouldThrowWhenIdNull() {
        Film film = new Film();
        film.setName("Name");
        film.setDescription("Desc");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(100);
        assertThrows(RuntimeException.class, () -> controller.update(film));
    }
}