package org.serratec.java2backend.projeto02.service;

import java.util.ArrayList;
import java.util.List;
import org.serratec.java2backend.projeto02.exception.TodoException;
import org.serratec.java2backend.projeto02.model.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	List<Todo> lista = new ArrayList<>();
	
	public void adicionar(Todo todo) {
		lista.add(todo);
	}
	
	public List<Todo> listaTodo(){
		return this.lista;
	}
	
	public Todo buscarId(Integer idTodo) throws TodoException {
		Todo todoDesejado = new Todo();
		
		for(Todo todo : lista) {
			if(todo.getId().equals(idTodo)) {
				todoDesejado = todo;
			}
		}
		
		if(todoDesejado.getId() == null) {
			throw new TodoException(idTodo);
		}
		
		return todoDesejado;
	}
	
	public void atualizar(Integer posicaoLista, Todo todoDaApi) {
		Todo todoDaListaSalva = new Todo();
		todoDaListaSalva = lista.get(posicaoLista);
		
		todoDaListaSalva.setId(todoDaApi.getId());
		todoDaListaSalva.setTitulo(todoDaApi.getTitulo());
		todoDaListaSalva.setDescricao(todoDaApi.getDescricao());		
	}
	
	public void deletar (int posicaoLista) {
		lista.remove(posicaoLista);
	}
	
}