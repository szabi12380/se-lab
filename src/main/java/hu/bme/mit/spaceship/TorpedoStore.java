
package hu.bme.mit.spaceship;

import java.util.Random;

/**
 * Class storing and managing the torpedoes of a ship.
 */
public class TorpedoStore {

  // Rate of failing to fire torpedoes [0.0, 1.0]
  private double failureRate = 0.0;

  private int torpedoCount = 0;

  // Reuse the same Random instance.
  private final Random generator = new Random();

  public TorpedoStore(int numberOfTorpedos) {
    this.torpedoCount = numberOfTorpedos;

    // Update failure rate if it was specified in an environment variable.
    String failureEnv = System.getenv("IVT_RATE");
    if (failureEnv != null) {
      try {
        failureRate = Double.parseDouble(failureEnv);
      } catch (NumberFormatException nfe) {
        failureRate = 0.0;
      }
    }
  }

  public boolean fire(int numberOfTorpedos) {
    if (numberOfTorpedos < 1 || numberOfTorpedos > this.torpedoCount) {
      throw new IllegalArgumentException("numberOfTorpedos");
    }

    double r = generator.nextDouble();

    if (r >= failureRate) {
      // Successful firing.
      this.torpedoCount -= numberOfTorpedos;
      return true;
    }

    // Simulated failure.
    return false;
  }

  public boolean isEmpty() {
    return this.torpedoCount <= 0;
  }

  public int getTorpedoCount() {
    return this.torpedoCount;
  }
}