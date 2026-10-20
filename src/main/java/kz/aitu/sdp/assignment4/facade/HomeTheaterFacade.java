package kz.aitu.sdp.assignment4.facade;

import java.util.Objects;
import kz.aitu.sdp.assignment4.subsystem.AmbientLights;
import kz.aitu.sdp.assignment4.subsystem.Amplifier;
import kz.aitu.sdp.assignment4.subsystem.MotorizedScreen;
import kz.aitu.sdp.assignment4.subsystem.Projector;
import kz.aitu.sdp.assignment4.subsystem.StreamingPlayer;

public class HomeTheaterFacade {
    private static final int MOVIE_LIGHT_LEVEL = 10;
    private static final int MUSIC_LIGHT_LEVEL = 40;
    private static final int DEFAULT_MOVIE_VOLUME = 65;
    private static final int DEFAULT_MUSIC_VOLUME = 45;

    private final Amplifier amplifier;
    private final StreamingPlayer player;
    private final Projector projector;
    private final MotorizedScreen screen;
    private final AmbientLights lights;

    public HomeTheaterFacade() {
        this(new Amplifier(), new StreamingPlayer("Apple TV 4K"), new Projector(),
                new MotorizedScreen(), new AmbientLights());
    }

    public HomeTheaterFacade(Amplifier amplifier, StreamingPlayer player,
                             Projector projector, MotorizedScreen screen, AmbientLights lights) {
        this.amplifier = Objects.requireNonNull(amplifier, "Amplifier cannot be null.");
        this.player = Objects.requireNonNull(player, "Streaming player cannot be null.");
        this.projector = Objects.requireNonNull(projector, "Projector cannot be null.");
        this.screen = Objects.requireNonNull(screen, "Screen cannot be null.");
        this.lights = Objects.requireNonNull(lights, "Lights cannot be null.");
    }

    public void watchMovie(String movieTitle) {
        validateTitle(movieTitle);
        System.out.println("\n=== Preparing Home Theater to watch \"" + movieTitle + "\" ===");
        try {
            lights.dim(MOVIE_LIGHT_LEVEL);
            screen.dropDown();

            projector.turnOn();
            projector.connectInput(player);
            projector.enableCinemaWidescreenMode(screen);

            amplifier.turnOn();
            amplifier.linkStreamingPlayer(player);
            amplifier.setSurroundSound();
            amplifier.setVolume(DEFAULT_MOVIE_VOLUME);

            player.turnOn();
            player.playMedia(movieTitle);
            amplifier.playAudio();
            projector.projectCurrentMedia();
            System.out.println("=== Movie started! Enjoy the show ===\n");
        } catch (RuntimeException e) {
            System.err.println("[Facade Error] Hardware coordination failed: " + e.getMessage());
            endSessionSafely();
        }
    }

    public void endMovie() {
        System.out.println("\n=== Shutting down Home Theater Movie Mode ===");
        endSessionSafely();
        System.out.println("=== Home Theater is now in standby ===\n");
    }

    public void listenToMusic(String albumTitle) {
        validateTitle(albumTitle);
        System.out.println("\n=== Preparing Audio Lounge for \"" + albumTitle + "\" ===");
        try {
            lights.dim(MUSIC_LIGHT_LEVEL);
            amplifier.turnOn();
            amplifier.linkStreamingPlayer(player);
            amplifier.setStereoSound();
            amplifier.setVolume(DEFAULT_MUSIC_VOLUME);

            player.turnOn();
            player.playMedia(albumTitle);
            amplifier.playAudio();
            System.out.println("=== Audio session active ===\n");
        } catch (RuntimeException e) {
            System.err.println("[Facade Error] Audio setup failed: " + e.getMessage());
            endSessionSafely();
        }
    }

    private void endSessionSafely() {
        player.stopMedia();
        player.turnOff();
        amplifier.turnOff();
        projector.turnOff();
        screen.rollUp();
        lights.turnOnFull();
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Media title must be provided to start the session.");
        }
    }
}
