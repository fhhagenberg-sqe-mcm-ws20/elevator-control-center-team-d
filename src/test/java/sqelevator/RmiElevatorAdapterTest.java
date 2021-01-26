package sqelevator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RmiElevatorAdapterTest {

    Registry registry;
    IElevator mockApi;
    RmiElevatorAdapter elevatorApi;

    @BeforeAll
    void before() throws AlreadyBoundException, RemoteException {
        mockApi = mock(IElevator.class, Mockito.withSettings().serializable());
        registry = LocateRegistry.createRegistry(1100);
        registry.bind("RmiConnectionTest", mockApi);

        elevatorApi = new RmiElevatorAdapter("rmi://localhost:1100/RmiConnectionTest");
    }

    @Test
    void testRmiConnectionLostException() throws RemoteException, AlreadyBoundException {
        await().atMost(300, TimeUnit.MILLISECONDS).until(() -> elevatorApi.isConnected());
        assertTrue(elevatorApi.isConnected());

        try {
            registry.unbind("RmiConnectionTest");
        } catch (NotBoundException ignored) {}

        elevatorApi.reconnect();
        await().atMost(300, TimeUnit.MILLISECONDS).until(() -> !elevatorApi.isConnected());
        assertFalse(elevatorApi.isConnected());

        registry.bind("RmiConnectionTest", mockApi);
        await().atMost(1000, TimeUnit.MILLISECONDS).until(() -> elevatorApi.isConnected());
        assertTrue(elevatorApi.isConnected());
    }

}