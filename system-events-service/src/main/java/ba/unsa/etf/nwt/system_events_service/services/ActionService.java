package ba.unsa.etf.nwt.system_events_service.services;

import ba.unsa.etf.nwt.system_events_service.models.Action;
import ba.unsa.etf.nwt.system_events_service.grpc.ActionsRequest;
import ba.unsa.etf.nwt.system_events_service.grpc.ActionsResponse;
import ba.unsa.etf.nwt.system_events_service.grpc.ActionsServiceGrpc;
import ba.unsa.etf.nwt.system_events_service.repositories.ActionRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;


@GrpcService
public class ActionService extends ActionsServiceGrpc.ActionsServiceImplBase {


    private ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public void save(ActionsRequest request, StreamObserver<ActionsResponse> responseObserver){
        Action action = new Action();
        action.setTimestamp(Instant.ofEpochSecond(request.getTimestamp().getSeconds(), request.getTimestamp().getNanos()));
        action.setService(request.getService());
        action.setActionType(request.getActionType());
        action.setResourceName(request.getResourceName());
        action.setResponseType(request.getResponseType());
        action.setUsername(request.getUsername());
        actionRepository.save(action);

        ActionsResponse response = ActionsResponse.newBuilder()
                .setStatus("OK")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
