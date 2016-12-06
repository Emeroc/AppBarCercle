package jason.emeric.app_bar.service;


import java.util.List;

import javax.ejb.Local;

import jason.emeric.app_bar.service.model.ExtraDto;


@Local
public interface IExtraService {
	
	public void add(ExtraDto e);
	public void update(ExtraDto e);
	public void delete(long id);
	public List<ExtraDto> list();
	public ExtraDto findById(long id);

}
