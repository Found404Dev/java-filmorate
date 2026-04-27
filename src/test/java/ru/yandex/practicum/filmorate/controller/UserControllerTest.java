package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController controller;

    @BeforeEach
    void setUp() {
        controller = new UserController();
    }

    @Test
    void getAll_shouldReturnEmptyListInitially() {
        assertTrue(controller.getAll().isEmpty());
    }

    @Test
    void create_shouldAddUserAndGenerateId() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setLogin("test");
        user.setBirthday(LocalDate.of(2000,1,1));
        User created = controller.create(user);
        assertNotNull(created.getId());
        assertEquals(1, created.getId());
        assertEquals(1, controller.getAll().size());
    }

    @Test
    void create_shouldUseLoginAsNameWhenNameIsNull() {
        User user = new User();
        user.setEmail("a@a.com");
        user.setLogin("login2");
        user.setName(null);
        User created = controller.create(user);
        assertEquals("login2", created.getName());
    }

    @Test
    void update_shouldModifyExistingUser() {
        User user = new User();
        user.setEmail("old@example.com");
        user.setLogin("old");
        User created = controller.create(user);
        created.setEmail("new@example.com");
        User updated = controller.update(created);
        assertEquals("new@example.com", updated.getEmail());
    }

    @Test
    void update_shouldThrowWhenIdNull() {
        User user = new User();
        assertThrows(RuntimeException.class, () -> controller.update(user));
    }

    @Test
    void update_shouldThrowWhenNotFound() {
        User user = new User();
        user.setId(999);
        assertThrows(RuntimeException.class, () -> controller.update(user));
    }
}