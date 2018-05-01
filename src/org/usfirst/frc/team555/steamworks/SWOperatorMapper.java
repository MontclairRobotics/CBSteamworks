package org.usfirst.frc.team555.steamworks;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.devices.CBButton;
import org.montclairrobotics.cyborg.mappers.CBTeleOpMapper;

public class SWOperatorMapper extends CBTeleOpMapper {
	SWRequestData rd = (SWRequestData)Cyborg.requestData;
	
	CBButton gearAutoOpenButton;
	CBButton gearAutoCloseButton;
	CBButton gearManualLeftOpenButton;
	CBButton gearManualLeftCloseButton;
	CBButton gearManualRightOpenButton;
	CBButton gearManualRightCloseButton;
	CBButton climbButton;


	public SWOperatorMapper(SWRobot robot) {
		super(robot);
	}

	@Override
	public void update() {
		rd.gearAutoClose = gearAutoCloseButton.getState();
		rd.gearAutoOpen = gearAutoOpenButton.getState();
		rd.gearManualLeftClose = gearManualLeftCloseButton.getState();
		rd.gearManualLeftOpen = gearManualLeftOpenButton.getState();
		rd.gearManualRightClose = gearManualRightCloseButton.getState();
		rd.gearManualRightOpen = gearManualRightOpenButton.getState();
		rd.climb = climbButton.getState();
	}
	
	public SWOperatorMapper setGearAutoOpenButton(CBButton button) {
		gearAutoOpenButton = button;
		return this;
	}

	public SWOperatorMapper setGearAutoCloseButton(CBButton button) {
		gearAutoCloseButton = button;
		return this;
	}

	public SWOperatorMapper setGearManualLeftOpenButton(CBButton button) {
		gearManualLeftOpenButton = button;
		return this;
	}

	public SWOperatorMapper setGearManualLeftCloseButton(CBButton button) {
		gearManualLeftCloseButton = button;
		return this;
	}

	public SWOperatorMapper setGearManualRightOpenButton(CBButton button) {
		gearManualRightOpenButton = button;
		return this;
	}

	public SWOperatorMapper setGearManualRightCloseButton(CBButton button) {
		gearManualRightCloseButton = button;
		return this;
	}

	public SWOperatorMapper setClimbButton(CBButton button) {
		climbButton = button;
		return this;
	}

}


