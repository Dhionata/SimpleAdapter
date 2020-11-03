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
//esse é oq estaria na api -> 15 kkkk mas acho q ele n tá pegando no prof.
/*{
	"name": "Luke Skywalker",
	"height": "172",
	"mass": "77",
	"hair_color": "blond",
	"skin_color": "fair",
	"eye_color": "blue",
	"birth_year": "19BBY",
	"gender": "male",
	"homeworld": "http://swapi.dev/api/planets/1/", esse vai retornar um
	outro objeto... n é n prof. ele vai retornar as informações desse
	endereço, é um objeto do tipo planeta...
	"films": [
		"http://swapi.dev/api/films/1/",
		"http://swapi.dev/api/films/2/",
		"http://swapi.dev/api/films/3/",
		"http://swapi.dev/api/films/6/"
	],
	"species": [],
	"vehicles": [
		"http://swapi.dev/api/vehicles/14/",
		"http://swapi.dev/api/vehicles/30/"
	],
	"starships": [
		"http://swapi.dev/api/starships/12/",
		"http://swapi.dev/api/starships/22/"
	],
	"created": "2014-12-09T13:50:51.644000Z",
	"edited": "2014-12-20T21:17:56.891000Z",
	"url": "http://swapi.dev/api/people/1/"
}*/