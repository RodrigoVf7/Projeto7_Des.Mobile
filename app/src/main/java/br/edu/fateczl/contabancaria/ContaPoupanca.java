package br.edu.fateczl.contabancaria;

/*
 *@author: RODRIGO VINICIUS FERRAZ DA SILVA
 *@RA: 1110482313043
 */

public class ContaPoupanca extends ContaBancaria {
    private int diaRendimento;

    public ContaPoupanca(String cliente, int numConta, float saldoInicial, int diaRendimento) {
        super(cliente, numConta, saldoInicial);
        this.diaRendimento = diaRendimento;
    }

    public void calcularNovoSaldo(float taxaRendimento) {
        float novoSaldo = getSaldo() * (1 + taxaRendimento / 100);
        depositar(novoSaldo - getSaldo());
    }
}

