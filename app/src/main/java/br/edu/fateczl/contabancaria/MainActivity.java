package br.edu.fateczl.contabancaria;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/*
 * Nome: [Seu Nome]
 * RA: [Seu RA]
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editCliente, editNumConta, editSaldoInicial, editValorOperacao;
    private TextView txtSaldoAtual;
    private RadioGroup radioGroupContaTipo;
    private ContaBancaria conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando componentes
        editCliente = findViewById(R.id.editCliente);
        editNumConta = findViewById(R.id.editNumConta);
        editSaldoInicial = findViewById(R.id.editSaldoInicial);
        editValorOperacao = findViewById(R.id.editValorOperacao);
        txtSaldoAtual = findViewById(R.id.txtSaldoAtual);
        radioGroupContaTipo = findViewById(R.id.radioGroupContaTipo);

        Button btnCriarConta = findViewById(R.id.btnCriarConta);
        Button btnSacar = findViewById(R.id.btnSacar);
        Button btnDepositar = findViewById(R.id.btnDepositar);
        Button btnCalcularSaldoPoupanca = findViewById(R.id.btnCalcularSaldoPoupanca);

        // Listener para criar conta
        btnCriarConta.setOnClickListener(v -> criarConta());

        // Listener para saque
        btnSacar.setOnClickListener(v -> {
            if (conta != null) {
                float valor = Float.parseFloat(editValorOperacao.getText().toString());
                if (!conta.sacar(valor)) {
                    Toast.makeText(this, "Saque não permitido", Toast.LENGTH_SHORT).show();
                }
                atualizarSaldo();
            }
        });

        // Listener para depósito
        btnDepositar.setOnClickListener(v -> {
            if (conta != null) {
                float valor = Float.parseFloat(editValorOperacao.getText().toString());
                conta.depositar(valor);
                atualizarSaldo();
            }
        });

        // Listener para calcular saldo da poupança
        btnCalcularSaldoPoupanca.setOnClickListener(v -> {
            if (conta instanceof ContaPoupanca) {
                float taxaRendimento = 0.5f; // ou qualquer valor que você queira
                ((ContaPoupanca) conta).calcularNovoSaldo(taxaRendimento);
                atualizarSaldo();
            } else {
                Toast.makeText(this, "Conta não é poupança", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void criarConta() {
        String cliente = editCliente.getText().toString();
        int numConta = Integer.parseInt(editNumConta.getText().toString());
        float saldoInicial = Float.parseFloat(editSaldoInicial.getText().toString());

        if (((RadioButton) findViewById(R.id.radioContaPoupanca)).isChecked()) {
            conta = new ContaPoupanca(cliente, numConta, saldoInicial, 1); // Exemplo dia de rendimento
        } else if (((RadioButton) findViewById(R.id.radioContaEspecial)).isChecked()) {
            conta = new ContaEspecial(cliente, numConta, saldoInicial, 1000); // Exemplo de limite
        }

        atualizarSaldo();
    }

    private void atualizarSaldo() {
        if (conta != null) {
            txtSaldoAtual.setText("Saldo Atual: " + conta.getSaldo());
        }
    }
}
