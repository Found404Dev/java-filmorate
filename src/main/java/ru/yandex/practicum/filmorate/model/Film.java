package ru.yandex.practicum.filmorate.model;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class Film {
    private Integer id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}