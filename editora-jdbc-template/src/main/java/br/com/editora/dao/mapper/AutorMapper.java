package br.com.editora.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.editora.dao.EditoraDao;
import br.com.editora.entity.Autor;
import br.com.editora.entity.Editora;

public class AutorMapper implements RowMapper<Autor>{

	private EditoraDao editoraDao;
	
	public AutorMapper() {
		super();
	}
	
	public AutorMapper(EditoraDao editoraDao) {
		this.editoraDao = editoraDao;
	}
	
	@Override
	public Autor mapRow(ResultSet rs, int rowNum) throws SQLException {

		Autor autor = new Autor();
		autor.setId(rs.getInt("ID_AUTOR"));
		autor.setNome(rs.getString("NOME"));
		autor.setEmail(rs.getString("EMAIL"));
		
		Integer idEditora = rs.getInt("ID_EDITORA");
		
		Editora editora = editoraDao.findById(idEditora);
		
		autor.setEditora(editora);
		
		return autor;
	}

	
	public class AutorWithEditoraMapper implements RowMapper<Autor> {

		@Override
		public Autor mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Autor autor = new Autor();
			autor.setId(rs.getInt("ID_AUTOR"));
			autor.setNome(rs.getString("NOME"));
			autor.setEmail(rs.getString("EMAIL"));
			
			Editora editora = new Editora();
			editora.setId(rs.getInt("ED_ID_EDITORA"));
			editora.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
			editora.setCidade(rs.getString("CIDADE"));
			editora.setEmail(rs.getString("ED_EMAIL"));
			
			autor.setEditora(editora);
			
			return autor;
		}
		
	}
	
}
