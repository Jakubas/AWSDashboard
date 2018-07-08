package awsdashboard.collector;

import java.util.List;
import awsdashboard.domain.EC2Info;

public interface AwsClient {

    public List<EC2Info> getInstances();
}
