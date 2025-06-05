package observer;

import observer.interfaces.Observer;

public class SendEmailNotifier implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Enviando email: " + message);
    }
}
