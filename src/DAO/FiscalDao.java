package DAO;
import modelo.Fiscal;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FiscalDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public FiscalDao() {
    }

    public boolean conectar() {
        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            //conn = DriverManager.getConnection("jdbc:sqlserver://localhost;database=banco;integratedSecurity=true;");  
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uva", "root", "");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }

    public int salvar(Fiscal fiscal) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO fiscal VALUES (?,?,?,?)");
            st.setString(1, fiscal.getCategoria());          
            st.setString(2, fiscal.getNome());
            st.setString(3, fiscal.getCpf());
            st.setString(4, fiscal.getMatricula_fisc());
            
            status = st.executeUpdate();
            return status; //retorna 1
        } catch (SQLException ex) {
            return ex.getErrorCode();
            //1062 tentativa de inserir uma matrícula já cadastrada.
        }
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {

        }
    }

    public Fiscal consultar(String matricula_fisc) {
        try {
            Fiscal fiscal = new Fiscal();
            st = conn.prepareStatement("SELECT * FROM fiscal WHERE matricula_fisc = ?");
            st.setString(1, matricula_fisc);
            rs = st.executeQuery();
            // verifica se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário
                fiscal.setMatricula_fisc(rs.getString("matricula_fisc"));
                fiscal.setNome(rs.getString("nome"));
                fiscal.setCpf(rs.getString("cpf"));
                
                return fiscal;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Boolean verifica(String matricula_fisc) {
        try {
            Fiscal fiscal = new Fiscal();
            st = conn.prepareStatement("SELECT * FROM fiscal WHERE matricula_fisc = ?");
            st.setString(1, matricula_fisc);
            rs = st.executeQuery();
            // verifica se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário
                
                return true;
                
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return null;
        }
    }
    public Boolean listar() {
        try {
            String sql="SELECT * FROM fiscal";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            int count=0;
            String content="Fiscais:\n";
            while (rs.next()){
                String matricula = rs.getString("matricula_fisc");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String categoria = rs.getString("categoria");

                String output = "#%s: %s - %s - %s - %s";

                content+=String.format(output, ++count, matricula, nome, cpf,categoria);
                content+="\n\n";
            }
            JOptionPane.showMessageDialog(null,content);
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
    public Boolean deletar(String matricula) {
        try {
            
            st = conn.prepareStatement("DELETE FROM fiscal WHERE matricula_fisc = ?");
            st.setString(1,matricula);
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Fiscal deletado do sistema!");
            }else{
                JOptionPane.showMessageDialog(null,"Essa Fiscal não existe!");
            }         
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
    public Boolean atualizar(String matricula) {
        try {
            
            String nome=JOptionPane.showInputDialog("Novo nome:");
            String cpf=JOptionPane.showInputDialog("Novo CPF:");
            String cat=JOptionPane.showInputDialog("Nova Categoria:");
            st = conn.prepareStatement("UPDATE fiscal SET nome = ?, cpf= ?,categoria= ? WHERE matricula_fisc = ?");
            st.setString(1,nome);
            st.setString(2,cpf);
            st.setString(3,cat);
            st.setString(4,matricula);
            
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Fiscal Atualizado!");
            }else{
                JOptionPane.showMessageDialog(null,"Esse Fiscal não existe!");
            }         
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
    

}