package ba.unsa.etf.nwt.system_events_service.services;

import ba.unsa.etf.nwt.system_events_service.grpc.SystemEventRequest;
import ba.unsa.etf.nwt.system_events_service.grpc.SystemEventResponse;
import ba.unsa.etf.nwt.system_events_service.grpc.SystemEventServiceGrpc;
import ba.unsa.etf.nwt.system_events_service.models.SystemEvent;
import ba.unsa.etf.nwt.system_events_service.repositories.SystemEventRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
@GrpcService
@Service
public class SystemEventService extends SystemEventServiceGrpc.SystemEventServiceImplBase {



    private  SystemEventRepository systemEventRepository;

    private ApplicationContext applicationContext;
    public SystemEventService(ApplicationContext applicationContext){
        this.applicationContext=applicationContext;
        this.systemEventRepository=applicationContext.getBean(SystemEventRepository.class);
    }
    @Override
    public void log(SystemEventRequest request, StreamObserver<SystemEventResponse> responseObserver) {
        System.out.println("LOG");

        SystemEvent systemEvent = new SystemEvent();
        systemEvent.setTimestamp(request.getTimestamp());
        systemEvent.setMicroservice(request.getMicroservice());
        systemEvent.setUser(request.getUser());
        systemEvent.setAction(request.getAction());
        systemEvent.setResource(request.getResource());
        systemEvent.setResponseType(request.getResponseType());

        System.out.println(systemEvent.getTimestamp());
        System.out.println(systemEvent.getMicroservice());
        System.out.println(systemEvent.getUser());
        System.out.println(systemEvent.getAction());
        System.out.println(systemEvent.getResource());
        System.out.println(systemEvent.getResponseType());

        systemEventRepository.save(systemEvent);

        SystemEventResponse response = SystemEventResponse.newBuilder()
                .setResponsemessage("logged!")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
