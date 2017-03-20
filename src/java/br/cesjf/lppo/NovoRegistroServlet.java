package br.cesjf.lppo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@WebServlet(name = "NovoRegistroServlet", urlPatterns = {"/novo.html"})
public class NovoRegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/novo-registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Visitante visitante = new Visitante();
        visitante.setNome(request.getParameter("nome"));
        visitante.setIdade(Integer.parseInt(request.getParameter("idade")));
        visitante.setEntrada(request.getParameter("entrada"));
    //    Date d = new Date();
     //   SimpleDateFormat df = new SimpleDateFormat("DD/MM/yyyy");
      //  try {
      //      d = df.parse("25/03/2010");
    //    } catch (ParseException ex) {
     //       ex.printStackTrace();
     //   }

        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate("INSERT INTO(nome, idade, entrada) VALUES('"
                    + visitante.getNome() + "','"
                    + visitante.getIdade() + "','"
                    + visitante.getEntrada() + "')");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("lista.html");
    }

}
