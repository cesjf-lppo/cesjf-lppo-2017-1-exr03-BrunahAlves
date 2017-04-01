package br.cesjf.lppo;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditaFormRegistroServlet", urlPatterns = {"/editaform.html"})
public class EditaFormRegistroServlet extends HttpServlet {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Visitante visitante = new Visitante();
        Long id = Long.parseLong(request.getParameter("id"));

        try {
            Calendar c = Calendar.getInstance();

            //Pegar os dados do banco
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM visitante WHERE id=" + id);
        
            if (resultado.next()) {
                visitante = new Visitante();
                visitante.setId(resultado.getLong("id"));
                visitante.setNome(resultado.getString("nome"));
                visitante.setIdade(resultado.getInt("idade"));
                visitante.setEntrada(resultado.getTimestamp("entrada"));
                visitante.setSaida(resultado.getTimestamp("saida"));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("visitante", visitante);
        request.getRequestDispatcher("WEB-INF/editaform-registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Visitante visitante = new Visitante();

        try {
            
            visitante.setId(Long.parseLong(request.getParameter("id")));
            visitante.setNome(request.getParameter("nome"));
            visitante.setIdade(Integer.parseInt(request.getParameter("idade")));
           visitante.setEntrada(df.parse(request.getParameter("entrada")));
           visitante.setSaida(df.parse(request.getParameter("saida")));
  //          visitante.setSaida(df.parse(request.getParameter("saida")));
            //Pegar os dados do banco
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            String Query = "UPDATE visitante SET nome='"
                    + visitante.getNome() + "',idade="
                    + visitante.getIdade() + ",entrada='"
                    + df.format(visitante.getEntrada()) + "', saida='"
                    + df.format(visitante.getSaida()) + "' WHERE id="
                    + visitante.getId();
            System.out.println(Query);
            operacao.executeUpdate(Query);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EditaFormRegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("lista.html"); //redirecionar para o servlet ListaRegistrosServlet

    }

}
