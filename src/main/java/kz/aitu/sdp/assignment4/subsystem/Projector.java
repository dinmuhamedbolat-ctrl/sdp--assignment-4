package kz.aitu.sdp.assignment4.subsystem;

public class Projector {
    private boolean isOn;
    private StreamingPlayer videoSource;

    public void turnOn() {
        isOn = true;
        System.out.println("[Projector] Laser lamp warming up (Power ON).");
    }

    public void turnOff() {
        isOn = false;
        videoSource = null;
        System.out.println("[Projector] Cooling down and powering OFF.");
    }

    public void connectInput(StreamingPlayer player) {
        if (!isOn) {
            throw new IllegalStateException("Projector must be ON before connecting HDMI input.");
        }
        if (player == null) {
            throw new IllegalArgumentException("Streaming player cannot be null.");
        }
        videoSource = player;
        System.out.println("[Projector] HDMI input locked to " + player.getDeviceName() + ".");
    }

    public void enableCinemaWidescreenMode(MotorizedScreen screen) {
        if (!screen.isDown()) {
            throw new IllegalStateException("Cannot calibrate widescreen mode: Projection screen is not lowered!");
        }
        System.out.println("[Projector] Aspect ratio calibrated to 21:9 Widescreen on lowered screen.");
    }

    public void projectCurrentMedia() {
        if (!isOn || videoSource == null) {
            throw new IllegalStateException("Projector must be ON and connected to a streaming player.");
        }
        String media = videoSource.getCurrentMedia();
        if (media == null) {
            throw new IllegalStateException("Streaming player is not playing any media.");
        }
        System.out.println("[Projector] Projecting video for \"" + media + "\".");
    }
}
