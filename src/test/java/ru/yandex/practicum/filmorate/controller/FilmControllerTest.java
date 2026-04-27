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
    void getAll_shouldReturnEmptyListInitially() {
        assertTrue(controller.getAll().isEmpty());
    }

    @Test
    void create_shouldAddFilmAndGenerateId() {
        Film film = new Film();
        film.setName("Inception");
        film.setReleaseDate(LocalDate.of(2010, 7, 16));
        film.setDuration(148);
        Film created = controller.create(film);
        assertNotNull(created.getId());
        assertEquals(1, created.getId());
        assertEquals(1, controller.getAll().size());
    }

    @Test
    void create_shouldThrowWhenReleaseDateTooEarly() {
        Film film = new Film();
        film.setName("Old");
        film.setReleaseDate(LocalDate.of(1895, 12, 27));
        film.setDuration(50);
        assertThrows(RuntimeException.class, () -> controller.create(film));
    }

    @Test
    void update_shouldModifyExistingFilm() {
        Film film = new Film();
        film.setName("Original");
        Film created = controller.create(film);
        created.setName("Updated");
        Film updated = controller.update(created);
        assertEquals("Updated", updated.getName());
    }

    @Test
    void update_shouldThrowWhenIdNull() {
        Film film = new Film();
        assertThrows(RuntimeException.class, () -> controller.update(film));
    }

    @Test
    void update_shouldThrowWhenNotFound() {
        Film film = new Film();
        film.setId(999);
        assertThrows(RuntimeException.class, () -> controller.update(film));
    }
}
