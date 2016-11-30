package fr.esstin.baresstin.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.esstin.baresstin.Extra;
import fr.esstin.baresstin.dao.ExtraDao;
import fr.esstin.baresstin.dto.ExtraDto;

@Stateless
public class ExtraService implements ExtraServiceLocal{

	@Inject
	ExtraDao extraDao;
	
	@Override
	public void add(ExtraDto ed) {
		Extra e = new Extra();
		e.setEnable(true);
		fromDto(e,ed);
		extraDao.save(e);
		
	}

	@Override
	public void update(ExtraDto ed) {
		Extra e = extraDao.findById(ed.getId());
		ed.setName(e.getName());
		fromDto(e, ed);
		extraDao.save(e);
		
	}

	@Override
	public void delete(long id) {
		extraDao.delete(id);
		
	}

	@Override
	public List<ExtraDto> list() {
		return fromEntityList(extraDao.list());
	}

	@Override
	public ExtraDto findById(long id) {
		Extra e = extraDao.findById(id);
		if(e!=null)
			return fromEntity(e);
		else
			return null;
	}
	
	public ExtraDto fromEntity(Extra e)
	{
		ExtraDto ed = new ExtraDto();
		ed.setId(e.getId());
		ed.setName(e.getName());
		ed.setPrice(e.getPrice());
		return ed;
		
	}
	
	public void fromDto(Extra e ,ExtraDto ed)
	{
		e.setName(ed.getName());
		e.setPrice(ed.getPrice());
	}
	
	public List<ExtraDto> fromEntityList(List<Extra> le)
	{
		List<ExtraDto> led = new ArrayList<ExtraDto>();
		for(Extra e:le)
		{
			led.add(fromEntity(e));
		}
		return led;
	}

}
