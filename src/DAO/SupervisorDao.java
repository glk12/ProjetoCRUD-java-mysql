package DAO;
import modelo.Supervisor;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SupervisorDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public SupervisorDao() {
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

    public int salvar(Supervisor supervisor) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO supervisor VALUES(?,?,?)");
            st.setString(1, supervisor.getMatricula_sup());
            st.setString(2, supervisor.getNome());
            st.setString(3, supervisor.getCpf());
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

    public Supervisor consultar(String matricula_sup) {
        try {
            Supervisor supervisor = new Supervisor();
            st = conn.prepareStatement("SELECT * FROM supervisor WHERE matricula_sup = ?");
            st.setString(1, matricula_sup);
            rs = st.executeQuery();
            // verifica se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário
                supervisor.setMatricula_sup(rs.getString("matricula_sup"));
                supervisor.setNome(rs.getString("nome"));
                supervisor.setCpf(rs.getString("cpf"));
                return supervisor;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }
        
    public Boolean verifica(String matricula_sup) {
        try {
            Supervisor supervisor = new Supervisor();
            st = conn.prepareStatement("SELECT * FROM supervisor WHERE matricula_sup = ?");
            st.setString(1, matricula_sup);
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
            String sql="SELECT * FROM supervisor";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            int count=0;
            String content="Supervisores:\n";
            while (rs.next()){
                String matricula = rs.getString("matricula_sup");
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
            
            st = conn.prepareStatement("DELETE FROM supervisor WHERE matricula_sup = ?");
            st.setString(1,matricula);
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Supervisor deletado do sistema!");
            }else{
                JOptionPane.showMessageDialog(null,"Essa Supervisor não existe!");
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
            st = conn.prepareStatement("UPDATE supervisor SET nome = ?, cpf= ? WHERE matricula_sup = ?");
            st.setString(1,nome);
            st.setString(2,cpf);
            st.setString(3,matricula);
            
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Supervisor Atualizado!");
            }else{
                JOptionPane.showMessageDialog(null,"Esse Supervisor não existe!");
            }         
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
}
