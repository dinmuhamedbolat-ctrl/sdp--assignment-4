package kz.aitu.sdp.assignment4.subsystem;

public class AmbientLights {
    private int brightnessLevel = 100;

    public void dim(int level) {
        if (level < 0 || level > 100) {
            throw new IllegalArgumentException("Brightness level must be between 0 and 100.");
        }
        brightnessLevel = level;
        System.out.println("[Lights] Dimming ambient lights to " + brightnessLevel + "%.");
    }

    public void turnOnFull() {
        brightnessLevel = 100;
        System.out.println("[Lights] Restoring ambient lights to 100%.");
    }
}
