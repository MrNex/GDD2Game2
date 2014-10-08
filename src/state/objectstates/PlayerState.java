package state.objectstates;

import mathematics.Vec;

public class PlayerState extends ObjectState {

	public PlayerState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enter() {
		attachedTo.setRunning(true);
		
	}

	@Override
	public void update() {
		attachedTo.getPos().add(new Vec(0.0001, 0.0));
		
		System.out.println(attachedTo.getPos().toString());
		
		attachedTo.updateShape();
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
