package test.api.gps.services;

public class CepResponse {

    private String cidade;
    private String bairro;
    private String uf;
    private String logradouro;
    private String tipoLogradouro;
    private String resultado;

    public CepResponse() {
    }

    public CepResponse(String cidade, String bairro, String uf, String logradouro, String tipoLogradouro, String resultado) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.uf = uf;
        this.logradouro = logradouro;
        this.tipoLogradouro = tipoLogradouro;
        this.resultado = resultado;
    }

    // Getters e setters
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
