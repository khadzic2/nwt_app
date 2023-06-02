package ba.unsa.etf.nwt.user_service.logging;

import ba.unsa.etf.nwt.system_events_service.grpc.ActionsRequest;
import ba.unsa.etf.nwt.system_events_service.grpc.ActionsServiceGrpc;
import com.google.protobuf.Timestamp;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class GRPCService {
    private final EurekaClient eurekaClient;

    public void save(String actionType, String resourceName, String responseType, String username) {
        try {
            Instant now = Instant.now();
            System.out.println("1");
            InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("system-events-service", false);
            System.out.println("2");
            ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), 9090)
                    .usePlaintext()
                    .build();
            System.out.println("3");
            ActionsServiceGrpc.ActionsServiceBlockingStub stub = ActionsServiceGrpc.newBlockingStub(channel);
            System.out.println("4");
            ActionsRequest actionsRequest=ActionsRequest.newBuilder()
                    .setTimestamp(Timestamp.newBuilder().setSeconds(now.getEpochSecond()).setNanos(now.getNano()).build())
                    .setService("user-service")
                    .setActionType(actionType)
                    .setResourceName(resourceName)
                    .setResponseType(responseType)
                    .setUsername(username)
                    .build();
            System.out.println(actionsRequest);
            stub.save(actionsRequest);
            System.out.println("6");
            channel.shutdown();
        } catch ( Exception e ) {
            System.out.println("Can't connect to system-events-service to store action!");
            e.printStackTrace();
        }
    }
}

