package com.voting_app;

abstract class Notification {
    abstract void PushNotification();
}
class Email extends Notification{

    @Override
    void PushNotification() {
        System.out.print("EMAIL: Election Poll is CLOSED\nResults will be announced later\nTHANK YOU FOR VOTING!");
    }
}
class SMS extends Notification{

    @Override
    void PushNotification() {
        System.out.print("SMS: Election Poll is CLOSED\nResults will be announced later\nTHANK YOU FOR VOTING!");
    }
}