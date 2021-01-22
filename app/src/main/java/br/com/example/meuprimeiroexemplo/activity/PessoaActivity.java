package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.debug.DebugActivity;

public class PessoaActivity extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
    }

    public void exibir(View view) {
        // Entrada
        EditText textNome = findViewById(R.id.textNome);
        EditText textSobreNome = findViewById(R.id.textSobreNome);
        EditText textEmail = findViewById(R.id.textEmail);
        EditText textPhone = findViewById(R.id.textPhone);
        // Processamento
        String nome, sobrenome, email, telefone;
        nome = textNome.getText().toString();
        sobrenome = textSobreNome.getText().toString();
        email = textEmail.getText().toString();
        telefone = textPhone.getText().toString();
        //Saída
        String dados;
        dados = String.format("Os valores informados foram: \n%s\n%s\n%s\n%s", nome, sobrenome, email, telefone);

        //Exibir dados para o usuário

        Toast.makeText(getApplication(), dados, Toast.LENGTH_LONG).show();
    }
}