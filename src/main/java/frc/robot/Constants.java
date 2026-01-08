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
  public static final double kMaximumSpeed = 4.47333006919;
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final Double kDeadband = 0.5;
  }

  public static class UpperJointConstants {
    
    public static final int kCurrentLimit = 30;
    public static final int kMotorAID = 38;
    public static final int kMotorBID = 39;
    public static final boolean kMotorAInverted = false;
    public static final boolean kMotorBInverted = true;

    public static final int kEncoderDIOPort = 9; 
    public static final double rawAtPos245 = 0.0127 + 1.0; // raw value plus 1.0 in order to unwrap it
    public static final double rawAtPos90 = 0.588;
    public static final double rawAtNeg65 = 0.143;

  }

  public static class LowerConstants {
  
    public static final int kMotorID = 40; 
    public static final boolean kMotorInverted = false;
    public static final int kCurrentLimit = 30;
    public static final int kEncoderDIOPort = 0;
  }
  
  public static class ClawConstants{
    public static final int kMotorID = 52; 
    public static final boolean kMotorInverted = false;
    public static final int kCurrentLimit = 5;
    public static final int kEncoderDIOPort = 0;
  }
    
}
