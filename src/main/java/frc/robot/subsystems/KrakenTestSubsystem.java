// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
// import com.ctre.phoenix6.configs.TalonFXConfiguration;
// import com.ctre.phoenix6.configs.TalonFXConfigurator;
// import com.ctre.phoenix6.hardware.TalonFX;

// public class KrakenTestSubsystem extends SubsystemBase {
//   /** Creates a new ExampleSubsystem. */
//   private final TalonFX myMotor;
//   public KrakenTestSubsystem() 
//   { 
//     myMotor = new TalonFX(12);
//     myMotor.getConfigurator().apply(new TalonFXConfiguration());
//     var currentConfiguration = new CurrentLimitsConfigs();
//     currentConfiguration.StatorCurrentLimit = 80;
//     currentConfiguration.StatorCurrentLimitEnable = true;

//     myMotor.getConfigurator().refresh(currentConfiguration);
//     myMotor.getConfigurator().apply(currentConfiguration);
//   }

//   public void setSpeed(double speed)
//   {
//     myMotor.set(speed);
//   }

//   public void setVoltage(double voltage)
//   {
//     myMotor.setVoltage(voltage);
//   }

//   public Command forward()
//   {
//     return runEnd(
//     () -> {
//       setSpeed(2);
//     },
//     () -> {
//       setVoltage(0);
//     });
//   }

//   /**
//    * Example command factory method.
//    *
//    * @return a command
//    */
//   public Command exampleMethodCommand() {
//     // Inline construction of command goes here.
//     // Subsystem::RunOnce implicitly requires `this` subsystem.
//     return runOnce(
//         () -> {
//           /* one-time action goes here */
//         });
//   }

//   /**
//    * An example method querying a boolean state of the subsystem (for example, a digital sensor).
//    *
//    * @return value of some boolean subsystem state, such as a digital sensor.
//    */
//   public boolean exampleCondition() {
//     // Query some boolean state, such as a digital sensor.
//     return false;
//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run

//   }

//   @Override
//   public void simulationPeriodic() {
//     // This method will be called once per scheduler run during simulation
//   }
// }
