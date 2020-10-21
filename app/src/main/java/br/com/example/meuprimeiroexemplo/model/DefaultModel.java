package br.com.example.meuprimeiroexemplo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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
public class DefaultModel implements Serializable {

    private Integer count;
    private String next;
    private String previous;
    @SerializedName("results")
    //foi eu n em..
    private List results = new ArrayList<People>();
      /*  "count":82,
                "next":"http://swapi.dev/api/people/?page=2",
                "previous":null,*/
}