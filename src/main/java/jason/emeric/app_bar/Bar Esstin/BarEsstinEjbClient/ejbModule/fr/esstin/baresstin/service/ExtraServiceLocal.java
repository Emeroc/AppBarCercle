package fr.esstin.baresstin.service;

import java.util.List;

import javax.ejb.Local;

import fr.esstin.baresstin.dto.ExtraDto;

@Local
public interface ExtraServiceLocal {
	
	public void add(ExtraDto e);
	public void update(ExtraDto e);
	public void delete(long id);
	public List<ExtraDto> list();
	public ExtraDto findById(long id);

}
