package br.com.example.meuprimeiroexemplo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.debug.DebugActivity;

public class HomeActivity extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void exibir(View view) {
        //
        int opcao = view.getId();
        Intent intent;

        switch (opcao) {
            case R.id.btnAddres:
                intent = new Intent(getApplicationContext(), AddresActivity.class);
                startActivity(intent);
                break;
            case R.id.btnUse:
                intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPessoa:
                intent = new Intent(getApplicationContext(), PessoaActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPost:
                intent = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(intent);
                break;
            case R.id.btnComments:
                intent = new Intent(getApplicationContext(), CommentsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPeople:
                intent = new Intent(getApplicationContext(), PeopleActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Opção inválida.", Toast.LENGTH_LONG).show();
                break;
        }
    }
}