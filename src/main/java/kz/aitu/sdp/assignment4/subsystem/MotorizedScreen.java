package kz.aitu.sdp.assignment4.subsystem;

public class MotorizedScreen {
    private boolean isDown;

    public void dropDown() {
        if (!isDown) {
            isDown = true;
            System.out.println("[Screen] Lowering motorized projection screen.");
        }
    }

    public void rollUp() {
        if (isDown) {
            isDown = false;
            System.out.println("[Screen] Rolling up motorized projection screen.");
        }
    }

    public boolean isDown() {
        return isDown;
    }
}
