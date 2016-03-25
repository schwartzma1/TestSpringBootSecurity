package repo;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component
@RepositoryRestResource(collectionResourceRel = "connector", path = "connector", exported=false )
public interface ConnectorTestRepository extends PagingAndSortingRepository<ConnectorTest, Long> {
	

}