package br.com.agenda.main;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import java.sql.SQLException;
import java.util.Date;

public class Application {
    public static void main(String[] args) throws SQLException {
        //SALVAR
        Contato contato= new Contato();
        contato.setId(1);
        contato.setNome("Bruno");
        contato.setIdade(30);
        contato.setDataCadastro(new Date());

        ContatoDAO contatoDAO = new ContatoDAO();
        //contatoDAO.save(contato);

        //UPDATE
        Contato c1 = new Contato();
        c1.setId(1);
        c1.setNome("Isabella");
        c1.setIdade(25);
        c1.setDataCadastro(new Date());

        //contatoDAO.update(c1);

        //DELETEBYID
        contatoDAO.deleteById(1);

        //LISTALL
        for (Contato c : contatoDAO.getContatos()){
            System.out.println("Contato: " + c.getNome());
        }

    }
}
