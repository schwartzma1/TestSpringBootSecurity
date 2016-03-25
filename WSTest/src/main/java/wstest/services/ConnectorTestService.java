package wstest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import repo.ConnectorTest;


/*
 * AuditChange service
 */
@Service
public class ConnectorTestService  {

	@Autowired
	protected PagingAndSortingRepository<ConnectorTest, Long> repo;
	
	public ConnectorTest create(ConnectorTest data) {
		return repo.save(data);
	}

	public ConnectorTest get(long id) {
		return repo.findOne(id);
	}

	public ConnectorTest update(ConnectorTest accessRequest) {
		return repo.save(accessRequest);
	}

	public void delete(Long id) {
		repo.delete(id);
	}

	// http://goo.gl/7fxvVf
	public Page<ConnectorTest> getAllPaged(Integer page, Integer size) {
		Page<ConnectorTest> pagedData = repo.findAll(new PageRequest(page, size));
		return pagedData;
	}

}
