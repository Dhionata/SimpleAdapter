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

public class Result {
    public String name;
    public String height;
    public String mass;
    public String hairColor;
    public String skinColor;
    public String eyeColor;
    public String birthYear;
    public String gender;
    public String homeworld;
    public String created;
    public String edited;
    public String url;
    //List
    public List<String> films = new ArrayList<>();
    public List<String> species = new ArrayList<>();
    public List<String> vehicles = new ArrayList<>();
    public List<String> starships = new ArrayList<>();
}
