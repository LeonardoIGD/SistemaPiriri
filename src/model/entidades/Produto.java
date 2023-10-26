package model.entidades;

public class Produto {
    private String codigo;
    private String descricao;
    private double quantidade;
    private double valorDeCompra;
    private double valorDeVenda;
    private String peso;
    private String vencimento;
    private String detalhes;

    //CONSTRUTOR
    public Produto() {
    }

    public Produto(String codigo, String descricao, double quantidade, double valorDeCompra, double valorDeVenda,
                   String peso, String vencimento, String detalhes) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorDeCompra = valorDeCompra;
        this.valorDeVenda = valorDeVenda;
        this.peso = peso;
        this.vencimento = vencimento;
        this.detalhes = detalhes;
    }

    //GETTERS AND SETTERS
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    public double getValorDeCompra() {
        return valorDeCompra;
    }
    public void setValorDeCompra(double valorDeCompra) {
        this.valorDeCompra = valorDeCompra;
    }
    public double getValorDeVenda() {
        return valorDeVenda;
    }
    public void setValorDeVenda(double valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }
    public String getPeso() {
        return peso;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }
    public String getVencimento() {
        return vencimento;
    }
    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }
    public String getDetalhes() {
        return detalhes;
    }
    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
