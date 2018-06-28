package awsdashboard.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import org.springframework.format.annotation.DateTimeFormat;
import software.amazon.awssdk.services.ec2.model.*;

@Entity
public class EC2Info {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String imageID;
    private String imageName;
    private String instanceId;
    private String instanceName;
    private InstanceType instanceType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Instant launchTime;
    private String privateIpAddress;
    private String publicDnsName;
    private String publicIpAddress;
    private Region region;
    private InstanceState state;
    private List<Tag> tags;

    public EC2Info() {}

    public EC2Info(Instance EC2Instance, Region region) {

        this.region = region;
        //EC2Instance.
    }
}
