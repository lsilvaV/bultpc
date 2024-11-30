package br.com.bultzpc.estado;

import br.com.bultzpc.model.Funcionario;
import java.util.ArrayList;


public class Estado {
    private static Estado instance;

    private ArrayList<Funcionario> funcionarios;

    private Estado() {
        funcionarios = new ArrayList<>();
    }

    public static Estado getInstance() {
        if (instance == null) {
            instance = new Estado();
        }
        return instance;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}