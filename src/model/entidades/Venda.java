package model.entidades;

import java.util.ArrayList;
import java.util.List;

public class Venda {

    private int id;
    private String horaVenda;
    private ArrayList<Produto> listaDeProdutos;
    private double valorDaVenda;
    Funcionario funcionario;
    private String status;

    // CONSTRUTOR
    public Venda(int id, String horaVenda, ArrayList<Produto> listaDeProdutos, double valorDaVenda,
                 Funcionario funcionario, String status) {
        this.id = id;
        this.horaVenda = horaVenda;
        this.listaDeProdutos = listaDeProdutos;
        this.valorDaVenda = valorDaVenda;
        this.funcionario = funcionario;
        this.status = status;
    }

    public Venda(ArrayList<Produto> listaDeProdutos, double valorDaVenda, Funcionario funcionario) {
        this.listaDeProdutos = listaDeProdutos;
        this.valorDaVenda = valorDaVenda;
        this.funcionario = funcionario;
        this.status = "Aberta";
    }

    // GETTERS AND SETTERS

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getHoraVenda() {
        return horaVenda;
    }
    public void setHoraVenda(String horaVenda) {
        this.horaVenda = horaVenda;
    }
    public ArrayList<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }
    public void setListaDeProdutos(ArrayList<Produto> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }
    public double getValorDaVenda() {
        return valorDaVenda;
    }
    public void setValorDaVenda(double valorDaVenda) {
        this.valorDaVenda = valorDaVenda;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
