package awsdashboard.domain;

import org.junit.Test;
import java.time.Instant;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Optional;
import software.amazon.awssdk.services.ec2.model.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EC2InfoTest {

    @Test
    public void create_withNoNameTag() {
        EC2Info ec2Info = new EC2Info(mockInstance(), mockRegion());
        assertNotNull(ec2Info);
        assertEquals(ec2Info.getInstanceName(), Optional.empty());
    }

    @Test
    public void create_withNameTag_NameShouldBeSet() {
        Optional<String> testName = Optional.of("test-instance");
        EC2Info ec2Info = new EC2Info(mockInstance(testName), mockRegion());
        assertNotNull(ec2Info);
        assertEquals(testName, ec2Info.getInstanceName());
    }

    private Instance mockInstance() {
        return mockInstance(Optional.empty());
    }

    private Instance mockInstance(Optional<String> instanceNameTagValue) {
        Instance mockInstance = mock(Instance.class);
        when(mockInstance.imageId()).thenReturn("imageId");
        when(mockInstance.instanceType()).thenReturn(InstanceType.T2_MICRO);
        when(mockInstance.launchTime()).thenReturn(Instant.ofEpochSecond(1_000_000));
        when(mockInstance.privateIpAddress()).thenReturn("10.23.32.125");
        when(mockInstance.publicDnsName()).thenReturn("ec2.53-63-21-112.compute.aws-hzlb.amazon.com");
        when(mockInstance.publicIpAddress()).thenReturn("53.63.21.112");
        when(mockInstance.state()).thenReturn(InstanceState.builder().code(16).name("running").build());
        if (instanceNameTagValue.isPresent()) {
            Tag tag = Tag.builder().key("Name").value(instanceNameTagValue.get()).build();
            when(mockInstance.tags()).thenReturn(Arrays.asList(tag));
        } else {
            when(mockInstance.tags()).thenReturn(new ArrayList<Tag>());
        }
        return mockInstance;
    }

    private Region mockRegion() {
        Region mockRegion = mock(Region.class);
        return mockRegion;
    }
}
