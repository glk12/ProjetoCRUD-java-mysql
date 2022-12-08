/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author 20212101201
 */
public class Viagem {
    private String numViagem;
    private String reclamacao;

    
    private String ocorrencia;
    private String horario_chegada_fisc;
    private String horario_chegada_pf;
    private String horario_chegada_ga;
    private double quilometragem;

    public String getReclamacao() {
        return reclamacao;
    }

    public void setReclamacao(String reclamacao) {
        this.reclamacao = reclamacao;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getHorario_chegada_fisc() {
        return horario_chegada_fisc;
    }

    public void setHorario_chegada_fisc(String horario_chegada_fisc) {
        this.horario_chegada_fisc = horario_chegada_fisc;
    }

    public String getHorario_chegada_pf() {
        return horario_chegada_pf;
    }

    public void setHorario_chegada_pf(String horario_chegada_pf) {
        this.horario_chegada_pf = horario_chegada_pf;
    }

    public String getHorario_chegada_ga() {
        return horario_chegada_ga;
    }

    public void setHorario_chegada_ga(String horario_chegada_ga) {
        this.horario_chegada_ga = horario_chegada_ga;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }
    public String getNumViagem() {
        return numViagem;
    }

    public void setNumViagem(String numViagem) {
        this.numViagem = numViagem;
    }
    
}
