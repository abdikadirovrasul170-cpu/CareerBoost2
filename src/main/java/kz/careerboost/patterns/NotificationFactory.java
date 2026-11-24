package kz.careerboost.patterns;

// ABSTRACT FACTORY: хабарлама отбасын жасайды.
public interface NotificationFactory {
    Notification createNotification();
    interface Notification { String send(String recipient, String message); }

    static NotificationFactory email() {
        return () -> (recipient, message) -> "EMAIL -> " + recipient + ": " + message;
    }
    static NotificationFactory sms() {
        return () -> (recipient, message) -> "SMS -> " + recipient + ": " + message;
    }
}
