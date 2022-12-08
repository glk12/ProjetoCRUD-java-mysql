package DAO;
import modelo.Viagem;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ViagemDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public ViagemDao() {
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

    public int salvar(Viagem viagem) {
        int status;
        try {
            
            st = conn.prepareStatement("INSERT INTO viagem VALUES(?,?,?,?,?,?,?)");
            
            st.setString(1, viagem.getReclamacao());
            st.setString(2, viagem.getOcorrencia());
            st.setString(3, viagem.getHorario_chegada_fisc());
            st.setString(4, viagem.getHorario_chegada_pf());
            st.setString(5, viagem.getHorario_chegada_ga());
            st.setDouble(6, viagem.getQuilometragem());
            st.setString(7, viagem.getNumViagem());
         
            status = st.executeUpdate();
            return status; //retorna 1
        } catch (SQLException ex) {
            return ex.getErrorCode();
            //1062 tentativa de inserir uma matrícula já cadastrada.
        }
    }
        public Viagem consultar(String numViagem) {
        try {
            Viagem viagem = new Viagem();
            st = conn.prepareStatement("SELECT * FROM viagem WHERE numViagem = ?");
            st.setString(1, numViagem);
            rs = st.executeQuery();
            // verifica se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário
                viagem.setReclamacao(rs.getString("reclamacao"));
                viagem.setOcorrencia(rs.getString("ocorrencia"));
                viagem.setHorario_chegada_fisc(rs.getString("horario_chegada_fisc"));
                viagem.setHorario_chegada_pf(rs.getString("horario_chegada_pf"));
                viagem.setHorario_chegada_ga(rs.getString("horario_chegada_ga"));
                viagem.setQuilometragem(Double.parseDouble(rs.getString("quilometragem")));
                viagem.setNumViagem(rs.getString("numViagem"));
                return viagem;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {

        }
    }
    
    public Boolean verifica(String matricula_cob) {
        try {
            
            st = conn.prepareStatement("SELECT * FROM viagem WHERE numViagem = ?");
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
            String sql="SELECT * FROM viagem";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            int count=0;
            String content="Viagens:\n";
            while (rs.next()){
                String rec=rs.getString("reclamacao");
                String oco=rs.getString("ocorrencia");
                String fisc=rs.getString("horario_chegada_fisc");
                String pf=rs.getString("horario_chegada_pf");
                String ga=rs.getString("horario_chegada_ga");
                Double qui=Double.parseDouble(rs.getString("quilometragem"));
                String num=rs.getString("numViagem");
                

                String output = "#%s: %s - %s - %s - %s - %s - %s - %s";

                content+=String.format(output, ++count, rec, oco, fisc,pf,ga,qui,num);
                content+="\n\n";
            }
            JOptionPane.showMessageDialog(null,content);
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    } 

    public Boolean deletar(String numviagem) {
        try {
            
            st = conn.prepareStatement("DELETE FROM viagem WHERE numViagem = ?");
            st.setString(1,numviagem);
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Viagem deletada do sistema!");
            }else{
                JOptionPane.showMessageDialog(null,"Essa viagem não existe!");
            }         
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
    public Boolean atualizar(String numviagem) {
        try {
            
            String rec=JOptionPane.showInputDialog("Nova reclamação::");
            String oco=JOptionPane.showInputDialog("Nova ocorrência:");
            String fisc=JOptionPane.showInputDialog("Novo horário de chegada no fiscal:");
            String pf=JOptionPane.showInputDialog("Novo horário de chegada no ponto final:");
            String ga=JOptionPane.showInputDialog("Novo horário de chegada na garagem:");
            Double quilo=Double.parseDouble(JOptionPane.showInputDialog("Nova quilometragem:"));
            
            st = conn.prepareStatement("UPDATE viagem SET reclamacao = ?, ocorrencia= ?, horario_chegada_fisc= ?,horario_chegada_pf= ?,horario_chegada_ga= ?, quilometragem = ? WHERE numViagem = ?");
            st.setString(1,rec);
            st.setString(2,oco);
            st.setString(3,fisc);
            st.setString(4,pf);
            st.setString(5,ga);
            st.setDouble(6,quilo);
            st.setString(7,numviagem);
            
            
            int c=st.executeUpdate();
            if(c>0){
                JOptionPane.showMessageDialog(null,"Viagem Atualizada!");
            }else{
                JOptionPane.showMessageDialog(null,"Essa Viagem não existe!");
            }         
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CobradorDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
}