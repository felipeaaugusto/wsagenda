package br.com.agenda.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.agenda.http.Contato;
import br.com.agenda.repository.ContatoRepository;
import br.com.agenda.repository.entity.ContatoEntity;

@Path("/service")
public class ServiceController {

	private final ContatoRepository repository = new ContatoRepository();

	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public Contato Cadastrar(Contato contato) {

		ContatoEntity entity = new ContatoEntity();

		try {
			entity.setNome(contato.getNome());
			entity.setTelefone(contato.getTelefone());
			entity.setDataCadastro(new Date());

			repository.Salvar(entity);

			if (entity != null)
				return new Contato(entity.getId(), entity.getNome(), entity.getTelefone(), entity.getDataCadastro());
		} catch (Exception e) {
			return null;
		}
		return contato;
	}

	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterar")
	public String Alterar(Contato contato) {

		ContatoEntity entity = new ContatoEntity();

		try {

			entity.setId(contato.getId());
			entity.setNome(contato.getNome());
			entity.setTelefone(contato.getTelefone());
			entity.setDataCadastro(contato.getDataCadastro());

			repository.Alterar(entity);

			return "Registro alterado com sucesso!";
		} catch (Exception e) {

			return "Erro ao alterar o registro " + e.getMessage();
		}
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todos")
	public List<Contato> Todos() {

		List<Contato> contatos = new ArrayList<Contato>();

		List<ContatoEntity> listaEntityContatos = repository.Todos();

		for (ContatoEntity entity : listaEntityContatos) {

			contatos.add(new Contato(entity.getId(), entity.getNome(), entity.getTelefone(), entity.getDataCadastro()));
		}

		return contatos;
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getContato/{id}")
	public Contato GetContato(@PathParam("id") Integer id) {

		ContatoEntity entity = repository.GetContato(id);

		if (entity != null)
			return new Contato(entity.getId(), entity.getNome(), entity.getTelefone(), entity.getDataCadastro());

		return null;
	}

	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{id}")
	public String Excluir(@PathParam("id") Integer id) {
		try {
			repository.Excluir(id);
			return "Registro excluido com sucesso!";
		} catch (Exception e) {
			return "Erro ao excluir o registro! " + e.getMessage();
		}
	}
}