package com.example.remote;

public class RemoteException extends Exception {
    public RemoteException() {
    }

    RemoteException(String message) {
        super(message);
    }

    RemoteException(Throwable cause) {
        super(cause);
    }
}
