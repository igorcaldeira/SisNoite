package conectaBanco;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Curso {

	// Atributos
	
	private int id;
	
	private String nome;
	
	private int duracao;
	
	private int n_alunos;

	// Encapsulamento
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getN_alunos() {
		return n_alunos;
	}

	public void setN_alunos(int n_alunos) {
		this.n_alunos = n_alunos;
	}
		
	// Método

	
	/**
	 * Seleciona os cursos no banco de dados SisNoite.
	 * 
	 * @throws Exception Erros e exceções do SQL e afins.
	 */
	public ArrayList<Curso> selecionar(String filtroNome) throws Exception {
		
		ResultSet rs = null;
		
		try
		{
			//Cria objeto de Acesso ao banco
			AcessoBD acessaBanco = new AcessoBD();
			
			String query = "SELECT * FROM Curso";
			
			if(!filtroNome.equals(""))
				 query += " WHERE nome LIKE '%"+filtroNome+"%'";
					
			//Executou a query (que esta sendo passada por parametro)
			rs = acessaBanco.aplicaQueryComRetorno(query);
		
		}
		//Faz o tratamento da exceção que pode ser retornada
		catch(Exception e)
		{
			throw e;
		}
		
		//Criou uma lista de cursos
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		//Preencheu a lista de cursos, percorrendo cada linha
		while (rs.next()) {
			
			Curso c = new Curso();

			//textBoxNome = rs.getString(2);
			
			c.id = rs.getInt(1);
			c.nome = rs.getString(2);
			c.n_alunos = rs.getInt(3);
			c.duracao = rs.getInt(4);
			
			//Adiciona cada item a lista
			cursos.add(c);
		}
		
		//retornar a lista de cursos
		return cursos;
	}
	
	/**
	 * Cadastra um novo curso no banco de dados SisNoite.
	 * 
	 * @throws Exception Erros e exceções do SQL e afins.
	 */
	public void cadastrar() throws Exception {
		
		try
		{
			//Cria objeto de Acesso ao banco
			AcessoBD acessaBanco = new AcessoBD();
			
			//Executou a query (que esta sendo passada por parametro)
			acessaBanco.aplicaQuerySemRetorno("INSERT INTO Curso VALUES ("+id+", '"+nome+"', "+duracao+", "+n_alunos+");");
		
		}
		//Faz o tratamento da exceção que pode ser retornada
		catch(Exception e)
		{
			throw e;
		}
		
	}

	/**
	 * Delete o curso desejado no banco de dados SisNoite.
	 * 
	 * @throws Exception Erros e exceções do SQL e afins.
	 */
	public void deletar(int chave) throws Exception {
		
		try
		{
			//Cria objeto de Acesso ao banco
			AcessoBD acessaBanco = new AcessoBD();
			
			//Executou a query (que esta sendo passada por parametro)
			acessaBanco.aplicaQuerySemRetorno("DELETE FROM Curso WHERE Codigo = "+chave);
		
		}
		//Faz o tratamento da exceção que pode ser retornada
		catch(Exception e)
		{
			throw e;
		}
		
	}

	/**
	 * Altera um curso no banco de dados SisNoite.
	 * 
	 * @throws Exception Erros e exceções do SQL e afins.
	 */
	public void alterar() throws Exception {
		
		try
		{
			//Cria objeto de Acesso ao banco
			AcessoBD acessaBanco = new AcessoBD();
			
			//Executou a query (que esta sendo passada por parametro)
			acessaBanco.aplicaQuerySemRetorno("UPDATE Curso SET nome = '"+nome+"', duracao = "+duracao+", n_alunos = "+n_alunos+" WHERE codigo = "+id+";");
		
		}
		//Faz o tratamento da exceção que pode ser retornada
		catch(Exception e)
		{
			throw e;
		}
		
	}
}
