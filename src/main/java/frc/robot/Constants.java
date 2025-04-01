// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final Double kDeadband = 0.05;
  }

  public static final double kMaximumSpeed = Units.feetToMeters(4.5);
  public static final double test = 2.0;

  public static class MatchStrikerConstants {
    public static final boolean kMotorInverted = false;
    public static final double kCurrentLimit = 5.0; // might be too much / too little
    public static final int kmotorID = 7; // set later

    public static final double kMaxSpeed = 1.0;
    public static final double kHoldingVoltage = 0.5;
  }
}
