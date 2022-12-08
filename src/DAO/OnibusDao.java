package DAO;
import modelo.Onibus;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class OnibusDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public OnibusDao() {
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

    public int salvar(Onibus onibus) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO onibus VALUES(?,?)");
            st.setInt(1, onibus.getNumero());
            st.setInt(2, onibus.getNumroleta());
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

    public Onibus consultar(int numero) {
        try {
            Onibus onibus = new Onibus();
            st = conn.prepareStatement("SELECT * FROM onibus WHERE numero = ?");
            st.setInt(1, numero);
            rs = st.executeQuery();
            // verifica se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário
                onibus.setNumero(rs.getInt("numero"));
                onibus.setNumroleta(rs.getInt("numroleta"));
                
                return onibus;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }
    public Boolean verifica(int numero) {
        try {
            Onibus onibus = new Onibus();
            st = conn.prepareStatement("SELECT * FROM onibus WHERE numero = ?");
            st.setInt(1, numero);
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
            String sql="SELECT * FROM onibus";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            int count=0;
            String content="Onibus:\n";
            while (rs.next()){
                String num = rs.getString("numero");
                String numroleta = rs.getString("numroleta");

                String output = "#%s: %s - %s";

                content+=String.format(output, ++count, num, numroleta);
                content+="\n\n";
            }
            JOptionPane.showMessageDialog(null,content);
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    } 
        public Boolean deletar(String numero) {
        try {
            
            st = conn.prepareStatement("DELETE FROM onibus WHERE numero = ?");
            st.setString(1,numero);
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Onibus deletado do sistema!");
            }else{
                JOptionPane.showMessageDialog(null,"Essa Onibus não existe!");
            }         
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
        public Boolean atualizar(String numero) {
        try {
                       
            String numroleta=JOptionPane.showInputDialog("Novo Número da Roleta");
            st = conn.prepareStatement("UPDATE onibus SET numroleta= ? WHERE numero = ?");
            st.setString(1,numroleta);
            st.setString(2,numero);
            
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Onibus Atualizado!");
            }else{
                JOptionPane.showMessageDialog(null,"Esse Onibus não existe!");
            }         
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }

}
