package com.project.task.manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode( of = { "id" } )
@ToString( of = { "id", "title" } )
public class Todo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id; // Identificador da tarefa

    @NotBlank
    @Size( min= 5, max=100 )
    @Column( length = 100, nullable = false )
    private String title; // Título da tarefa

    @Column( nullable = false )
    private LocalDateTime createdAt; // Data e hora de quando a tarefa foi criada

    @FutureOrPresent
    @Column( nullable = false )
    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private LocalDate deadLine; // Prazo final para a tarefa ser concluída

    @Column( nullable = true )
    private LocalDate finishedAt; // Data de quando a tarefa foi finalizada
}
