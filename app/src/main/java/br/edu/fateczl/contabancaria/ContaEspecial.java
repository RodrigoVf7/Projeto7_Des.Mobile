package br.edu.fateczl.contabancaria;
/*
 *@author: RODRIGO VINICIUS FERRAZ DA SILVA
 *@RA: 1110482313043
 */

public class ContaEspecial extends ContaBancaria {
    private float limite;

    public ContaEspecial(String cliente, int numConta, float saldoInicial, float limite) {
        super(cliente, numConta, saldoInicial);
        this.limite = limite;
    }

    @Override
    public boolean sacar(float valor) {
        if (valor <= getSaldo() + limite) {
            depositar(-valor);
            return true;
        }
        return false; // Saque não permitido
    }
}

