package com.polygor.contactlist.dto;

import java.util.Objects;
import java.io.Serializable;

public class PeopleDto implements Serializable {

    private Long id;
    private String name;
    private String imageUrl;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleDto personDto = (PeopleDto) o;
        return id.equals(personDto.id) && name.equals(personDto.name) && imageUrl.equals(personDto.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageUrl);
    }
}
