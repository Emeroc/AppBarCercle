package jason.emeric.app_bar.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jason.emeric.app_bar.repository.BalanceHistory;
import jason.emeric.app_bar.repository.IUserRepository;
import jason.emeric.app_bar.repository.UserEntity;
import jason.emeric.app_bar.repository.Year;
import jason.emeric.app_bar.service.exception.UserDoesntExistException;
import jason.emeric.app_bar.service.model.BalanceHistoryDto;
import jason.emeric.app_bar.service.model.UserDto;
import jason.emeric.app_bar.service.model.YearDto;

@Stateless
public class UserService implements IUserService /*ClietnServiceLocal*/ {
	

		@Inject
		private IUserRepository UserRep;//private ClientDao clientDao;

		@EJB
		private MailService mailService;

		@Override
		public boolean add(UserDto c) {
			UserEntity e = new UserEntity();
			if (c.getLogin() != null)
				if (c.getLogin().length() > 0)
					if (UserRep.findByLogin(c.getLogin()) != null) {
						return false;
					}

			fromDto(c, e);
			e.setBalance(new BigDecimal(0));
			//e.setLogin(c.getLogin());
			e.setEnable(true);
			UserRep.save(e);
			if (c.getMail() != null)
				if (c.getMail().length() > 0)
					mailService
							.sendMail(
									c.getMail(),
									c.getName(),
									"Bar ESSTIN : Bienvenue",
									"Bonjour "
											+ c.getFirstname()
											+ ",\n\nNous avons bien tenu compte de votre inscription.\nVous pouvez consulter votre historique à l'adresse suivante (si vous avez un identifiant de l'université de Lorraine) : http://bar.esstin.univ-lorraine.fr/BarEsstinWeb/client/list \n\nL'équipe Bar",null);

			return true;
		}

		@Override
		public List<UserDto> list() {
			// bricolage pour mettre des données
			/*
			 * if (UserRep.findById(1) == null) { Client c1 = new Client();
			 * c1.setId(1); c1.setName("Emile"); Client c2 = new Client();
			 * c2.setId(2); c2.setName("Etienne"); Client c3 = new Client();
			 * c3.setId(3); c3.setName("Eric"); UserRep.save(c1);
			 * UserRep.save(c2); UserRep.save(c3); c1 = new Client();
			 * c1.setId(1); c1.setName("Damien"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Jean-Jacques"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Irène"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Patrick"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Jonas"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Zamzibar"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Marcel"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Georges"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Claudine"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Géralde"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Théau"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Joachim"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Mathieu"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Kévin"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Timéo"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Michel"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Arlette"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Andrée"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Coraline"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Laurie"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Emeric"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Benedict"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Jean-Paul"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Manuela"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Valentin"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Sidoni"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Benoit"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Roger"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Irma"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Jonhathan"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Frédéric"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Frédérique"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Nicolas"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Romain"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Benjamin"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Vincent"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Christophe"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Rémi"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Hervé"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Judithe"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Gisèle"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Prudence"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Solange"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Rolande"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Denise"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Constatain"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Rita"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Ferdinant"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Marcial"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Maxence"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Jean-Baptiste"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Romualde"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Guy"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Barnabay"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3); c1 = new Client(); c1.setId(1);
			 * c1.setName("Justin"); c2 = new Client(); c2.setId(2);
			 * c2.setName("Clotilde"); c3 = new Client(); c3.setId(3);
			 * c3.setName("Zoé"); UserRep.save(c1); UserRep.save(c2);
			 * UserRep.save(c3);
			 * 
			 * }
			 */

			List<UserDto> results = new ArrayList<UserDto>();
			List<UserEntity> entities = UserRep.list();

			for (UserEntity c : entities) {
				results.add(fromEntity(c));
			}
			return results;
		}

		@Override
		public UserDto findById(long id) {
			UserEntity existingClient = UserRep.findById(id);

			if (existingClient == null) {
				return null;
			}
			return fromEntity(existingClient);
		}

		@Override
		public UserDto findByIdOrCreateGuest(long id, String name) {
			UserDto client = findById(id);

			if (client == null) {
				client = new UserDto();
				client.setId(-1);
				client.setName(name);
			}
			return client;
		}

		@Override
		public void delete(long id) {
			UserEntity existingClient = UserRep.findById(id);

			if (existingClient == null) {
				throw new UserDoesntExistException("Client " + id
						+ " doesn't exist");
			}
			UserRep.delete(existingClient);
		}

		private UserDto fromEntity(UserEntity e) {
			UserDto dto = new UserDto();
			dto.setId(e.getId());
			dto.setName(e.getName());
			dto.setBalance(e.getBalance());
			dto.setContributor(e.isContributor());
			dto.setEnable(e.isEnable());
			dto.setFirstname(e.getFirstname());
			dto.setLogin(e.getLogin());
			dto.setMail(e.getMail());
			dto.setYearId(e.getYear().getId());
			dto.setYear(e.getYear().getYear());
			return dto;
		}

		private void fromDto(UserDto c, UserEntity e) {
			// e.setId(c.getId());
			e.setName(c.getName());
			e.setContributor(c.isContributor());
			e.setFirstname(c.getFirstname());
			e.setYear(UserRep.findYearById(c.getYearId()));
			e.setLogin(c.getLogin());
			e.setMail(c.getMail());
		}

		@Override
		public void update(UserDto c) {
			UserEntity existingClient = UserRep.findById(c.getId());

			if (existingClient == null) {
				throw new UserDoesntExistException("Client " + c.getId()
						+ " doesn't exist");
			}

			fromDto(c, existingClient);
			UserRep.save(existingClient);

		}

		@Override
		public void addBalance(long cId, BigDecimal price, boolean check,
				String admin) {
			UserEntity existingClient = UserRep.findById(cId);

			if (price.doubleValue() <= 0)
				throw new UserDoesntExistException("price " + price.intValue()
						+ " is negative");
			if (existingClient == null) {
				throw new UserDoesntExistException("Client " + cId
						+ " doesn't exist");
			}
			/*
			 * partie fixe
			 */
			BalanceHistory b = new BalanceHistory();
			b.setDate(new Date());
			b.setOperationId(0);
			b.setPrice(price);
			b.setAdmin(admin);
			if (check)
				b.setStatus(4);
			else
				b.setStatus(3);

			/*
			 * au cas où il est cotisant
			 */
			if (existingClient.isContributor() == true) {
				BalanceHistory balance = new BalanceHistory();
				balance.setDate(new Date());
				balance.setOperationId(0);
				balance.setAdmin(admin);
				balance.setStatus(2);
				if (existingClient.getBalance().compareTo(new BigDecimal(0)) > 0) {
					balance.setPrice(price.multiply(new BigDecimal(0.1)));
					UserRep.addBalance(existingClient, balance);
				} else {
					BigDecimal bi = existingClient.getBalance().add(price);
					if (bi.compareTo(new BigDecimal(0)) > 0) {
						balance.setPrice((bi.multiply(new BigDecimal(0.1))));
						UserRep.addBalance(existingClient, balance);
					}
				}
			}
			UserRep.addBalance(existingClient, b);

		}

		@Override
		public List<BalanceHistoryDto> listHistory(long clientId, int start) {
			return fromEntityList4Balance(UserRep.listHistory(clientId, start));
		}

		public BalanceHistoryDto fromEntity(BalanceHistory bh) {
			BalanceHistoryDto bhd = new BalanceHistoryDto();
			bhd.setClientId(bh.getClient().getId());
			bhd.setDate(bh.getDate());
			bhd.setId(bh.getId());
			bhd.setOperationId(bh.getOperationId());
			bhd.setPrice(bh.getPrice());
			bhd.setStatus(bh.getStatus());
			bhd.setAdmin(bh.getAdmin());
			return bhd;
		}

		public List<BalanceHistoryDto> fromEntityList4Balance(
				List<BalanceHistory> lbh) {
			List<BalanceHistoryDto> lbhd = new ArrayList<BalanceHistoryDto>();
			for (BalanceHistory bh : lbh)
				lbhd.add(fromEntity(bh));
			return lbhd;
		}

		@Override
		public UserDto findByLogin(String login)
				throws UserDoesntExistException {
			if (login.isEmpty())
				throw new UserDoesntExistException("Client " + login
						+ " doesn't exist");
			if (login.length() < 1)
				throw new UserDoesntExistException("Client " + login
						+ " doesn't exist");
			UserEntity c = UserRep.findByLogin(login);
			if (c == null)
				throw new UserDoesntExistException("Client " + login
						+ " doesn't exist");
			return fromEntity(c);
		}

		@Override
		public List<YearDto> listYear() {
			List<Year> l = UserRep.listYear();
			List<YearDto> ld = new ArrayList<YearDto>();
			for (Year y : l) {
				YearDto yd = new YearDto();
				yd.setId(y.getId());
				yd.setYear(y.getYear());
				ld.add(yd);
			}
			return ld;
		}
	}


