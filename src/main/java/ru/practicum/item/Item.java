package ru.practicum.item;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "items", schema = "public")
public class Item {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private String url;
}
