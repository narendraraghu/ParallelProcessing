package com.parallel.streams;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookEntity
{
    //Defining book id as primary key
    @Id
    @Column
    private int bookId;
    @Column
    @NotBlank(message = "Book Name required")
    private String bookName;
    @Column(updatable = false,unique = true)
    @Size(min=5,message="Author Name should have at least 5 characters")
    @NotBlank(message = "Author Name required")
    private String author;
    @Column
    @NotNull
    private int price;
}