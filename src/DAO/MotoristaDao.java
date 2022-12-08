package DAO;
import modelo.Motorista;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MotoristaDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public MotoristaDao() {
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

    public int salvar(Motorista motorista) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO motorista VALUES(?,?,?)");
            st.setString(1, motorista.getMatricula_mot());
            st.setString(2, motorista.getNome());
            st.setString(3, motorista.getCpf());
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

    public Motorista consultar(String matricula_mot) {
        try {
            Motorista motorista = new Motorista();
            st = conn.prepareStatement("SELECT * FROM motorista WHERE matricula_mot = ?");
            st.setString(1, matricula_mot);
            rs = st.executeQuery();
            // verifica se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário
                motorista.setMatricula_mot(rs.getString("matricula_mot"));
                motorista.setNome(rs.getString("nome"));
                motorista.setCpf(rs.getString("cpf"));
                
                return motorista;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Boolean verifica(String matricula_mot) {
        try {
            Motorista motorista = new Motorista();
            st = conn.prepareStatement("SELECT * FROM motorista WHERE matricula_mot = ?");
            st.setString(1, matricula_mot);
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
    }public Boolean listar() {
        try {
            String sql="SELECT * FROM motorista";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            int count=0;
            String content="Motoristas:\n";
            while (rs.next()){
                String matricula = rs.getString("matricula_mot");
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
            
            st = conn.prepareStatement("DELETE FROM motorista WHERE matricula_mot = ?");
            st.setString(1,matricula);
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Motorista deletado do sistema!");
            }else{
                JOptionPane.showMessageDialog(null,"Essa Motorista não existe!");
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
            st = conn.prepareStatement("UPDATE motorista SET nome = ?, cpf= ? WHERE matricula_mot = ?");
            st.setString(1,nome);
            st.setString(2,cpf);
            st.setString(3,matricula);
            
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Motorista Atualizado!");
            }else{
                JOptionPane.showMessageDialog(null,"Esse Motorista não existe!");
            }         
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
}
