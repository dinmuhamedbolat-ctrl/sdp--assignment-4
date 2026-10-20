package kz.aitu.sdp.assignment4.subsystem;

public class Amplifier {
    private boolean isOn;
    private int volume = 20;
    private String audioMode = "Stereo";
    private StreamingPlayer connectedPlayer;

    public void turnOn() {
        isOn = true;
        System.out.println("[Amplifier] Powering ON.");
    }

    public void turnOff() {
        isOn = false;
        connectedPlayer = null;
        System.out.println("[Amplifier] Powering OFF.");
    }

    public void linkStreamingPlayer(StreamingPlayer player) {
        if (!isOn) {
            throw new IllegalStateException("Cannot link player while Amplifier is OFF.");
        }
        if (player == null) {
            throw new IllegalArgumentException("Streaming player cannot be null.");
        }
        connectedPlayer = player;
        System.out.println("[Amplifier] Linked digital audio input to " + player.getDeviceName() + ".");
    }

    public void setSurroundSound() {
        audioMode = "Dolby Atmos 7.1 Surround";
        System.out.println("[Amplifier] Audio mode set to " + audioMode + ".");
    }

    public void setStereoSound() {
        audioMode = "2.0 High-Fidelity Stereo";
        System.out.println("[Amplifier] Audio mode set to " + audioMode + ".");
    }

    public void setVolume(int level) {
        if (level < 0 || level > 100) {
            throw new IllegalArgumentException("Volume must be between 0 and 100.");
        }
        volume = level;
        System.out.println("[Amplifier] Volume set to " + volume + ".");
    }

    public void playAudio() {
        if (!isOn || connectedPlayer == null) {
            throw new IllegalStateException("Amplifier must be ON and linked to a streaming player.");
        }
        String media = connectedPlayer.getCurrentMedia();
        if (media == null) {
            throw new IllegalStateException("Streaming player is not playing any media.");
        }
        System.out.println("[Amplifier] Playing audio for \"" + media + "\" in " + audioMode
                + " at volume " + volume + ".");
    }
}
