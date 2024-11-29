package br.com.bultzpc.model;

import java.sql.Date;

public class Funcionario {
    
    private String cpf, nome, cargo;
    private Date dataNasc;
    private float salario;

    // Construtor
    public Funcionario(String cpf, String nome, Date dataNasc, String cargo, float salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario() {
    }
    
    // ToString()
    @Override
    public String toString() {
        return "Funcionario{" + "cpf=" + cpf + ", nome=" + nome + ", dataNasc=" + dataNasc + ", cargo=" + cargo + ", salario=" + salario + '}';
    }
    @Override
    
    // Equals e HashCode
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Float.floatToIntBits(this.salario);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        return Float.floatToIntBits(this.salario) == Float.floatToIntBits(other.salario);
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
    
}
