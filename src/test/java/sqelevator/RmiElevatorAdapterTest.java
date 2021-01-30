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
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RmiElevatorAdapterTest {

    Registry registry;
    IElevator mockApi;
    RmiElevatorAdapter elevatorApi;

    @BeforeAll
    void before() throws AlreadyBoundException, RemoteException {
        mockApi = mock(IElevator.class, Mockito.withSettings().serializable());
        when(mockApi.getCommittedDirection(0)).thenReturn(1);
        when(mockApi.getElevatorAccel(0)).thenReturn(100);
        when(mockApi.getElevatorButton(0, 0)).thenReturn(Boolean.TRUE);
        when(mockApi.getElevatorDoorStatus(0)).thenReturn(1);
        when(mockApi.getElevatorFloor(0)).thenReturn(1);
        when(mockApi.getElevatorNum()).thenReturn(10);
        when(mockApi.getElevatorPosition(0)).thenReturn(50);
        when(mockApi.getElevatorSpeed(0)).thenReturn(20);
        when(mockApi.getElevatorWeight(0)).thenReturn(250);
        when(mockApi.getElevatorCapacity(0)).thenReturn(10);
        when(mockApi.getFloorButtonDown(0)).thenReturn(Boolean.FALSE);
        when(mockApi.getFloorButtonUp(0)).thenReturn(Boolean.TRUE);
        when(mockApi.getFloorHeight()).thenReturn(5);
        when(mockApi.getFloorNum()).thenReturn(8);
        when(mockApi.getTarget(0)).thenReturn(2);
        when(mockApi.getClockTick()).thenReturn(12345L);
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

    @Test
    void t_rmiGetterConnection() throws RemoteException {
        await().atMost(300, TimeUnit.MILLISECONDS).until(() -> elevatorApi.isConnected());

        assertTrue(elevatorApi.isConnected());
        assertEquals(1,elevatorApi.getCommittedDirection(0));
        assertEquals(100,elevatorApi.getElevatorAccel(0));
        assertEquals(Boolean.TRUE, elevatorApi.getElevatorButton(0,0));
        assertEquals(1,elevatorApi.getElevatorDoorStatus(0));
        assertEquals(1,elevatorApi.getElevatorFloor(0));
        assertEquals(10,elevatorApi.getElevatorNum());
        assertEquals(50,elevatorApi.getElevatorPosition(0));
        assertEquals(20,elevatorApi.getElevatorSpeed(0));
        assertEquals(250,elevatorApi.getElevatorWeight(0));
        assertEquals(10,elevatorApi.getElevatorCapacity(0));
        assertEquals(Boolean.FALSE, elevatorApi.getFloorButtonDown(0));
        assertEquals(Boolean.TRUE, elevatorApi.getFloorButtonUp(0));
        assertEquals(5, elevatorApi.getFloorHeight());
        assertEquals(8, elevatorApi.getFloorNum());
        assertEquals(2, elevatorApi.getTarget(0));
        assertEquals(12345L, elevatorApi.getClockTick());
    }

    @Test
    void t_rmiGetterConnectionFailed() throws RemoteException, AlreadyBoundException {
        RmiElevatorAdapter elevatorApi;
        IElevator mockApi_f = mock(IElevator.class, Mockito.withSettings().serializable());

        when(mockApi_f.getCommittedDirection(0)).thenThrow(new RemoteException());
        when(mockApi_f.getElevatorAccel(0)).thenThrow(new RemoteException());
        when(mockApi_f.getElevatorButton(0, 0)).thenThrow(new RemoteException());
        when(mockApi_f.getElevatorDoorStatus(0)).thenThrow(new RemoteException());
        when(mockApi_f.getElevatorFloor(0)).thenThrow(new RemoteException());
        when(mockApi_f.getElevatorNum()).thenThrow(new RemoteException());
        when(mockApi_f.getElevatorPosition(0)).thenThrow(new RemoteException());
        when(mockApi_f.getElevatorSpeed(0)).thenThrow(new RemoteException());
        when(mockApi_f.getElevatorWeight(0)).thenThrow(new RemoteException());
        when(mockApi_f.getElevatorCapacity(0)).thenThrow(new RemoteException());
        when(mockApi_f.getFloorButtonDown(0)).thenThrow(new RemoteException());
        when(mockApi_f.getFloorButtonUp(0)).thenThrow(new RemoteException());
        when(mockApi_f.getFloorHeight()).thenThrow(new RemoteException());
        when(mockApi_f.getFloorNum()).thenThrow(new RemoteException());
        when(mockApi_f.getTarget(0)).thenThrow(new RemoteException());
        when(mockApi_f.getClockTick()).thenThrow(new RemoteException());

        Registry registry_f = LocateRegistry.createRegistry(1101);
        registry_f.bind("RmiConnectionTest", mockApi_f);
        elevatorApi = new RmiElevatorAdapter("rmi://localhost:1101/RmiConnectionTest");

        assertTrue(elevatorApi.isConnected());
        assertEquals(0,elevatorApi.getCommittedDirection(0));
        assertEquals(0,elevatorApi.getElevatorAccel(0));
        assertEquals(Boolean.FALSE, elevatorApi.getElevatorButton(0,0));
        assertEquals(0,elevatorApi.getElevatorDoorStatus(0));
        assertEquals(0,elevatorApi.getElevatorFloor(0));
        assertEquals(0,elevatorApi.getElevatorNum());
        assertEquals(0,elevatorApi.getElevatorPosition(0));
        assertEquals(0,elevatorApi.getElevatorSpeed(0));
        assertEquals(0,elevatorApi.getElevatorWeight(0));
        assertEquals(0,elevatorApi.getElevatorCapacity(0));
        assertEquals(Boolean.FALSE, elevatorApi.getFloorButtonDown(0));
        assertEquals(Boolean.FALSE, elevatorApi.getFloorButtonUp(0));
        assertEquals(0, elevatorApi.getFloorHeight());
        assertEquals(0, elevatorApi.getFloorNum());
        assertEquals(0, elevatorApi.getTarget(0));
        assertEquals(0L, elevatorApi.getClockTick());
    }
}