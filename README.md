# Assignment #4 — Facade Design Pattern (ShP-2216)

## Overview

This repository contains a Java 17 implementation of the **Facade** structural design pattern for a **Home Theater System** (Option B).

The `HomeTheaterFacade` provides a simplified interface (`watchMovie`, `endMovie`, `listenToMusic`) that orchestrates five collaborating subsystem classes:

- `Amplifier`
- `StreamingPlayer`
- `Projector`
- `MotorizedScreen`
- `AmbientLights`

## Project Structure

- `kz.aitu.sdp.assignment4.subsystem` — Independent hardware controller classes with state validation.
- `kz.aitu.sdp.assignment4.facade` — `HomeTheaterFacade` coordinating subsystem initialization and safe teardown.
- `kz.aitu.sdp.assignment4.Main` — Client code that interacts exclusively with the Facade.

## How to Run

1. Open the project in **IntelliJ IDEA** (JDK 17+).
2. Run `kz.aitu.sdp.assignment4.Main`.
