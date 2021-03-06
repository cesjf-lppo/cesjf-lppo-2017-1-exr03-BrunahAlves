package br.cesjf.lppo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditaRegistro", urlPatterns = {"/edita.html"})
public class EditaRegistro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Visitante visitante = new Visitante();
        Long id = Long.parseLong(request.getParameter("id"));

        try {
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
                //   visitante.setEntrada(resultado.getTimestamp("entrada"));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("visitante", visitante);
        request.getRequestDispatcher("WEB-INF/edita-registro.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Visitante visitante = new Visitante();
        visitante.setId(Long.parseLong(request.getParameter("id")));
        visitante.setNome(request.getParameter("nome"));
        visitante.setIdade(Integer.parseInt(request.getParameter("idade")));
// pessoa.setStatus(Integer.parseInt(request.getParameter("status")));
        try {
            //Pegar os dados do banco
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate("UPDATE visitante SET nome='"
                    + visitante.getNome() + "',idade="
                    + visitante.getIdade() + "WHERE id="
                    + visitante.getId());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("lista.html"); //redirecionar para o servlet ListaRegistrosServlet

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
