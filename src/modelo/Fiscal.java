/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author 20212101201
 */
public class Fiscal extends Pessoa{
    String categoria;
    String matricula_fisc;

    public String getMatricula_fisc() {
        return matricula_fisc;
    }

    public void setMatricula_fisc(String matricula_fisc) {
        this.matricula_fisc = matricula_fisc;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
