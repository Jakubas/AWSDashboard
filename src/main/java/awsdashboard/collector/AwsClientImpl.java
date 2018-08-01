package awsdashboard.collector;

import awsdashboard.domain.EC2Info;

import java.util.List;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.ec2.EC2Client;
import software.amazon.awssdk.services.ec2.EC2ClientBuilder;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.Reservation;

@Component
public class AwsClientImpl implements AwsClient {

    EC2Client ec2Client;

    public AwsClientImpl(AwsSettings settings) {
        setClients(settings);
    }

    private void setClients(AwsSettings settings) {
        EC2ClientBuilder builder = EC2Client.builder();

        if (settings.getProfile() != null) {
            AwsCredentialsProvider profileProvider = ProfileCredentialsProvider.builder()
                    .profileName(settings.getProfile()).build();
            AwsCredentialsProvider credentialsProvider = AwsCredentialsProviderChain.builder()
                    .addCredentialsProvider(profileProvider).build();
            builder = builder.credentialsProvider(credentialsProvider);
        }
        if (settings.getRegion() != null) {
            builder = builder.region(settings.getRegion());
        }
        ec2Client = builder.build();
    }

    @Override
    public List<EC2Info> getInstances() {

        List<Reservation> reservations = ec2Client.describeInstances().reservations();

        List<EC2Info> ec2Infos;
        for (Reservation reservation: reservations) {
            List<Instance> instances = reservation.instances();
            for (Instance instance: instances) {

                //EC2Info ec2Info = new EC2Info(instance, settings.getRegion());
            }
        }
        //ec2infos.add()
        return null;
    }
}
