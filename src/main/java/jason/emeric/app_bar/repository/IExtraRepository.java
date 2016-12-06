package jason.emeric.app_bar.repository;


import java.util.List;


public interface IExtraRepository {
	
	public void save(ExtraEntity e);
	public void delete(long id);
	public List<ExtraEntity> list();
	public ExtraEntity findById(long id);

}
