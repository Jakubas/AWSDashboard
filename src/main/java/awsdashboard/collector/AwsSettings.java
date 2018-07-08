package awsdashboard.collector;

import software.amazon.awssdk.regions.Region;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Holds AWS settings used by the collector
 */
@Component
@ConfigurationProperties(prefix = "aws")
public class AwsSettings {

    private Region region;
    private String profile;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
