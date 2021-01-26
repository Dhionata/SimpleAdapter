package br.com.example.meuprimeiroexemplo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;
}