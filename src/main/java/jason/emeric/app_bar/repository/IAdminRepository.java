package jason.emeric.app_bar.repository;



import java.util.List;


public interface IAdminRepository {
	
	public void add(AdminEntity a);
	public void update (AdminEntity a);
	public List<AdminEntity> list();
	public AdminEntity findById(long id);
	public void delete(long id);
	public AdminEntity findByLogin(String login);
	public void reactivate(long longValue);
}
