package kz.aitu.sdp.assignment4;

import kz.aitu.sdp.assignment4.facade.HomeTheaterFacade;

public class Main {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        homeTheater.watchMovie("Dune: Part Two");
        homeTheater.endMovie();

        homeTheater.listenToMusic("Daft Punk - Random Access Memories");
        homeTheater.endMovie();
    }
}
