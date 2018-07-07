package awsdashboard.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;
import software.amazon.awssdk.services.ec2.model.Tag;
import software.amazon.awssdk.services.ec2.model.Region;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.InstanceType;
import software.amazon.awssdk.services.ec2.model.InstanceStateName;

@Entity
//Given an EC2Instance it will extract info about the instance
public class EC2Info {

    @Id
    private String instanceId;

    //TODO: Link imageId to a given Image (Map from output of #DescribeImages)
    private String imageId;
    private Optional<String> instanceName;
    private InstanceType instanceType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Instant launchTime;

    private String privateIpAddress;
    private Optional<String> publicDnsName;
    private Optional<String> publicIpAddress;

    private Region region;

    private InstanceStateName stateName;
    private List<Tag> tags;

    public EC2Info() {}

    public EC2Info(Instance EC2Instance, Region region) {
        this.region = region;
        imageId = EC2Instance.imageId();
        instanceId = EC2Instance.instanceId();
        instanceType = EC2Instance.instanceType();
        launchTime = EC2Instance.launchTime();
        privateIpAddress = EC2Instance.privateIpAddress();
        publicDnsName = Optional.of(EC2Instance.publicDnsName());
        publicIpAddress = Optional.of(EC2Instance.publicIpAddress());
        stateName = EC2Instance.state().name();
        tags = EC2Instance.tags();
        instanceName = findInstanceNameInTags();
    }

    public Optional<String> getInstanceName() {
        return instanceName;
    }

    private Optional<String> findInstanceNameInTags() {
        Optional<Tag> nameTag = tags.stream().filter(a -> a.key().equals("Name")).findFirst();

        String instanceName = null;
        if (nameTag.isPresent()) {
            instanceName = nameTag.get().value();
        }
        return Optional.ofNullable(instanceName);
    }
}
