package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "values")
@Getter
@Setter
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;

    @Override
    public String toString() {
        return "Value{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", option=" + (option != null ? option.getName() : "null") +
               '}';
    }
}
