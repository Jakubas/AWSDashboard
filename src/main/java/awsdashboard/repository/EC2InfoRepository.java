package awsdashboard.repository;

import awsdashboard.domain.EC2Info;
import org.springframework.data.repository.CrudRepository;

public interface EC2InfoRepository extends CrudRepository<EC2Info, String> {

}
