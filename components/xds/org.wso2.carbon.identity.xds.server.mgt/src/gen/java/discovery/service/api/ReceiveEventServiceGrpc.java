package discovery.service.api;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: event.proto")
public final class ReceiveEventServiceGrpc {

  private ReceiveEventServiceGrpc() {}

  public static final String SERVICE_NAME = "discovery.service.api.ReceiveEventService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<discovery.service.api.Event.ReceivedEvent,
      discovery.service.api.Event.EventReceivedResponse> METHOD_RECEIVE_EVENT =
      io.grpc.MethodDescriptor.<discovery.service.api.Event.ReceivedEvent, discovery.service.api.Event.EventReceivedResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "discovery.service.api.ReceiveEventService", "ReceiveEvent"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              discovery.service.api.Event.ReceivedEvent.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              discovery.service.api.Event.EventReceivedResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReceiveEventServiceStub newStub(io.grpc.Channel channel) {
    return new ReceiveEventServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReceiveEventServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ReceiveEventServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReceiveEventServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ReceiveEventServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ReceiveEventServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void receiveEvent(discovery.service.api.Event.ReceivedEvent request,
        io.grpc.stub.StreamObserver<discovery.service.api.Event.EventReceivedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RECEIVE_EVENT, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_RECEIVE_EVENT,
            asyncUnaryCall(
              new MethodHandlers<
                discovery.service.api.Event.ReceivedEvent,
                discovery.service.api.Event.EventReceivedResponse>(
                  this, METHODID_RECEIVE_EVENT)))
          .build();
    }
  }

  /**
   */
  public static final class ReceiveEventServiceStub extends io.grpc.stub.AbstractStub<ReceiveEventServiceStub> {
    private ReceiveEventServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReceiveEventServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReceiveEventServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReceiveEventServiceStub(channel, callOptions);
    }

    /**
     */
    public void receiveEvent(discovery.service.api.Event.ReceivedEvent request,
        io.grpc.stub.StreamObserver<discovery.service.api.Event.EventReceivedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RECEIVE_EVENT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ReceiveEventServiceBlockingStub extends io.grpc.stub.AbstractStub<ReceiveEventServiceBlockingStub> {
    private ReceiveEventServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReceiveEventServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReceiveEventServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReceiveEventServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public discovery.service.api.Event.EventReceivedResponse receiveEvent(discovery.service.api.Event.ReceivedEvent request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RECEIVE_EVENT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ReceiveEventServiceFutureStub extends io.grpc.stub.AbstractStub<ReceiveEventServiceFutureStub> {
    private ReceiveEventServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReceiveEventServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReceiveEventServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReceiveEventServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<discovery.service.api.Event.EventReceivedResponse> receiveEvent(
        discovery.service.api.Event.ReceivedEvent request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RECEIVE_EVENT, getCallOptions()), request);
    }
  }

  private static final int METHODID_RECEIVE_EVENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ReceiveEventServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReceiveEventServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RECEIVE_EVENT:
          serviceImpl.receiveEvent((discovery.service.api.Event.ReceivedEvent) request,
              (io.grpc.stub.StreamObserver<discovery.service.api.Event.EventReceivedResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ReceiveEventServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return discovery.service.api.Event.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ReceiveEventServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReceiveEventServiceDescriptorSupplier())
              .addMethod(METHOD_RECEIVE_EVENT)
              .build();
        }
      }
    }
    return result;
  }
}
