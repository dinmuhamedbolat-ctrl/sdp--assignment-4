package kz.aitu.sdp.assignment4.subsystem;

public class StreamingPlayer {
    private final String deviceName;
    private boolean isOn;
    private String currentMedia;

    public StreamingPlayer(String deviceName) {
        if (deviceName == null || deviceName.isBlank()) {
            throw new IllegalArgumentException("Device name cannot be empty.");
        }
        this.deviceName = deviceName;
    }

    public void turnOn() {
        isOn = true;
        System.out.println("[Player] " + deviceName + " booting up...");
    }

    public void turnOff() {
        isOn = false;
        currentMedia = null;
        System.out.println("[Player] " + deviceName + " shutting down.");
    }

    public void playMedia(String title) {
        if (!isOn) {
            throw new IllegalStateException("StreamingPlayer must be turned ON before playing media.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Media title cannot be empty.");
        }
        currentMedia = title;
        System.out.println("[Player] Streaming in 4K HDR: \"" + currentMedia + "\".");
    }

    public void stopMedia() {
        if (currentMedia != null) {
            System.out.println("[Player] Stopping playback of \"" + currentMedia + "\".");
            currentMedia = null;
        }
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getCurrentMedia() {
        return currentMedia;
    }
}
