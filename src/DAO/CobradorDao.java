package DAO;
import modelo.Cobrador;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CobradorDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public CobradorDao() {
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

    public int salvar(Cobrador cobrador) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO cobrador VALUES(?,?,?)");
            st.setString(1, cobrador.getMatricula_cob());
            st.setString(2, cobrador.getNome());
            st.setString(3, cobrador.getCpf());
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

    public Cobrador consultar(String matricula_cob) {
        try {
            Cobrador cobrador = new Cobrador();
            st = conn.prepareStatement("SELECT * FROM cobrador WHERE matricula_cob = ?");
            st.setString(1, matricula_cob);
            rs = st.executeQuery();
            // verifica se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário
                cobrador.setMatricula_cob(rs.getString("matricula_cob"));
                cobrador.setNome(rs.getString("nome"));
                cobrador.setCpf(rs.getString("cpf"));
                return cobrador;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Boolean verifica(String matricula_cob) {
        try {
            Cobrador cobrador = new Cobrador();
            st = conn.prepareStatement("SELECT * FROM cobrador WHERE matricula_cob = ?");
            st.setString(1, matricula_cob);
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
            String sql="SELECT * FROM cobrador";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            int count=0;
            String content="Cobradores:\n";
            while (rs.next()){
                String matricula = rs.getString("matricula_cob");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                String output = "#%s: %s - %s - %s";

                content+=String.format(output, ++count, matricula, nome, cpf);
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
            
            st = conn.prepareStatement("DELETE FROM cobrador WHERE matricula_cob = ?");
            st.setString(1,matricula);
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Cobrador deletado do sistema!");
            }else{
                JOptionPane.showMessageDialog(null,"Essa Cobrador não existe!");
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
            st = conn.prepareStatement("UPDATE cobrador SET nome = ?, cpf= ? WHERE matricula_cob = ?");
            st.setString(1,nome);
            st.setString(2,cpf);
            st.setString(3,matricula);
            
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Cobrador Atualizado!");
            }else{
                JOptionPane.showMessageDialog(null,"Esse Cobrador não existe!");
            }         
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
        

}



