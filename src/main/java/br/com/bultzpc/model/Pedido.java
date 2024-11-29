package br.com.bultzpc.model;

import java.sql.Date;
import java.util.Objects;

public class Pedido {
    
    private int codigo;
    private String cpfCliente;
    private Date dataPedido;
    private Float total;
    
    // Construtor
    public Pedido(){
        
    }
    
    public Pedido(int codigo, String cpfCliente, Date dataPedido, Float total) {
        this.codigo = codigo;
        this.cpfCliente = cpfCliente;
        this.dataPedido = dataPedido;
        this.total = total;
    }
    
    // ToString
    @Override
    public String toString() {
        return "Pedido{" + "cpfCliente=" + cpfCliente + '}';
    }
    
    // Equals e Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.total);
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
        final Pedido other = (Pedido) obj;
        return Objects.equals(this.total, other.total);
    }
    
    // Getters e Setters

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    
    
    
}
