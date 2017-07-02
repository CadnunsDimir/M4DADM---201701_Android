//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Tiago Silva do Nascimento
//******************************************************
package com.cadnunsdev.cadastrodepesssoasfisicasprojetofinal.core.entidades;

/**
 * Created by Tiago Silva on 26/06/2017.
 */
// OBJETIVO.......: Entidade básica do sistema
public class PessoaFisica {
    private int id;
    private String nome;
    private String cpf;
    private int idade;
    private String telefone;
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nome +" - CPF : " +formatCPF();
    }
    private String formatCPF(){
        if(cpf.length() == 11){
            String parte1 = cpf.substring(0,3);
            String parte2 = cpf.substring(3,6);
            String parte3 = cpf.substring(6,9);
            String dig = cpf.substring(9);
            String ponto = ".";
            String traco = "-";

            return  parte1+ponto+parte2+ponto+parte3+traco+dig;
        }
        return cpf;
    }
}
