package jason.emeric.app_bar.service;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import jason.emeric.app_bar.repository.ExtraEntity;
import jason.emeric.app_bar.repository.IExtraRepository;
import jason.emeric.app_bar.service.model.ExtraDto;



@Stateless
public class ExtraService implements IExtraService{

	@Inject
	IExtraRepository extraDao;

	@Override
	public void add(ExtraDto ed) {
		ExtraEntity e = new ExtraEntity();
		e.setEnable(true);
		fromDto(e,ed);
		extraDao.save(e);

	}

	@Override
	public void update(ExtraDto ed) {
		ExtraEntity e = extraDao.findById(ed.getId());
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
		ExtraEntity e = extraDao.findById(id);
		if(e!=null)
			return fromEntity(e);
		else
			return null;
	}

	public ExtraDto fromEntity(ExtraEntity e)
	{
		ExtraDto ed = new ExtraDto();
		ed.setId(e.getId());
		ed.setName(e.getName());
		ed.setPrice(e.getPrice());
		return ed;

	}

	public void fromDto(ExtraEntity e ,ExtraDto ed)
	{
		e.setName(ed.getName());
		e.setPrice(ed.getPrice());
	}

	public List<ExtraDto> fromEntityList(List<ExtraEntity> le)
	{
		List<ExtraDto> led = new ArrayList<ExtraDto>();
		for(ExtraEntity e:le)
		{
			led.add(fromEntity(e));
		}
		return led;
	}





}
