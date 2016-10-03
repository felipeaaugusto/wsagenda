package br.com.agenda.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.agenda.repository.entity.ContatoEntity;

public class ContatoRepository {

	private final EntityManagerFactory entityManagerFactory;

	private final EntityManager entityManager;

	public ContatoRepository() {

		/*
		 * CRIANDO O NOSSO EntityManagerFactory COM AS PORPRIEDADOS DO ARQUIVO
		 * persistence.xml
		 */
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_agenda");

		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void Salvar(ContatoEntity contatoEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(contatoEntity);
		this.entityManager.getTransaction().commit();
	}

	public void Alterar(ContatoEntity contatoEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(contatoEntity);
		this.entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<ContatoEntity> Todos() {
		return this.entityManager.createQuery("SELECT a FROM ContatoEntity a ORDER BY a.nome").getResultList();
	}

	public ContatoEntity GetContato(Integer id) {
		return this.entityManager.find(ContatoEntity.class, id);
	}

	public void Excluir(Integer id) {
		ContatoEntity agenda = this.GetContato(id);

		this.entityManager.getTransaction().begin();
		this.entityManager.remove(agenda);
		this.entityManager.getTransaction().commit();
	}
}