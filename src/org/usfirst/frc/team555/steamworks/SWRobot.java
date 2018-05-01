package org.usfirst.frc.team555.steamworks;


import org.montclairrobotics.cyborg.*;
import org.montclairrobotics.cyborg.assemblies.CBDriveModule;
import org.montclairrobotics.cyborg.assemblies.CBSrxArrayController;
import org.montclairrobotics.cyborg.behaviors.*;
import org.montclairrobotics.cyborg.controllers.CBDifferentialDriveController;
import org.montclairrobotics.cyborg.data.CBLogicData;
import org.montclairrobotics.cyborg.data.CBStdDriveControlData;
import org.montclairrobotics.cyborg.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.devices.CBDashboardChooser;
import org.montclairrobotics.cyborg.devices.CBNavX;
import org.montclairrobotics.cyborg.devices.CBDeviceEnum;
import org.montclairrobotics.cyborg.mappers.CBArcadeDriveMapper;
import org.montclairrobotics.cyborg.utils.*;
import org.montclairrobotics.cyborg.utils.CBEnums.CBDriveMode;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.SPI;

/**
 * 
 * This is the main robot definition class.
 * 
 */
public class SWRobot extends Cyborg {
	
	final static int driveStickId = 1;
	final static int operStickId = 0;
	
	//
	// List Custom Hardware Devices...
	// This should include all of the active devices
	//
	//private class Devices {
	//	private CBDeviceId
			// input
			//autoSelect,autoAlliance
			//navx
			//gearAutoOpenButton,
			//gearAutoCloseButton,
			//gearManualLeftOpenButton,
			//gearManualLeftCloseButton,
			//gearManualRightOpenButton,
			//gearManualRightCloseButton,
			//climbButton,
			//forwardAxis, rotationAxis,
			//gyroLockButton //spinPov,
			//leftOpenSwitch, leftCloseSwitch,
			//rightOpenSwitch, rightCloseSwitch
			// output
			//driveMotorLeft1, driveMotorLeft2,
			//driveMotorRight1, driveMotorRight2,		
			//driveEncoderLeft, driveEncoderRight
			//climbMotorLeft,climbMotorRight,
			//gearMotorLeft, gearMotorRight
			;
	//}
	//private Devices devices = new Devices();
	
	
	
	
	
	enum Devs implements CBDeviceEnum {
		driveEncoderLefta, driveEncoderLeftb,
		driveEncoderRighta, driveEncoderRightb,
		leftOpenSwitch, leftCloseSwitch,
		rightOpenSwitch, rightCloseSwitch,
		// output
		driveMotorLeft1,driveMotorLeft2,driveMotorRight1,driveMotorRight2,
		gearMotorLeft,gearMotorRight,climbMotorLeft,climbMotorRight,
		// Input Controllers
		driveStick, operStick,
		forwardAxis, rotationAxis,
		// Input buttons
		gearAutoOpenButton,
		gearAutoCloseButton,
		gearManualLeftOpenButton,
		gearManualLeftCloseButton,
		gearManualRightOpenButton,
		gearManualRightCloseButton,
		climbButton,
		gyroLockButton, 
	}

	
	
	
	@Override
	public void cyborgInit() {

		// hardware configuration
		canBus
			.add(Devs.driveMotorRight1, 1)
			.add(Devs.driveMotorRight2, 2)
			.add(Devs.driveMotorLeft1, 3)
			.add(Devs.driveMotorLeft2, 4)
			.add(Devs.gearMotorLeft, 5)
			.add(Devs.gearMotorRight, 6)
			.add(Devs.climbMotorLeft, 7)
			.add(Devs.climbMotorRight, 8)
			;
		
		pdbSlots
			.add(Devs.driveMotorLeft1, 0)
			.add(Devs.driveMotorLeft2, 1)
			.add(Devs.driveMotorRight1, 2)
			.add(Devs.driveMotorRight2, 3)
			.add(Devs.gearMotorLeft, 4)
			.add(Devs.gearMotorRight, 5)
			.add(Devs.climbMotorLeft, 6)
			.add(Devs.climbMotorRight, 7)
			;
		
		dioBus
			.add(Devs.leftCloseSwitch, 0)
			.add(Devs.leftOpenSwitch, 1)
			.add(Devs.driveEncoderLefta, 2)
			.add(Devs.driveEncoderLeftb, 3)
			.add(Devs.driveEncoderRighta, 4)
			.add(Devs.driveEncoderRightb, 5)
			.add(Devs.rightCloseSwitch, 6)
			.add(Devs.rightOpenSwitch, 7)
			;
		
		stickList
			.add(Devs.driveStick, 1)
			.add(Devs.operStick, 0)
			;
		
		axisList
			.add(Devs.forwardAxis, Devs.driveStick, 1)
			.add(Devs.rotationAxis, Devs.driveStick, 0)
			;
		
		buttonList
			.add(Devs.gearAutoOpenButton, Devs.operStick, 1)
			.add(Devs.gearAutoCloseButton, Devs.operStick, 2)
			.add(Devs.gearManualLeftOpenButton, Devs.operStick, 3)
			.add(Devs.gearManualLeftCloseButton, Devs.operStick, 4)
			.add(Devs.gearManualRightOpenButton, Devs.operStick, 5)
			.add(Devs.gearManualRightCloseButton, Devs.operStick, 6)
			.add(Devs.climbButton, Devs.operStick, 7)
			;
				
		//
		// Data Initialization
		//
		// Initialize data stores
		// The drive and general data stores are separated 
		// because the drive stores will most likely be 
		// pre-built and the custom ones will handle 
		// robot specific data requirements. 
		// 
		requestData	= new SWRequestData();
		controlData	= new SWControlData();
		logicData 	= new CBLogicData();
		
		requestData.driveData = new CBStdDriveRequestData();
		controlData.driveData = new CBStdDriveControlData();

		//
		// Configure Hardware Adapter
		//
		//Cyborg.hardwareAdapter = 
		//		new CBHardwareAdapter(this)
		//		.setJoystickCount(2);
		//CBHardwareAdapter ha = Cyborg.hardwareAdapter;		
		
		// Robot Hardware 
		// Input devices
		//devices.navx 				= ha.add(new CBNavX(SPI.Port.kMXP));

		//devices.driveEncoderLeft 	= ha.add(
		//		new CBEncoder(2, 3, EncodingType.k4X, true, 4*76.25/5865)
		//		);
		//devices.driveEncoderRight 	= ha.add(
		//		new CBEncoder(4, 5, EncodingType.k4X, true, 4*76.25/5865)
		//		);

		
		// Driver's Station Controls	
		//devices.forwardAxis 	= ha.add(
		//		new CBAxis(driveStickId, 1)
		//		.setDeadzone(0.1)
		//		.setScale(-1.0) // stick forward => robot forward
		//		);
		//devices.rotationAxis 	= ha.add(
		//		new CBAxis(driveStickId, 0)
		//		.setDeadzone(0.1)
		//		.setScale(-1.0) // stick left => robot left/counterClockwise
		//		);
		//devices.forward2Axis 	= ha.add(
		//		new CBAxis(operStickId, 1)
		//		.setDeadzone(0.1)
		//		.setScale(-1.0) // stick forward => robot forward
		//		); // for Tank drive
		
		//devices.gearAutoOpenButton			= ha.add();
		//devices.gearAutoCloseButton			= ha.add(new CBButton(operStickId, 2));
		//devices.gearManualLeftOpenButton	= ha.add(new CBButton(operStickId, 3));
		//devices.gearManualLeftCloseButton	= ha.add(new CBButton(operStickId, 4));
		//devices.gearManualRightOpenButton	= ha.add(new CBButton(operStickId, 5));
		//devices.gearManualRightCloseButton	= ha.add(new CBButton(operStickId, 6));
		//devices.climbButton					= ha.add(new CBButton(operStickId, 7));
		
		//devices.leftCloseSwitch		= ha.add(new CBDigitalInput(0));
		//devices.leftOpenSwitch		= ha.add(new CBDigitalInput(1));
		//devices.rightCloseSwitch 	= ha.add(new CBDigitalInput(6));
		//devices.rightOpenSwitch 	= ha.add(new CBDigitalInput(7));


		//devices.spinPov 		= ha.add(new CBPov(operStickId, 0));

		//devices.autoSelect		= ha.add(
		//		new CBDashboardChooser<Integer>("Auto:")
		//		.setTiming(CBGameMode.preGame, 0)
		//		.addDefault("Select", -1)
		//		.addChoice("Center", 1)
		//		.addChoice("Left", 2)
		//		.addChoice("Right", 3)
		//		);
		//devices.autoAlliance	= ha.add(
		//		new CBDashboardChooser<Integer>("Alliance:")
		//		.setTiming(CBGameMode.preGame, 0)
		//		.addDefault("Select", -1)
		//		.addChoice("Red", 1)
		//		.addChoice("Blue", 2)
		//		);

		// Output devices
		//devices.driveMotorLeft1		= ha.add(canBuilder.build(Devs.driveMotorLeft1)); //ha.add(new CBCANTalon(3));
		//devices.driveMotorLeft2		= ha.add(canBuilder.build(Devs.driveMotorLeft2));
		//devices.driveMotorRight1	= ha.add(canBuilder.build(Devs.driveMotorRight1));
		//devices.driveMotorRight2	= ha.add(canBuilder.build(Devs.driveMotorRight2));
		//devices.gearMotorLeft		= ha.add(canBuilder.build(Devs.gearMotorLeft));
		//devices.gearMotorRight		= ha.add(canBuilder.build(Devs.gearMotorRight).setInverted(true));
		//devices.climbMotorLeft		= ha.add(canBuilder.build(Devs.climbMotorLeft));
		//devices.climbMotorRight		= ha.add(canBuilder.build(Devs.climbMotorRight).setInverted(true));

		
		// Co-processor Vision System
		//devices.visionPipeline	 = ha.add(
		//		new CBContourReport("GRIP/mynewreport")
		//		.setTiming(CBGameMode.anyPeriodic, 0)
		//		);

		
		// 
		// Create Drivetrain controller
		// This controller will also be used by the Sensor Mapper
		// 
		CBDifferentialDriveController driveTrainController = 
			new CBDifferentialDriveController(this)
				.addDriveModule(
					new CBDriveModule(new CB2DVector(-13.75,0), 0)
					.addSpeedControllerArray(
							new CBSrxArrayController()
							.setDriveMode(CBDriveMode.Speed)
							.addSpeedController(builder.CBCANTalon(Devs.driveMotorLeft1))
							.addSpeedController(builder.CBCANTalon(Devs.driveMotorLeft2))
							.setEncoder(builder.CBEncoder(Devs.driveEncoderLefta, Devs.driveEncoderLeftb, EncodingType.k4X, true, 4*76.25/5865))
							.setErrorCorrection(
									new CBPIDErrorCorrection()
									.setConstants(new double[]{0.08,0,0})
									)
							)
					)
				.addDriveModule(
					new CBDriveModule(new CB2DVector( 13.75,0), 180)
					.addSpeedControllerArray(
							new CBSrxArrayController()
							.setDriveMode(CBDriveMode.Speed)
							.addSpeedController(builder.CBCANTalon(Devs.driveMotorRight1))
							.addSpeedController(builder.CBCANTalon(Devs.driveMotorRight2))
							.setEncoder(builder.CBEncoder(Devs.driveEncoderRighta, Devs.driveEncoderRightb, EncodingType.k4X, true, 4*76.25/5865))
							.setErrorCorrection(
									new CBPIDErrorCorrection()
									.setConstants(new double[]{0.08,0,0})
									)
							)
					);

		

		//
		// Input Mapper Initialization
		//
		
		// Tank Drive Stick Input Example...
		//this.addTeleOpMapper(
		//		new CBTankDriveMapper(this, devices.forwardAxis,
		//				devices.forward2Axis)
		//		.setDeadZone(0.1)
		//		.setGyroLockButton(devices.gyroLockButton)
		//		);

		// Arcade Drive...
		// Use pre-built Cyborg plug-in to map arcade drive control  
		this.addTeleOpMapper(
				new CBArcadeDriveMapper(this)
				.setAxes(builder.CBAxis(Devs.forwardAxis).setDeadzone(0.1).setScale(-1.0), 
						null, 
						builder.CBAxis(Devs.rotationAxis).setDeadzone(0.1).setScale(-1.0))
						// No strafe axis
				.setGyroLockButton(builder.CBButton(Devs.gyroLockButton))
				.setAxisScales(0, 40, 90) // no strafe, 40 inches/second, 90 degrees/second
				);
		// Use teleOp mappers for operator mapping
		this.addTeleOpMapper(
				new SWOperatorMapper(this)
				.setClimbButton(builder.CBButton(Devs.climbButton))
				.setGearAutoCloseButton(builder.CBButton(Devs.gearAutoCloseButton))
				.setGearAutoOpenButton(builder.CBButton(Devs.gearAutoOpenButton))
				.setGearManualLeftCloseButton(builder.CBButton(Devs.gearManualLeftCloseButton))
				.setGearManualLeftOpenButton(builder.CBButton(Devs.gearManualLeftOpenButton))
				.setGearManualRightCloseButton(builder.CBButton(Devs.gearManualRightCloseButton))
				.setGearManualRightOpenButton(builder.CBButton(Devs.gearManualRightOpenButton))
				);
		
		// Use custom mappers for sensor/full-time mapping
		this.addCustomMapper(
			new SWSensorMapper(this)
				.setAutoChooser(
					new CBDashboardChooser<Integer>("Auto:")
						.setTiming(CBGameMode.preGame, 0)
						.addDefault("Select", -1)
						.addChoice("Center", 1)
						.addChoice("Left", 2)
						.addChoice("Right", 3))
				.setAllianceChooser(
					new CBDashboardChooser<Integer>("Alliance:")
						.setTiming(CBGameMode.preGame, 0)
						.addDefault("Select", -1)
						.addChoice("Red", 1)
						.addChoice("Blue", 2))
				.setGyroLockSource(new CBNavX(SPI.Port.kMXP))
				.setDrivetrain(driveTrainController)
				//.setDriveEncoders(devices.driveEncoderLeft, devices.driveEncoderRight)
				.setLimitSwitches(builder.CBDigitalInput(Devs.leftOpenSwitch), builder.CBDigitalInput(Devs.leftCloseSwitch), builder.CBDigitalInput(Devs.rightOpenSwitch), builder.CBDigitalInput(Devs.rightCloseSwitch))
				);

		
		//
		// Output Controller Initialization
		//
		this.addRobotController(driveTrainController);
				
		this.addRobotController(
				new SWManipulatorController(this)
				.setClimbMotors(
						new CBSrxArrayController()
						.setDriveMode(CBDriveMode.Power)
						.addSpeedController(builder.CBCANTalon(Devs.climbMotorLeft))
						.addSpeedController(builder.CBCANTalon(Devs.climbMotorRight).setInverted(true))
						)
				.setLeftMotor(builder.CBCANTalon(Devs.gearMotorLeft))
				.setRightMotor(builder.CBCANTalon(Devs.gearMotorRight).setInverted(true))
				);
		
		//
		// Behavior Processors
		//
		this.addBehavior(
				new CBStdDriveBehavior(this)
				.setGyroLockTracker(
						new CBPIDErrorCorrection()
						.setConstants(new double[]{0.2, 0.0, 2.0})
						.setInputLimits(-180, 180) // assumes navx source in degrees
						)
				);
		this.addBehavior(
				new SWManipulatorBehavior(this)
				);
		
		//this.addAutonomous(
		//		new SHAutonomous(this)
		//		.setFireTarget(160, 200, 10, 20)
		//		);

	}

	@Override
	public void cyborgTeleopInit() {

	}

	@Override
	public void cyborgTestInit() {
		
	}

	@Override
	public void cyborgTestPeriodic() {
		
	}
}
