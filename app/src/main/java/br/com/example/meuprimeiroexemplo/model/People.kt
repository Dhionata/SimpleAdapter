package br.com.example.meuprimeiroexemplo.model;

import java.util.ArrayList;
import java.util.List;

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
public class People {
    //Integer
    private Integer id;
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    //String
    private String homeworld;
    private List<String> films = new ArrayList<>();
    private List<String> species = new ArrayList<>();
    private List<String> vehicles = new ArrayList<>();
    private List<String> starships = new ArrayList<>();
    private String created;
    private String edited;
    private String url;
    //Listas

}
